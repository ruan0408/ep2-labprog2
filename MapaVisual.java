import java.awt.*;
import java.awt.event.*;
import java.awt.TexturePaint;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;
import java.awt.geom.Ellipse2D; // Circulo do robo

class Celula
{ 
		Polygon p = new Polygon();
		BufferedImage ime;
		Graphics2D Gime;
		int x,y;
		int r;

		Celula(int x, int y, int r , BufferedImage t) 
		{
			ime = t;
			this.r = r;
			this.x = x;
			this.y = y;

			for (int i = 0; i < 6; i++)
				p.addPoint(x + (int) (r * Math.cos(i * 2 * Math.PI / 6)),
						   y + (int) (r * Math.sin(i * 2 * Math.PI / 6)));
			
			//Gime = ime.createGraphics();
		}

		void draw(Graphics g) 
		{ 
			Graphics2D g2d = (Graphics2D) g;
			Rectangle rec = new Rectangle(0,0,100,100);
			g2d.setPaint(new TexturePaint(ime, rec));
			//g2d.setColor(Color.blue);
			g2d.fill(p);
		//		try{			Thread.sleep(50);}catch(Exception e){System.out.println("HUE");} 
		}

		public int x()
		{
			return this.x;
		}

		public int y()
		{
			return this.y;
		}

		public int raio()
		{
			return this.r;
		}	

		void trans(int dx, int dy) 
		{
			p.translate(dx, dy);
		}
}	

class Campo extends JPanel
{
	Celula[][] cel;
	Mapa mapa;
	BufferedImage robo = null;

	public Campo(Mapa mapa, int L)
	{
		this.mapa = mapa;
		this.criaCampo(L);

	}

	private void criaCampo(int L)
	{
		Celula celTemp;
	
		double DELTA = 0.0;
		double Dx = 3.0*L/2.0;
		double Dy = (L*Math.sqrt(3));
		BufferedImage lama = null;
		BufferedImage grama = null;
		BufferedImage agua = null;
		BufferedImage depSemCristal = null;
		BufferedImage depComCristal = null;

		try 
		{
			lama = ImageIO.read(this.getClass().getResource("lama.jpg"));
			grama = ImageIO.read(this.getClass().getResource("grama.png"));
			agua = ImageIO.read(this.getClass().getResource("agua.jpg"));
			robo = ImageIO.read(this.getClass().getResource("robo.png"));
			depSemCristal = ImageIO.read(this.getClass().getResource("depositovazio.png"));
			depComCristal = ImageIO.read(this.getClass().getResource("depositoocupado.png"));
		}
		catch (Exception e) {System.exit(1);}

		cel = new Celula[mapa.largura()][mapa.altura()];

		for(int i = 0; i < mapa.largura(); i++)
		{
			for(int j = 0; j < mapa.altura(); j++)
			{
				DELTA = i%2 == 1? Dy/2.0 : 0;
				if(mapa.getTerreno(j, i).eRugoso())
					cel[j][i] = new Celula((int)(L + i*Dx),(int) (DELTA + L + j*Dy), L, lama);
				else if(mapa.getTerreno(j, i).eLiso())
					cel[j][i] = new Celula((int)(L + i*Dx),(int) (DELTA + L + j*Dy), L, grama);
				else if(mapa.getTerreno(j, i).eAgua())
					cel[j][i] = new Celula((int)(L + i*Dx),(int) (DELTA + L + j*Dy), L, agua);
				else if(mapa.getTerreno(j, i).eDeposito() && mapa.getTerreno(j, i).toDeposito().temCristal())
					cel[j][i] = new Celula((int)(L + i*Dx),(int) (DELTA + L + j*Dy), L, depComCristal);
				else if(mapa.getTerreno(j, i).eDeposito() && !mapa.getTerreno(j, i).toDeposito().temCristal())
					cel[j][i] = new Celula((int)(L + i*Dx),(int) (DELTA + L + j*Dy), L, depSemCristal);

			}
		}
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for (int i = 0; i < cel.length; i++) 
			for (int j = 0; j < cel[0].length; j++)
			{

				cel[i][j].draw(g2d); // pinta as células no contexto gráfico
				if(mapa.getTerreno(i,j).temRobo()) desenhaRobo(i, j , g2d);
			}
	}

	private void desenhaRobo(int i, int j, Graphics2D g2d)
	{
		Celula cel = this.cel[i][j];
		int x = cel.x();
		int y = cel.y();
		int raio = cel.raio();

		Rectangle rec = new Rectangle(x-15,y-20,30,40);
		g2d.setPaint(new TexturePaint(robo, rec));
		g2d.fill(rec);

		//g2d.drawRect(x-20, y-51, 800, 500);
		



		//Graphics2D g2d = (Graphics2D)g;
		// Assume x, y, and diameter are instance variables.
	/*	Ellipse2D.Double circle = new Ellipse2D.Double(x-raio/2, y-raio/2, raio, raio);
		g2d.setPaint(Color.blue);
		g2d.fill(circle);*/
/*
		g2d.setColor(Color.BLACK);
        g2d.fillOval(x, y, 10, 10);*/
	}
}


class Tela extends JFrame
{
	public Tela(Campo campo, int H, int W) 
	{
		setTitle("Polygon");
		setSize(H, W);
		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{
				System.exit(0);
			}
		});
		add(campo);
		setVisible(true);
	}
}

public class MapaVisual
{
	Mapa mapa;
	Celula[][] cel;
	int W,H, L;
	Tela tela;

	public MapaVisual(Mapa mapa, int W, int H, int L) {
		this.mapa = mapa;
		this.W = W;
		this.H = H;
		this.L = L;

	}

	public void atualiza()
	{
		tela.repaint();
	}


	
	public void abreJanela() 
	{
		Campo campo = new Campo(mapa,L);
		this.tela = new Tela(campo, H, W);
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                Tela telah = tela;
                telah.setVisible(true);
            }
        });

    }



}
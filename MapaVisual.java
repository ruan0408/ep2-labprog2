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
		double r;

		Celula(int x, int y, double r , BufferedImage t) 
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
			int height = ime.getHeight();
			int width = ime.getWidth();
			Double scale = height > width? 2.0*r/height : 2.0*r/width;
			Graphics2D g2d = (Graphics2D) g;

			Rectangle rec = new Rectangle(0,0,(int)(height*scale),(int)(width*scale));
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

		public double raio()
		{
			return this.r;
		}	

		public void setIme(BufferedImage img)
		{
			this.ime = img;
		}

		public void trans(int dx, int dy) 
		{
			p.translate(dx, dy);
		}
}	

class Campo extends JPanel
{
	Celula[][] cel;
	Mapa mapa;
	BufferedImage base = null;
	BufferedImage robo = null;
	BufferedImage depSemCristal = null;

	public Campo(Mapa mapa, int L)
	{
		this.mapa = mapa;
		this.criaCampo(L);

	}

	private void criaCampo(int L)
	{
		double DELTA = 0.0;
		double Dx = 2*L*Math.sqrt(3)/2;
		double Dy = 2*L;
		double raio = (L/2.0 + L*Math.sqrt(3)/2.0-7);// o 7 é optimal
		BufferedImage lama, grama, agua, depComCristal, fundo; 
		lama = grama = agua = depComCristal = fundo = null;
		
		try 
		{
			lama = ImageIO.read(this.getClass().getResource("lama.jpg"));
			grama = ImageIO.read(this.getClass().getResource("grama.png"));
			agua = ImageIO.read(this.getClass().getResource("agua.jpg"));
			robo = ImageIO.read(this.getClass().getResource("robo.png"));
			base = ImageIO.read(this.getClass().getResource("base.png"));
			depSemCristal = ImageIO.read(this.getClass().getResource("depositovazio.png"));
			depComCristal = ImageIO.read(this.getClass().getResource("depositoocupado.png"));
		}
		catch (Exception e) 
		{	
			System.out.println("Faltam arqivos de imagem!");
			System.exit(1);
		}

		cel = new Celula[mapa.altura()][mapa.largura()];

		for(int j = 0; j < mapa.largura(); j++)
		{	
			DELTA = j%2 == 1? L : 0;
			for(int i = 0; i < mapa.altura(); i++)
			{
				if(mapa.getTerreno(i, j).eRugoso())    fundo = lama;
				else if(mapa.getTerreno(i, j).eLiso()) fundo = grama;
				else if(mapa.getTerreno(i, j).eAgua()) fundo = agua;
				else if(mapa.getTerreno(i, j).eDeposito() && mapa.getTerreno(i, j).toDeposito().temCristal())
					fundo = depComCristal;
				cel[i][j] = new Celula((int)(L/2.0 + j*Dx),(int)(L/2.0 + i*Dy + DELTA ), raio, fundo);
			}
		}
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		try {
        	  BufferedImage background = ImageIO.read(this.getClass().getResource("background.jpg"));
 		  g2d.drawImage(background, null, 0, 0);		
 
       		} catch (Exception e) {
       		} 

		for (int i = 0; i < cel.length; i++) 
			for (int j = 0; j < cel[0].length; j++)
			{

				//cel[i][j].draw(g2d); // pinta as células no contexto gráfico
				if(mapa.getTerreno(i,j).eDeposito() && !mapa.getTerreno(i,j).toDeposito().temCristal())
				{
					cel[i][j].setIme(depSemCristal);
				}
				cel[i][j].draw(g2d); // pinta as células no contexto gráfico 
				if(mapa.getTerreno(i,j).temRobo()) desenhaRobo(i, j, g2d);
				if(mapa.getTerreno(i,j).eBase()) desenhaBase(i, j, g2d);
			}
	}

	/*private void desenhaDepositoVazio(int i, int j,Graphics2D g2d)
	{
		Celula cel = this.cel[i][j];
		int x = cel.x();
		int y = cel.y();
		int raio = cel.raio();

		Rectangle rec = new Rectangle(0,0,100,100);
		g2d.setPaint(new TexturePaint(depSemCristal, rec));
		g2d.fill(rec);
	}*/

	private void desenhaRobo(int i, int j, Graphics2D g2d)
	{
		Celula cel = this.cel[i][j];
		int x = cel.x();
		int y = cel.y();
		double raio = cel.raio();

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

	private void desenhaBase(int i, int j, Graphics2D g2d)
	/* Cópia do desenhaRobo. */
	{
		Celula cel = this.cel[i][j];
		int x = cel.x();
		int y = cel.y();

		Rectangle rec = new Rectangle(x-15,y-20,30,40);
		g2d.setPaint(new TexturePaint(base, rec));
		g2d.fill(rec);
	}
}


class Tela extends JFrame
{
	public Tela(Campo campo, int H, int W) 
	{
		setTitle("Caça aos cristais!");
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
	int W, H, L;
	Tela tela;

	public MapaVisual(Mapa mapa, int W, int H, int L) 
	{
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

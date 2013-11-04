import java.awt.*;
import java.awt.event.*;
import java.awt.TexturePaint;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;

class Celula
    { 
		Polygon p = new Polygon();
		BufferedImage ime;
		Graphics2D Gime;

		Celula(int x, int y, int r /*, BufferedImage t*/) {
			//ime = t;

			for (int i = 0; i < 6; i++)
				p.addPoint(x + (int) (r * Math.cos(i * 2 * Math.PI / 6)),
						   y + (int) (r * Math.sin(i * 2 * Math.PI / 6)));
			
			//Gime = ime.createGraphics();
		}

		void draw(Graphics g) { 
			Graphics2D g2d = (Graphics2D) g;
			Rectangle r = new Rectangle(0,0,100,100);
			//g2d.setPaint(new TexturePaint(ime, r));
			g2d.setColor(Color.blue);
			g2d.fill(p);
	//		try{			Thread.sleep(50);}catch(Exception e){System.out.println("HUE");} 
		}	

		void trans(int dx, int dy) {
			p.translate(dx, dy);
	}
}	

class Campo extends JPanel
{
	Celula[][] cel;

	public Campo(Celula[][] cel)
	{
		this.cel = cel;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for (int i = 0; i < 10; i++) 
			for (int j = 0; j < 10; j++)
				cel[i][j].draw(g); // pinta as células no contexto gráfico
	}
}


class Tela extends Frame
{
	public Tela(Campo campo) {
		setTitle("Polygon");
		setSize(600, 600);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
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

	public MapaVisual(Mapa mapa, int W, int H, int L) {
		this.mapa = mapa;
		/*
		setTitle("Polygon");
		setSize(W, H);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		add(new Campo(cel));
		setVisible(true);*/
	}


	public void criaVisual(int L)
	{
		Celula celTemp;
	
		double DELTA = 0.0;
		double Dx = 3.0*L/2.0;
		double Dy = (L*Math.sqrt(3) +0.11);

		cel = new Celula[mapa.largura()][mapa.altura()];


		for(int i = 0; i < mapa.largura(); i++)
		{
			for(int j = 0; j < mapa.altura(); j++)
			{
				DELTA = i%2 == 1? Dy/2.0 : 0;
				cel[j][i] = new Celula((int)(L + i*Dx),(int) (DELTA + L + j*Dy), L);

				

			}
		}

	}

	public void abreJanela() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Tela tela = new Tela(new Campo(cel));
                tela.setVisible(true);
            }
        });
    }



}
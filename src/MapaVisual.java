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
	BufferedImage baseV = null;
	BufferedImage baseA = null;
	BufferedImage roboV = null;
	BufferedImage roboA = null;
	BufferedImage depSemCristal = null;
	BufferedImage depComCristal = null;
	BufferedImage background = null;
	BufferedImage cristal = null;


	public Campo(Mapa mapa, int L)
	{
		this.mapa = mapa;
		this.criaCampo(L);
		//this.setSize(800, 800);
	}

	private void criaCampo(int L)
	{
		double DELTA = 0.0;
		double Dx = 3*L/2.0;
		double Dy = (L*Math.sqrt(3)-1);// o -1 é optimal
		double metadeAlt = L*Math.sqrt(3)/2;
		double metadeLarg = L;
		double raio = L;
		BufferedImage lama, grama, agua, fundo, deposito; 
		lama = grama = agua = fundo = deposito = null;
		
		try 
		{
			//deposito = ImageIO.read(this.getClass().getResource("deposito.png"));
			//cristal = ImageIO.read(this.getClass().getResource("cristal.png"));
			lama = ImageIO.read(this.getClass().getResource("/img/lama.jpg"));
			grama = ImageIO.read(this.getClass().getResource("/img/grama.png"));
			agua = ImageIO.read(this.getClass().getResource("/img/agua.jpg"));
			roboV = ImageIO.read(this.getClass().getResource("/img/roboV.png"));
			roboA = ImageIO.read(this.getClass().getResource("/img/roboA.png"));
			baseV = ImageIO.read(this.getClass().getResource("/img/baseV.png"));
			baseA = ImageIO.read(this.getClass().getResource("/img/baseA.png"));
			background = ImageIO.read(this.getClass().getResource("/img/background.jpg"));
			depSemCristal = ImageIO.read(this.getClass().getResource("/img/newDepVoid.png"));
			depComCristal = ImageIO.read(this.getClass().getResource("/img/newDep.png"));
		}
		catch (Exception e) 
		{	
			System.out.println("Faltam arqivos de imagem!");
			System.exit(1);
		}

		cel = new Celula[mapa.altura()][mapa.largura()];

		for(int j = 0; j < mapa.largura(); j++)
		{	
			DELTA = j%2 == 1? metadeAlt : 0;
			for(int i = 0; i < mapa.altura(); i++)
			{
				if(mapa.getTerreno(i, j).eRugoso())    fundo = lama;
				else if(mapa.getTerreno(i, j).eLiso()) fundo = grama;
				else if(mapa.getTerreno(i, j).eAgua()) fundo = agua;
				else if(mapa.getTerreno(i, j).eDeposito() && mapa.getTerreno(i, j).toDeposito().temCristal())  fundo = depComCristal;
				//else if(mapa.getTerreno(i, j).eBase()) fundo = base;
				else fundo = grama; //Default em caso de erro
				//else if(mapa.getTerreno(i, j).eDeposito())
					
					//fundo = deposito;
				cel[i][j] = new Celula((int)(metadeLarg + j*Dx),(int)(metadeAlt + i*Dy + DELTA ), raio, fundo);
			}
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		try 
		{
			Rectangle rec = new Rectangle(0,0,background.getWidth(),background.getHeight());
			g2d.setPaint(new TexturePaint(background, rec));
			Rectangle rec2 = new Rectangle(0,0,10000,10000);
			g2d.fill(rec2);
        	  
 		  //g2d.drawImage(background, null, 0, 0);		
 
       	} 
       	catch (Exception e) { System.out.println("");}
       		 
		for (int i = 0; i < cel.length; i++) 
			for (int j = 0; j < cel[0].length; j++)
			{

				//cel[i][j].draw(g2d); // pinta as células no contexto gráfico
				//if(mapa.getTerreno(i,j).eDeposito())
				if(mapa.getTerreno(i,j).eDeposito()) //&& mapa.getTerreno(i,j).toDeposito().temCristal())
				{
					if(mapa.getTerreno(i,j).toDeposito().temCristal() ) cel[i][j].setIme(depComCristal);
					//cel[i][j].setIme(deposito);
					//desenhaCristal(i, j, g2d);

					else cel[i][j].setIme(depSemCristal);
				}
				
				cel[i][j].draw(g2d); // pinta as células no contexto gráfico
				if(mapa.getTerreno(i,j).eBase()) 
					{
						switch(mapa.getTerreno(i,j).toBase().getTime().getId())
						{
							case 1 :desenhaElemento(baseV, i, j, g2d);break;
				 			case 2 :desenhaElemento(baseA, i, j, g2d);break;
				 			default: System.out.println("Ainda não suportamos mais times");
						}	
					}
				if(mapa.getTerreno(i,j).temRobo())
				{
					switch(mapa.getTerreno(i,j).getRobo().getTime().getId())
					{
				 		case 1 :desenhaElemento(roboV, i, j, g2d);break;
				 		case 2 :desenhaElemento(roboA, i, j, g2d);break;
				 		default: System.out.println("Ainda não suportamos mais times");
				 	}
				}
			}
	}

	private void desenhaElemento(BufferedImage elem, int i, int j, Graphics2D g2d)
	{
		Celula cel = this.cel[i][j];
		int x = cel.x();
		int y = cel.y();
		double raio = cel.raio();

		Rectangle rec = new Rectangle(x-15,y-20,30,40);
		g2d.setPaint(new TexturePaint(elem, rec));
		g2d.fill(rec);
	}
	
}


class Tela extends JFrame
{
	public Tela(Campo campo, int H, int W) 
	{
		//JScrollPane scroller = new JScrollPane(campo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//add(scroller);

		setTitle("Caça aos cristais!");
		setSize(W, H);
		
		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{
				System.exit(0);
			}
		});
		this.setLocationRelativeTo(null);
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

	public MapaVisual(Mapa mapa, int H, int W, int L) 
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

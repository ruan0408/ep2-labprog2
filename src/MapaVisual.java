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
			
		}

		void draw(Graphics g) 
		{ 
			int height = ime.getHeight();
			int width = ime.getWidth();
			Double scale = height > width? 2.0*r/height : 2.0*r/width;
			Graphics2D g2d = (Graphics2D) g;

			Rectangle rec = new Rectangle(0,0,(int)(height*scale),(int)(width*scale));
			g2d.setPaint(new TexturePaint(ime, rec));
			g2d.fill(p);
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

}	

class Campo extends JPanel
{
	Celula[][] cel;
	Mapa mapa;
	boolean gameOver = false;
	BufferedImage baseV = null;
	BufferedImage baseA = null;
	BufferedImage roboVfrente = null;
	BufferedImage roboVcostas = null;
	BufferedImage roboVdireita = null;
	BufferedImage roboVesquerda = null;
	BufferedImage roboAfrente = null;
	BufferedImage roboAcostas = null;
	BufferedImage roboAdireita = null;
	BufferedImage roboAesquerda = null;
	BufferedImage roboVhit = null;
	BufferedImage roboAhit = null;
	BufferedImage depSemCristal = null;
	BufferedImage depComCristal = null;
	BufferedImage background = null;
	BufferedImage cristal = null;
	BufferedImage bomba = null;
	BufferedImage explosion = null;
	BufferedImage game_over = null;


	public Campo(Mapa mapa, int L)
	{
		this.mapa = mapa;
		this.criaCampo(L);
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
			lama = ImageIO.read(this.getClass().getResource("/img/lama.jpg"));
			grama = ImageIO.read(this.getClass().getResource("/img/grama.png"));
			agua = ImageIO.read(this.getClass().getResource("/img/agua.jpg"));
			roboVfrente = ImageIO.read(this.getClass().getResource("/img/roboVfrente.png"));
			roboVcostas = ImageIO.read(this.getClass().getResource("/img/roboVcostas.png"));
			roboVdireita = ImageIO.read(this.getClass().getResource("/img/roboVdireita.png"));
			roboVesquerda = ImageIO.read(this.getClass().getResource("/img/roboVesquerda.png"));
			roboAfrente = ImageIO.read(this.getClass().getResource("/img/roboAfrente.png"));
			roboAcostas = ImageIO.read(this.getClass().getResource("/img/roboAcostas.png"));
			roboAdireita = ImageIO.read(this.getClass().getResource("/img/roboAdireita.png"));
			roboAesquerda = ImageIO.read(this.getClass().getResource("/img/roboAesquerda.png"));
			roboVhit = ImageIO.read(this.getClass().getResource("/img/roboVhit.png"));
			roboAhit = ImageIO.read(this.getClass().getResource("/img/roboAhit.png"));
			baseV = ImageIO.read(this.getClass().getResource("/img/baseV.png"));
			baseA = ImageIO.read(this.getClass().getResource("/img/baseA.png"));
			background = ImageIO.read(this.getClass().getResource("/img/background.jpg"));
			depSemCristal = ImageIO.read(this.getClass().getResource("/img/newDepVoid.png"));
			depComCristal = ImageIO.read(this.getClass().getResource("/img/newDep.png"));
			bomba = ImageIO.read(this.getClass().getResource("/img/bomba.png"));
			explosion = ImageIO.read(this.getClass().getResource("/img/explosion.png"));
			game_over = ImageIO.read(this.getClass().getResource("/img/game_over.png"));
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
				else fundo = grama; //Default em caso de erro
									
				cel[i][j] = new Celula((int)(metadeLarg + j*Dx),(int)(metadeAlt + i*Dy + DELTA ), raio, fundo);
			}
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		BufferedImage robo_draw = null;
		
		try 
		{
			Rectangle rec = new Rectangle(0,0,background.getWidth(),background.getHeight());
			g2d.setPaint(new TexturePaint(background, rec));
			Rectangle rec2 = new Rectangle(0,0,10000,10000);
			g2d.fill(rec2);
        		   
       	} 
       	catch (Exception e) { System.out.println("");}
       		 
		for (int i = 0; i < cel.length; i++) 
			for (int j = 0; j < cel[0].length; j++)
			{
				if(mapa.getTerreno(i,j).eDeposito()) //&& mapa.getTerreno(i,j).toDeposito().temCristal())
				{
					if(mapa.getTerreno(i,j).toDeposito().temCristal() ) cel[i][j].setIme(depComCristal);
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
				if(mapa.getTerreno(i,j).bombExploded())
				{
					desenhaElemento(explosion, i, j, g2d);
					danoRobosRedor(i,j);
					mapa.getTerreno(i,j).setNoBomb();
				}
				if(mapa.getTerreno(i,j).hasBomb())
				{
					desenhaElemento(bomba, i, j, g2d);
					mapa.getTerreno(i,j).decTimerBomb();
				}
				if(mapa.getTerreno(i,j).temRobo())
				{
					switch(mapa.getTerreno(i,j).getRobo().getTime().getId())
					{
				 		case 1 :
				 			if(mapa.getTerreno(i,j).getRobo().gotHit()) 
				 				robo_draw = roboVhit;
				 			else
				 			{
				 				switch(mapa.getTerreno(i,j).getRobo().getSide())
				 				{
				 					case UP:robo_draw = roboVcostas; break;
				 					case DW:robo_draw = roboVfrente; break;
				 					case UR:
				 					case DR:robo_draw = roboVdireita; break;
				 					case UL:
				 					case DL:robo_draw = roboVesquerda; break;
				 					default:robo_draw = roboVfrente;
				 				}
				 			}
				 			break;
				 		case 2 :
				 			if(mapa.getTerreno(i,j).getRobo().gotHit())
				 				robo_draw = roboAhit;
				 			else
				 			{
				 				switch(mapa.getTerreno(i,j).getRobo().getSide())
				 				{
				 					case UP:robo_draw = roboAcostas; break;
				 					case DW:robo_draw = roboAfrente; break;
				 					case UR:
				 					case DR:robo_draw = roboAdireita; break;
				 					case UL:
				 					case DL:robo_draw = roboAesquerda; break;
				 					default:robo_draw = roboAfrente;
				 				}
				 			}
				 			break;
				 		default: System.out.println("Ainda não suportamos mais times");
				 	}
				 	desenhaElemento(robo_draw, i, j, g2d);
				}
			}

		if (this.gameOver) 
		{
			game_over = getScaledImage(game_over, MapaVisual.W, MapaVisual.H);
			Rectangle rec = new Rectangle(0,0, game_over.getWidth(), game_over.getHeight());
			g2d.setPaint(new TexturePaint(game_over, rec));
			Rectangle rec2 = new Rectangle(0,0, game_over.getWidth(), game_over.getHeight());
			g2d.fill(rec2);
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

	private BufferedImage getScaledImage(Image srcImg, int w, int h)
	{
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}

	private void danoRobosRedor(int x, int y)
	{
		Robo robo;
		try
		{
			if(mapa.getUp(x, y).temRobo())
			{
				robo = mapa.getUp(x, y).getRobo();
				//robo.perdeVida(40);
				robo.gotHit(true);
			}
			if(mapa.getUpRight(x, y).temRobo())
			{
				robo = mapa.getUpRight(x, y).getRobo();
				//robo.perdeVida(40);
				robo.gotHit(true);
			}
			if(mapa.getDownRight(x, y).temRobo())
			{
				robo = mapa.getDownRight(x, y).getRobo();
				//robo.perdeVida(40);
				robo.gotHit(true);
			}
			if(mapa.getDown(x, y).temRobo())
			{
				robo = mapa.getDown(x, y).getRobo();
				//robo.perdeVida(40);
				robo.gotHit(true);
			}
			if(mapa.getDownLeft(x, y).temRobo())
			{
				robo = mapa.getDownLeft(x, y).getRobo();
				//robo.perdeVida(40);
				robo.gotHit(true);
			}
			if(mapa.getUpLeft(x, y).temRobo())
			{
				robo = mapa.getUp(x, y).getRobo();
				//robo.perdeVida(40);
				robo.gotHit(true);
			}
		}
		catch(Exception e){System.out.println("Bomba fora do mapa?");}
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
				
		//setVisible(true);
	}
}

public class MapaVisual
{
	Mapa mapa;
	Celula[][] cel;
	static int W, H, L;
	Tela tela;
	Campo campo;

	public MapaVisual(Mapa mapa, int H, int W, int L)
	{
		this.mapa = mapa;
		this.W = W;
		this.H = H;
		this.L = L;
		this.campo = null;
		this.tela = null;
	}

	public void atualiza()
	{
		tela.repaint();
	}
	
	public void abreJanela() 
	{
		this.campo = new Campo(mapa,L);
		this.tela = new Tela(campo, H, W);
		campo.setPreferredSize(new Dimension( W, H));
		JScrollPane scrollFrame = new JScrollPane(campo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tela.add(scrollFrame);

        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                tela.setVisible(true);
            }
        });

    }

    public void gameOver()
    {
   		campo.gameOver = true;
		try 
		{
   			Thread.sleep(5000);
		} 
		catch(InterruptedException ex) {Thread.currentThread().interrupt();}
		tela.setVisible(false);
		System.exit(0);    	
    }
}

import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String args[])
	{
		String path = null;
		try 
		{
			path = new java.io.File(".").getCanonicalPath();
		}
		catch(Exception e)
		{
			System.out.println("Problema ao achar path");
			System.out.println(e.getMessage());
			System.exit(-1);	
		}

		
		Mapa mapa = new Mapa(path+"/data/map/mapa3.txt");   
  		Arena arena = new Arena(mapa);
		ArrayList<Programa> programas = new ArrayList<Programa>();
		Programa[] progAux = new Programa[0];
		int i = 0;
		Parser parser = new Parser(System.in);
		try
		{
			for(i =0;args.length > 0 && i<args.length;i++)
			{
				
				programas.add(parser.traduzRobo(args[i]));
			}
		}
		catch(Exception e)
		{
			System.out.println("Problema ao tentar traduzir o arquivo "+args[i]);
			System.out.println(e.getMessage());
			System.exit(-1);
		}	

		progAux = programas.toArray(progAux);

		System.out.println("Quantidade de robos: "+progAux.length);
		arena.insereExercito(progAux, 1);
		//arena.insereExercito(progAux,2);
				
		int larguraJanela, alturaJanela;
		if(mapa.largura()%2 == 1) larguraJanela = ((mapa.largura()-1)/2)*90 + 60;
		else larguraJanela = mapa.largura()*90/2 + 15;
		alturaJanela = (int)((mapa.altura()+1)*Math.sqrt(3)*30);

		MapaVisual mv = new MapaVisual(mapa, alturaJanela, larguraJanela, 30);

		mv.abreJanela();



		while(arena.atualiza())
		{
			mv.atualiza();

			try 
			{
   				 Thread.sleep(100);
			} 
			catch(InterruptedException ex) {Thread.currentThread().interrupt();}
		}

			mv.gameOver();
	}
}


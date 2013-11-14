public class Main{
	public static void main(String args[]){
		Programa[] programas = new Programa[0];
		Mapa mapa = new Mapa("mapa.txt");
	    Arena arena = new Arena(mapa);
	    Programa programa;



		MapaVisual mv = new MapaVisual(mapa,(int)(mapa.altura()*Math.sqrt(3)*30 + Math.sqrt(3)*15), 90*mapa.largura()/2 + 30, 30);

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
		
	}
}


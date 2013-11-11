public class Main{
	public static void main(String args[]){
		Programa[] programas = new Programa[1];
		Mapa mapa = new Mapa("mapa.txt");
	    Arena arena = new Arena(mapa);
	    Programa programa;


programa = new Programa();
programa.add( new Comando("MYTIME", null));
programa.add( new Comando("PRN", null));
programa.add( new Comando("END", null));
programas[0] = programa;

		MapaVisual mv = new MapaVisual(mapa, 800, 800,30);

		mv.abreJanela();


		arena.insereExercito(programas, 1);

		while(arena.atualiza())
		{
			mv.atualiza();

			try {
   				 Thread.sleep(100);
			} catch(InterruptedException ex) {
   				 Thread.currentThread().interrupt();
			}
		}
		
	}
}


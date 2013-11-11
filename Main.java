public class Main{
	public static void main(String args[]){

		Programa[] programas = new Programa[4];
 
		Mapa mapa = new Mapa("mapa.txt");
	    Arena arena = new Arena(mapa);
	    Programa programa;


programas = new Programa[1];
programa = new Programa();
programa.add( new Comando("PUSH", new Numero(1) ));
programa.add( new Comando("WALK", null));
programa.add( new Comando("PUSH", new Numero(2) ));
programa.add( new Comando("WALK", null));
programa.add( new Comando("PUSH", new Numero(3) ));
programa.add( new Comando("WALK", null));
programa.add( new Comando("PUSH", new Numero(4) ));
programa.add( new Comando("WALK", null));
programa.add( new Comando("PUSH", new Numero(5) ));
programa.add( new Comando("WALK", null));
programa.add( new Comando("PUSH", new Numero(6) ));
programa.add( new Comando("WALK", null));
programa.add( new Comando("JMP", new Numero( 0 )));
programas[0] = programa;
arena.insereExercito(programas, 1);
programas = new Programa[1];
programa = new Programa();
programa.add( new Comando("MYTIME", null));
programa.add( new Comando("PRN", null));
programa.add( new Comando("END", null));
programas[0] = programa;
arena.insereExercito(programas, 2);

		MapaVisual mv = new MapaVisual(mapa, 800, 800,30);

		mv.abreJanela();


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


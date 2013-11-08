public class Main{
	public static void main(String args[]){
		Programa[] programas = new Programa[1];
		Mapa mapa = new Mapa("mapa.txt");
	    Arena arena = new Arena(mapa);
	    Programa programa;


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

		MapaVisual mv = new MapaVisual(mapa, 800, 800,30);

		mv.abreJanela();


		arena.insereExercito(programas, 1);

		while(arena.atualiza())
		{
			mv.atualiza();

			try {
   				 Thread.sleep(1000);
			} catch(InterruptedException ex) {
   				 Thread.currentThread().interrupt();
			}
		}
		
	}
}


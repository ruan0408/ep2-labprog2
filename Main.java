public class Main{
	public static void main(String args[]){
		Programa[] programas = new Programa[1];
		Mapa mapa = new Mapa("mapa2.txt");
	    Arena arena = new Arena(mapa);
	    Programa programa;


programa = new Programa();
programa.add( new Comando("PUSH", new Numero(1) ));
programa.add( new Comando("STO", new Numero(0) ));
programa.add( new Comando("RCL", new Numero(0) ));
programa.add( new Comando("LOOK", null));
programa.add( new Comando("PUSH", new Numero(2) ));
programa.add( new Comando("ISA", null));
programa.add( new Comando("JIT", new Numero( 18 )));
programa.add( new Comando("RCL", new Numero(0) ));
programa.add( new Comando("PUSH", new Numero(1) ));
programa.add( new Comando("ADD", null));
programa.add( new Comando("DUP", null));
programa.add( new Comando("PUSH", new Numero(7) ));
programa.add( new Comando("EQ", null));
programa.add( new Comando("JIF", new Numero( 16 )));
programa.add( new Comando("POP", null));
programa.add( new Comando("PUSH", new Numero(1) ));
programa.add( new Comando("STO", new Numero(0) ));
programa.add( new Comando("JMP", new Numero( 2 )));
programa.add( new Comando("RCL", new Numero(0) ));
programa.add( new Comando("WALK", null));
programa.add( new Comando("JMP", new Numero( 2 )));
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


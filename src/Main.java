public class Main{
	public static void main(String args[]){
		Programa[] programas = new Programa[2];
		Mapa mapa = new Mapa("/nfs/home/bcc/ruan/Documents/labprog2/ep2-labprog2/bin/../data/map/mapa.txt");
	    Arena arena = new Arena(mapa);
	    Programa programa;


programas = new Programa[1];
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
programa.add( new Comando("PUSH", new Frase("HUE1") ));
programa.add( new Comando("PRN", null));
programa.add( new Comando("RCL", new Numero(0) ));
programa.add( new Comando("WALK", null));
programa.add( new Comando("PUSH", new Frase("HUE2") ));
programa.add( new Comando("PRN", null));
programa.add( new Comando("JMP", new Numero( 2 )));
programas[0] = programa;
arena.insereExercito(programas, 1);

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


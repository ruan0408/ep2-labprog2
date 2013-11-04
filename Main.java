public class Main{
	public static void main(String args[]){
		Programa[] programas = new Programa[1];
		Mapa mapa = new Mapa();
	    Arena arena = new Arena(mapa);
	    Posicao[] posicoes = new Posicao[1];
	    Programa programa;


programa = new Programa();
programa.add( new Comando("WALK", new Numero(1) ));
programa.add( new Comando("WALK", new Numero(2) ));
programa.add( new Comando("WALK", new Numero(3) ));
programa.add( new Comando("WALK", new Numero(4) ));
programa.add( new Comando("WALK", new Numero(5) ));
programa.add( new Comando("WALK", new Numero(6) ));
programa.add( new Comando("JMP", new Numero( 0 )));
posicoes[0] = new Posicao(0,0);
programas[0] = programa;


	MapaVisual mv = new MapaVisual(mapa, 800, 800,30);
	mv.criaVisual(30);
	mv.abreJanela();


		arena.insereExercito(programas, 1);

		//while(arena.atualiza());
		
	}
}


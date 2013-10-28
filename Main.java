public class Main{
	public static void main(String args[]){
		Programa[] programas = new Programa[1];
		Mapa mapa = new Mapa(1,1);
	    Arena arena = new Arena(mapa);
	    Posicao[] posicoes = new Posicao[1];
	    Programa programa;


programa = new Programa();
programa.add( new Comando("PUSH", new Numero(10) ));
programa.add( new Comando("PUSH", new Numero(4) ));
programa.add( new Comando("ADD", null));
programa.add( new Comando("PUSH", new Numero(3) ));
programa.add( new Comando("MUL", null));
programa.add( new Comando("PRN", null));
programa.add( new Comando("END", null));
posicoes[0] = new Posicao(0,0);
programas[0] = programa;


		arena.insereExercito(programas, posicoes, 1);

		while(arena.atualiza());
		
	}
}


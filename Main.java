public class Main{
	public static void main(String args[]){
		Programa[] programas = new Programa[0];
		Mapa mapa = new Mapa(0,0);
	    Arena arena = new Arena(mapa);
	    Posicao[] posicoes = new Posicao[0];
	    Programa programa;




		arena.insereExercito(programas, posicoes, 1);

		while(arena.atualiza());
		
	}
}


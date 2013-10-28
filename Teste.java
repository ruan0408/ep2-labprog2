import java.util.*;


public class Teste
{
  public static void main(String args[])
  {
    Programa[] programas = new Programa[3]; 
    Mapa mapa = new Mapa(5,5);
    Arena arena = new Arena(mapa);
    Robo robo1, robo2;
    Posicao[] posicoes = new Posicao[3];


   Programa programa = new Programa();
    programas[0] = programa;

    posicoes[0] = new Posicao(1,1);

    programa.add( new Comando("PUSH", new Numero(0) ));
    programa.add( new Comando("STO", new Numero(0) ));
    programa.add( new Comando("PUSH", new Numero(1) ));
    programa.add( new Comando("STO", new Numero(1) ));
    programa.add( new Comando("RCL", new Numero(0) ));
    programa.add( new Comando("PUSH", new Numero(1) ));
    programa.add( new Comando("ADD", null));
    programa.add( new Comando("DUP", null));
    programa.add( new Comando("STO", new Numero(0) ));
    programa.add( new Comando("RCL", new Numero(1) ));
    programa.add( new Comando("MUL", null));
    programa.add( new Comando("STO", new Numero(1) ));
    programa.add( new Comando("RCL", new Numero(0) ));
    programa.add( new Comando("DUP", null));
    programa.add( new Comando("STO", new Numero(0) ));
    programa.add( new Comando("PUSH", new Numero(6) ));
    programa.add( new Comando("EQ", null));
    programa.add( new Comando("JIF", new Numero( 4 )));
    programa.add( new Comando("RCL", new Numero(1) ));
    programa.add( new Comando("PRN", null));
    programa.add( new Comando("END", null));

    posicoes[1] = new Posicao(2,2);

    programa = new Programa();
    programas[1] = programa;
    programa.add( new Comando("PUSH", new Numero(10) ));
    programa.add( new Comando("PUSH", new Numero(4) ));
    programa.add( new Comando("ADD", null));
    programa.add( new Comando("PUSH", new Numero(3) ));
    programa.add( new Comando("MUL", null));
    programa.add( new Comando("PRN", null));
    programa.add( new Comando("END", null));

     posicoes[2] = new Posicao(3,2);

    programa = new Programa();
    programas[2] = programa;
    programa.add( new Comando("PUSH", new Numero(10) ));
    programa.add( new Comando("STO", new Numero(4) ));

   // robo2.carregaPrograma(programa);

  arena.insereExercito(programas, posicoes, 1);





   //for(int i = 0; i < 100; i++) arena.atualiza();
  while(arena.atualiza());



  }


}

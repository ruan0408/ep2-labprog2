import java.util.*;
public class Teste
{
  public static void main(String args[])
  {
    List<Comando> programa = new ArrayList<Comando>();
    Mapa mapa = new Mapa(5,5);
    Arena arena = new Arena(mapa);
    Robo robo1, robo2;

    robo1 = new Robo(arena,2,2,1);

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


    robo1.carregaPrograma(programa);


    robo2 = new Robo(arena, 1,1, 2);


    programa = new ArrayList<Comando>();
    programa.add( new Comando("PUSH", new Numero(10) ));
    programa.add( new Comando("PUSH", new Numero(4) ));
    programa.add( new Comando("ADD", null));
    programa.add( new Comando("PUSH", new Numero(3) ));
    programa.add( new Comando("MUL", null));
    programa.add( new Comando("PRN", null));
    programa.add( new Comando("END", null));

    robo2.carregaPrograma(programa);

    arena.insereExercito(robo1);
    arena.insereExercito(robo2);




   for(int i = 0; i < 100; i++) arena.atualiza();



  }


}

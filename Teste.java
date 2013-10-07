import java.util.*;
public class Teste
{
  public static void main(String args[])
  {
    List<Comando> programa = new ArrayList<Comando>();
    Mapa mapa = new Mapa(5,5);
    Arena arena = new Arena(mapa);
    Maquina vm = new Maquina(arena);

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


    vm.carregaPrograma(programa);

    while(vm.temProx()){ vm.executaProx(); }



  }


}

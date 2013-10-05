import java.util.*;

public class Maquina
{
  private Pilha dados;
  private List<Empilhavel> mem;
  private List<Comando> prog;
  private Arena arena;
  
  Maquina(Arena arena)
  {
    this.arena = arena;
    mem = new ArrayList<Empilhavel>();
    prog = new ArrayList<Comando>();
    dados = new Pilha();
  }

  /*private void pushDados(Empilhavel emp)
  {
    this.pilha.push(emp);
  }
  private Empilhavel popDados()
  {
   return this.pilha.pop(); 
  }*/
  //private Empilhavel peekDados
  //private void setMem
  //private Empilhavel getMem
  //

  private void executaCmd(Comando cmd)
  {
    String code = cmd.getCode();
    Numero valor = cmd.getValor();

    if(code == "PUSH")
    {
      this.dados.push(valor);
    }
  }
}

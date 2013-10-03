import java.util.*;

public class Maquina
{
  private Pilha pilha;
  private List<Empilhavel> mem;
  private List<Comando> prog;
  private Arena arena;
  
  Maquina(Arena arena)
  {
    this.arena = arena;
    mem = new List<Empilhavel>();
    prog = new List<Comando>();
    pilha = new Pilha();
  }

  public void executa(){}//talvez int
}

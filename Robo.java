import java.util.*;

public class Robo implements Programavel
{
  private Maquina vm;
  private int vida;
  private int x, y;
  private int time;
  private Cristal cristal;

  Robo(Arena arena,int x, int y, int time)
  {
    this.vm = new Maquina(arena,this);
    this.vida = 100; //Total de vida
    this.x = x;
    this.y = y;
    this.time = time;
    cristal = null;
  }

  public void carregaPrograma(List<Comando> prog)
  {
    this.vm.carregaPrograma(prog);
  }

  public void getCristal(Cristal cr)
  {
    this.cristal = cr;
  }

  public boolean temCristal()
  {
    if(this.cristal == null) return false;
    return true;
  }

  public int getX()
  {
    return this.x;
  }

  public int getY()
  {
    return this.y;
  }

  public void setVida(int vida)
  {
    this.vida = vida;
  }
  
  public int getVida()
  {
    return this.vida;
  }

  public void executaAcao()
  {
    this.vm.executaProx();
  }
}

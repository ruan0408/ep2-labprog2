import java.util.*;

public class Robo implements Programavel
{
  private Maquina vm;
  private int vida;
  private int x, y;
  private int time;
  private int atraso;
  private Cristal cristal;

  Robo(Arena arena,int x, int y, int time)
  {
    this.vm = new Maquina(arena,this);
    this.vida = 100; //Total de vida
    this.x = x;
    this.y = y;
    this.atraso = 0; 
    this.time = time;
    cristal = null;
  }

  public void carregaPrograma(List<Comando> prog)
  {
    this.vm.carregaPrograma(prog);
  }

  public void coletaCristal(Cristal cr)
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
  
  public int getVida()
  {
    return this.vida;
  }

  public void setAtraso(int atraso)
  {
    this.atraso = atraso;
  }
  public Cristal dropCristal()
  {
    Cristal cris = this.cristal;
    this.cristal = null;
    return cris;
  }

  public void setVida(int vida)
  {
    this.vida = vida;
  }
  public void executaAcao()
  {
    this.vm.executaProx();
  }
}

public class Robo implements Programavel
{
  private Maquina vm;
  private int vida;
  private int x, y;
  private int time;

  Robo(Maquina vm, int x, int y, int time)
  {
    this.vm = vm;
    this.vida = 100; //Total de vida
    this.x = x;
    this.y = y;
    this.time = time;
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

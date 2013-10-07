public class Robo
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

  private void setVida(int vida)
  {
    this.vida = vida;
  }
  
  private int getVida()
  {
    return this.vida;
  }

  private void executaAcao()
  {
    this.vm.executaProx();
  }
}

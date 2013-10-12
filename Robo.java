import java.util.*;

public class Robo implements Programavel
{
  private Maquina vm;
  private int vida;
  private int x, y;
  private int time;
  private Atraso atraso;
  private Cristal cristal;


  /****** Construtor ******/


  Robo(Arena arena,int x, int y, int time)
  {
    this.vm = new Maquina(arena,this);
    this.vida = 100; //Total de vida
    this.x = x;
    this.y = y;
    this.atraso = null; 
    this.time = time;
    cristal = null;
  }


  /****** Getters ******/


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

  public Atraso getAtraso()
  {
    return this.atraso;
  }

  public Maquina getVm()
  {
    return this.vm;
  }

  
  /****** Setters ******/


  public void setAtraso(Atraso atraso)
  {
    this.atraso = atraso;
  }

  public void setVida(int vida)
  {
    this.vida = vida;
  }


  /****** Funções ******/
  /* Recebe uma lista de comandos (programa) e o insere na máquina virtual do robô. */

  public void perdeVida(int dano)
  {
    this.vida -= dano;
  }

  public boolean temVida()
  {
    return this.vida > 0;
  }

  public void carregaPrograma(List<Comando> prog)
  {
    this.vm.carregaPrograma(prog);
  }

  /* Recebe um cristal e o insere no robô. */
  public void coletaCristal(Cristal cr)
  {
    this.cristal = cr;
  }

  /* Verifica se o robô está ou não carregando um cristal, retornando TRUE se está e FALSE caso contrário. */
  public boolean temCristal()
  {
    if(this.cristal == null) return false;
    return true;
  }

  /* Atribui ao atraso valor "null" (sinal que não há atraso). */
  public void tiraAtraso()
  {
    this.setAtraso(null);
  }

  /* Retorna 1 se atraso for diferente de null, 0 caso contrário. */
  public boolean temAtraso()
  {
    return this.atraso != null;
  }

  /* Remove o cristal do robô, o qual é retornado pela função. */
  public Cristal dropCristal()
  {
    Cristal cris = this.cristal;
    this.cristal = null;
    return cris;
  }
  
  /* Se não houver atraso, então a função executa o próximo comando do programa do robô. */
  public void executaAcao()
  {
    if(!this.temAtraso()) 
    {
      this.vm.executaProx();
    }
      
  }


}

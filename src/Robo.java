import java.util.*;

public class Robo implements Programavel, Empilhavel
{
  private static int cont = 0;

  private int ind;
  private Maquina vm;
  private int vida;
  private Posicao pos;
  private Time time;
  private Atraso atraso;
  private Cristal cristal;
  private boolean gotHit;
  private Direcao side;
  private int energia;

  /****** Construtor ******/


  Robo(Arena arena,Posicao pos, Time time)
  {
    this.vm = new Maquina(arena,this);
    this.vida = 100; //Total de vida
    this.energia = 100;
    this.pos = pos;
    this.atraso = null; 
    this.time = time;
    this.ind = cont++;
    this.gotHit = false;
    this.side = Direcao.DW;
    cristal = null;
  }


  /****** Getters ******/

  public int getInd()
  {
    return this.ind;
  }

  public int getX()
  {
    return this.pos.getX();
  }

  public int getY()
  {
    return this.pos.getY();
  }

  public Posicao getPos()
  {
    return this.pos;
  }
  
  public int getVida()
  {
    return this.vida;
  }

  public Time getTime()
  {
    return this.time;
  }

  public Atraso getAtraso()
  {
    return this.atraso;
  }

  public boolean gotHit()
  {
    return this.gotHit;
  }

  Direcao getSide()
  {
    return this.side;
  }


  /****** Setters ******/

  void setSide(Direcao dir)
  {
    this.side = dir;
  }

  void gotHit(boolean h)
  {
    this.gotHit = h;
  }

  public void setPos(Posicao pos)
  {
    this.pos = pos;
  }


  public void setAtraso(Atraso atraso)
  {
    this.atraso = atraso;
  }

  public void setVida(int vida)
  {
    this.vida = vida;
  }


  /****** Funções ******/

  public boolean semEnergia()
  {
    return this.energia == 0;
  }

  public boolean fullEnergia()
  {
    return this.energia == 100;
  }
  
  public void perdeEnergia(int power)
  {
    if(this.energia <= power) this.energia = 0;
    else this.energia -= power;
  }

  public void ganhaEnergia()
  {
    if(this.energia >= 90) this.energia = 100;
    else this.energia += 10;
  }
  public boolean canPutBomb()
  {
    return this.energia >= 50;
  }
  public void perdeVida(int dano)
  {
    this.vida -= dano;
  }

  public boolean temVida()
  {
    return this.vida > 0;
  }

  /* Recebe uma lista de comandos (programa) e o insere na máquina virtual do robô. */
  public void carregaPrograma(Programa prog)
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

  public void push(Empilhavel resp)
  {
    this.vm.pushDados(resp);
  }

  public Empilhavel pop()
  {
    return this.vm.popDados();
  }
  
  /* Se não houver atraso, então a função executa o próximo comando do programa do robô. */
  public boolean executaAcao()
  {
    if(!this.temAtraso()) 
    {
      return this.vm.executaProx();
    }
    return true;
  }


  public boolean temAcao()
  {
    return this.vm.temProx();
  }


}

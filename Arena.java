import java.util.*;
import java.lang.Enum;

public class Arena
{
  private static final int TAM_EX = 10;
  private static final int UP = 1;
  private static final int UR = 2;
  private static final int DR = 3;
  private static final int DW = 4;
  private static final int DL = 5;
  private static final int UL = 6;

  private Mapa mapa;
  private List<Robo> robos;
  private int tempo;


  /****** Construtor ******/


  Arena(Mapa mapa)
  {
    this.mapa = mapa;
    tempo = 0;
    this.robos = new ArrayList<Robo>();//cria lista de robos vazia a principio
  }

  /*public Empilhavel sistema(Acao act, Robo bot)
  {
    
  }
*/

  /****** Funções ******/

  /* Função que percorrerá a lista de robôs presentes na arena (em ordem aleatória). 
     Para cada robô encontrado, chamará uma função que executará o próximo comando   
     presente no programa do robô, dado o devido respeito às condições de atraso.    */
     public void atualiza()
     {
      Robo roboTemp;
      Iterator<Robo> it;
      /* Aleatoriza o vetor de robôs. */
      Collections.shuffle(robos);

      /* Percorre a lista de robôs, executando os comandos de cada um (de acordo com o estado de seu atraso). */
      for(it = robos.iterator(); it.hasNext();)
      {
        roboTemp = it.next();
        if(!roboTemp.temAtraso())
          roboTemp.executaAcao();
        else
        {
          Atraso at = roboTemp.getAtraso();
          if(at.getTempo() <= 0)
          {
           this.sistema( at.getOperacao());
           roboTemp.tiraAtraso();
         }
         else at.somaTempo(-1);

       }
     }
   /* Incrementa a unidade que trata do tempo transcorrido desde o início do programa.                
   Ou seja, cada ciclo da função Atualiza representa uma unidade de tempo transcorrida no programa.*/
   this.tempo++;
 }


 /* AJUDEM O TIO PECÊ NESSA DAQUI AHHHHHHH *********************************************
  ********************************************************************************
 *************************************************** . */
 public void sistema(Operacao op)
 {
  
  Comando cmd = op.getCmd();
  Robo robo = (Robo) op.getOrigem();
  /* O comando que entra é do tipo bolado, existem alguns comandos bolados.  
  Reconheceremos eles aqui  daremos a resposta dizendo se executamos ou   
  não(de acordo com o estado do sistema)                                  */
  int x = robo.getX();
  int y = robo.getY();
  Terreno terrAtual = mapa.getTerreno(x, y);
  Terreno terrTemp = null;
  Empilhavel resp = new Numero(0);// a principio empilharemos falso, pode mudar nos if's abaixo

  int valor = (int)(((Numero)cmd.getValor()).getVal());

  try
  {
    switch(valor)
    {
      case UP : terrTemp = mapa.getUp(x, y); break;
      case UR : terrTemp = mapa.getUpRight(x, y); break;
      case DR : terrTemp = mapa.getDownRight(x, y); break;
      case DW : terrTemp = mapa.getDown(x, y); break;
      case DL : terrTemp = mapa.getDownLeft(x, y); break;
      case UL : terrTemp = mapa.getUpLeft(x, y); break;
      default : System.out.println("Direçao inválida!");
    }
  }
  catch(ArrayIndexOutOfBoundsException e)//acho que deveriamos fazer isso nas funções do mapa, mas foda-se
  {
    System.out.println("Tentando acessar posição fora do mapa!");
  }

  /* If's que verificarão o comando que foi passado como operação e
  o executarão de acordo com a funcionalidade de cada um.         */
  if(cmd.codeEquals("WALK") && !terrTemp.temRobo())
  { 
    if(terrAtual instanceof Rugoso) 
    {
      robo.setAtraso(new Atraso(op,3));
    }
    else
    {
      terrAtual.removeRobo();
      terrTemp.putRobo(robo);
      resp = new Numero(1);
    }
  }
  else if(cmd.codeEquals("COLLECT") && !robo.temCristal() 
    && terrTemp instanceof Deposito && ((Deposito)terrTemp).temCristal() ) 
  {
    Deposito dep = (Deposito) terrTemp;
    robo.coletaCristal(dep.popCristal());
    resp = new Numero(1);
  } 
    else if(cmd.codeEquals("DROP") && terrTemp instanceof Base && robo.temCristal())//COISAS A SEREM REVISTAS AQUI ***************************************************
    {
      //if(terrTemp instanceof Base)
      ((Base)terrTemp).putCristal(robo.dropCristal());
      /*else
      {
        Cristal cris = robo.dropCristal();
        Terreno depTemp = mapa.getTerreno(cris.getX(), cris.getY());
        ((Deposito)depTemp).putCristal(cris);
      }*/
      resp = new Numero(1);
    }
    else if(cmd.codeEquals("ATK") && terrTemp.temRobo())
    {
      Robo inim = terrTemp.getRobo();
      inim.perdeVida(10);
      /*if(!inim.temVida())
      {

      }*/
      resp = new Numero(1);
    }
    robo.getVm().getDados().push(resp);
  }


  /* Cria um robo na posição x,y : 
       -Retorna true caso tenha conseguido inserir,
       -Retorna false c.c.                            
  */
  public boolean insereExercito(Robo rb)
  {
    /* Tenta colocar o robo no mapa. Caso já 
    exista um robo, a função irá retornar 0*/
    if(mapa.putRobo(rb))
    {
      this.robos.add(rb);
      return true;
    }
    return false;
  }


  //função que insere exercito forRealz
  /**private boolean insereExercito(int time)
  {
    Random rand = new Random(tempo);
    Robo novoRobo;
    Terreno terr;
    int x, y;
    
    for(int i = 0; i < TAM_EX; i++)
    {
      do
      {
        x = rand.nextInt(mapa.largura);
        y = rand.nextInt(mapa.altura);
        terr = this.mapa.getTerreno(x, y);
      }
      while(terr.temRobo());
      
      novoRobo = new Robo(this,x, y, time);
      terr.putRobo(novoRobo);
      this.robos.add(novoRobo);
    }
    return true;
  }

  /* Função que removerá um exército(robô) na posição (x,y) passada como parâmetro. */
  public void removeExercito(int x, int y)
  {
    Robo roboTemp;

    roboTemp = mapa.getTerreno(x,y).getRobo();
    mapa.getTerreno(x,y).removeRobo();
    /* Verifica se existe ou não um robô na posição (x,y). */
    if(roboTemp != null)
    {
      Iterator<Robo> it = robos.iterator();
      /* Percorre a lista de robôs até encontrar o robô que desejamos remover.
      Este é, então, removido da lista de robôs.                            */
      while (it.hasNext()) 
      {
        Robo rb = it.next();
        if (rb == roboTemp) 
        {
          it.remove();
          break;
        }
      }
    }
  }
}



import java.util.*;
import java.lang.Enum;

public class Arena
{
  private Mapa mapa;
  private List<Robo> robos;
  private int tempo;

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
  public void atualiza()
  {
    Robo roboTemp;
    Iterator<Robo> it;
    
    for(it = robos.iterator(); it.hasNext();)
    {
      roboTemp = it.next();
      roboTemp.executaAcao();
    }
    this.tempo++;
  }

  public boolean sistema(Comando cmd, Robo robo)
  {
    /*O comando que entra é do tipo bolado, existem alguns comandos bolados.
    reconheceremos eles aqui e daremos a resposta dizendo se executamos ou 
    não(de acordo com o estado do sistema)*/
    if(cmd.getCode().equalsIgnoreCase("WALK"))
    {
      //verifica estado do sistema
    }
    else if(cmd.getCode().equalsIgnoreCase("COLLECT"))
    {
      if(cmd.getValor().equalsIgnoreCase("UP"))
    }
    else if(cmd.getCode().equalsIgnoreCase("DROP"))
    {

    }
    else if(cmd.getCode().equalsIgnoreCase("ATKDIST"))
    {

    }
    else if(cmd.getCode().equalsIgnoreCase("ATKMEELE"))
    {

    }
  }



  /* ******************
    Cria um robo na posição x,y 
    {
      Retorna 1 caso tenha conseguido inserir,
      0 c.c.
    }
    ***************** */
  public boolean insereExercito(int x, int y, int team)
  {
    /* Cria uma máquina, em teoria, vazia. Talvez mudemos depois.*/
    Maquina maqTemp = new Maquina(this); 


    Robo roboTemp = new Robo(maqTemp, x, y, team);


     /*Tenta colocar o robo no mapa. Caso já 
      Exista um robo, a função irá retornar 0*/
    if(mapa.putRobo(roboTemp,x,y))
    {
      this.robos.add(roboTemp);
      return true;
    }

    return false;


    
  }

  public void removeExercito(int x, int y)
  {
    Robo roboTemp;


    roboTemp = mapa.get(x,y).getRobo();
    mapa.get(x,y).removeRobo();

    if(roboTemp != null)
    {
      Iterator<Robo> it = robos.iterator();
      while (it.hasNext()) {
        Robo rb = it.next();
        if (rb == roboTemp) {
          it.remove();
          break;
        }
      }

    }


  }
}



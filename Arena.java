import java.util.*;
import java.lang.String;
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
    reconheceremos eles aqui  daremos a resposta dizendo se executamos ou 
    não(de acordo com o estado do sistema)*/
    int x = robo.getX();
    int y = robo.getY();
    Terreno terrAtual = mapa.get(x, y);
    Terreno terrTemp;

    if(cmd.getValor() == 1)terrTemp = mapa.getUp(x, y);
    else if(cmd.getValor() == 2)terrTemp = mapa.getUpRight(x, y);
    else if(cmd.getValor() == 3)terrTemp = mapa.getDownRight(x, y);
    else if(cmd.getValor() == 4)terrTemp = mapa.getDown(x, y);   
    else if(cmd.getValor() == 5)terrTemp = mapa.getDownLeft(x, y);
    else if(cmd.getValor() == 6)terrTemp = mapa.getUpLeft(x, y);

    if(cmd.getCode().equalsIgnoreCase("WALK") && !terrTemp.temRobo())
    {
      terrAtual.removeRobo();
      terrTemp.putRobo(robo);
    }
    /*else if(cmd.getCode().equalsIgnoreCase("COLLECT"))
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

    }*/
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



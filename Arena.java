import java.util.*;
import java.lang.Enum;
import java.util.Random;

public class Arena
{
  private static final int TAM_EX = 10;
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
    Collections.shuffle(robos);

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
   this.tempo++;
 }

 public boolean sistema(Operacao op)
 {
  Move enumMove;

  Comando cmd = op.getCmd();
  Robo robo = (Robo) op.getOrigem();
    /*O comando que entra é do tipo bolado, existem alguns comandos bolados.
    reconheceremos eles aqui  daremos a resposta dizendo se executamos ou 
    não(de acordo com o estado do sistema)*/
    int x = robo.getX();
    int y = robo.getY();
    Terreno terrAtual = mapa.getTerreno(x, y);
    Terreno terrTemp = null;

    //String code = cmd.getCode();
    int valor = (int)(((Numero)cmd.getValor()).getVal());

    if(valor == 1)terrTemp = mapa.getUp(x, y);
    else if(valor == 2)terrTemp = mapa.getUpRight(x, y);
    else if(valor == 3)terrTemp = mapa.getDownRight(x, y);
    else if(valor == 4)terrTemp = mapa.getDown(x, y);
    else if(valor == 5)terrTemp = mapa.getDownLeft(x, y);
    else if(valor == 6)terrTemp = mapa.getUpLeft(x, y);

    if(cmd.codeEquals("WALK") && !terrTemp.temRobo())
    { 

      if(terrAtual instanceof Rugoso) 
        robo.setAtraso(new Atraso(op,3));
      else
      {
        terrAtual.removeRobo();
        terrTemp.putRobo(robo);
      }
      return true;
    }
    else if(terrTemp instanceof Deposito && cmd.codeEquals("COLLECT")) 
    {
      Deposito dep = (Deposito) terrTemp;
      if(dep.temCristal() && !robo.temCristal())
      {
        robo.coletaCristal(dep.popCristal());
        return true;
      } 
    }
    else if((terrTemp instanceof Terreno) && cmd.codeEquals("DROP"))
    {
      if(terrTemp instanceof Base)
      {
        ((Base)terrTemp).putCristal(robo.dropCristal());
        return true;
      }
      else
      {
        Cristal cris = robo.dropCristal();
        Terreno depTemp = mapa.getTerreno(cris.getX(), cris.getY());
        ((Deposito)depTemp).putCristal(cris);
        return true;
      }
    }
    /*else if(code.equalsIgnoreCase("ATKDIST"))
    {
      //peitos
    }
    else if(code.equalsIgnoreCase("ATKMEELE"))
    {
      //+peitos
    }*/
    return false;
  }
  /* ******************
  Cria um robo na posição x,y 
  Retorna 1 caso tenha conseguido inserir,
  0 c.c.
  ***************** */
  
  public boolean insereExercito(Robo rb)
  {
    /*Tenta colocar o robo no mapa. Caso já 
    Exista um robo, a função irá retornar 0*/
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
  */
  public void removeExercito(int x, int y)
  {
    Robo roboTemp;

    roboTemp = mapa.getTerreno(x,y).getRobo();
    mapa.getTerreno(x,y).removeRobo();

    if(roboTemp != null)
    {
      Iterator<Robo> it = robos.iterator();
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



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

  /****** Funções ******/

  /* Função que percorrerá a lista de robôs presentes na arena (em ordem aleatória). 
     Para cada robô encontrado, chamará uma função que executará o próximo comando   
     presente no programa do robô, dado o devido respeito às condições de atraso.    */
     public boolean atualiza()
     {
      Robo roboTemp;
      RndIterator<Robo> it;
      boolean alguemTemAcao = false;
      /* Aleatoriza o vetor de robôs. */
      //Collections.shuffle(robos);

      /* Percorre a lista de robôs, executando os comandos de cada um (de acordo com o estado de seu atraso). */
      for(it = new RndIterator<Robo>(robos); it.hasNext();)
      {
        roboTemp = it.next();
        if(!roboTemp.temAtraso())
        {
          if(roboTemp.executaAcao())
          {
            alguemTemAcao = true;
          }
        }
        else
        {
          Atraso at = roboTemp.getAtraso();
          if(at.getTempo() <= 0)
          {
           this.sistema( at.getOperacao());
           roboTemp.tiraAtraso();
         }
         else at.somaTempo(-1);
         alguemTemAcao = true;
       }
     }
   /* Incrementa a unidade que trata do tempo transcorrido desde o início do programa.                
   Ou seja, cada ciclo da função Atualiza representa uma unidade de tempo transcorrida no programa.*/
   this.tempo++;
   return alguemTemAcao;
 }

 /******
  Essa função recebe um objeto do tipo Operacao representando
  uma chamada ao sistema feita por algum robo.
  A função executa a ação necessária para executar a operação passada
  ***** */
  public void sistema(Operacao op)
  {

    Comando cmd = op.getCmd();
    Robo robo = (Robo) op.getOrigem();
    int x = robo.getX();
    int y = robo.getY();
    int indRobo = robo.getInd();
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
      push(robo, resp);
      return;
    }

  /* If's que verificarão o comando que foi passado como operação e
  o executarão de acordo com a funcionalidade de cada um.         */
  if(cmd.codeEquals("WALK") && !terrTemp.temRobo())
  { 
    if(terrAtual.eRugoso()) 
    {
      System.out.println("Robo "+indRobo+" tentou se mover, mas está em um terreno rugoso.\n Isso pode levar algum tempo");
      robo.setAtraso(new Atraso(op,3));
      resp = new Numero(1); // Conseguiu se mover, mas vai demorar
    }
    else
    {     
      this.moveRobo(terrAtual, terrTemp);
      System.out.println("Robo "+indRobo+" se moveu para a posição ("+terrTemp.getX()+","+terrTemp.getY()+")");
      resp = new Numero(1);
    }
  }
  else if(cmd.codeEquals("COLLECT") && podeColetar(robo, terrTemp)) 
  {
    System.out.println("Robo "+indRobo+" coletou 1 cristal");
    Deposito dep = terrTemp.toDeposito();
    robo.coletaCristal(dep.popCristal());
    resp = new Numero(1);
  } 
  else if(cmd.codeEquals("DROP") && robo.temCristal())
  {
    if(terrTemp.eBase()) 
    {
      dropCristal(robo, terrTemp);
      System.out.println("Robo "+indRobo+" deixou 1 cristal na base");
      resp = new Numero(1);        
    }
    else{
      System.out.println("Robo "+indRobo+" tentou deixar cristal na base, mas acabou perdendo ele");
      perdeCristal(robo);
    }
  }
  else if(cmd.codeEquals("ATK") && terrTemp.temRobo())
  {
    Robo inim = terrTemp.getRobo();
    inim.perdeVida(10);
    System.out.println("Robo "+indRobo+" atacou o robo "+inim.getInd()+"!");
    if(!inim.temVida()){
      System.out.println("Robo "+inim.getInd()+" morreu");
      removeRobo(inim);
    } 
    resp = new Numero(1);
  }
  this.push(robo, resp);//empilha a resposta no robo
}


  /* Cria um robo na posição x,y : 
       -Retorna true caso tenha conseguido inserir,
       -Retorna false c.c.                            
  */
       private boolean insereRobo(Robo rb)
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

public void insereExercito(Programa[] programas, Posicao[] posicoes, int time)
{
  int tam = Math.min(programas.length, posicoes.length);


  for(int i = 0; i < tam; i++)
  {
    Robo rb = new Robo(this,posicoes[i], time);
    rb.carregaPrograma(programas[i]);

    if( insereRobo(rb) )
      System.out.println("Robo "+i+" inserido com sucesso");
    else
      System.out.println("Falha ao tentar inserir o robo "+i);
  }

}  

/* Remove todos os robos que pertencem ao time passado */
public void removeExercito(int time)
{

  Iterator<Robo> it = robos.iterator();
  while (it.hasNext()) 
  {
    Robo rb = it.next();
    if (rb.getTime() == time) removeRobo(rb);      
  }
}

 /* Recebe um robo, que será retirado da lista de robos
 ativos e que será removido do mapa */
 private void removeRobo(Robo rb)
 {
  Posicao pos = rb.getPos();
  mapa.getTerreno(pos).removeRobo();
  robos.remove(rb);
}

private void moveRobo(Terreno origem, Terreno destino)
{
  Robo robo = origem.removeRobo();
  destino.putRobo(robo);
  robo.setPos(destino.getPos());
}

private boolean podeColetar(Robo robo, Terreno destino)
{
  return (!robo.temCristal() && destino.eDeposito() && destino.toDeposito().temCristal());
}

private void dropCristal(Robo robo, Terreno destino)
{
  destino.toBase().putCristal(robo.dropCristal());
}

private void perdeCristal(Robo robo)
{
  Cristal cris = robo.dropCristal();
  Terreno depTemp = mapa.getTerreno(cris.getX(), cris.getY());
  depTemp.toDeposito().putCristal(cris);
}

private void push(Robo robo, Empilhavel resp)
{
  robo.push(resp);
}
}



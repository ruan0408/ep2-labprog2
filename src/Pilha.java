import java.util.*;

public class Pilha
{

  private Stack<Empilhavel> pilha;


  /****** Construtor ******/


  public Pilha()
  {
      pilha = new Stack<Empilhavel>();
  }


  /****** Funções ******/


  /* Verifica se a pilha está vazia, retornando TRUE se estiver vazia e 0 caso contrário*/
  public boolean vazio()
  {
    return pilha.empty();
  }
  
  /* Empilha na pilha o valor passado como argumento. */
  public void push(Empilhavel emp)
  {
    pilha.push(emp);     
  }

  /* Desempilha o último elemento da pilha e o retorna. */
  public Empilhavel pop()
  {
    if(!pilha.empty())
      return pilha.pop();
    else
    {
      System.out.println("Pop em pilha vazia");
      return null;
    }
  }

  /* Retorna o último elemtno da pilha, sem desempilhá-lo. */
  public Empilhavel look()
  {
    if(!pilha.empty())
      return pilha.peek();
    else
    {
      System.out.println("Look em pilha vazia");
      return null;
    }
  }

  /* Duplica o último elemento da pilha (empilha-se uma réplica do último elemento). */
  public void duplica()
  {
    if(!pilha.empty())
      pilha.push(this.look());      
    else
    {
      System.out.println("Tentativa de duplicação em pilha vazia.");
    }
  }

  
  



}

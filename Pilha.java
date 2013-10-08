import java.util.*;


public class Pilha
{

  private Stack<Empilhavel> pilha;

  public Pilha()
  {
      pilha = new Stack<Empilhavel>();
  }



  public boolean vazio()
  {
    return pilha.empty();
  }
  
  public void push(Empilhavel emp)
  {
    pilha.push(emp);     
  }

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

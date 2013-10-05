import java.util.*;

public class Maquina
{
  private Pilha dados;
  private List<Empilhavel> mem;
  private List<Comando> prog;
  private Arena arena;
  
  Maquina(Arena arena)
  {
    this.arena = arena;
    mem = new ArrayList<Empilhavel>();
    prog = new ArrayList<Comando>();
    dados = new Pilha();
  }

  /*private void pushDados(Empilhavel emp)
  {
    this.pilha.push(emp);
  }
  private Empilhavel popDados()
  {
   return this.pilha.pop(); 
  }*/
  //private Empilhavel peekDados
  //private void setMem
  //private Empilhavel getMem
  //

  private void executaCmd(Comando cmd)
  {
    String code = cmd.getCode();
    Empilhavel valor = cmd.getValor();
    Empilhavel aux1, aux2;

    if(code.equalsIgnoreCase("PUSH"))
    {
      this.dados.push(valor);
    }
    else if(code.equalsIgnoreCase("POP"))
    {
      this.dados.pop();
    }
    else if(code.equalsIgnoreCase("DUP"))
    {
      this.dados.push(this.dados.look());
    }
    else if(code.equalsIgnoreCase("ADD"))
    {
      aux1 = this.dados.pop();
      aux2 = this.dados.pop();
      if(aux1 instanceof Numero && aux2 instanceof Numero)
      {
        this.dados.push(aux1+aux2);
      }
      else
      {
        System.out.println("ERRO: tentativa de somar não-números");
        this.dados.push(aux2);
        this.dados.push(aux1);
      }
    }
    else if(code.equalsIgnoreCase("SUB"))
    {
      this.dados.push(this.dados.look());
    }
    else if(code.equalsIgnoreCase("MUL"))
    {
      this.dados.push(this.dados.look());
    }
    else if(code.equalsIgnoreCase("DIV"))
    {
      this.dados.push(this.dados.look());
    }
    else if(code.equalsIgnoreCase("EQ"))
    {
      this.dados.push(this.dados.look());
    }
    else if(code.equalsIgnoreCase("GT"))
    {
      this.dados.push(this.dados.look());
    }
    else if(code.equalsIgnoreCase("GE"))
    {
      this.dados.push(this.dados.look());
    }
    else if(code.equalsIgnoreCase("LT"))
    {
      this.dados.push(this.dados.look());
    }
    else if(code.equalsIgnoreCase("LE"))
    {
      this.dados.push(this.dados.look());
    }
    else if(code.equalsIgnoreCase("NE"))
    {
      this.dados.push(this.dados.look());
    }
    else if(code.equalsIgnoreCase("JMP"))
    {
      this.dados.push(this.dados.look());
    }
    else if(code.equalsIgnoreCase("JIT"))
    {
      this.dados.push(this.dados.look());
    }
    else if(code.equalsIgnoreCase("JIF"))
    {
      this.dados.push(this.dados.look());
    }
    else if(code.equalsIgnoreCase("STO"))
    {
      this.dados.push(this.dados.look());
    }
    else if(code.equalsIgnoreCase("RCL"))
    {
      this.dados.push(this.dados.look());
    }
    else if(code.equalsIgnoreCase("END"))
    {
      this.dados.push(this.dados.look());
    }
    else if(code.equalsIgnoreCase("PRN"))
    {
      this.dados.push(this.dados.look());
    }
  }
}

import java.util.*;
import java.lang.String;

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
        Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
        Numero resu = new Numero(n1.getVal() + n2.getVal());
        this.dados.push(resu);
      }
      else
      {
        System.out.println("ERRO: tentativa de somar não-números");
       /* this.dados.push(aux2);
        this.dados.push(aux1);*/
      }
    }
    else if(code.equalsIgnoreCase("SUB"))
    {
      aux1 = this.dados.pop();
      aux2 = this.dados.pop();
      if(aux1 instanceof Numero && aux2 instanceof Numero)
      {
        Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
        Numero resu = new Numero(n2.getVal() - n1.getVal());
        this.dados.push(resu);
      }
      else
      {
        System.out.println("ERRO: tentativa de subtrair não-números");
        this.dados.push(aux2);
        this.dados.push(aux1);
      }
    }
    else if(code.equalsIgnoreCase("MUL"))
    {
      aux1 = this.dados.pop();
      aux2 = this.dados.pop();
      if(aux1 instanceof Numero && aux2 instanceof Numero)
      {
        Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
        Numero resu = new Numero(n1.getVal() * n2.getVal());
        this.dados.push(resu);
      }
      else
      {
        System.out.println("ERRO: tentativa de multiplicar não-números");
        this.dados.push(aux2);
        this.dados.push(aux1);
      }
    }
    else if(code.equalsIgnoreCase("DIV"))
    {
      aux1 = this.dados.pop();
      aux2 = this.dados.pop();
      if(aux1 instanceof Numero && aux2 instanceof Numero)
      {
        Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
        Numero resu = new Numero(n2.getVal()/ n1.getVal());
        this.dados.push(resu);
      }
      else
      {
        System.out.println("ERRO: tentativa de dividir não-números");
        this.dados.push(aux2);
        this.dados.push(aux1);
      }
    }
    else if(code.equalsIgnoreCase("EQ"))
    {
      aux1 = this.dados.pop();
      aux2 = this.dados.pop();
      if(aux1 instanceof Numero && aux2 instanceof Numero)
      {
        Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
        Numero resu;
        if(n2.getVal() == n1.getVal()) resu = new Numero(1);
        else resu = new Numero(0);
        this.dados.push(resu);
      }
      else
      {
        System.out.println("ERRO: tentativa de comparar não-números");
        this.dados.push(aux2);
        this.dados.push(aux1);
      }
    }
    else if(code.equalsIgnoreCase("GT"))
    {
      aux1 = this.dados.pop();
      aux2 = this.dados.pop();
      if(aux1 instanceof Numero && aux2 instanceof Numero)
      {
        Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
        Numero resu;
        if(n2.getVal() > n1.getVal()) resu = new Numero(1);
        else resu = new Numero(0);
        this.dados.push(resu);
      }
      else
      {
        System.out.println("ERRO: tentativa de comparar não-números");
        this.dados.push(aux2);
        this.dados.push(aux1);
      }
    }
    else if(code.equalsIgnoreCase("GE"))
    {
      aux1 = this.dados.pop();
      aux2 = this.dados.pop();
      if(aux1 instanceof Numero && aux2 instanceof Numero)
      {
        Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
        Numero resu;
        if(n2.getVal() >= n1.getVal()) resu = new Numero(1);
        else resu = new Numero(0);
        this.dados.push(resu);
      }
      else
      {
        System.out.println("ERRO: tentativa de comparar não-números");
        this.dados.push(aux2);
        this.dados.push(aux1);
      }
    }
    else if(code.equalsIgnoreCase("LT"))
    {
      aux1 = this.dados.pop();
      aux2 = this.dados.pop();
      if(aux1 instanceof Numero && aux2 instanceof Numero)
      {
        Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
        Numero resu;
        if(n2.getVal() < n1.getVal()) resu = new Numero(1);
        else resu = new Numero(0);
        this.dados.push(resu);
      }
      else
      {
        System.out.println("ERRO: tentativa de comparar não-números");
        this.dados.push(aux2);
        this.dados.push(aux1);
      }
    }
    else if(code.equalsIgnoreCase("LE"))
    {
      aux1 = this.dados.pop();
      aux2 = this.dados.pop();
      if(aux1 instanceof Numero && aux2 instanceof Numero)
      {
        Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
        Numero resu;
        if(n2.getVal() <= n1.getVal()) resu = new Numero(1);
        else resu = new Numero(0);
        this.dados.push(resu);
      }
      else
      {
        System.out.println("ERRO: tentativa de comparar não-números");
        this.dados.push(aux2);
        this.dados.push(aux1);
      }
    }
    else if(code.equalsIgnoreCase("NE"))
    {
      aux1 = this.dados.pop();
      aux2 = this.dados.pop();
      if(aux1 instanceof Numero && aux2 instanceof Numero)
      {
        Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
        Numero resu;
        if(n2.getVal() != n1.getVal()) resu = new Numero(1);
        else resu = new Numero(0);
        this.dados.push(resu);
      }
      else
      {
        System.out.println("ERRO: tentativa de comparar não-números");
        this.dados.push(aux2);
        this.dados.push(aux1);
      }
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
      aux1 = this.dados.pop();
      if(aux1 instanceof Numero)
      {
        String str = Integer.toString(((Numero)aux1).getVal());
        System.out.println(str);
      }
      else//acho que da pra encapsular todas essas coisas e fazer as porras imprimirem a si mesmo.
      {
        System.out.println("erro");
      }
    }
  }
}

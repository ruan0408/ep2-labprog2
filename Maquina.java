import java.util.*;
import java.lang.String;

public class Maquina
{
  private Pilha dados;
  private List<Empilhavel> mem;
  private List<Comando> prog;
  private Arena arena;
  private int index;
  
  Maquina(Arena arena)
  {
    this.arena = arena;
    mem = new ArrayList<Empilhavel>();
    prog = new ArrayList<Comando>();
    dados = new Pilha();

    this.index = 0;
  }


  public void carregaPrograma(List<Comando> progTemp)
  {
    prog = progTemp;
    this.index = 0;
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

 public boolean temProx()
 {
    return !(index >= prog.size());

 }

 public void executaProx()
 {
    if(!this.temProx()) return;
        
    this.index = executaCmd( prog.get(this.index) );
    
  }

 private int executaCmd(Comando cmd)
 {
  String code = cmd.getCode();
  Empilhavel valor = cmd.getValor();
  Empilhavel aux1, aux2;
  int novoIndice = this.index + 1;

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
      Numero resu = new Numero(n2.getVal() * n1.getVal());
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
      Numero resu = new Numero(n2.getVal()/n1.getVal());
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
      //aux1.ne(aux2);
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
    else if(code.equalsIgnoreCase("JMP"))//assumo que os labels ja foram substituidos por numeros
    {
      novoIndice = (int) ((Numero)valor).getVal();
    }
    else if(code.equalsIgnoreCase("JIT"))
    {
      aux1 = this.dados.pop();
      if(aux1 instanceof Numero)
      {
       if(((Numero)aux1).getVal() != 0) novoIndice = (int) ((Numero)valor).getVal();
      }
      else
        System.out.println("Tentando comparar não-numeros");
    }
    else if(code.equalsIgnoreCase("JIF"))
    {
      aux1 = this.dados.pop();
      System.out.println(aux1);
      if(aux1 instanceof Numero)
      {
       if(((Numero)aux1).getVal() == 0) novoIndice = (int) ((Numero)valor).getVal();
      }
      else
        System.out.println("Tentando comparar não-numeros");
    }
    else if(code.equalsIgnoreCase("STO"))
    {
      aux1 = this.dados.pop();
      this.mem.add(( (int)((Numero)valor).getVal()), aux1);
    }
    else if(code.equalsIgnoreCase("RCL"))
    {
      int index = (int) ((Numero)valor).getVal();
      this.dados.push( this.mem.get(index) );
      this.mem.remove(index);
      
    }
    else if(code.equalsIgnoreCase("END"))
    {
      novoIndice = this.prog.size();
    }
    else if(code.equalsIgnoreCase("PRN"))
    {
      aux1 = this.dados.pop();
      if(aux1 instanceof Numero)
      {
        System.out.println(((Numero)aux1).getVal());
      }
      else//acho que da pra encapsular todas essas coisas e fazer as porras imprimirem a si mesmo.
      {
        System.out.println("erro");
      }
    }
    System.out.println("HUE");
    return novoIndice;
  }
}

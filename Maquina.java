import java.util.*;
import java.lang.String;

public class Maquina
{
  private Pilha dados;
  private List<Empilhavel> mem;
  private List<Comando> prog;
  private Arena arena;
  private int index;
  private Programavel obj;
  
  public Maquina(Arena arena)
  {
    this.arena = arena;
    mem = new ArrayList<Empilhavel>();
    prog = new ArrayList<Comando>();
    dados = new Pilha();

    obj = null;

    this.index = 0;
  }

  public Maquina(Arena arena, Programavel obj)
  {
    this(arena); // Contrutor com um argumento só sendo chamado
    this.obj = obj;

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
  Empilhavel valor = cmd.getValor();
  Empilhavel aux1, aux2;
  int novoIndice = this.index + 1;

  if(cmd.codeEquals("PUSH"))
  {
    this.dados.push(valor);
  }
  else if(cmd.codeEquals("POP"))
  {
    this.dados.pop();
  }
  else if(cmd.codeEquals("DUP"))
  {
    this.dados.push(this.dados.look());
  }
  else if(cmd.codeEquals("ADD"))
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
   else if(cmd.codeEquals("SUB"))
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
  else if(cmd.codeEquals("MUL"))
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
  else if(cmd.codeEquals("DIV"))
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
  else if(cmd.codeEquals("EQ"))
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
  else if(cmd.codeEquals("GT"))
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
  else if(cmd.codeEquals("GE"))
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
  else if(cmd.codeEquals("LT"))
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
  else if(cmd.codeEquals("LE"))
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
  else if(cmd.codeEquals("NE"))
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
    else if(cmd.codeEquals("JMP"))//assumo que os labels ja foram substituidos por numeros
    {
      novoIndice = (int) ((Numero)valor).getVal();
    }
    else if(cmd.codeEquals("JIT"))
    {
      aux1 = this.dados.pop();
      if(aux1 instanceof Numero)
      {
       if(((Numero)aux1).getVal() != 0) novoIndice = (int) ((Numero)valor).getVal();
      }
      else
        System.out.println("Tentando comparar não-numeros");
    }
    else if(cmd.codeEquals("JIF"))
    {
      aux1 = this.dados.pop();
      if(aux1 instanceof Numero)
      {
       if(((Numero)aux1).getVal() == 0) novoIndice = (int) ((Numero)valor).getVal();
      }
      else
        System.out.println("Tentando comparar não-numeros");
    }
    else if(cmd.codeEquals("STO"))
    {
      aux1 = this.dados.pop();
      this.mem.add(( (int)((Numero)valor).getVal()), aux1);
    }
    else if(cmd.codeEquals("RCL"))
    {
      int index = (int) ((Numero)valor).getVal();
      this.dados.push( this.mem.get(index) );
      this.mem.remove(index);
      
    }
    else if(cmd.codeEquals("END"))
    {
      novoIndice = this.prog.size();
    }
    else if(cmd.codeEquals("PRN"))
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
    return novoIndice;
  }

/*Função que faz chamada ao sistema (arena).
  Retorna um empilhável como resposta que será colocado na
  pilha(? talvez)*/

  private void sistema(Comando cmd)
  {
    Empilhavel resp;
    Operacao op;

    op = new Operacao(cmd,obj);

    /* Verifica qual operação é, e coloca no obj operação
      os empilhaveis necessários */


      //Para testes, automaticamente adiciona um empilhavel na operação

      op.pushArg(this.dados.pop());




    arena.sistema(op); //Cast temporário de (Robo)

    //Push resp
  }
}

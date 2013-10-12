import java.util.*;
import java.lang.String;

public class Maquina
{
  private Pilha dados;
  private List<Empilhavel> mem;
  private Programa prog;
  private Arena arena;
  private int index;
  private Programavel obj;
  

  /****** Construtor ******/


  public Maquina(Arena arena)
  {
    this.arena = arena;
    mem = new ArrayList<Empilhavel>();
    prog = new Programa();
    dados = new Pilha();

    obj = null;

    this.index = 0;
  }

  public Maquina(Arena arena, Programavel obj)
  {
    this(arena); // Contrutor com um argumento só sendo chamado
    this.obj = obj;

  }


  /****** Funções ******/
  public Pilha getDados()
  {
    return this.dados;
  }

  /* Função que recebe um programa (lista de comandos) e o insere na máquina virtual. */
  public void carregaPrograma(Programa progTemp)
  {
    prog = progTemp;
    this.index = 0;
  }

  /* Retorna TRUE se ainda não estivermos no fim do programa (se ainda houver um próximo comando) e FALSE c.c. */
  public boolean temProx()
  {
     return !(index >= prog.size());
  }

  /* Chama a função "executaCmd" no próximo comando, se o mesmo existir. */
  public void executaProx()
  {
     if(!this.temProx()) return;
         
     this.index = executaCmd( prog.get(this.index) );
     
   }

 
 /* Função que recebe um comando e o executa, de acordo com seu código. */
 private int executaCmd(Comando cmd)
 {
  Empilhavel valor = cmd.getValor();
  Empilhavel aux1, aux2;
  int novoIndice = this.index + 1;

  /* Insere na pilha. */
  if(cmd.codeEquals("PUSH"))
  {
    this.dados.push(valor);
  }
  /* Remove ultimo membro da pilha. */
  else if(cmd.codeEquals("POP"))
  {
    this.dados.pop();
  }
  /* Duplica o último elemento da pilha. */
  else if(cmd.codeEquals("DUP"))
  {
    this.dados.push(this.dados.look());
  }
  /* Desempilha os dois últimos elementos da pilha, os soma e empilha o resultado. */
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
   /* Desempilha os dois últimos elementos da pilha, subtrai o penultimo pelo ultimo e empilha o resultado. */
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
  /* Desempilha os dois últimos elementos da pilha, os multiplica e empilha o resultado. */
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
  /* Desempilha os dois últimos elementos da pilha, divide o penúltimo pelo ultimo e empilha o resultado. */
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
  /* Desempilha os dois últimos elementos da pilha, empilha 1 caso sejam iguals e 0 caso contrário. */
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
  /* Desempilha os dois últimos elementos da pilha, empilha 1 se o penúltimo elemento for maior que o último, empilha 0 caso contrário. */
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
  /* Desempilha os dois últimos elementos da pilha, empilha 1 se o penúltimo elemento for maior ou igual ao último, empilha 0 caso contrário. */
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
  /* Desempilha os dois últimos elementos da pilha, empilha 1 se o penúltimo elemento for menor que o último, empilha 0 caso contrário. */
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
  /* Desempilha os dois últimos elementos da pilha, empilha 1 se o penúltimo elemento for menor ou igual ao último, empilha 0 caso contrário. */
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
  /* Desempilha os dois últimos elementos da pilha, empilha 1 se eles forem diferentes e 0 caso contrário. */
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
    /* Modifica o índice do programa para o valor passado como argumento ao comando. */
    else if(cmd.codeEquals("JMP"))//assumo que os labels ja foram substituidos por numeros
    {
      novoIndice = (int) ((Numero)valor).getVal();
    }
    /* Se o último valor da pilha for 1, modifica o índice do programa para o valor passado como argumento ao comando. */
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
    /* Se o último valor da pilha for 0, modifica o índice do programa para o valor passado como argumento ao comando. */
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
    /* Desempilha o ultimo elemento da pilha de dados e o insere na memória na posição passada como argumento ao comando. */
    else if(cmd.codeEquals("STO"))
    {
      aux1 = this.dados.pop();
      this.mem.add(( (int)((Numero)valor).getVal()), aux1);
    }
    /* Remove o dado armazenado na posição da memoria passada como argumento ao comando e o empilha na pilha de dados. */
    else if(cmd.codeEquals("RCL"))
    {
      int index = (int) ((Numero)valor).getVal();
      this.dados.push( this.mem.get(index) );
      this.mem.remove(index);
    }
    /* Encerra o programa modificando o índice de leitura para uma linha depois da ultima linha de código, finalizando, desse modo, a leitura do programa. */
    else if(cmd.codeEquals("END"))
    {
      novoIndice = this.prog.size();
    }
    /* Desempilha o ultimo elemento da pilha de dados e o imprime na saída padrão. */
    else if(cmd.codeEquals("PRN"))
    {
      aux1 = this.dados.pop();
      if(aux1 instanceof Numero)
      {
        System.out.println(((Numero)aux1).getVal());
      }
      else if (aux1 instanceof Frase) 
      {
        System.out.println(((Frase)aux1).getString()); 
      }
      else//acho que da pra encapsular todas essas coisas e fazer as porras imprimirem a si mesmo.
      {
        System.out.println("erro");
      }
    }
    else if(cmd.codeEquals("WALK") || cmd.codeEquals("COLLECT")
            || cmd.codeEquals("DROP") || cmd.codeEquals("ATK")) 
    {
      this.sistema(cmd);
    }
    
    return novoIndice;
  }


/* Função que faz chamada ao sistema (arena).
   Retorna um empilhável como resposta que será colocado na
   pilha(? talvez)					    */
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

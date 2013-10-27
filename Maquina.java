import java.util.*;
import java.lang.String;

public class Maquina
{
  private Pilha dados;
  private Empilhavel[] mem;
  private Programa prog;
  private Variaveis vars;
  private Arena arena;
  private int index;
  private Programavel obj;
  

  /****** Construtor ******/


  public Maquina(Arena arena)
  {
    this.arena = arena;
    mem = new Empilhavel[10];
    prog = new Programa();
    dados = new Pilha();
    vars = new Variaveis();

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
 public void executaProx(int indRobo)
 {
   if(!this.temProx()) return;

   this.index = executaCmd( prog.get(this.index), indRobo );

 }

 public void pushDados(Empilhavel resp)
 {
  dados.push(resp);
}


/* Função que recebe um comando e o executa, de acordo com seu código. */
private int executaCmd(Comando cmd, int indRobo)
{
  Empilhavel valor = cmd.getValor();
  Empilhavel aux1, aux2;
  int novoIndice = this.index + 1;

  /* Insere na pilha. */
  if(cmd.codeEquals("PUSH"))
  {
    this.dados.push(valor);
    //System.out.println("Robo "+indRobo+": PUSH");
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
    this.add();
  }
  /* Desempilha os dois últimos elementos da pilha, subtrai o penultimo pelo ultimo e empilha o resultado. */
  else if(cmd.codeEquals("SUB"))
  {
    this.sub();
  }
  /* Desempilha os dois últimos elementos da pilha, os multiplica e empilha o resultado. */
  else if(cmd.codeEquals("MUL"))
  {
    this.mul();
  }
  /* Desempilha os dois últimos elementos da pilha, divide o penúltimo pelo ultimo e empilha o resultado. */
  else if(cmd.codeEquals("DIV"))
  {
    this.div();
  }
  /* Desempilha os dois últimos elementos da pilha, empilha 1 caso sejam iguals e 0 caso contrário. */
  else if(cmd.codeEquals("EQ"))
  {
    this.eq();
  }
  /* Desempilha os dois últimos elementos da pilha, empilha 1 se o penúltimo elemento for maior que o último, empilha 0 caso contrário. */
  else if(cmd.codeEquals("GT"))
  {
    this.gt();
  }
  /* Desempilha os dois últimos elementos da pilha, empilha 1 se o penúltimo elemento for maior ou igual ao último, empilha 0 caso contrário. */
  else if(cmd.codeEquals("GE"))
  {
    this.ge();
  }
  /* Desempilha os dois últimos elementos da pilha, empilha 1 se o penúltimo elemento for menor que o último, empilha 0 caso contrário. */
  else if(cmd.codeEquals("LT"))
  {
    this.lt();
  }
  /* Desempilha os dois últimos elementos da pilha, empilha 1 se o penúltimo elemento for menor ou igual ao último, empilha 0 caso contrário. */
  else if(cmd.codeEquals("LE"))
  {
    this.le();
  }
  /* Desempilha os dois últimos elementos da pilha, empilha 1 se eles forem diferentes e 0 caso contrário. */
  else if(cmd.codeEquals("NE"))
  {
    this.ne();
  }
  /* Modifica o índice do programa para o valor passado como argumento ao comando. */
  else if(cmd.codeEquals("JMP"))//assumo que os labels ja foram substituidos por numeros
  {
    novoIndice = (int) ((Numero)valor).getVal();
  }
  /* Se o último valor da pilha for 1, modifica o índice do programa para o valor passado como argumento ao comando. */
  else if(cmd.codeEquals("JIT"))
  {
    int arg = (int) ((Numero)valor).getVal();
    novoIndice = this.jit(arg, novoIndice);//vai pular para o indice arg, ou continuar em novoIndice
  }
  /* Se o último valor da pilha for 0, modifica o índice do programa para o valor passado como argumento ao comando. */
  else if(cmd.codeEquals("JIF"))
  {
    int arg = (int) ((Numero)valor).getVal();
    novoIndice = this.jif(arg, novoIndice);//vai pular para o indice arg, ou continuar em novoIndice
  }
  /* Desempilha o ultimo elemento da pilha de dados e o insere na memória na posição passada como argumento ao comando. */
  else if(cmd.codeEquals("STO"))
  {
    int index = ((int)((Numero)valor).getVal());
    this.sto(index);
  }
  /* Remove o dado armazenado na posição da memoria passada como argumento ao comando e o empilha na pilha de dados. */
  else if(cmd.codeEquals("RCL"))
  { 
    int index1;
    index1 = (int) ((Numero)valor).getVal();
    this.dados.push( this.mem[index1] );//não remove o valor da memoria
    //this.mem.remove(index);
  }
  /* Encerra o programa modificando o índice de leitura para uma linha depois da ultima linha de código, finalizando, desse modo, a leitura do programa. */
  else if(cmd.codeEquals("END"))
  {
    novoIndice = this.prog.size();
  }
  /* Desempilha o ultimo elemento da pilha de dados e o imprime na saída padrão. */
  else if(cmd.codeEquals("PRN"))
  {
    this.prn();
  }
  else if(cmd.codeEquals("ALO"))
  {
    this.alo();
  }
  else if(cmd.codeEquals("SET"))
  {
    this.set();
  }
  else if(cmd.codeEquals("GET"))
  {
    this.get();
  }
  else if(cmd.codeEquals("WALK") || cmd.codeEquals("COLLECT")
    || cmd.codeEquals("DROP") || cmd.codeEquals("ATK")) 
  {
    this.sistema(cmd);
  }
  System.out.println("Robô "+indRobo+": "+cmd.toString());
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


  /* Aloca uma variavel, e empilha seu endereco */
  private void alo()
  {
    Endereco end = vars.aloc();
    dados.push(end);
  }

  /*Desempilha um Empilhavel e um endereco, e coloca esse
  Empilhavel no endereco dado, e o Endereco é empilhado de novo
  na pilha*/
  private void set()
  {
    Empilhavel aux1 = this.dados.pop();//pode gerar exceções
    Empilhavel aux2 = this.dados.pop();
    if(aux2 instanceof Endereco) vars.set((Endereco)aux2, aux1);
    this.dados.push(aux1);   

  }

  /*
    Desempilha um endereco, e substitui pelo valor
    no endereco daquela variavel*/
  private void get()
  {
    Empilhavel aux1 = this.dados.pop();//pode gerar exceções
    if(aux1 instanceof Endereco) this.dados.push(vars.get((Endereco)aux1));
  }

  private void add()
  {
    Empilhavel aux1 = this.dados.pop();//pode gerar exceções
    Empilhavel aux2 = this.dados.pop();
    if(aux1 instanceof Numero && aux2 instanceof Numero)
    {
      Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
      Numero resu = new Numero(n1.getVal() + n2.getVal());
      this.dados.push(resu);
    }
    else throw new ArithmeticException("Tentando somar não-números");
  }

  private void sub()
  {
    Empilhavel aux1 = this.dados.pop();//pode gerar exceções
    Empilhavel aux2 = this.dados.pop();
    if(aux1 instanceof Numero && aux2 instanceof Numero)
    {
      Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
      Numero resu = new Numero(n2.getVal() - n1.getVal());
      this.dados.push(resu);
    }
    else throw new ArithmeticException("Tentando subtrair não-números");
  }

  private void mul()
  {
    Empilhavel aux1 = this.dados.pop();//pode gerar exceções
    Empilhavel aux2 = this.dados.pop();
    if(aux1 instanceof Numero && aux2 instanceof Numero)
    {
      Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
      Numero resu = new Numero(n2.getVal() * n1.getVal());
      this.dados.push(resu);
    }
    else throw new ArithmeticException("Tentando multiplicar não-números");
  }

  private void div()
  {
    Empilhavel aux1 = this.dados.pop();//pode gerar exceções
    Empilhavel aux2 = this.dados.pop();
    if(aux1 instanceof Numero && aux2 instanceof Numero)
    {
      Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
      Numero resu = new Numero(n2.getVal()/n1.getVal());
      this.dados.push(resu);
    }
    else throw new ArithmeticException("Tentando dividir não-números");
  }

  private void eq()
  {
    Empilhavel aux1 = this.dados.pop();//pode gerar exceções
    Empilhavel aux2 = this.dados.pop();
    if(aux1 instanceof Numero && aux2 instanceof Numero)
    {
      Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
      Numero resu;
      if(n2.getVal() == n1.getVal()) resu = new Numero(1);
      else resu = new Numero(0);
      this.dados.push(resu);
    }
    else throw new ArithmeticException("Tentando comparar não-números");
  }

  private void ne()
  {
    Empilhavel aux1 = this.dados.pop();//pode gerar exceções
    Empilhavel aux2 = this.dados.pop();
    if(aux1 instanceof Numero && aux2 instanceof Numero)
    {
      Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
      Numero resu;
      if(n2.getVal() != n1.getVal()) resu = new Numero(1);
      else resu = new Numero(0);
      this.dados.push(resu);
    }
    else throw new ArithmeticException("Tentando comparar não-números");
  }

  private void ge()
  {
    Empilhavel aux1 = this.dados.pop();//pode gerar exceções
    Empilhavel aux2 = this.dados.pop();
    if(aux1 instanceof Numero && aux2 instanceof Numero)
    {
      Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
      Numero resu;
      if(n2.getVal() >= n1.getVal()) resu = new Numero(1);
      else resu = new Numero(0);
      this.dados.push(resu);
    }
    else throw new ArithmeticException("Tentando comparar não-números");
  }

  private void gt()
  {
    Empilhavel aux1 = this.dados.pop();//pode gerar exceções
    Empilhavel aux2 = this.dados.pop();
    if(aux1 instanceof Numero && aux2 instanceof Numero)
    {
      Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
      Numero resu;
      if(n2.getVal() > n1.getVal()) resu = new Numero(1);
      else resu = new Numero(0);
      this.dados.push(resu);
    }
    else throw new ArithmeticException("Tentando comparar não-números");
  }

  private void le()
  {
    Empilhavel aux1 = this.dados.pop();//pode gerar exceções
    Empilhavel aux2 = this.dados.pop();
    if(aux1 instanceof Numero && aux2 instanceof Numero)
    {
      Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
      Numero resu;
      if(n2.getVal() <= n1.getVal()) resu = new Numero(1);
      else resu = new Numero(0);
      this.dados.push(resu);
    }
    else throw new ArithmeticException("Tentando comparar não-números");
  }

  private void lt()
  {
    Empilhavel aux1 = this.dados.pop();//pode gerar exceções
    Empilhavel aux2 = this.dados.pop();
    if(aux1 instanceof Numero && aux2 instanceof Numero)
    {
      Numero n1 = (Numero) aux1; Numero n2 = (Numero) aux2;
      Numero resu;
      if(n2.getVal() < n1.getVal()) resu = new Numero(1);
      else resu = new Numero(0);
      this.dados.push(resu);
    }
    else throw new ArithmeticException("Tentando comparar não-números");
  }

  private int jit(int arg, int indexAtual)
  {
    Empilhavel aux1 = this.dados.pop();
    if(aux1 instanceof Numero)
    {
      if((int)(((Numero)aux1).getVal()) != 0) return arg; 
      else return indexAtual;
    }
    else throw new ArithmeticException("Tentando comparar não-números");
  }

  private int jif(int arg, int indexAtual)
  {
    Empilhavel aux1 = this.dados.pop();
    if(aux1 instanceof Numero)
    {
      if((int)(((Numero)aux1).getVal()) == 0) return arg; 
      else return indexAtual;
    }
    else throw new ArithmeticException("Tentando comparar não-números");
  }

  private void sto(int index)
  {
    Empilhavel aux1 = this.dados.pop();
    if((this.mem.length) <= index)
      this.mem = Arrays.copyOf(this.mem, index+1);
    
    this.mem[index] = aux1;
  }

  private void prn()
  {
    Empilhavel aux1 = this.dados.pop();
    if(aux1 instanceof Numero)
    {
      System.out.println(((Numero)aux1).getVal());
    }
    if (aux1 instanceof Frase) 
    {
      System.out.println(((Frase)aux1).getString()); 
    }
  }
}

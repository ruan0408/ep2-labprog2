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
  
  /* Função que recebe um programa (lista de comandos) e o insere na máquina virtual. */
  public void carregaPrograma(Programa progTemp)
  {
    prog = progTemp;
    this.index = 0;
  }

  /* Retorna TRUE se ainda não estivermos no fim do programa (se ainda houver um próximo comando) e FALSE c.c. */
  private boolean temProx()
  {
   return !(index >= prog.size());
  }

 /* Chama a função "executaCmd" no próximo comando, se o mesmo existir. */
 public boolean executaProx()
 {
   if(!this.temProx()) return false;

   this.index = executaCmd( prog.get(this.index));
   return true;

 }

 public void pushDados(Empilhavel resp)
 {
  dados.push(resp);
 }

 public Empilhavel popDados()
 {
    return dados.pop();
 }


/* Função que recebe um comando e o executa, de acordo com seu código. */
private int executaCmd(Comando cmd)
{
  Empilhavel valor = cmd.getValor();
  Empilhavel aux1, aux2;
  String code = cmd.getCode();
  int novoIndice = this.index + 1;
  int arg;

  switch(Instrucoes.valueOf(code))
  {
    case PUSH: this.dados.push(valor); break;
    case POP : this.dados.pop(); break;
    case DUP : this.dados.push(this.dados.look()); break;
    case ADD : this.add(); break;
    case SUB : this.sub(); break;
    case DIV : this.div(); break;
    case MUL : this.mul(); break;
    case EQ  : this.eq(); break;
    case GT  : this.gt(); break;
    case GE  : this.ge(); break;
    case LT  : this.lt(); break;
    case LE  : this.le(); break;
    case NE  : this.ne(); break;
    
    case JMP : novoIndice = (int) ((Numero)valor).getVal(); break;
    
    case JIT : arg = (int) ((Numero)valor).getVal();
               novoIndice = this.jit(arg, novoIndice); break;//vai pular para o indice arg, ou continuar em novoIndice
    
    case JIF : arg = (int) ((Numero)valor).getVal();
               novoIndice = this.jif(arg, novoIndice); break;//vai pular para o indice arg, ou continuar em novoIndice
    
    case STO : int index = ((int)((Numero)valor).getVal());
               this.sto(index); break;
    
    case RCL : int index1;
               index1 = (int) ((Numero)valor).getVal();
               this.dados.push( this.mem[index1] ); break;//não remove o valor da memoria
    
    case END : novoIndice = this.prog.size(); break;
    
    case PRN : this.prn(); break;
    case ALO : this.alo(); break;
    case SET : this.set(); break;
    case GET : this.get(); break;
    case WALK:
    case COLLECT:
    case DROP:
    case ATK : this.sistema(cmd); break;
    default  : System.out.println("Não é instrução válida!");
               System.exit(1);
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

    //op.pushArg(this.dados.pop());




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
    int indR = ( (Robo)this.obj).getInd(); //Sabemos, por enquanto, que é um robô.
    Empilhavel aux1 = this.dados.pop();
    if(aux1 instanceof Numero)
    {
      System.out.println("Robo "+indR+" diz: "+((Numero)aux1).getVal());
    }
    if (aux1 instanceof Frase) 
    {
     System.out.println("Robo "+indR+" diz: "+((Frase)aux1).getString()); 
    }
  }
}

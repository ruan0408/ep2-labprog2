public class Operacao
{
	private Comando cmd;
  private Programavel origem;
  private Pilha args;


  /****** Construtor *****/


  public Operacao(Comando cmd,Programavel origem)
  {
    this.cmd = cmd;
    this.origem = origem;
  }


  /****** Getters ******/


  public Comando getCmd()
  {
    return this.cmd;
  }

  public Programavel getOrigem()
  {
    return this.origem;
  }


  /****** Funções ******/

  /* Empilha na pilha de argumentos o argumento passado como parâmetro à função. */
  public void pushArg(Empilhavel emp)
  {
    args.push(emp);
  }

  /* Retira o último membro da pilha de argumentos, o qual é retornado. */
  public Empilhavel popArg()
  {
    return args.pop();
  }

  /* Verifica se a lista de argumentos está vazia, retornando TRUE se está vazia e FALSE caso contrário. */
  public boolean temArg()
  {
    return !args.vazio();
  }
}

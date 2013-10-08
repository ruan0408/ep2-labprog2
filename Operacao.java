public class Operacao
{
	private Comando cmd;
  private Programavel origem;
  private Pilha args;


  public Operacao(Comando cmd,Programavel origem)
  {
    this.cmd = cmd;
    this.origem = origem;
  }


  public Comando getCmd()
  {
    return this.cmd;
  }

  public Programavel getOrigem()
  {
    return this.origem;
  }


  public void pushArg(Empilhavel emp)
  {
    args.push(emp);
  }

  public Empilhavel popArg()
  {
    return args.pop();
  }

  public boolean temArg()
  {
    return !args.vazio();
  }
}
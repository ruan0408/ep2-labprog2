import java.util.*;

public class Programa
{

	ArrayList<Comando> prog;


        /****** Construtor ******/


	public Programa()
	{
		prog = new ArrayList<Comando>();
	}

	public Programa(Vector<Comando> prog)
	{
		this.prog = new ArrayList<Comando>(prog);
	}

	private void dump()
	{
		Comando cmd;
		int i = 0;

		for(ListIterator<Comando> it = this.prog.listIterator(); it.hasNext();)
		{
			 cmd = it.next();
			 int arg = -1;
			 Empilhavel emp = cmd.getValor();
			 if(emp instanceof Numero) arg =(int) ((Numero)emp).getVal();
			System.out.println(i++ +": "+cmd.getCode()+" "+arg);
		}
	}


        /****** Getters ******/


	public Comando get(int i)
	{
		return prog.get(i);
	}


        /****** Funções ******/


	public void add(Comando cmd)
	{
		prog.add(cmd);
	}

	public int size()
	{
		return prog.size();
	}
}

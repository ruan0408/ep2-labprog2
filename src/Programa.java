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

import java.util.*;

public class Programa
{

	ArrayList<Comando> prog;

	public Programa()
	{
		prog = new ArrayList<Comando>();
	}

	public void add(Comando cmd)
	{
		prog.add(cmd);
	}

	public int size()
	{
		return prog.size();
	}

	public Comando get(int i)
	{
		return prog.get(i);
	}
}
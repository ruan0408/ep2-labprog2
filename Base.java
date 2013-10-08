import java.util.*;
public class Base extends Terreno
{
	private int time;
	private List<Cristal> cristais;

	Base(int time)
	{
		this.time = time;
		this.cristais = new ArrayList<Cristal>();
	}

	public boolean putCristal(Cristal cris)
	{
		return cristais.add(cris);
	}

	public int numCristais()
	{
		return cristais.size();
	}



}
import java.util.*;

public class Base extends Terreno
{
	private int time;
	private List<Cristal> cristais;


	/****** Construtor ******/


	Base(Posicao pos,int time)
	{
		super(pos);
		this.time = time;
		this.cristais = new ArrayList<Cristal>();
	}


	/****** Funções ******/


	/* Insere um cristal na lista de cristais de uma Base. */
	public boolean putCristal(Cristal cris)
	{
		return cristais.add(cris);
	}

	/* Retorna o número de cristais de uma Base. */
	public int numCristais()
	{
		return cristais.size();
	}
}

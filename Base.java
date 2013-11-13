/* Classe que representa a base de algum time de robos */

import java.util.*;

public class Base extends Terreno
{
	private Time time;
	private List<Cristal> cristais;


	/****** Construtor ******/


	Base(Posicao pos,int time)
	{
		super(pos);
		this.time = new Time(time,this);
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

	public Time getTime()
	{
		return this.time;
	}
}

/* Classe que representa endereço de uma variável local */

public class Endereco implements Empilhavel
{
	int end;


	/****** Construtor ******/


	public Endereco(int end)
	{
		this.end = end;
	}


	/****** Getters ******/


	public int get()
	{
		return this.end;
	}


	/****** Setters ******/


	public void set(int end)
	{
		this.end = end;
	}


}

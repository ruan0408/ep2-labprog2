import java.lang.String;

public class Frase implements Empilhavel
{
	private String frase;


	/****** Construtor ******/


	Frase(String frase)
	{
		this.frase = frase;
	}


	/****** Getters ******/


	public String getString()
	{
		return this.frase;
	}



}

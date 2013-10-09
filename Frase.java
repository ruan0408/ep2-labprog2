import java.lang.String;

public class Frase implements Empilhavel
{
	private String frase;

	Frase(String frase)
	{
		this.frase = frase;
	}

	public String getString()
	{
		return this.frase;
	}
}
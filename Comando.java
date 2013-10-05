public class Comando
{
	private String code;
	private Numero valor;

	Comando(String code, Numero valor)
	{
		 this.code = code;
		 this.valor = valor;
	}

	void setCode(String codigo)
	{
		this.code = codigo;
	}
	
	void setValor(Numero valor)
	{
		this.valor = valor;
	}
	
	String getCode()
	{
		return this.code;
	}
	
	Numero getValor()
	{
		return this.valor;
	}
}

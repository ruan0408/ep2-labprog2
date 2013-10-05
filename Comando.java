public class Comando
{
	private String code;
	private Empilhavel valor;

	Comando(String code, Empilhavel valor)
	{
		 this.code = code;
		 this.valor = valor;
	}

	void setCode(String codigo)
	{
		this.code = codigo;
	}
	
	void setValor(Empilhavel valor)
	{
		this.valor = valor;
	}
	
	String getCode()
	{
		return this.code;
	}
	
	Empilhavel getValor()
	{
		return this.valor;
	}
}

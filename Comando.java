public class Comando
{
	private String code;
	private String valor;

	Comando(String code, String valor)
	{
		 this.code = code;
		 this.valor = valor;
	}

	void setCode(String codigo)
	{
		this.code = codigo;
	}
	
	void setValor(String valor)
	{
		this.valor = valor;
	}
	
	String getCode()
	{
		return this.code;
	}
	
	String getValor()
	{
		return this.valor;
	}
}

public class Comando
{
	private String code;
	private Empilhavel valor;

	public Comando(String code, Empilhavel valor)
	{
		 this.code = code;
		 this.valor = valor;
	}

	public void setCode(String codigo)
	{
		this.code = codigo;
	}
	
	public void setValor(Empilhavel valor)
	{
		this.valor = valor;
	}
	
	public String getCode()
	{
		return this.code;
	}
	
	public Empilhavel getValor()
	{
		return this.valor;
	}
}

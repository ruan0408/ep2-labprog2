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


	/*
		Função que recebe uma string e 
		retorna TRUE caso o código do comanbdo seja
		igual a string. Retorna FALSE c.c.
	*/
	public boolean codeEquals(String str)
	{
		return this.code.equals(str);
	}
	
	public Empilhavel getValor()
	{
		return this.valor;
	}
}

public class Comando
{
	private String code;
	private Empilhavel valor;


	/****** Construtor ******/


	public Comando(String code, Empilhavel valor)
	{
		 this.code = code;
		 this.valor = valor;
	}


	/****** Getters ******/	


	public String getCode()
	{
		return this.code;
	}
	
	public Empilhavel getValor()
	{
		return this.valor;
	}


	/****** Setters ******/


	public void setCode(String codigo)
	{
		this.code = codigo;
	}
	
	public void setValor(Empilhavel valor)
	{
		this.valor = valor;
	}


	/****** Funções ******/


	/*  Função que recebe uma string e 
	    retorna TRUE caso o código do comanbdo seja
	    igual a string. Retorna FALSE c.c.             */
	public boolean codeEquals(String str)
	{
		return this.code.equals(str);
	}

	public String toString()
	{
		String s = null;

		if(codeEquals("WALK")) s += "Andou para ";
		else if (codeEquals("COLLECT")) s += "Coletou cristal à ";	
		else if (codeEquals("DROP")) s += "Dropou cristal à ";		
		else if (codeEquals("ATK")) s += "Atacou à ";
		else System.out.println("Não é syscall!!");

		//if(valor != null)
		//{
		if(valor instanceof Numero)
		{
			double aux = ((Numero)valor).getVal();
			switch((int)aux)
			{
				case 1: s+="cima";break;
				case 2: s+="direita superior";break;
				case 3: s+="direita inferior";break;
				case 4: s+="baixo";break;
				case 5: s+="esquerda inferior";break;
				case 6: s+="esquerda superior";break;
				default : System.out.println("Direção inválida!");
			}
			
		}
		else System.out.println("Isso não deveria acontecer!");
			/*else if(valor instanceof Endereco)
			{
				s+=((Endereco)valor).get();
			}
			else if(valor instanceof Frase)
			{
				s+=((Frase)valor).getString();
			}
			else if(valor instanceof Terreno)
			{
				//ainda não implementado
			}
		}*/
		return s;
	}
}

public class Deposito extends Terreno
{
	private Cristal cristal;


	/****** Construtor ******/


	Deposito(Posicao pos)
	{
		super(pos);
		this.cristal = null;
	}


	/****** Funções ******/	


	/* Retorna TRUE caso o Deposito possua um Cristal. */
	public boolean temCristal()
	{
		return this.cristal != null;
	}

	/* Insere um Cristal no Deposito. */
	public void putCristal(Cristal cristal)
	{	
		this.cristal = cristal;
	}

	/* Remove o cristal do Deposito, retornando o mesmo. */
	public Cristal popCristal()
	{
		Cristal crisTemp = this.cristal;
		this.cristal = null;
		return crisTemp;
	}
}

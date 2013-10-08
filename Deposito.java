public class Deposito extends Terreno
{
	private Cristal cristal;

	Deposito()
	{
		this.cristal = null;
	}

	public boolean temCristal()
	{
		return this.cristal != null;
	}

	public void putCristal(Cristal cristal)
	{	
		this.cristal = cristal;
	}

	public Cristal getCristal()
	{
		return this.cristal;
	}

	public void removeCristal()
	{
		if(this.temCristal()) 
			this.cristal = null;
	}

}
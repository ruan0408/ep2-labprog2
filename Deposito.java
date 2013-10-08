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

	public Cristal popCristal()
	{
		Cristal crisTemp = this.cristal;
		this.cristal = null;
		return crisTemp;
	}

}
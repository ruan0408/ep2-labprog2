public class Terreno
{
	Robo robo;

	Terreno()
	{
		this.robo = null;
	}
	
	public boolean temRobo()
	{
		if(robo != null) return true;
		return false;
	}

	public void putRobo(Robo roboTemp)
	{
		this.robo = roboTemp;
	}

	public Robo getRobo()
	{
		return this.robo;
	}

	/* Remove o robo desse terreno */

	public void removeRobo()
	{
		this.robo = null;
	}

}
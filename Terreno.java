public class Terreno
{
	Robo robo;


 /****** Construtor ******/


	Terreno()
	{
		this.robo = null;
	}
	


	/****** Getters ******/

	public Robo getRobo()
	{
		return this.robo;
	}

	/****** Funções ******/

	/* Remove o robo desse terreno */
	public void removeRobo()
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
}

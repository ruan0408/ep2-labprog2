public class Terreno
{
	Robo robo;
	Posicao pos;


 /****** Construtor ******/


	Terreno(Posicao pos)
	{
		this.pos = pos;
		this.robo = null;
	}
	


	/****** Getters ******/

	public Robo getRobo()
	{
		return this.robo;
	}

	public int getX()
	{
		return this.pos.getX();

	}

	public int getY()
	{
		return this.pos.getY();

	}

	public Posicao getPos()
	{
		return this.pos;

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

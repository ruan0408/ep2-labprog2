import java.util.Vector;

public class Terreno implements Empilhavel
{
	Robo robo;
	Posicao pos;
	public Vector<Programavel> programaveis;
	int bomba;

 	/****** Construtor ******/


	Terreno(Posicao pos)
	{
		this.pos = pos;
		this.robo = null;
		this.bomba = -1;
		this.programaveis = new Vector<Programavel>(0);
	}	


	/****** Getters ******/

	public boolean bombExploded()
	{
		return this.bomba == 0;
	}
	public boolean hasBomb()
	{
		return this.bomba >= 0;
	}

	public void setBomb()
	{
		this.bomba = 5;
	}

	public void setNoBomb()
	{
		this.bomba = -1;
	}

	public void decTimerBomb()
	{
		this.bomba -= 1;
	}

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
	public Robo removeRobo()
	{
		Robo roboTemp = this.robo;
		this.robo = null;
		return roboTemp;
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

	public boolean eRugoso()
	{
		return (this instanceof Rugoso);
	}

	public boolean eLiso()
	{
		return (this instanceof Liso);
	}

	public boolean eAgua()
	{
		return (this instanceof Agua);
	}

	public boolean eDeposito()
	{
		return (this instanceof Deposito);
	}

	public Deposito toDeposito()
	{
		return (Deposito)this;
	}

	public boolean eBase()
	{
		return (this instanceof Base);
	}

	public Base toBase()
	{
		return (Base)this;
	}

	public void addProgramavel(Programavel prg)
	{
		this.programaveis.add(prg);
	}

	public void removeProgramavel(Programavel prg)
	{
		this.programaveis.remove(prg);
	}
}

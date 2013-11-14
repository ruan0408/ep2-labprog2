public class Time implements Empilhavel
{
	int time;
	int pontos;
	Base base;

	public Time(int team, Base base)
	{
		this.time = team;
		this.pontos = 0;
		this.base = base;
	}

	public int getId()
	{
		return this.time;
	}

	public Base getBase()
	{
		return this.base;
	}

	public int getPontos()
	{
		return this.pontos;
	}

	public void ganhaPonto()
	{
		this.pontos += 1;
	}
}
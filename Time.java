public class Time
{
	int time;
	Base base;

	public Time(int team, Base base)
	{
		this.time = team;
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
}
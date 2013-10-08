public class Atraso
{
	private int temp;
	private Operacao op;

	public Atraso(Operacao op, int temp)
	{
		this.op = op;
		this.temp = temp;
	}

	public Operacao getOperacao()
	{
		return this.op;
	}

	public int getTempo()
	{
		return this.temp;
	}

	public void somaTempo(int i)
	{
		this.temp += i;
	}

}
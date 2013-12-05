import java.util.*;

public class Vetor
{
	Empilhavel[] vet;
	public Vetor(int n)
	{
		vet = new Empilhavel[n];
	}

	public void set(Empilhavel emp, int index)
	{
		if((this.vet.length) <= index)
	 		this.vet = Arrays.copyOf(this.vet, index+1);

		this.vet[index] = emp;
	}

	public int length()
	{
		return vet.length;
	}

	public Empilhavel get(int index)
	{
		return vet[index];
	}
}
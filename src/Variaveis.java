import java.util.*;

public class Variaveis
{

	private Empilhavel[] mem;
	private int freeIndex;
	private int tam;


        /****** Construtor ******/


	public Variaveis()
	{
		mem = new Empilhavel[10];
		freeIndex = 0;
		tam = 10;
	}

	public Variaveis(int tam)
	{
		if(tam > 0)
		{
			mem = new Empilhavel[tam];
			freeIndex = 0;
			this.tam = tam;
		}
		else throw new IllegalArgumentException("Tamanho ilegal passado para alocar memória de variáveis.");
	}


        /****** Getters ******/


	public Empilhavel get(Endereco end)
	{
		return mem[end.get()];
	}


        /****** Setters ******/


	public void set(Endereco end, Empilhavel val)
	{
		mem[end.get()] = val;
	}


        /****** Funções ******/


	/*Retorna um novo endereco de um local livre na memória (preenchido com um número) */
	public Endereco aloc()
	{
		Endereco end = new Endereco(freeIndex);
		mem[freeIndex] = new Numero(0);
		this.proxFreeIndex();
		return end;
	}

	/* Define como null o lugar dessa variavel, e posiciona o freeIndex para
	o próximo local livre. */

	public void free(Endereco end)
	{
		int index = end.get();
		mem[index] = null;
		proxFreeIndex();
	}

	/*
	Retorna a próxima posição livre do vetor.
	Aumenta o vetor caso não exista tal posição.
	*/

	private void proxFreeIndex()
	{
		while(freeIndex < tam && mem[freeIndex] != null) freeIndex++;
		if(freeIndex == tam)
		{ 
			this.dobraTamanho();
			freeIndex++;
		}
	}

	private void dobraTamanho()
	{
		tam *=2;
		mem = Arrays.copyOf(mem, tam);
	}

}

/***
Classe que representa uma instrução atrasada em um Robô.

Exemplo: Robo tenta andar em um terreno rugoso. Ele demorará mais para completar essa ação do que o usual.
***/

public class Atraso
{
	private int temp;
	private Operacao op;


	/****** Construtor ******/


	public Atraso(Operacao op, int temp)
	{
		this.op = op;
		this.temp = temp;
	}


	/****** Getters ******/


	public Operacao getOperacao()
	{
		return this.op;
	}

	public int getTempo()
	{
		return this.temp;
	}


	/****** Funções ******/


	/* Função que incrementa o atraso em 'i' unidades.*/
	public void somaTempo(int i)
	{
		this.temp += i;
	}

}

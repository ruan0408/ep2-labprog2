/* Referencia usada para visualização da numeração
do mapa: 

http://www.codeproject.com/Articles/14948/Hexagonal-grid-for-games-and-other-projects-Part-1

*/


public class Mapa
{
	int altura, largura;
	Terreno[][] matriz;


	/*

		Retorna uma matriz exagonal de altura "alt" e
		largura "larg".

	*/
	public Mapa(int alt, int larg)
	{
		matriz = new Terreno[alt][larg];
		altura = alt;
		largura = larg;


		for(int i = 0; i < alt; i++)
		{
			for(int j = 0; j < larg; j++)
			{
				matriz[i][j] = new Terreno();
			}
		}
	}


	public boolean putRobo(Robo roboTemp, int x, int y)
	{
		if(x < largura && y < altura && y > 0 && x > 0)
		{
			if(!matriz[x][y].temRobo())
			{
				matriz[x][y].putRobo(roboTemp);
				return true;
			}
		}

		return false;
	}

	public void removeRobo(int x, int y)
	{
		if(x < largura && y < altura && y > 0 && x > 0)
		{
			matriz[x][y].removeRobo();
		}
	}


/* *************** Falta o tratamento de erros ************ */


/* ************************************
	Funções que retornam o terreno de uma dada posição (i,j) e
	dos hexagonos adjacentes

	*********************** */

	/* Retorna o terreno na posição i,j */
	public Terreno get(int i, int j)
	{
		return matriz[i][j];
	}

	public Terreno getUpLeft(int i, int j)
	{
		if(j%2 == 1) // j é impar
		{
			return matriz[i][j-1];
		}
		else
		{
			return matriz[i-1][j-1];
		}
	}

	public Terreno getUpRigh(int i, int j)
	{
		if(j%2 == 1) // j é impar
		{
			return matriz[i][j+1];
		}
		else
		{
			return matriz[i-1][j+1];
		}
	}


	public Terreno getDownLeft(int i, int j)
	{
		if(j%2 == 1) // j é impar
		{
			return matriz[i][j-1];
		}
		else
		{
			return matriz[i+1][j-1];
		}
	}

	public Terreno getDownRigh(int i, int j)
	{
		if(j%2 == 1) // j é impar
		{
			return matriz[i][j+1];
		}
		else
		{
			return matriz[i+1][j+1];
		}
	}

	public Terreno getDown(int i, int j)
	{
		return matriz[i-1][j];
	}

	public Terreno getUp(int i, int j)
	{
		return matriz[i+1][j];
	}

}

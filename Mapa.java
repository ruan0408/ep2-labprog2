/* Referencia usada para visualização da numeração
do mapa: 

http://www.codeproject.com/Articles/14948/Hexagonal-grid-for-games-and-other-projects-Part-1

*/


public class Mapa
{
	int altura, largura;
	Terreno[][] matriz;
	

	/****** Construtor ******/


	/*  Retorna uma matriz hexagonal de altura "alt" e 
	    largura "larg".                                 */
	public Mapa(int alt, int larg)
	{
		matriz = new Terreno[alt][larg];
		altura = alt;
		largura = larg;

		for(int i = 0; i < alt; i++)
			for(int j = 0; j < larg; j++)
				matriz[i][j] = new Terreno(new Posicao(i,j));
	}


/* ************************************
	Funções que retornam o terreno de uma dada posição (i,j) e
	dos hexagonos adjacentes.

	*********************** */

	public boolean existeTerreno(int i, int j)
	{
		return (i > 0 && i < altura && j > 0 && j < largura);
	}

	/* Retorna o terreno na posição i,j */
	public Terreno getTerreno(int i, int j)
	{
		if(!existeTerreno(i,j)) throw new ArrayIndexOutOfBoundsException();
		return matriz[i][j];
	}

	public Terreno getTerreno(Posicao pos)
	{
		if(!existeTerreno(pos.getX(),pos.getY())) throw new ArrayIndexOutOfBoundsException();
		return matriz[pos.getX()][pos.getY()];
	}

	/* Região superior esquerda. */
	public Terreno getUpLeft(int i, int j)
	{
		if(j%2 == 1)// j é impar
		{
			if(!existeTerreno(i,j-1)) throw new ArrayIndexOutOfBoundsException();
			return matriz[i][j-1]; 
		} 
		else // j é par	
		{
			if(!existeTerreno(i-1,j-1)) throw new ArrayIndexOutOfBoundsException();
			return matriz[i-1][j-1]; 
		}
	}

	/* Região superior direita. */
	public Terreno getUpRight(int i, int j)
	{

		if(j%2 == 1) 
		{
			if(!existeTerreno(i,j+1)) throw new ArrayIndexOutOfBoundsException();
			return matriz[i][j+1];
		} 
		else
		{
			if(!existeTerreno(i-1,j+1)) throw new ArrayIndexOutOfBoundsException();
			return matriz[i-1][j+1];
		} 
	}
	
	/* Região inferior esquerda. */
	public Terreno getDownLeft(int i, int j)
	{
		if(j%2 == 0) 
		{
			if(!existeTerreno(i,j-1)) throw new ArrayIndexOutOfBoundsException();
			return matriz[i][j-1];
		}
		else
		{ 

			if(!existeTerreno(i+1,j-1))throw new ArrayIndexOutOfBoundsException();
			return matriz[i+1][j-1]; // j é par
		}
	}

	/* Região inferior direita. */
	public Terreno getDownRight(int i, int j)
	{
		if(j%2 == 0) 
		{
			if(!existeTerreno(i,j+1)) throw new ArrayIndexOutOfBoundsException();
			return matriz[i][j+1]; // j é impar
		}
		else 
		{
			if(!existeTerreno(i+1,j+1)) throw new ArrayIndexOutOfBoundsException();
			 return matriz[i+1][j+1]; // j é par
		}
	}
	
	/* Região imediatamente inferior. */
	public Terreno getDown(int i, int j)
	{
		if(!existeTerreno(i-1,j))throw new ArrayIndexOutOfBoundsException();
		return matriz[i-1][j];
	}

	/* Região imediatamente superior. */
	public Terreno getUp(int i, int j)
	{
		if(!existeTerreno(i+1,j))throw new ArrayIndexOutOfBoundsException();
		return matriz[i+1][j];

	}


	/****** Funções ******/


	/* Função que recepe um robô e o insere no mapa. */
	public boolean putRobo(Robo roboTemp)
	{
		/* Encontra as posições X e Y do robô. */
		int x = roboTemp.getX();
		int y = roboTemp.getY();
		
		/* Caso seja possível (a posição do robô não esteja ocupada e nem se encontre
		   fora dos limites do mapa, inserimos o robô no mapa e retornamos TRUE. É  
		   retornado o valor FALSE caso contrário.                                    */
		if(x < largura && y < altura && y >= 0 && x >= 0)
		{
			if(!matriz[x][y].temRobo())
			{
				matriz[x][y].putRobo(roboTemp);
				return true;
			}
		}
		return false;
	}

	/* Função que receberá uma posição e removerá o robô localizado nesta posição. */
	public void removeRobo(int x, int y)
	{
		if(x < largura && y < altura && y > 0 && x > 0)
			matriz[x][y].removeRobo();
	}
}

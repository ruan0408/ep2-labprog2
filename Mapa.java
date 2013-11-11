import java.io.*;
import java.util.*;

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

	public Mapa(String arquivo)
	{
		String linTemp;
		int[][] mapaInt = null;

		 try
		 {

		 	 /* Abertura de arquivo. Fonte: http://www.roseindia.net/java/beginners/java-read-file-line-by-line.shtml */
			 // Open the file that is the first 
			 // command line parameter
			 FileInputStream fstream = new FileInputStream(arquivo);
			 // Get the object of DataInputStream
			 DataInputStream in = new DataInputStream(fstream);
			 BufferedReader br = new BufferedReader(new InputStreamReader(in));

			 /* ************* */


			 linTemp = br.readLine(); // Primeira linha tem as dimensões do mapa
		     String[] dimS = linTemp.split(" "); 
		     if(dimS.length < 2)  throw new IllegalArgumentException("Arquivo do mapa no formato errado"); // Arquivo no formato errado.

		     // Construção da matriz que vai definir o mapa

		     this.altura = Integer.parseInt(dimS[0]);
		     this.largura = Integer.parseInt(dimS[1]);

		     mapaInt = new int[altura][];

		     for(int i = 0; i < altura; i++)
		     	mapaInt[i] = traduzLinha(br.readLine(), largura);


		 		//Close the input stream
				in.close();
			}
			catch (Exception e)
			{//Catch exception if any
				System.err.println("Error: " + e.getMessage());
				System.exit(-1);
			}


		     // Construção do mapa em si

		     this.matriz = new Terreno[altura][largura];

		     for(int i = 0; i < altura; i++)
		     	for(int j = 0; j < largura; j++)
		     	{
		     		Posicao pos = new Posicao(i,j);
		     		switch(mapaInt[i][j])
		     		{
		     			case 0: this.matriz[i][j] = new Liso(pos); break;
		     			case 1: this.matriz[i][j] = new Rugoso(pos); break;
		     			case 2: this.matriz[i][j] = new Agua(pos); break;
		     			case 3: this.matriz[i][j] = new Deposito(pos); 
		     					this.matriz[i][j].toDeposito().putCristal(new Cristal(i,j));
		     					break;
		     			default: 
		     				if(mapaInt[i][j] < 0){
		     					this.matriz[i][j] = new Base(pos, Math.abs(mapaInt[i][j]));
						 	}
						 	else { 
								this.matriz[i][j] = new Liso(pos);
							}

		     		}
		     	}






		



	}

	private int[] traduzLinha(String linha, int tam)
	{
		String[] nums = linha.split(" ");
		int[] resp = new int[tam];
		for(int i = 0; i < tam; i++)
				resp[i] = Integer.parseInt(nums[i]);

		return resp;
	}

	//Construtor de mapa predefinido
	public Mapa()
	{
		/*{ 0, 0, 0, 1, 2, 2, 2, 2, 1, 1},
		 { 0, 0, 1, 1, 2, 2, 2, 2, 1, 1},
		{ 0, 0, 1, 2, 2, 2, 0, 2, 1, 1},
		 { 0, 0, 1, 1, 1, 2, 2, 2, 2, 2},
		{ 0, 0, 0, 0, 1, 2, 2, 2, 2, 2},
		 { 0, 0, 0, 1, 0, 2, 2, 2, 2, 2},
		{ 0, 0, 1, 1, 0, 0, 0, 2, 2, 1},
		 { 0, 0, 1, 1, 0, 0, 2, 2, 2, 1},
		{ 1, 1, 1, 1, 0, 0, 0, 1, 1, 1 },
		 { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}*/

		matriz = new Terreno[10][10];
		altura = 10;
		largura = 10;

		for(int i = 0; i < altura; i++)
			for(int j = 0; j < largura; j++)
				if(i > j+1)
					matriz[i][j] = new Rugoso(new Posicao(i,j));
				else if(j > i+1)
					matriz[i][j] = new Liso(new Posicao(i,j));
				else //if(i == j)
					matriz[i][j] = new Agua(new Posicao(i,j));
				

		//matriz[0][0] = new Base(new Posicao(0, 0), 1);
		//matriz[9][9] = new Base(new Posicao(9, 9), 2);
	}

	public int altura()
	{
		return this.altura;
	}

	public int largura()
	{
		return this.largura;
	}


/* ************************************
	Funções que retornam o terreno de uma dada posição (i,j) e
	dos hexagonos adjacentes.

	*********************** */

	public boolean existeTerreno(int i, int j)
	{
		return (i >= 0 && i < altura && j >= 0 && j < largura);
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
		if(!existeTerreno(i+1,j))throw new ArrayIndexOutOfBoundsException();
		return matriz[i+1][j];
	}

	/* Região imediatamente superior. */
	public Terreno getUp(int i, int j)
	{
		if(!existeTerreno(i-1,j))throw new ArrayIndexOutOfBoundsException();
		return matriz[i-1][j];

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

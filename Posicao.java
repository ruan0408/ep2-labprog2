import java.util.*;

public class Posicao
{
	private int x,y;


        /****** Construtor ******/


	public Posicao(int x, int y)
	{
		this.x = x;
		this.y = y;
	}


        /****** Getters ******/


	public int getX()
	{
		return this.x;
	}


	public int getY()
	{
		return this.y;
	}



	/***
	Aleatoria as cordenadas da posição, sendo que os atributos irão receber:

	x = inteiro aleaório entre 0 e maxX
	y = inteiro aleaório entre 0 e maxY

	****/

	public void randXY(int maxX, int maxY)
	{
		this.x = (int) (maxX*Math.random());
		this.y = (int) (maxY*Math.random());
	}


        /****** Setters ******/


	public void setX(int v)
	{
		this.x = v;
	}


	public void setY(int v)
	{
		this.y = v;
	}
}

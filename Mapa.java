public class Mapa
{
	int altura, largura;
	Terreno[][] matriz;


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



}

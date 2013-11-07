public class Vizinhanca implements Empilhavel
{
	private Terreno [] viz;
	
	public Vizinhanca(Mapa mapa, Posicao pos)
	{
		viz = new Terreno [6];
		int x = pos.getX();
		int y = pos.getY();

		for(int i = 0; i < 6; i++)
		{
			try
			{			
				switch(i)
				{
					case 0 : viz[i] = mapa.getUp(x, y); break;
					case 1 : viz[i] = mapa.getUpRight(x, y); break;
					case 2 : viz[i] = mapa.getDownRight(x, y); break;
					case 3 : viz[i] = mapa.getDown(x, y); break;
					case 4 : viz[i] = mapa.getDownLeft(x, y); break;
					case 5 : viz[i] = mapa.getUpLeft(x, y);
					default:
				}
			}
			catch(ArrayIndexOutOfBoundsException e){viz[i] = null;}
		}
	}

	Terreno[] getVizinhanca()
	{
		return this.viz;
	}
}
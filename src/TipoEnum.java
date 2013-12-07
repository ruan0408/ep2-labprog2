public enum TipoEnum
{
	AGUA(0), LISO(1), RUGOSO(2), DEPOSITO(3), ROBO(4), CRISTAL(5), TERRENO(6), NUMERO(7);

	private final int id; 
    private TipoEnum(int id) 
    {
        this.id = id;
    }

    public int id()
    {
    	return this.id;
    }

    public static TipoEnum getTerr(int id)
    {
        return TipoEnum.values()[id];
    	/*switch(id)
    	{
    		case 0: return AGUA;
    		case 1: return LISO;
    		case 2: return RUGOSO;
    		default: return RUGOSO;
    	}*/
    }
}
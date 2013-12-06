public enum TerrEnum{
	AGUA(0), LISO(1), RUGOSO(2);

	private final int id; 
    TerrEnum(int id) {
        this.id = id;
    }

    public int id()
    {
    	return this.id;
    }

    public static TerrEnum getTerr(int id)
    {
    	switch(id)
    	{
    		case 0: return AGUA;
    		case 1: return LISO;
    		case 2: return RUGOSO;
    		default: return RUGOSO;
    	}
    }
}
public enum Direcao
{
	UP(1), UR(2), DR(3), DW(4), DL(5), UL(6);

	private final int value;

    private Direcao(int value) 
    {
        this.value = value;
    }
    public int getValue() 
    {
    	return value;
    }

    public static Direcao toDirecao(int i)
    {
    	return Direcao.values()[i-1];
    }
}
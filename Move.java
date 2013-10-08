public enum Move
{
	UP(1), UR(2), DR(3), DW(4), DL(5), UL(6);

	private int move;

	Move(int move)
	{
		this.move = move;
	}

	public int getVal()
	{
		return this.move;
	}
}
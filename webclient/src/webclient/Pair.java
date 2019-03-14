package webclient;

public class Pair<t,s> {
	private t left;
	private s right;
	Pair(t left,s right)
	{
		this.left=left;
		this.right=right;
	}
	public t getLeft()
	{
		return left;
	}
	public s getRight()
	{
		return right;
	}

}

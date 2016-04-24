
class Cell
{
	private int rCoord;
	private int cCoord;
	private char c;

	// -------------------------------------------------------------
	public Cell(char marker,int rC, int cC)               // constructor
	{
		c = marker;
		rCoord = rC;
		cCoord = cC;
	}
	//--------------------------------------------------------------	
	public int getR()
	{
		return rCoord;
	}
	//--------------------------------------------------------------	
	public int getC()
	{
		return cCoord;
	}
	//--------------------------------------------------------------	
	public char getLabel()
	{
		return c;   
	}
	// -------------------------------------------------------------
	public void setLabel(char newLabel){
		c = newLabel;
	}
	// -------------------------------------------------------------


}  // end class Student

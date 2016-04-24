//generic stack class
//use for Stack of visited nodes

public class StackX {
	private int currentSize = 0;
	private Cell[] st;
	private int top;
	//------------------------------------------------------------
	public StackX(int Size)           // constructor
	{
		st = new Cell[Size];    // make array
		top = -1;
	}
	//------------------------------------------------------------
	public void push(Cell j)   // put item on stack
	{ 
		st[++top] = j;
		currentSize++;
	}
	//------------------------------------------------------------
	public Cell pop()          // take item off stack
	{ 	
		currentSize--;
		return st[top--]; 
	}
	//------------------------------------------------------------
	public Cell peek()         // peek at top of stack
	{ return st[top]; }
	//------------------------------------------------------------
	public boolean isEmpty()  // true if nothing on stack
	{ return (top == -1); }
	//------------------------------------------------------------
	public int getSize(){
		return currentSize;
	}
}

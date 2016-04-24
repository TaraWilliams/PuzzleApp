import java.util.Scanner;

public class Maze {

	String phrase;
	boolean hasPlus=false; // specifically for the valid neighbor list but referenced elsewhere
	Cell[] validNeighbors = new Cell[8];					// to make new list of valid neighbors	
	//2D Array Constructor	
	//Set Phrase
	//Set Letter
	//Set Invalid/Null

	private Cell [][] Cells;


	public Maze(int rowNum, int columnNum) //constructor
	{
		Cells = new Cell[rowNum][columnNum];
		for (int r = 0; r < rowNum; r++)			//Loop to create "blank" graph/puzzle spaces
		{
			for (int c = 0; c < columnNum; c++)
			{
				Cells[r][c] = new Cell('*',r,c);
			}
		}
	}

	//------------------------------------------------------------
	public Cell[] getNeighbors(int r, int c){

		//Lists every neighbor of cell at coordinates r,c
		Cell[] neighborList = new Cell[8];

		int k = 0;

		if(Cells[r+1][c+1]!=null){
			neighborList[k]=Cells[r+1][c+1];
			k++;
		}
		if(Cells[r+1][c-1]!=null){
			neighborList[k]=Cells[r+1][c-1];
			k++;
		}
		if(Cells[r-1][c-1]!=null){
			neighborList[k]=Cells[r-1][c-1];
			k++;
		}
		if(Cells[r-1][c+1]!=null){
			neighborList[k]=Cells[r-1][c+1];
			k++;
		}
		if(Cells[r+1][c]!=null){
			neighborList[k]=Cells[r+1][c];
			k++;
		}
		if(Cells[r-1][c]!=null){
			neighborList[k]=Cells[r-1][c];
			k++;
		}
		if(Cells[r][c+1]!=null){
			neighborList[k]=Cells[r][c+1];
			k++;
		}
		if(Cells[r][c-1]!=null){
			neighborList[k]=Cells[r][c-1];
			k++;
		}
		return neighborList;
	}

	//------------------------------------------------------------
	public Cell[] getValidNeighbors(int r, int c){
		//check neighbor list until one has a '+'
		hasPlus = false;
		int k=0;
		int j=0;
		Cell[] neighborList = getNeighbors(r, c);		// Use list of all neighbors

		while(k<neighborList.length)
		{
			if(neighborList[k].getLabel() == '+')
			{
				validNeighbors[j]=neighborList[k];
				j++;
				hasPlus=true;
			}
			k++;
		}

		return validNeighbors;
	}
	// -------------------------------------------------------------
	public void setPhrase(String ph){
		phrase = ph;
	}
	//------------------------------------------------------------
	public void setValidCells(){
		//Scan and set "valid" spaces
		Scanner coordInput = new Scanner(System.in);
		System.out.println("Type each set of coordinates you want to have included, one line at a time, separated by a comma");
		System.out.println("Example:' 1,1 '");

		int numLoc = 0;
		while(numLoc<phrase.length()){ //until the number of locations is equal to the number of letters in the phrase

			String thing = coordInput.next();
			int spaceLoc = thing.indexOf(',');
			String thingR = thing.substring(0, spaceLoc);
			String thingC = thing.substring(spaceLoc+1);

			int r = Integer.parseInt(thingR); // set r coordinate
			int c = Integer.parseInt(thingC); // set c coordinate
			Cells[r][c].setLabel('+');    // use those coordinates to set label at that location to '+'
			numLoc++;
		} // end while

		//coordInput.close();
	}

	//-------------------------------------------------------------

	public void go(int startR,int startC){

		StackX stack = new StackX(phrase.length());
		Cells[startR][startC].setLabel(phrase.charAt(stack.getSize()+1));
		stack.push(Cells[startR][startC]);
		displayMaze();
		solve(this, stack);

	}
	//------------------------------------------------------------
	public Boolean solve(Maze maze, StackX stack){

		//get neighbor of top (getValidNeighbors(top coordinates)[0])
		//use first one, push onto stack and change that label to phrase.charAt(stack.getSize)
		int k = 0;	
		Cell curr = stack.peek();
		Cell[] currNeigh = getValidNeighbors(curr.getR(), curr.getC());
		Cell currNextNeigh = currNeigh[k];
		char currLetter = phrase.charAt(stack.getSize());
		


		if(stack.getSize()<phrase.length() && hasPlus==false){ //currNeigh does not have a +
			return false;
		}
		else if(stack.getSize()==phrase.length() && !isInMaze('+')){
			return true;
		}
		else{		
			stack.push(currNextNeigh);
			while(currNeigh.length>k){
				if (solve(this,stack)){
					currNextNeigh.setLabel(currLetter);
					displayMaze();
					return true;
				}
				else if(k<currNeigh.length){
					displayMaze();

					k++;
					currNextNeigh = currNeigh[k];
				}
			}
			return false;
		}
	}
	//------------------------------------------------------------
	public Boolean isInMaze(char searchKey){
		for (int i = 0; i < Cells.length; i++) {
			for (int j = 0; j < Cells.length; j++) {
				if (Cells[i][j].getLabel()==searchKey)
					return true;
			}
		}

		return false;
	}
	//------------------------------------------------------------
	public void displayMaze(){
		System.out.println("Current Graph:");   
		for (int i = 0; i < Cells.length; i++) {
			System.out.print("|");                       
			int j; 
			for (j = 0 ; j < Cells[i].length - 1; j++) {

				System.out.print(Cells[i][j].getLabel()+"|");   
			}
			System.out.print(Cells[i][j].getLabel()+"|");         
			if(i!=Cells.length -1){                   
				System.out.println("");                
			}
		}
		System.out.println("");
	}
	//------------------------------------------------------------



	//	public int getAdjUnvisitedVertex(int v)
	//	   {
	//	   for(int j=0; j<nVerts; j++)
	//	      if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
	//	         return j;
	//	   return -1;
	//	   }  // end getAdjUnvisitedVertex()

}

//Search method or class
//Check adjacency - choose an unmarked cell
//mark it and add to stack
//if all are marked, pop one to backtrack one letter
//done when "unvisited && not null" cells = 0 and spaces visited = phrase.length

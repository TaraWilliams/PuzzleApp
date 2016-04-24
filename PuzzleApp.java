import java.util.Scanner;

public class PuzzleApp {
	public static void main(String[] args){

		//Get input here
		//Start cell?
		//Pre-set letters?
		//solve(go)
		
		Maze testMaze = new Maze(8,8);
		testMaze.displayMaze();

		//Scan and set phrase
			Scanner phraseInput = new Scanner(System.in);
			System.out.println("Type your phrase (skip spaces)");
			testMaze.setPhrase(phraseInput.next());
			

		//Scan and set "valid" spaces
			testMaze.setValidCells();
			testMaze.displayMaze();

		//Set start coordinates
			System.out.println("What coordinates do you want to start at?");	//prompt
			Scanner startInput = new Scanner(System.in);						//scan
			String thing = startInput.nextLine();
			int spaceLoc = thing.indexOf(',');								//get index of comma
			String thingR = thing.substring(0, spaceLoc);					//use index of comma to separate numbers
			String thingC = thing.substring(spaceLoc+1);
			
			int r = Integer.parseInt(thingR); // set r coordinate
			int c = Integer.parseInt(thingC); // set c coordinate
		
		//go
			testMaze.go(r, c);
		
		phraseInput.close();
		startInput.close();
		
	
	} // end main
} // end class

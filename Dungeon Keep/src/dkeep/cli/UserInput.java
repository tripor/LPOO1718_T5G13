package dkeep.cli;

import java.util.Scanner;

public class UserInput {
	
	Scanner s = new Scanner(System.in);
	
	/*Transforms the input:
	 * W-1 UP
	 * S-2 Down
	 * A-3 Left
	 * D-4 Right
	 * P(Exit)-0
	 * Error- -1
	 */
	private int transformInput(String input)
	
	{
		switch(input) {
			case "W": return 1;
			case "w": return 1;
			case "S": return 2;
			case "s": return 2;
			case "A": return 3;
			case "a": return 3;
			case "D": return 4;
			case "d": return 4;
			case "P": return 0;
			case "p": return 0;
			case "0": return 0;
		}
		return -1;
	}
	public void closeReader()
	{
		s.close();
	}
	public int handler()
	{
		String input="";
		
		System.out.print("\nWhat is the movement you want to make?\n(W-Up, S-Down, A-Left, D-Right, P/0-Exit): ");
		if(s.hasNextLine())
		{
			input = s.nextLine();
		}
		else
		{
			
		}

		int movement = transformInput(input);

		if (movement == 0)// exit case
		{
			System.out.println("Exiting...");
			return movement;
		}
		else if (movement == -1)//error state
		{
			System.out.println("An error has ocurred during the input. Please provide a valid input.");
			return -1;
		}
		return movement;
	}

}

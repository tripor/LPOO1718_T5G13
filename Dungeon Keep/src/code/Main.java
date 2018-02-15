package code;

import java.util.Scanner;

public class Main {
	
	static String[][] map= {{"X","X","X","X","X","X","X","X","X","X"},
			 		 {"X","H"," "," ","I"," ","X"," ","G","X"},
			 		 {"X","X","X"," ","X","X","X"," "," ","X"},
			 		 {"X"," ","I"," ","I"," ","X"," "," ","X"},
			 		 {"X","X","X"," ","X","X","X"," "," ","X"},
			 		 {"I"," "," "," "," "," "," "," "," ","X"},
			 		 {"I"," "," "," "," "," "," "," "," ","X"},
			 		 {"X","X","X"," ","X","X","X","X"," ","X"},
			 		 {"X"," ","I"," ","I"," ","X"," "," ","X"},
			 		 {"X","X","X","X","X","X","X","X","X","X"}};
	
	public static void printscreen()
	{
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(map[i][j]);
			}
			System.out.print("\n");
		}
	}
	/*Transforms the input:
	 * W-1
	 * S-2
	 * A-3
	 * D-4
	 * P(Exit)-0
	 * Error- -1
	 */
	public static int transformInput(String input)
	
	{
		switch(input)
		{
		case "W":return 1;
		case "w":return 1;
		case "S":return 2;
		case "s":return 2;
		case "A":return 3;
		case "a":return 3;
		case "D":return 4;
		case "d":return 4;
		case "P":return 0;
		case "p":return 0;
		}
		return -1;
	}

	public static void main(String[] args) {
		boolean sair=false;
		
		Scanner s= new Scanner(System.in);
		
		String input;
		
		while(sair==false)
		{
			printscreen();
			System.out.print("What is the movement you want to make?(W-Up,S-Down,A-Left,D-Right,P-Exit): ");
			input=s.nextLine();
			int movement=transformInput(input);
			if (movement == 0)// exit case
				break;
			else if (movement == -1)//error state
			{
				System.out.println("An error has ocurred during the input. Please provide a valid input.");
				continue;
			}
			switch(movement)
			{
			///asads
			}
			
		}
		s.close();
	}

}

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
	
	public static void push_remove(String str, int atX, int atY, int byeX, int byeY){
		
		if(atX >= 0){
			System.out.print("\nPush: " + atX + ", " + atY);
			map[atX][atY] = str;
		}
		if(byeX >= 0){
			System.out.print("\nRemove: " + byeX + ", " + byeY);
			map[byeX][byeY] = " ";
		}
	}

	public static void push(String str, int atX, int atY){
		push_remove(str, atX, atY, -1, -1);
	}

	public static void remove(int byeX, int byeY){
		push_remove("", -1, -1, byeX, byeY);
	}

	public static void printscreen()
	{
		System.out.print("\n\n\n\n");

		for (int i=0; i<map.length; i++) {
			for (int j=0; j<map[i].length; j++) {

				if(map[i][j] == null){
					map[i][j] = " ";
				}
				System.out.print(map[i][j] + "|");
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
			
			}
			
		}
		s.close();
	}

}

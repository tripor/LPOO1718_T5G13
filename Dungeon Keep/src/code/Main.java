package code;

import java.util.Scanner;

public class Main {
	
	static String[][] map = {
				{"X","X","X","X","X","X","X","X","X","X"},
				{"X","H"," "," ","I"," ","X"," ","G","X"},
				{"X","X","X"," ","X","X","X"," "," ","X"},
				{"X"," ","I"," ","I"," ","X"," "," ","X"},
				{"X","X","X"," ","X","X","X"," "," ","X"},
				{"I"," "," "," "," "," "," "," "," ","X"},
				{"I"," "," "," "," "," "," "," "," ","X"},
				{"X","X","X"," ","X","X","X","X"," ","X"},
				{"X"," ","I"," ","I"," ","X"," "," ","X"},
				{"X","X","X","X","X","X","X","X","X","X"}
			};

	static String[][] copy = map;
	
	static int positionX = 1;
	static int positionY = 1;
	
	public static boolean push_remove(String str, int atX, int atY, int byeX, int byeY){
		
		if(atX >= 0){

			if(!map[atX][atY].equals(" ")){
				return false;
			}
			// whether there is a wall, do something
			
			System.out.print("\nPush: " + atX + ", " + atY);
			map[atX][atY] = str;
		}
		if(byeX >= 0){
			System.out.print("\nRemove: " + byeX + ", " + byeY);
			map[byeX][byeY] = " ";
		}
		
		return true;
	}

	public static void moveTo(int toX, int toY){
		boolean has_moved = push_remove("H", toX, toY, positionX, positionY);

		if(has_moved){
			positionX = toX;
			positionY = toY;
		}
	}

	// public static void push(String str, int atX, int atY){
	// 	push_remove(str, atX, atY, -1, -1);
	// }

	// public static void remove(int byeX, int byeY){
	// 	push_remove("", -1, -1, byeX, byeY);
	// }

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
	 * W-1 UP
	 * S-2 Down
	 * A-3 Left
	 * D-4 Right
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

		boolean sair = false;

		String input;
		Scanner s = new Scanner(System.in);
		
		while(sair==false)
		{
			printscreen();
			System.out.print("What is the movement you want to make? (W-Up, S-Down, A-Left, D-Right, P-Exit): ");
			input = s.nextLine();

			int movement = transformInput(input);

			if (movement == 0)// exit case
			{
				break;
			}
			else if (movement == -1)//error state
			{
				System.out.println("An error has ocurred during the input. Please provide a valid input.");
				continue;
			}
			switch(movement)
			{
				// moving
				case 1: moveTo(positionX-1, positionY);
					break;
				case 2: moveTo(positionX+1, positionY);
					break;
				case 3: moveTo(positionX, positionY-1);
					break;
				case 4: moveTo(positionX, positionY+1);
					break;
			}
		}
		s.close();
	}

}

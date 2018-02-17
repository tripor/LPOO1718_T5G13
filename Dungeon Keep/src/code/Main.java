package code;

import java.util.Scanner;

public class Main {
	
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

	public static void main(String[] args) {

		int cur_level = 2;
		GameMap map = new GameMap(cur_level);

		boolean sair = false;

		String input;
		Scanner s = new Scanner(System.in);
		
		while(sair==false)
		{
			map.printscreen();
			System.out.print("What is the movement you want to make? (W-Up, S-Down, A-Left, D-Right, P/0-Exit): ");
			input = s.nextLine();

			int movement = transformInput(input);

			if (movement == 0)// exit case
			{
				System.out.println("Exiting...");
				break;
			}
			else if (movement == -1)//error state
			{
				System.out.println("An error has ocurred during the input. Please provide a valid input.");
				continue;
			}
			else
			{
				int state=map.moveHeroTo(movement);
				if(state==1)
				{
					if(cur_level==1){
						cur_level++;
						map = new GameMap(cur_level);
					}
					else{
						System.out.println("Victory");
						break;
					}
				}
				else if(state==2)
				{
					map.printscreen();
					System.out.println("Defeat. You were caught by a guard");
					break;
				}
			}
			
		}
		s.close();
	}

}

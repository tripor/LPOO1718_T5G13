package dkeep.character;

import java.util.Random;
import dkeep.logic.*;

public abstract class Guard extends Character {
	
	public Guard(String[][] map){

		super();

		// System.out.print(map);

		int xMax = map.length-1;
		int yMax = map[0].length-1;

		int x = randomNumber(0, xMax);
		int y = randomNumber(0, yMax);

		System.out.print("[Guard] new " + typeGuard() + "()\n");

		positionX = x;
		positionY = y;

		System.out.print("[Guard] at " + positionX + ", " + positionY + ".\n");
	}

	// rd() will return a corner which had never failed.

	// e.g., it failed because there is a wall,
	//    in the next loop, it will omit the side of wall,
	//    which means only 3 sides to random.

	//    if in the next loop, one more side is found to be a ... door?
	//    then the third loop will only 2 sides to random.
	protected int rd(int[] fail_pos){

		int count_available = 0;

		for(int i=0; i<fail_pos.length; i++){
			if(fail_pos[i] == 0){
				// this corner never failed in the previous loop

				count_available++;
			}
		}

		// System.out.print("\n[>> "+count_available);

		if(count_available == 0){
			System.out.print("Error: No space for guard / club.");
			System.exit(0);
		}

		int max = count_available, min = 1;
		// int max = 4, min = 3;

		Random rand = new Random();
		int rand_result = rand.nextInt((max-min) + 1) + min;
			// nextInt(): return min <= X < max

		int count_order = 0;

		for(int i=0; i<fail_pos.length; i++){
			if(fail_pos[i] == 0){
				// this corner never failed in the previous loop

				count_order++;

				if(count_order == rand_result){
					return (i+1);
					// because our return should be 1 ~ N,
					// if return 0, it will be bugged.
				}
			}
		}
		return rand_result;
	}
	
	protected int randomNumber(int min, int max)
	{
		Random rand = new Random();
		int rand_result = rand.nextInt((max-min) + 1) + min;
		return rand_result;
	}


	// OTHER THINGS...
	
	protected String[] path = {"L","D","D","D","D","L","L","L","L","L","L","D","R","R","R","R","R","R","R","U","U","U","U","U"};
	protected int position_path=0;
	protected boolean foward_walking=true;
	/*
	 * 1 UP
	 * 2 Down
	 * 3 Left
	 * 4 Right
	 */
	public abstract int[] guardNextPosition(Guard guard, GameMap gamearea);
	
	public abstract String typeGuard();
}

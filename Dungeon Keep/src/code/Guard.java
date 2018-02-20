package code;

import java.util.Random;

public class Guard extends Character {

	// rd() will return a corner which had never failed.

	// e.g., it failed because there is a wall,
	//    in the next loop, it will omit the side of wall,
	//    which means only 3 sides to random.

	//    if in the next loop, one more side is found to be a ... door?
	//    then the third loop will only 2 sides to random.

	private int rd(int[] fail_pos){

		int count_available = 0;

		for(int i=0; i<fail_pos.length; i++){
			if(fail_pos[i] == 0){
				// this corner never failed in the previous loop

				count_available++;
			}
		}

		System.out.print("\n[>> "+count_available);

		if(count_available == 0){
			System.out.print("Error: No space for guard / club.");
			System.exit(0);
		}

		int max = count_available, min = 1;
		// int max = 4, min = 3;

		Random rand = new Random();
		int rand_result = rand.nextInt((max-min) + 1) + min;
			// nextInt(): return 0 <= X < N

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


	// OTHER THINGS...
	
	private String[] path = {"L","D","D","D","D","L","L","L","L","L","L","D","R","R","R","R","R","R","R","U","U","U","U","U"};
	private int position_path=0;
	/*
	 * 1 UP
	 * 2 Down
	 * 3 Left
	 * 4 Right
	 */
	public int[] guardNextPosition(Guard guard, GameMap gamearea) {

		int[] pos = new int[]{guard.positionX, guard.positionY};

		if(gamearea.current_level == 2){
			// later.
		}
		else{
			// level = 1
			position_path++;
			if(position_path>path.length)position_path=1;

			switch (path[position_path-1]) {
				case "U":	// UP
					pos[0]--;
					break;
				case "D":	// DOWN
					pos[0]++;
					break;
				case "L":	// LEFT
					pos[1]--;
					break;
				case "R":	// RIGHT
					pos[1]++;
					break;
			}
			return pos;
		}



		//=====LEVEL 2 STARTS.

		boolean success = false;

		// mark the position which cannot put club
		// for prevent an infinite loop.
		// only four corners.
		int[] fail_pos = new int[]{0, 0, 0, 0};

		// do {
		for(int i=0; i<fail_pos.length; i++){

			int rand_result = rd(fail_pos);
			pos[0] = guard.positionX;
			pos[1] = guard.positionY;

			switch (rand_result) {
				case 1:	// UP
					pos[1]++;
					break;
				case 2:	// DOWN
					pos[1]--;
					break;
				case 3:	// LEFT
					pos[0]--;
					break;
				case 4:	// RIGHT
					pos[0]++;
					break;
			}

			if(pos[0] < 0 || pos[1] < 0){
				// fail: out of border (left, top)
			}
			else if(pos[0] >= gamearea.map.length){
				// fail: out of border (right)
			}
			else if(pos[1] >= gamearea.map[0].length){
				// fail: out of border (bottom)
			}
			else if(!(
				gamearea.map[pos[0]][pos[1]].equals(gamearea._empty_cell)
				|| gamearea.map[pos[0]][pos[1]].equals(gamearea._lever)
			)){
				// fail: it is a wall, a door, ...
			}
			else{
				success = true;
				break;
			}

			if(!success){
				fail_pos[rand_result-1] = 1;
			}

		}
		// } while(!success);

		return pos;
	}

	public Guard(){
		super();
	}
}

package dkeep.logic.character;

import java.util.Random;
import dkeep.logic.*;

public abstract class Guard extends Character {

	/**
	 * Saves amount of turns the guard has been stunned
	 */
	public int stunned = 0;	// if 1, 2 = stunned
	/**
	 * Save the sleeping state of the guard
	 */
	public boolean sleep = false;
	/**
	 * Constructor of the class 
	 */
	public Guard() {
		super();
		this.my_char="G";
	}
	/**
	 * rd() will return a corner which had never failed.
	 * e.g., it failed because there is a wall,
	 * in the next loop, it will omit the side of wall,
	 * which means only 3 sides to random.
	 * if in the next loop, one more side is found to be a ... door?
	 * then the third loop will only 2 sides to random.
	 * @param fail_pos array with position that haven't fail for the guard to move to
	 * @return a movement for the guard to move to
	 */
	protected int rd(int[] fail_pos){

		int count_available = 0;

		for(int i=0; i<fail_pos.length; i++){
			if(fail_pos[i] == 0){
				// this corner never failed in the previous loop

				count_available++;
			}
		}

		if(count_available == 0){
			System.exit(0);
		}

		int max = count_available, min = 1;
		// int max = 4, min = 3;

		Random rand = new Random();
		int rand_result = rand.nextInt(max-min + 1) + min;
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
	
	/**
	 * Check if the guard can move to the position i want him to go
	 */
	public boolean push_remove(int toX, int toY, GameMap game) {
		// guard

		boolean can_move = ((toX < game.getMap().length || toY < game.getMap()[0].length)
				&& (game.getMap()[toX][toY].equals(defenitions._empty_cell)
						|| game.getMap()[toX][toY].equals(defenitions._lever)
						|| game.getMap()[toX][toY].equals(defenitions._crazy_ogre)
						||game.getMap()[toX][toY].equals(defenitions._ogre_at_key)));
		if(game.getMap()[toX][toY].equals(defenitions._lever))
		{
			game.getMap()[toX][toY] = defenitions._ogre_at_key;
			this.positionX=toX;
			this.positionY=toY;
			
			return true;
		}

		// colision detection
		if (!can_move) {
			return false;
		}

		game.getMap()[toX][toY] = this.my_char;
		this.positionX=toX;
		this.positionY=toY;
		
		return true;
	}
	
	
	


	// OTHER THINGS...
	
	protected String[] path = {"L","D","D","D","D","L","L","L","L","L","L","D","R","R","R","R","R","R","R","U","U","U","U","U"};
	protected int position_path=0;
	/**
	 * Walking direction of the guard
	 */
	public boolean foward_walking=true;
	/*
	 * 1 UP
	 * 2 Down
	 * 3 Left
	 * 4 Right
	 */
	/**
	 * Calculates the guard next position
	 * @param gamearea the current game
	 * @return array int with 2 position corresponding to the new position X and Y of the guard
	 */
	public abstract int[] guardNextPosition(GameMap gamearea);
	/**
	 * Gets the type of guard
	 * @return String with the type of guard
	 */
	public abstract String typeGuard();
}

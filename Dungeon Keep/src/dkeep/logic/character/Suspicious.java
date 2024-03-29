package dkeep.logic.character;

import dkeep.logic.GameMap;
/**
 * 
 * Class the implements the logic of the guard Suspicious
 *
 */
public class Suspicious extends Guard {
	/**
	 * Calculates the guard next position and he can randomly change it course
	 */
	public int[] guardNextPosition( GameMap gamearea) {
		int[] pos = new int[] { this.positionX, this.positionY };

		int reverse_movement = this.randomNumber(1, 3);
		if (reverse_movement == 3) {
			this.foward_walking = false;
		} else {
			this.foward_walking = true;
		}
		if (this.foward_walking) { // if he is going forward
			position_path++;
		} 
		if (position_path > path.length)// when going forward the number is bigger than the path
			position_path = 1;
		if (position_path < 1)// when going backwards the number is below 1
			position_path = path.length;

		switch (path[position_path - 1]) {
		case "U": // UP
			if (this.foward_walking)
				pos[0]--;
			else
				pos[0]++;
			break;
		case "D": // DOWN
			if (this.foward_walking)
				pos[0]++;
			else
				pos[0]--;
			break;
		case "L": // LEFT
			if (this.foward_walking)
				pos[1]--;
			else
				pos[1]++;
			break;
		case "R": // RIGHT
			if (this.foward_walking)
				pos[1]++;
			else
				pos[1]--;
			break;
		}
		if(this.foward_walking==false){ // if he is going backwards
			position_path--;
		}
		return pos;
	}
	/**
	 * 
	 */
	public String typeGuard() {
		return "suspicious";
	}
	/**
	 * Constructor of the class Suspicious
	 */
	public Suspicious(){
		super();
	}

}

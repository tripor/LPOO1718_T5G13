package dkeep.character;

import dkeep.logic.GameMap;

public class Drunken extends Guard {


	public int[] guardNextPosition(GameMap gamearea) {
		int[] pos = new int[] { this.positionX, this.positionY };

		if (sleep == true)// has fallen asleep
		{
			int is_awake = this.randomNumber(1, 3); // decide if he is going to wake up or not
			if (is_awake == 3)// wake up
			{
				sleep = false;
				int movement = this.randomNumber(1, 3);// decide if he is going to move foward or backwards
				if (movement == 3) {
					this.foward_walking = false;
				} else {
					this.foward_walking = true;
				}
			}
			this.my_char="g";
		} else {
			int is_going_sleep = this.randomNumber(1, 5); //decide if he is going to fall asleep

			if (is_going_sleep == 5) {
				sleep = true;
				this.my_char="g";
			} else {
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
				if (this.foward_walking == false) { // if he is going backwards
					position_path--;
				}
			}

		}
		return pos;
	}

	public boolean isSleep() {
		return sleep;
	}

	public void setSleep(boolean sleep) {
		this.sleep = sleep;
	}

	public String typeGuard() {
		return "drunken";
	}

	public Drunken(String[][] map){
		super();
	}

}

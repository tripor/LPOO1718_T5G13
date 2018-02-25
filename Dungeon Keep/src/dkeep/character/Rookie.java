package dkeep.character;

import dkeep.logic.GameMap;

public class Rookie extends Guard {

	public int[] guardNextPosition(Guard guard, GameMap gamearea) {

		int[] pos = new int[] { guard.positionX, guard.positionY };
		position_path++;
		if (position_path > path.length)
			position_path = 1;

		switch (path[position_path - 1]) {
		case "U": // UP
			pos[0]--;
			break;
		case "D": // DOWN
			pos[0]++;
			break;
		case "L": // LEFT
			pos[1]--;
			break;
		case "R": // RIGHT
			pos[1]++;
			break;
		}
		return pos;

	}

}

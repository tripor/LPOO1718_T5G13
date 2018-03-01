package dkeep.character;

import dkeep.logic.GameMap;
import dkeep.logic.defenitions;

public class Ogre extends Guard {

	public int[] guardNextPosition(Character guard, GameMap gamearea) {

		// =====LEVEL 2 STARTS.
		int[] pos = new int[]{guard.positionX, guard.positionY};

		boolean success = false;

		// mark the position which cannot put club
		// for prevent an infinite loop.
		// only four corners.
		int[] fail_pos = new int[] { 0, 0, 0, 0 };

		// do {
		for (int i = 0; i < fail_pos.length; i++) {

			int rand_result = rd(fail_pos);
			pos[0] = guard.positionX;
			pos[1] = guard.positionY;

			switch (rand_result) {
			case 1: // UP
				pos[1]++;
				break;
			case 2: // DOWN
				pos[1]--;
				break;
			case 3: // LEFT
				pos[0]--;
				break;
			case 4: // RIGHT
				pos[0]++;
				break;
			}

			if (pos[0] < 0 || pos[1] < 0) {
				// fail: out of border (left, top)
			} else if (pos[0] >= gamearea.map.length) {
				// fail: out of border (bottom)
			} else if (pos[1] >= gamearea.map[0].length) {
				// fail: out of border (right)
			} else if (!(gamearea.map[pos[0]][pos[1]].equals(defenitions._empty_cell)
					|| gamearea.map[pos[0]][pos[1]].equals(defenitions._lever)
					|| gamearea.map[pos[0]][pos[1]].equals(defenitions._ogre_club))) {
				// fail: it is a wall, a door, ...
			} else {
				success = true;
				break;
			}

			if (!success) {
				fail_pos[rand_result - 1] = 1;
			}

		}
		// } while(!success);

		System.out.print("\nguardNextPosition() - ["+pos[0]+","+pos[1]+"] \n");

		return pos;
	}

	public String typeGuard() {
		return "ogre";
	}

	public Ogre(String[][] map){
		super(map);
	}

}

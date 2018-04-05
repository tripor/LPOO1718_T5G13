package dkeep.logic.character;

import dkeep.logic.GameMap;
import dkeep.logic.defenitions;

public class Ogre extends Guard {

	public int[] guardNextPosition(GameMap gamearea) {

		// =====LEVEL 2 STARTS.
		int[] pos = new int[]{this.positionX, this.positionY};

		boolean success = false;

		// mark the position which cannot put club
		// for prevent an infinite loop.
		// only four corners.
		int[] fail_pos = new int[] { 0, 0, 0, 0 };

		// do {
		for (int i = 0; i < fail_pos.length; i++) {

			int rand_result = rd(fail_pos);
			pos[0] = this.positionX;
			pos[1] = this.positionY;

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
			} else if (pos[0] >= gamearea.getMap().length) {
				// fail: out of border (bottom)
			} else if (pos[1] >= gamearea.getMap()[0].length) {
				// fail: out of border (right)
			} else {
				success = true;
				break;
			}

			if (!success) {
				fail_pos[rand_result - 1] = 1;
			}

		}

		return pos;
	}

	public String typeGuard() {
		return "ogre";
	}

	public Ogre(String[][] map){
		super(map);
		this.my_char=defenitions._crazy_ogre;
	}

}

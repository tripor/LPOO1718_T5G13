package dkeep.logic.character;

import dkeep.logic.*;
/**
 * 
 * Class that make the logic of the club
 *
 */
public class Club extends Ogre {

	/**
	 * Calculates the next position of the club depending on the position of the owner
	 * @param gamearea the current game
	 * @param my_owner the owner of the club
	 */
	public void clubNextPosition(GameMap gamearea, Character my_owner) {
		this.positionX=my_owner.positionX;
		this.positionY=my_owner.positionY;

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
				success = this.push_remove(pos[0], pos[1], gamearea);
				if(success)
					break;
			}

			if (!success) {
				fail_pos[rand_result - 1] = 1;
			}

		}
		
		
	}
	/**
	 * Cosntructor of the class CLub
	 */
	public Club(){
		super();
		this.my_char=defenitions._ogre_club;
	}
	/**
	 * Check if the club if able to move to the position i want to
	 */
	public boolean push_remove(int toX, int toY, GameMap game) {

		boolean can_move = ((game.getMap()[toX][toY].equals(defenitions._empty_cell)
				|| game.getMap()[toX][toY].equals(defenitions._lever))
				&& (toX < game.getMap().length || toY < game.getMap()[0].length));

		if (game.getMap()[toX][toY].equals(defenitions._lever))
		{

			game.setMap(defenitions._club_at_key, toX, toY);
			this.positionX=toX;
			this.positionY=toY;
			return true;
		}

		// colision detection
		if (!can_move) {
			return false;
		}
		game.setMap(this.my_char, toX, toY);
		this.positionX=toX;
		this.positionY=toY;
		return true;
	}
}

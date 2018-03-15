package dkeep.character;

import dkeep.logic.*;

public class Club extends Ogre {

	public int[] clubNextPosition(Character guard, GameMap gamearea) {

		System.out.print("\n[Club] clubNextPosition(" + guard.positionX + "," + guard.positionY + ")");
		return guardNextPosition(gamearea);
	}

	public Club(String[][] map){
		super(map);
	}
	public boolean push_remove(int toX, int toY, GameMap game) {

		if (this.my_char.equals(defenitions._hero_club)) {
			boolean can_move = ((game.getMap()[toX][toY].equals(defenitions._empty_cell)
					|| game.getMap()[toX][toY].equals(defenitions._lever))
					&& (toX < game.getMap().length || toY < game.getMap()[0].length));

			if (game.getMap()[toX][toY].equals(defenitions._lever))
				this.my_char = defenitions._club_at_key;

			// colision detection
			if (!can_move) {
				return false;
			}
		} else if (this.my_char.equals(defenitions._ogre_club)) {
			boolean can_move = ((game.getMap()[toX][toY].equals(defenitions._empty_cell)
					|| game.getMap()[toX][toY].equals(defenitions._lever))
					&& (toX < game.getMap().length || toY < game.getMap()[0].length));

			if (game.getMap()[toX][toY].equals(defenitions._lever))
				this.my_char = defenitions._club_at_key;
			// colision detection
			if (!can_move) {
				return false;
			}
		}

		game.setMap(this.my_char, toX, toY);

		return true;
	}
}

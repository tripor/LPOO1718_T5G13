package dkeep.logic.character;

import dkeep.logic.GameMap;
import dkeep.logic.defenitions;
/**
 * 
 * Class that implements the logic of the Hero
 *
 */
public class Hero extends Character {

	/**
	 * Constructor of the class Hero
	 */
	public Hero(){
		super();
		this.my_char=defenitions._hero;
	}
	/**
	 * Check if the hero can move to the position i want to. If its a lever it will open the door. If a key he will pick it up and if he tries to open a door with a key it will open it
	 */
	public boolean push_remove(int toX, int toY, GameMap game) {
		boolean can_move = ((game.getMap()[toX][toY].equals(defenitions._empty_cell)
				|| game.getMap()[toX][toY].equals(defenitions._opened_door)
				|| game.getMap()[toX][toY].equals(defenitions._lever))
				&& (toX < game.getMap().length || toY < game.getCopied_map()[0].length));

		if (game.getMap()[toX][toY].equals(defenitions._door) && this.my_char.equals(defenitions._hero_at_key)) {
			game.setCopied_map(defenitions._opened_door, toX, toY);
			game.setMap(defenitions._opened_door, toX, toY);
			game.setMap(this.my_char, this.positionX, this.positionY);
			return false;
		}

		// colision detection

		if (game.getCopied_map()[toX][toY].equals(defenitions._lever)) // Open doors
		{
			if (game.getCurrent_level().game_level.getValue() == 2) {
				// remove the key
				game.setCopied_map(defenitions._empty_cell, toX, toY);
				game.setMap(defenitions._hero_at_key, toX, toY);
				this.my_char=defenitions._hero_at_key;
				game.setMap(this.my_char, toX, toY);
				this.positionX=toX;
				this.positionY=toY;
				return true;

			} else {
				game.openDoors();
			}
		}
		if (!can_move) {

			game.setMap(this.my_char, this.positionX, this.positionY);
			return false;
		}
		game.setMap(this.my_char, toX, toY);
		this.positionX=toX;
		this.positionY=toY;
		return true;
	}
}

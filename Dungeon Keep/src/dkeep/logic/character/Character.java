package dkeep.logic.character;

import java.util.ArrayList;

import dkeep.logic.GameMap;
/**
 * 
 * Abstract class for all the hero,guards and clubs in the game
 *
 */
public abstract class Character {
	/**
	 * Gets the defenitions of the current character state
	 * @return a string with a single char corresponding to the character state
	 */
	public String getMy_char() {
		return my_char;
	}
	/**
	 * Sets the character char
	 * @param my_char the char i want to change to
	 */
	public void setMy_char(String my_char) {
		this.my_char = my_char;
	}
	/**
	 * position X in the map of the character
	 */
	public int positionX;
	/**
	 * position Y in the map of the character
	 */
	public int positionY;
	protected String my_char;
	/**
	 * ArrayList of Club with all the clubs the character has
	 */
	public ArrayList<Club> clubs = new ArrayList<Club>();

	// Move and delete the "trail"
	/**
	 * 
	 * @param toX the X position i want the character to be in 
	 * @param toY the Y position i want the character to be in
	 * @param game the current game
	 * @return true if it moved and false if it hasn't moved
	 */
	public abstract boolean push_remove(int toX, int toY, GameMap game);
	/**
	 * Construct for the class
	 */
	public Character(){
	}
	
}

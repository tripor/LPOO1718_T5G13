package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

import dkeep.logic.character.Guard;
import dkeep.logic.character.Hero;
/**
 * 
 * class that does the main logic of the game
 *
 */
public abstract class GameMap {

	protected String[][] map;
	protected String[][] copied_map;
	/**
	 * Changes the background map
	 * @param copied_map the map i want to change to
	 */
	public void setCopied_map(String[][] copied_map) {
		this.copied_map = copied_map;
	}
	/**
	 * generates a random number
	 * @param min minimum number
	 * @param max maximum number
	 * @return the randomly generated number
	 */
	protected int randomNumber(int min, int max)
	{
		Random rand = new Random();
		int rand_result = rand.nextInt((max-min) + 1) + min;
		return rand_result;
	}
	// init characters
	protected Hero hero = new Hero();

	// Guard guard;
	protected ArrayList<Guard> guards = new ArrayList<Guard>();

	// Club club = new Club();
	/**
	 * set the class hero for this game
	 * @param hero the hero i want to change to
	 */
	public void setHero(Hero hero) {
		this.hero = hero;
	}

	// mark current level for display
	protected CurrentLevel current_level;
	/**
	 * gets the top layer map
	 * @return	the top layer map
	 */
	public String[][] getMap() {
		return map;
	}
	/**
	 * change a position in the top layer map
	 * @param str the string i want to change to
	 * @param posX the position X to where i want it to be
	 * @param posY the position Y to where i want it to be
	 */
	public void setMap(String str,int posX,int posY) {
		this.map[posX][posY]=str;
	}
	/**
	 * Returns an array String of the bottom/background layer map
	 * @return an array with the bottom map
	 */
	public String[][] getCopied_map() {
		return copied_map;
	}
	/**
	 * change a position in the bottom/background layer map
	 * @param str the string i want to change to
	 * @param posX the X position i want to change
	 * @param posY the Y position i want to change
	 */
	public void setCopied_map(String str,int posX,int posY) {
		this.copied_map[posX][posY]=str;
	}
	/**
	 * sets the corrent map acording to an array String
	 * @param map the array String i wan to change to
	 */
	public void setMap(String[][] map) {
		this.map = map;
	}
	/**
	 * get the hero of the game
	 * @return class hero of this game
	 */
	public Hero getHero() {
		return hero;
	}
	/**
	 * gets an ArrayList of all the guards present in the level
	 * @return an ArrayList with all the class guard
	 */
	public ArrayList<Guard> getGuards() {
		return guards;
	}
	/**
	 * sets the current guards of a game
	 * @param guards the ArrayList of guards i want to chango to
	 */
	public void setGuards(ArrayList<Guard> guards) {
		this.guards = guards;
	}
	/**
	 * gets the current level of the game
	 * @return class CurrentLevel is returned
	 */
	public CurrentLevel getCurrent_level() {
		return current_level;
	}
	/**
	 * sets the current level of this game
	 * @param current_level class CurrentLevel. The level I want to change to
	 */
	public void setCurrent_level(CurrentLevel current_level) {
		this.current_level = current_level;
	}
	/**
	 * Abstract method
	 */
	public abstract void getRandomGuard();
	protected void setPositionInMap(String str,int posX,int posY)
	{
		map[posX][posY]=str;
	}
	/**
	 * Sets the top layer map to be equal to the bottom layer map
	 */
	public void clearMap()
	{
		for (int i = 0; i < map.length; i++) {
			map[i] = this.copied_map[i].clone();
		}
	}

	/** updates the game map depending on the currente level
	 * 
	 */
	protected void updateMap() {
		// pointer only, need to copy
		String[][] this_level_map = current_level.getMap();

		// copy map
		map = new String[this_level_map.length][this_level_map[0].length];
		for (int i = 0; i < this_level_map.length; i++) {
			map[i] = this_level_map[i].clone();
		}

		// copy one more time
		copied_map = new String[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			copied_map[i] = map[i].clone();
		}
	}
	/**
	 * Abstract method
	 */
	public abstract void markPositions();

	// Class constructor
	/**
	 * Class constructor
	 */
	public GameMap() {
	}
	
	

	/**
	 * Prints in the screen the map and the characters
	 */
	public void printscreen() {
		System.out.print("\n\n\n\n");

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + "|");
			}
			System.out.print("\n");
		}
	}

	/**
	 * Changes the every I to S in both maps
	 */
	public void openDoors() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {

				if (map[i][j].equals(defenitions._door)) {
					
					map[i][j] = defenitions._opened_door;
					copied_map[i][j] = defenitions._opened_door;
				}
			}
		}
	}

	
	/**
	 * Abstract method
	 * @return true if the hero has been caught or false if not
	 */
	public abstract boolean checkGuard();
	/**
	 * Move hero depending on the input and the guard depending on it's path.
	 * Abstract method
	 * @param type_movement the type of movement of the hero
	 * @return  0- no errors ; 1- case the hero leaves the room return 2- case
	 */
	public abstract int moveHeroTo(int type_movement);
}

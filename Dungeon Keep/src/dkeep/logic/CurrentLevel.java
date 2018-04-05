package dkeep.logic;
/**
 * 
 * This class keeps tracks of the current level of the game
 *
 */
public abstract class CurrentLevel {
	//DEFENITIONS
	/**
	 * Enum for the type of level there is
	 * 
	 */
	public static enum Level {
		FIRST(1), SECOND(2), NADA(0);

		private int value;

		private Level(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}
	
	
	//VARIABLES
	/**
	 * Public variable that save the number of the level
	 */
	public Level game_level;

	//CONSTRUCTOR
	/**
	 * Constructor for current level. Creates sets game_level to level 1.
	 */
	public CurrentLevel() {
		game_level = Level.FIRST;
	}
	/**
	 * Constructor that sets the game_level depending on the paramenter
	 * @param nivel game level that I want to be on
	 */
	public CurrentLevel(CurrentLevel.Level nivel) {
		game_level = nivel;
	}

	//METHODS
	/**
	 * get a map
	 * @return the map corresponding to the level
	 */
	public abstract String[][] getMap();
	/**
	 * change level
	 * @param new_state the new state level that I want to change to
	 */
	public void changeLevel(Level new_state) {
		game_level = new_state;
	}

}

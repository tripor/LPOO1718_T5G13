package dkeep.logic;

public abstract class CurrentLevel {
	//DEFENITIONS
	public static enum Level {
		FIRST(1), SECOND(2), TEST1(11), TEST2(12);

		private int value;

		private Level(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}
	
	
	//VARIABLES
	public Level game_level;

	//CONSTRUCTOR
	public CurrentLevel() {
		game_level = Level.FIRST;
	}
	public CurrentLevel(CurrentLevel.Level nivel) {
		game_level = nivel;
	}

	//METHODS
	
	public abstract String[][] getMap();
	
	public void changeLevel(Level new_state) {
		game_level = new_state;
	}

}

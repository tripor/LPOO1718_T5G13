package dkeep.logic;

public class Maps extends CurrentLevel {
	
	String[][][] map_warehouse = new String[][][] {
		{
			{ "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
			{ "X", " ", " ", " ", "I", " ", "X", " ", " ", "X" }, 
			{ "X", "X", "X", " ", "X", "X", "X", " ", " ", "X" },
			{ "X", " ", "I", " ", "I", " ", "X", " ", " ", "X" }, 
			{ "X", "X", "X", " ", "X", "X", "X", " ", " ", "X" },
			{ "I", " ", " ", " ", " ", " ", " ", " ", " ", "X" }, 
			{ "I", " ", " ", " ", " ", " ", " ", " ", " ", "X" },
			{ "X", "X", "X", " ", "X", "X", "X", "X", " ", "X" }, 
			{ "X", " ", "I", " ", "I", " ", "X", "k", " ", "X" },
			{ "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" }
		},

		{
			{ "X","X","X","X","X","X","X","X","X" },
			{ "I"," "," "," "," "," "," ","k","X" },
			{ "X"," "," "," "," "," "," "," ","X" },
			{ "X"," "," "," "," "," "," "," ","X" },
			{ "X"," "," "," "," "," "," "," ","X" },
			{ "X"," "," "," "," "," "," "," ","X" },
			{ "X"," "," "," "," "," "," "," ","X" },
			{ "X"," "," "," "," "," "," "," ","X" },
			{ "X","X","X","X","X","X","X","X","X" }
		}
		
	};
	/**
	 * Constructor for the maps class
	 */
	public Maps()
	{
		super();
	}
	/**
	 * Constructor for the maps class
	 * @param nivel the level i want the game to be in
	 */
	public Maps(CurrentLevel.Level nivel)
	{
		super(nivel);
	}
	/**
	 * Return the game map depending current on the game_state
	 */
	public String[][] getMap()
	{
		switch(game_level)
		{
		case FIRST:
			return map_warehouse[0];
		case SECOND:
			return map_warehouse[1];
		default:
			return map_warehouse[0];
		}
		
	}
	/**
	 * Return a game map depending on the user input
	 * @param current_game_level the level i want to obtain the map
	 * @return array string with the map corresponding to the level
	 */
	public String[][] getMap(Level current_game_level)
	{
		switch(current_game_level)
		{
		case FIRST:
			return map_warehouse[0];
		case SECOND:
			return map_warehouse[1];
		default:
			return map_warehouse[0];
		}
	}

}

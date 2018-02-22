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
	
	public Maps()
	{
		super();
	}
	
	
	//Return the game map depending current on the game_state
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
	
	//Return a game map depending on the user input
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

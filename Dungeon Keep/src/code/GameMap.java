package code;

public class GameMap {

/*======DEFINITIONS======*/

	String _wall        = "X";
	String _door        = "I";
	String _opened_door = "S";
	String _lever       = "k";
	String _empty_cell  = " ";

	String _hero        = "H";
	String _hero_at_key = "K";

	String _guard       = "G";

	String _crazy_ogre  = "0";
	String _ogre_at_key = "$";

	String _ogre_club   = "*";
	String _club_at_key = "$";


	// Pre-defined map.
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

/*======REAL FUNCTIONS======*/

	String[][] map;
	String[][] copied_map;

	// init characters
	Hero hero = new Hero();
	Guard guard = new Guard();
	Club club = new Club();

	// mark current level for display
	int current_level;

	// Class constructor
	public GameMap(int level) {

		if(level == 2){
			hero.positionX = 7;
			hero.positionY = 1;
			guard.positionX = 1;
			guard.positionY = 4;
		}
		else {	// level 1
			level = 1;
			hero.positionX = 1;
			hero.positionY = 1;
			guard.positionX = 1;
			guard.positionY = 8;
		}

		// pointer only, need to copy
		String[][] this_level_map = map_warehouse[level-1];

		// mark current level
		current_level = level;

		// copy map
		map = new String[this_level_map.length][this_level_map[0].length];
		for(int i=0; i<this_level_map.length; i++){
			map[i] = this_level_map[i].clone();
		}

		// copy one more time
		copied_map = new String[map.length][map[0].length];
		for(int i=0; i<map.length; i++){
			copied_map[i] = map[i].clone();
		}

		// mark default position in the map, for hero & guard
		map[hero.positionX][hero.positionY] = _hero;

		if(current_level == 2){
			map[guard.positionX][guard.positionY] = _crazy_ogre;

			int[] pos = club.clubNextPosition(guard, this);
			club.positionX = pos[0];
			club.positionY = pos[1];

			map[club.positionX][club.positionY] = _ogre_club;
		}
		else{
			// level 1
			map[guard.positionX][guard.positionY] = _guard;
		}
	}

	// Prints in the screen the map and the characters
	public void printscreen() {
		System.out.print("\n\n\n\n");

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {

				boolean char_printed = false;

				// special handle for the key in level 2
				if(current_level == 2
					&& copied_map[i][j].equals(_lever)){

					if(map[i][j].equals(_crazy_ogre)){

						char_printed = true;
						System.out.print(_ogre_at_key + "|");
					}
					else if(map[i][j].equals(_ogre_club)){
						
						char_printed = true;
						System.out.print(_club_at_key + "|");
					}
					else if(map[i][j].equals(_hero)){
						
						char_printed = true;
						_hero=_hero_at_key;
						copied_map[i][j] = _empty_cell;
						System.out.print(_hero_at_key + "|");
					}
				}
				
				if(!char_printed){
					System.out.print(map[i][j] + "|");
				}
			}
			System.out.print("\n");
		}
	}
	
	// Changes the I to S
	private void openDoors() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {

				if(map[i][j]==_door) {

					map[i][j] = _opened_door;
					copied_map[i][j] = _opened_door;
				}
			}
		}
	}

	// Move and delete the "trail"
	public boolean push_remove(String str, int atX, int atY, int byeX, int byeY) {

		if (atX >= 0 && atY>=0) {

			if(str == _hero){

				boolean can_move = (
					map[atX][atY].equals(_empty_cell) ||
					map[atX][atY].equals(_opened_door) ||
					map[atX][atY].equals(_lever)
				);

				if(map[atX][atY].equals(_door))
				{
					openDoors();
					return false;
				}

				// colision detection
				if (!can_move) {
					return false;
				}

				if(map[atX][atY].equals(_lever)) //Open doors
				{
					

					if(current_level == 2){
						// remove the key
						/*_hero=_hero_at_key;
						copied_map[atX][atY] = _empty_cell;*/
					}
					else
					{
						openDoors();
					}
				}
			}
			else{
				// guard

				boolean can_move = (
					map[atX][atY].equals(_empty_cell) ||
					map[atX][atY].equals(_lever)
				);

				// colision detection
				if (!can_move) {
					return false;
				}
			}

			System.out.print("\nPush: " + atX + ", " + atY);
			map[atX][atY] = str;
		}
		if (byeX >= 0 && byeY>=0) {
			System.out.print("\nRemove: " + byeX + ", " + byeY);
			map[byeX][byeY] = copied_map[byeX][byeY];
		}

		return true;
	}
	
	private boolean checkGuard()
	{
		if(map[guard.positionX+1][guard.positionY].equals(_hero))
			return true;
		if(map[guard.positionX-1][guard.positionY].equals(_hero))
			return true;
		if(map[guard.positionX][guard.positionY+1].equals(_hero))
			return true;
		if(map[guard.positionX][guard.positionY-1].equals(_hero))
			return true;


		if(current_level == 2){

			if(map[club.positionX+1][club.positionY].equals(_hero))
				return true;
			if(map[club.positionX-1][club.positionY].equals(_hero))
				return true;
			if(map[club.positionX][club.positionY+1].equals(_hero))
				return true;
			if(map[club.positionX][club.positionY-1].equals(_hero))
				return true;
		}
		
		return false;
	}

	//Move hero depending on the input and the guard depending on it's path
	/* return 0- no errors
	 * return 1- case the hero leaves the room
	 * return 2- case the guard catches the hero
 	 */
	public int moveHeroTo(int type_movement) {
		int toX = hero.positionX, toY = hero.positionY;
		switch (type_movement) {
		case 1:
			toX = hero.positionX - 1;
			toY = hero.positionY;
			break;
		case 2:
			toX = hero.positionX + 1;
			toY = hero.positionY;
			break;
		case 3:
			toX = hero.positionX;
			toY = hero.positionY - 1;
			break;
		case 4:
			toX = hero.positionX;
			toY = hero.positionY + 1;
			break;
		}
		if(toX<0||toY<0)
		{
			return 1;
		}
		boolean has_moved = push_remove(_hero, toX, toY, hero.positionX, hero.positionY);

		if (has_moved) {
			hero.positionX = toX;
			hero.positionY = toY;
		}

		int[] guard_new_pos = guard.guardNextPosition(guard, this);

		toX = guard_new_pos[0];
		toY = guard_new_pos[1];

		// if current level is "2"
		if(current_level == 2){
			has_moved = push_remove(_crazy_ogre, toX, toY, guard.positionX, guard.positionY);
		}
		// else
		else{
			// level 1
			has_moved = push_remove(_guard, toX, toY, guard.positionX, guard.positionY);
		}
		
		if (has_moved) {
			guard.positionX = toX;
			guard.positionY = toY;

			// if level 2, move the club also.
			if(current_level == 2){
				int[] pos = club.clubNextPosition(guard, this);
				has_moved = push_remove(_ogre_club, pos[0], pos[1], club.positionX, club.positionY);

				if(has_moved) {
					club.positionX = pos[0];
					club.positionY = pos[1];
				}
			}
		}
		//check if the guard sees the hero
		if(checkGuard()==true)
		{
			return 2;
		}
		
		return 0;
	}
}

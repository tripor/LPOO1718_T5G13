package code;

public class GameMap {
	String[][] map = new String[][] { { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
									  { "X", " ", " ", " ", "I", " ", "X", " ", " ", "X" }, 
									  { "X", "X", "X", " ", "X", "X", "X", " ", " ", "X" },
									  { "X", " ", "I", " ", "I", " ", "X", " ", " ", "X" }, 
									  { "X", "X", "X", " ", "X", "X", "X", " ", " ", "X" },
									  { "I", " ", " ", " ", " ", " ", " ", " ", " ", "X" }, 
									  { "I", " ", " ", " ", " ", " ", " ", " ", " ", "X" },
									  { "X", "X", "X", " ", "X", "X", "X", "X", " ", "X" }, 
									  { "X", " ", "I", " ", "I", " ", "X", "k", " ", "X" },
									  { "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" } };
	String[][] copy=new String[map.length][map[0].length];

	Character hero = new Character();
	Character guard = new Character();;

	// Class constructor
	public GameMap() {
		hero.positionX = 1;
		hero.positionY = 1;
		guard.positionX = 1;
		guard.positionY = 8;
		for(int i=0;i<map.length;i++)
		{
			System.arraycopy(map[i], 0, copy[i], 0, map[i].length);
		}
	}

	// Prints in the screen the map and the characters
	public void printscreen() {
		System.out.print("\n\n\n\n");

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {

				if (i==hero.positionX && j==hero.positionY) {
					System.out.print("H"+"|");
				}
				else if(i==guard.positionX && j==guard.positionY)
				{
					System.out.print("G"+"|");
				}
				else
					System.out.print(map[i][j] + "|");
			}
			System.out.print("\n");
		}
	}
	
	// Changes the I to S
	private void openDoors() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if(map[i][j]=="I")
				{
					map[i][j]="S";
					copy[i][j]="S";
				}
			}
		}
	}

	// Move and delete the "trail"
	public boolean push_remove(String str, int atX, int atY, int byeX, int byeY) {

		if (atX >= 0 && atY>=0) {

			//colision detection
			if (!map[atX][atY].equals(" ") && !map[atX][atY].equals("S") && !map[atX][atY].equals("k") ) {
				
				return false;
			}
			if(map[atX][atY].equals("k")) //Open doors
			{
				openDoors();
			}

			System.out.print("\nPush: " + atX + ", " + atY);
			map[atX][atY] = str;
		}
		if (byeX >= 0 && byeY>=0) {
			System.out.print("\nRemove: " + byeX + ", " + byeY);
			map[byeX][byeY] = copy[byeX][byeY];
		}

		return true;
	}
	
	private boolean checkGuard()
	{
		if(map[guard.positionX+1][guard.positionY]=="H")
			return true;
		if(map[guard.positionX-1][guard.positionY]=="H")
			return true;
		if(map[guard.positionX][guard.positionY+1]=="H")
			return true;
		if(map[guard.positionX][guard.positionY-1]=="H")
			return true;
		
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
		boolean has_moved = push_remove("H", toX, toY, hero.positionX, hero.positionY);

		if (has_moved) {
			hero.positionX = toX;
			hero.positionY = toY;
		}
		
		//time for the guard to move
		switch (guard.guardNextPosition()) {
		case 1:
			toX = guard.positionX - 1;
			toY = guard.positionY;
			break;
		case 2:
			toX = guard.positionX + 1;
			toY = guard.positionY;
			break;
		case 3:
			toX = guard.positionX;
			toY = guard.positionY - 1;
			break;
		case 4:
			toX = guard.positionX;
			toY = guard.positionY + 1;
			break;
		}
		has_moved=push_remove("G", toX, toY, guard.positionX, guard.positionY);
		if (has_moved) {
			guard.positionX = toX;
			guard.positionY = toY;
		}
		//check if the guard sees the hero
		if(checkGuard()==true)
		{
			return 2;
		}
		
		return 0;
	}
}

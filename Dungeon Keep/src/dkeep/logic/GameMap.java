package dkeep.logic;

import java.util.Random;
// import java.util.Iterator;
import java.util.ArrayList;
import dkeep.character.*;

public abstract class GameMap {

	/* ======REAL FUNCTIONS====== */

	protected String[][] map;
	protected String[][] copied_map;

	// init characters
	protected Hero hero = new Hero();

	// Guard guard;
	protected ArrayList<Guard> guards = new ArrayList<Guard>();

	// Club club = new Club();

	// mark current level for display
	protected CurrentLevel current_level;

	public String[][] getMap() {
		return map;
	}

	public void setMap(String[][] map) {
		this.map = map;
	}

	public String[][] getCopied_map() {
		return copied_map;
	}

	public void setCopied_map(String[][] copied_map) {
		this.copied_map = copied_map;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public ArrayList<Guard> getGuards() {
		return guards;
	}

	public void setGuards(ArrayList<Guard> guards) {
		this.guards = guards;
	}

	public CurrentLevel getCurrent_level() {
		return current_level;
	}

	public void setCurrent_level(CurrentLevel current_level) {
		this.current_level = current_level;
	}

	protected void getRandomGuard() {
		System.out.println("Get Random Guard");

		int total_typeOfGuard = 3;

		Random rand = new Random();
		int rand_result = rand.nextInt(total_typeOfGuard)+1;
		
		 //rand_result = 2;

		Guard g;
		// pick one and init it, it's useless.

		switch (rand_result) {

			// inside Guard, posX & posY defined.
			// passing "map" is because need to fetch width & height of map.

		case 1:
			g = new Rookie(map);
			// // it is the default value of g.
			System.out.println("Init Rookie.");
			break;
		case 2:
			g = new Drunken(map);
			System.out.println("Init Drunken.");
			break;
		case 3:
			g = new Suspicious(map);
			System.out.println("Init Suspicious.");
			break;
		default:
			g = new Rookie(map);
		}

		if (current_level.game_level.getValue() == 2) {
			g= new Ogre(map);
			g.positionX=1;
			g.positionY=7;
			Club c = new Club(map);
			int[] pos = c.clubNextPosition(g, this);
			c.positionX = pos[0];
			c.positionY = pos[1];

			g.clubs.add(c);
		}

		guards.add(g);

		// System.out.print("[Main]: " + this.map.length);

	}
	protected void setPositionInMap(String str,int posX,int posY)
	{
		map[posX][posY]=str;
	}

	// updates the game map depending on the currente level
	protected void updateMap() {
		System.out.println("Update Map");
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

	abstract void markPositions();

	// Class constructor
	public GameMap() {
		System.out.println("Init Game map");
	}


	// Prints in the screen the map and the characters
	public void printscreen() {
		System.out.print("\n\n\n\n");

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {

				boolean char_printed = false;

				// special handle for the key in level 2
				if (current_level.game_level.getValue() == 2 && copied_map[i][j].equals(defenitions._lever)) {

					if (map[i][j].equals(defenitions._crazy_ogre)) {

						char_printed = true;
						System.out.print(defenitions._ogre_at_key + "|");
					} else if (map[i][j].equals(defenitions._ogre_club)) {

						char_printed = true;
						System.out.print(defenitions._club_at_key + "|");
					} else if (map[i][j].equals(defenitions._hero)) {

						char_printed = true;
						defenitions._hero = defenitions._hero_at_key;
						copied_map[i][j] = defenitions._empty_cell;
						System.out.print(defenitions._hero_at_key + "|");
					}
				}

				if (!char_printed) {
					System.out.print(map[i][j] + "|");
				}
			}
			System.out.print("\n");
		}
	}

	// Changes the I to S
	protected void openDoors() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {

				if (map[i][j] == defenitions._door) {

					map[i][j] = defenitions._opened_door;
					copied_map[i][j] = defenitions._opened_door;
				}
			}
		}
	}

	// Move and delete the "trail"
	public boolean push_remove(String str, int atX, int atY, int byeX, int byeY) {

		if (atX >= 0 && atY >= 0) {

			if (str == defenitions._hero || str == defenitions._hero_at_key || str == defenitions._hero_with_arm) {

				boolean can_move = (map[atX][atY].equals(defenitions._empty_cell)
						|| map[atX][atY].equals(defenitions._opened_door) || map[atX][atY].equals(defenitions._lever) 
						|| map[atX][atY].equals(defenitions._ogre_club)
						|| map[atX][atY].equals(defenitions._club_at_key)
						|| map[atX][atY].equals(defenitions._hero_club)
						|| map[atX][atY].equals(defenitions._hero));

				if (map[atX][atY].equals(defenitions._door) && str.equals(defenitions._hero_at_key)) {
					openDoors();
					return false;
				}

				// colision detection
				if (!can_move) {
					return false;
				}

				if (this.copied_map[atX][atY].equals(defenitions._lever)) // Open doors
				{

					if (current_level.game_level.getValue() == 2) {
						// remove the key

						str=defenitions._hero_at_key;
						copied_map[atX][atY] = defenitions._empty_cell;

						System.out.print("\nPush: " + atX + ", " + atY);
						map[atX][atY] = str;
						System.out.print("\nRemove: " + byeX + ", " + byeY);
						map[byeX][byeY] = copied_map[byeX][byeY];
						return true;

					} else {
						openDoors();
					}
				}
			} else if(str == defenitions._crazy_ogre || str == defenitions._ogre_at_key || str == defenitions._ogre_stunned) {
				// guard

				boolean can_move = (atX > map.length || atY > map[0].length
						|| map[atX][atY].equals(defenitions._empty_cell) || map[atX][atY].equals(defenitions._lever)
						|| map[atX][atY].equals(defenitions._ogre_club)
						|| map[atX][atY].equals(defenitions._club_at_key)
						|| map[atX][atY].equals(defenitions._ogre_stunned)
						|| map[atX][atY].equals(defenitions._crazy_ogre)
						|| map[atX][atY].equals(defenitions._ogre_at_key));

				// colision detection
				if (!can_move) {
					return false;
				}
			}
			else if(str == defenitions._hero_club)
			{
				boolean can_move = (atX > map.length || atY > map[0].length
						|| map[atX][atY].equals(defenitions._empty_cell) || map[atX][atY].equals(defenitions._lever) || map[atX][atY].equals(defenitions._ogre_club));

				// colision detection
				if (!can_move) {
					return false;
				}
			}
			else if(str ==defenitions._ogre_club)
			{
				boolean can_move = (atX > map.length || atY > map[0].length
						|| map[atX][atY].equals(defenitions._empty_cell) || map[atX][atY].equals(defenitions._lever));

				// colision detection
				if (!can_move) {
					return false;
				}
			}

			System.out.print("\nPush: " + atX + ", " + atY);
			map[atX][atY] = str;
		}
		if ((byeX >= 0 && byeY >= 0 && map[byeX][byeY].equals(str))) {
			System.out.print("\nRemove: " + byeX + ", " + byeY);
			map[byeX][byeY] = copied_map[byeX][byeY];
		}

		return true;
	}

	protected abstract boolean checkGuard();

	// Move hero depending on the input and the guard depending on it's path
	/*
	 * return 0- no errors return 1- case the hero leaves the room return 2- case
	 * the guard catches the hero
	 */
	public abstract int moveHeroTo(int type_movement);
}

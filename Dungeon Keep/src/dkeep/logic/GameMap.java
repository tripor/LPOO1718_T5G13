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

	public void setMap(String str,int posX,int posY) {
		this.map[posX][posY]=str;
	}

	public String[][] getCopied_map() {
		return copied_map;
	}

	public void setCopied_map(String str,int posX,int posY) {
		this.copied_map[posX][posY]=str;
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

		Guard g;
		
		if (current_level.game_level.getValue() == 1) {

			int total_typeOfGuard = 3;

			Random rand = new Random();
			int rand_result = rand.nextInt(total_typeOfGuard) + 1;

			//rand_result = 2;

			// pick one and init it, it's useless.

			switch (rand_result) {

			// inside Guard, posX & posY defined.
			// passing "map" is because need to fetch width & height of map.

			case 1:
				g = new Rookie(map);
				// // it is the default value of g.
				break;
			case 2:
				g = new Drunken(map);
				break;
			case 3:
				g = new Suspicious(map);
				break;
			default:
				g = new Rookie(map);
			}

			guards.add(g);

		} else if (current_level.game_level.getValue() == 2) {
			g= new Ogre(map);
			g.positionX=1;
			g.positionY=7;
			Club c = new Club(map);
			int[] pos = c.clubNextPosition(g, this);
			c.positionX = pos[0];
			c.positionY = pos[1];

			g.clubs.add(c);

			guards.add(g);
		}

		// System.out.print("[Main]: " + this.map.length);

	}
	protected void setPositionInMap(String str,int posX,int posY)
	{
		map[posX][posY]=str;
	}
	
	protected void clearMap()
	{
		for (int i = 0; i < map.length; i++) {
			map[i] = this.copied_map[i].clone();
		}
	}

	// updates the game map depending on the currente level
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

	protected abstract void markPositions();

	// Class constructor
	public GameMap() {
	}
	
	

	// Prints in the screen the map and the characters
	public void printscreen() {
		System.out.print("\n\n\n\n");

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + "|");
			}
			System.out.print("\n");
		}
	}

	// Changes the I to S
	public void openDoors() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {

				if (map[i][j] == defenitions._door) {

					map[i][j] = defenitions._opened_door;
					copied_map[i][j] = defenitions._opened_door;
				}
			}
		}
	}

	

	public abstract boolean checkGuard();

	// Move hero depending on the input and the guard depending on it's path
	/*
	 * return 0- no errors return 1- case the hero leaves the room return 2- case
	 * the guard catches the hero
	 */
	public abstract int moveHeroTo(int type_movement);
}

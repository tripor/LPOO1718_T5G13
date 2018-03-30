package dkeep.logic;

import java.util.ArrayList;

import javax.swing.JTextArea;

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

	protected abstract void getRandomGuard();
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

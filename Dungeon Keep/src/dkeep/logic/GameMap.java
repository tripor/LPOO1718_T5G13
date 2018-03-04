package dkeep.logic;

import java.util.Random;
// import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import dkeep.character.*;

public class GameMap {

	/* ======REAL FUNCTIONS====== */

	public String[][] map;
	String[][] copied_map;

	// init characters
	Hero hero = new Hero();

	// Guard guard;
	ArrayList<Guard> guards = new ArrayList<Guard>();

	// Club club = new Club();

	// mark current level for display
	public CurrentLevel current_level;

	private void getRandomGuard() {

		int total_typeOfGuard = 3;

		Random rand = new Random();
		int rand_result = rand.nextInt(total_typeOfGuard)+1;
		
		// rand_result = 2;

		Guard g = new Rookie(map);
		// pick one and init it, it's useless.

		switch (rand_result) {

			// inside Guard, posX & posY defined.
			// passing "map" is because need to fetch width & height of map.

		case 1:
			// g = new Rookie(map);
			// // it is the default value of g.
			System.out.println("Init Rookie.\n");
			break;
		case 2:
			g = new Drunken(map);
			System.out.println("Init Drunken.\n");
			break;
		case 3:
			g = new Suspicious(map);
			System.out.println("Init Suspicious.\n");
			break;
		}

		if (current_level.game_level.getValue() == 2) {
			Club c = new Club(map);
			int[] pos = c.clubNextPosition(g, this);
			c.positionX = pos[0];
			c.positionY = pos[1];

			g.clubs.add(c);
		}

		guards.add(g);

		// System.out.print("[Main]: " + this.map.length);

	}

	// updates the game map depending on the currente level
	private void updateMap() {
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

	private void markPositions() {
		// mark default position in the map, for hero & guard
		map[hero.positionX][hero.positionY] = defenitions._hero;

		if (current_level.game_level.getValue() == 2) {
			// map[guard.positionX][guard.positionY] = defenitions._crazy_ogre;

			for (Guard g : guards){
				map[g.positionX][g.positionY] = defenitions._crazy_ogre;

				for (Club c : g.clubs){
					map[c.positionX][c.positionY] = defenitions._ogre_club;
				}
			}

			for (Club c : hero.clubs){
				map[c.positionX][c.positionY] = defenitions._hero_club;
			}

			// int[] pos = club.clubNextPosition(guard, this);
			// club.positionX = pos[0];
			// club.positionY = pos[1];

			// map[club.positionX][club.positionY] = defenitions._ogre_club;
		} else {
			// level 1

			// map[guard.positionX][guard.positionY] = defenitions._guard;

			for (Guard g : guards){
				map[g.positionX][g.positionY] = defenitions._guard;
			}
		}
	}

	// Class constructor
	public GameMap() {

		System.out.print("GameMap()\n\n");

		/*
		 * level 2 positions if hero.positionX = 7; hero.positionY = 1; guard.positionX
		 * = 1; guard.positionY = 4;
		 */
		current_level = new Maps();

		hero.positionX = 1;
		hero.positionY = 1;

		this.updateMap();

		// LV 1, 1 Guard, Random Role
		this.getRandomGuard();

		guards.get(0).positionX = 1;
		guards.get(0).positionY = 8;

		// guard.positionX = 1;
		// guard.positionY = 8;

		this.updateMap();
		this.markPositions();

	}

	// Updates the level
	public void updateLevel() {
		switch (current_level.game_level.getValue()) {
		case 1:
			hero.positionX = 1;
			hero.positionY = 1;

			// guard.positionX = 1;
			// guard.positionY = 8;
		case 2:
			// guard = new Ogre();

			guards = new ArrayList<Guard>();

			hero.positionX = 7;
			hero.positionY = 1;

			Club hero_club = new Club(map);

			int heroClubPos[] = hero_club.clubNextPosition(hero, this);

			hero_club.positionX = heroClubPos[0];
			hero_club.positionY = heroClubPos[1];

			System.out.print("\n[Hero Club] Hero is at " + hero.positionX + ", " + hero.positionY + ".");
			System.out.print("\n[Hero Club] Hero Club is at " + hero_club.positionX + ", " + hero_club.positionY + ".\n\n");

			hero.clubs.add(hero_club);

			// guard.positionX = 1;
			// guard.positionY = 4;

			updateMap();
			markPositions();

			// before generating the guard
			// need to mark hero first,
			// otherwise, the guard may overlap the hero.
			this.getRandomGuard();
			this.getRandomGuard();
		}
		updateMap();
		markPositions();
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
	private void openDoors() {
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

			if (str == defenitions._hero) {

				boolean can_move = (map[atX][atY].equals(defenitions._empty_cell)
						|| map[atX][atY].equals(defenitions._opened_door) || map[atX][atY].equals(defenitions._lever));

				if (map[atX][atY].equals(defenitions._door) && str.equals(defenitions._hero_at_key)) {
					openDoors();
					return false;
				}

				// colision detection
				if (!can_move) {
					return false;
				}

				if (map[atX][atY].equals(defenitions._lever)) // Open doors
				{

					if (current_level.game_level.getValue() == 2) {
						// remove the key
						/*
						 * _hero=_hero_at_key; copied_map[atX][atY] = _empty_cell;
						 */
					} else {
						openDoors();
					}
				}
			} else {
				// guard

				boolean can_move = (
						atX >= map.length
						|| atY >= map[0].length
						|| map[atX][atY].equals(defenitions._empty_cell)
						|| map[atX][atY].equals(defenitions._lever)
					);

				// colision detection
				if (!can_move) {
					return false;
				}
			}

			System.out.print("\nPush: " + atX + ", " + atY);
			map[atX][atY] = str;
		}
		if (byeX >= 0 && byeY >= 0) {
			System.out.print("\nRemove: " + byeX + ", " + byeY);
			map[byeX][byeY] = copied_map[byeX][byeY];
		}

		return true;
	}

	private boolean checkGuard() {

		if (current_level.game_level.getValue() == 2) {
			for(Club c : hero.clubs){

				if(c.positionX+1 < map.length && c.positionY < map[0].length
				&& map[c.positionX+1][c.positionY].equals(defenitions._crazy_ogre)){
					map[c.positionX+1][c.positionY] = defenitions._ogre_stunned;
				}
				else if(c.positionX < map.length && c.positionY+1 < map[0].length
				&& map[c.positionX][c.positionY+1].equals(defenitions._crazy_ogre)){
					map[c.positionX][c.positionY+1] = defenitions._ogre_stunned;
				}
				else if(c.positionX-1 < map.length && c.positionY < map[0].length
				&& map[c.positionX-1][c.positionY].equals(defenitions._crazy_ogre)){
					map[c.positionX-1][c.positionY] = defenitions._ogre_stunned;
				}
				else if(c.positionX < map.length && c.positionY-1 < map[0].length
				&& map[c.positionX][c.positionY-1].equals(defenitions._crazy_ogre)){
					map[c.positionX][c.positionY-1] = defenitions._ogre_stunned;
				}
			}
		}

		for(Guard guard : guards){
			if(map[guard.positionX][guard.positionY].equals(defenitions._ogre_stunned)){
				guard.stunned = 1;
			}
		}

		for(Guard guard : guards){

			if (!(
				map[guard.positionX][guard.positionY].equals(defenitions._guard_sleep)
				|| map[guard.positionX][guard.positionY].equals(defenitions._ogre_stunned)
			)) {
				if (map[guard.positionX + 1][guard.positionY].equals(defenitions._hero))
					return true;
				if (map[guard.positionX - 1][guard.positionY].equals(defenitions._hero))
					return true;
				if (map[guard.positionX][guard.positionY + 1].equals(defenitions._hero))
					return true;
				if (map[guard.positionX][guard.positionY - 1].equals(defenitions._hero))
					return true;
			}

			if (current_level.game_level.getValue() == 2) {

				for(Club club : guard.clubs){

					if (map[club.positionX + 1][club.positionY].equals(defenitions._hero))
						return true;
					if (map[club.positionX - 1][club.positionY].equals(defenitions._hero))
						return true;
					if (map[club.positionX][club.positionY + 1].equals(defenitions._hero))
						return true;
					if (map[club.positionX][club.positionY - 1].equals(defenitions._hero))
						return true;
				}
			}
		}
		return false;
	}

	// Move hero depending on the input and the guard depending on it's path
	/*
	 * return 0- no errors return 1- case the hero leaves the room return 2- case
	 * the guard catches the hero
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
		if (toX < 0 || toY < 0) {
			return 1;
		}

		boolean has_moved = push_remove(defenitions._hero, toX, toY, hero.positionX, hero.positionY);

		if (has_moved) {
			hero.positionX = toX;
			hero.positionY = toY;
		}

		for(Club club : hero.clubs){

			int[] pos = new int[2];

			boolean success = false;
			pos[0] = 0;
			pos[1] = 0;

			for(int i=0; i<5; i++) {

				pos = club.clubNextPosition(hero, this);
				has_moved = push_remove(defenitions._hero_club, pos[0], pos[1], club.positionX, club.positionY);

				if(has_moved){
					success = true;
					break;
				}
			}

			if(success==false){
				System.out.print("\n\n\n===========================\n[Hero Club] No Space to move. Error.\n\n");
				System.exit(0);
			}

			club.positionX = pos[0];
			club.positionY = pos[1];
		}

		for (Guard guard : guards){

			System.out.print("\nStunned = " + guard.stunned);

			if(guard.stunned > 2){
				// 0 = no stun;
				// 1 = just stun (1st round);
				// 2 = stun 2nd round;
				guard.stunned = 0;
			}

			if(guard.stunned > 0){
				guard.stunned++;
			}

			if(guard.stunned == 0){

				boolean success = false;

				for(int i=0; i<5; i++) {

					int[] guard_new_pos = guard.guardNextPosition(guard, this);

					toX = guard_new_pos[0];
					toY = guard_new_pos[1];

					System.out.print("\nto: " + toX + "," + toY);

					// if current level is "2"
					if (current_level.game_level.getValue() == 2) {
						has_moved = push_remove(defenitions._crazy_ogre, toX, toY, guard.positionX, guard.positionY);
					}
					// else
					else {
						// level 1
						has_moved = push_remove(defenitions._guard, toX, toY, guard.positionX, guard.positionY);
					}

					if(has_moved){
						success = true;
						break;
					}
				}

				if(success==false){
					System.out.print("\n===========================\n[Guard - " + guard.typeGuard() + "] No Space. Error.\n\n");
					System.exit(0);
				}

				guard.positionX = toX;
				guard.positionY = toY;
			}

			// if level 2, move the club also.
			if (current_level.game_level.getValue() == 2) {
				for (Club club : guard.clubs){

					int[] pos = new int[2];

					boolean success = false;
					pos[0] = 0;
					pos[1] = 0;

					for(int i=0; i<5; i++) {

						pos = club.clubNextPosition(guard, this);
						has_moved = push_remove(defenitions._ogre_club, pos[0], pos[1], club.positionX, club.positionY);

						if(has_moved){
							success = true;
							break;
						}
					}

					if(success==false){
						System.out.print("\n\n\n===========================\n[Guard Club] No Space. Error.\n\n");
						System.exit(0);
					}

					club.positionX = pos[0];
					club.positionY = pos[1];
				}
			}

		}

		// int[] guard_new_pos = guard.guardNextPosition(guard, this);

		// toX = guard_new_pos[0];
		// toY = guard_new_pos[1];

		// // if current level is "2"
		// if (current_level.game_level.getValue() == 2) {
		// 	has_moved = push_remove(defenitions._crazy_ogre, toX, toY, guard.positionX, guard.positionY);
		// }
		// // else
		// else {
		// 	// level 1
		// 	has_moved = push_remove(defenitions._guard, toX, toY, guard.positionX, guard.positionY);
		// }

		// if (has_moved) {
		// 	guard.positionX = toX;
		// 	guard.positionY = toY;

		// 	// if level 2, move the club also.
		// 	if (current_level.game_level.getValue() == 2) {
		// 		int[] pos = club.clubNextPosition(guard, this);
		// 		has_moved = push_remove(defenitions._ogre_club, pos[0], pos[1], club.positionX, club.positionY);

		// 		if (has_moved) {
		// 			club.positionX = pos[0];
		// 			club.positionY = pos[1];
		// 		}
		// 	}
		// }

		// check if the guard sees the hero
		if (checkGuard() == true) {
			return 2;
		}

		return 0;
	}
}
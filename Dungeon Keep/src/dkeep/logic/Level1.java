package dkeep.logic;

import dkeep.character.Guard;
import dkeep.character.Drunken;

public class Level1 extends GameMap {

	@Override
	public void markPositions() {
		System.out.println("mark positions level1");

		// mark default position in the map, for hero & guard
		map[hero.positionX][hero.positionY] = defenitions._hero;
		// level 1

		// map[guard.positionX][guard.positionY] = defenitions._guard;

		for (Guard g : guards) {
			map[g.positionX][g.positionY] = defenitions._guard;

		}

	}
	
	public Level1()
	{
		System.out.println("Init Level1");

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

	@Override
	public boolean checkGuard() {

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

		}
		return false;
	}

	@Override
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

		for (Guard guard : this.guards) {

			boolean success = false;

			for (int i = 0; i < 5; i++) {

				int[] guard_new_pos = guard.guardNextPosition(guard, this);

				toX = guard_new_pos[0];
				toY = guard_new_pos[1];

				System.out.print("\nto: " + toX + "," + toY);

				// level 1
				if (guard.typeGuard() == "drunken" && ((Drunken) guard).isSleep() == true) {
					success = true;
					has_moved = push_remove(defenitions._guard_sleep, toX, toY, guard.positionX, guard.positionY);
				} else
				{
					has_moved = push_remove(defenitions._guard, toX, toY, guard.positionX, guard.positionY);
				}

				
				
				if (has_moved) {
					success = true;
					break;
				}
			}

			if (success == false) {
				System.out.print(
						"\n===========================\n[Guard - " + guard.typeGuard() + "] No Space. Error.\n\n");
				System.exit(0);
			}

			guard.positionX = toX;
			guard.positionY = toY;
		}

		if (checkGuard() == true) {
			return 2;
		}

		return 0;
	}

}
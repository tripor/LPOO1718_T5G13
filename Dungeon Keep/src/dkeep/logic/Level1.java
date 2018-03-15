package dkeep.logic;

import java.util.Random;

import dkeep.character.Drunken;
import dkeep.character.Guard;
import dkeep.character.Rookie;
import dkeep.character.Suspicious;

public class Level1 extends GameMap {

	protected void getRandomGuard() {
		Guard g;
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
	}
	
	@Override
	public void markPositions() {

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

		/*
		 * level 2 positions if hero.positionX = 7; hero.positionY = 1; guard.positionX
		 * = 1; guard.positionY = 4;
		 */
		current_level = new Maps();
		this.updateMap();

		hero.positionX = 1;
		hero.positionY = 1;

		// LV 1, 1 Guard, Random Role
		this.getRandomGuard();

		guards.get(0).positionX = 1;
		guards.get(0).positionY = 8;

		// guard.positionX = 1;
		// guard.positionY = 8;

		this.markPositions();
	}

	@Override
	public boolean checkGuard() {

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
		this.clearMap();
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
		System.out.println(toX+ " " +toY);
		boolean has_moved = this.hero.push_remove(toX, toY,this);
		for (Guard guard : this.guards) {

			boolean success = false;

			for (int i = 0; i < 5; i++) {

				int[] guard_new_pos = guard.guardNextPosition(this);

				toX = guard_new_pos[0];
				toY = guard_new_pos[1];


				has_moved = guard.push_remove(toX, toY, this);
				
				if (has_moved) {
					success = true;
					break;
				}
			}

			if (success == false) {
				System.exit(0);
			}
		}

		if (checkGuard() == true) {
			return 2;
		}

		return 0;
	}
	public boolean placeHero(int posX,int posY)
	{
		return this.hero.push_remove(posX, posY,this);
	}

}

package dkeep.logic;

import java.util.ArrayList;

import dkeep.character.Club;
import dkeep.character.Drunken;
import dkeep.character.Guard;

public class LevelTest extends GameMap{

	@Override
	void markPositions() {
		map[hero.positionX][hero.positionY] = defenitions._hero;

		for (Guard g : guards) {
			map[g.positionX][g.positionY] = defenitions._guard;

		}

	}
	
	public LevelTest()
	{

		guards = new ArrayList<Guard>();

		hero.positionX = 1;
		hero.positionY = 1;
		this.getRandomGuard();
		guards.get(0).positionX=1;
		guards.get(0).positionX=3;
		updateMap();
		markPositions();
	}

	@Override
	protected boolean checkGuard() {
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
					has_moved = push_remove(defenitions._guard, toX, toY, guard.positionX, guard.positionY);

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

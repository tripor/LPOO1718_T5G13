package dkeep.logic;

import java.util.ArrayList;

import dkeep.character.Club;
import dkeep.character.Guard;

public class Level2 extends GameMap {

	@Override
	void markPositions() {
		map[hero.positionX][hero.positionY] = defenitions._hero_with_arm;
		// map[guard.positionX][guard.positionY] = defenitions._crazy_ogre;

		for (Guard g : guards) {
			map[g.positionX][g.positionY] = defenitions._crazy_ogre;

			for (Club c : g.clubs) {
				map[c.positionX][c.positionY] = defenitions._ogre_club;
			}
		}

		for (Club c : hero.clubs) {
			map[c.positionX][c.positionY] = defenitions._hero_club;
		}

		// int[] pos = club.clubNextPosition(guard, this);
		// club.positionX = pos[0];
		// club.positionY = pos[1];

		// map[club.positionX][club.positionY] = defenitions._ogre_club;

	}
	
	public Level2()
	{
		super();
		System.out.println("Init Level2");

		// guard = new Ogre();
		this.current_level= new Maps(CurrentLevel.Level.SECOND);
		updateMap();
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

		// before generating the guard
		// need to mark hero first,
		// otherwise, the guard may overlap the hero.
		this.getRandomGuard();
		this.getRandomGuard();
		updateMap();
		markPositions();
	}

	@Override
	protected boolean checkGuard() {
		boolean hit_ogre=false;
		for (Club c : hero.clubs) {

			if (c.positionX + 1 < map.length && c.positionY < map[0].length
					&& map[c.positionX + 1][c.positionY].equals(defenitions._crazy_ogre)) {
				map[c.positionX + 1][c.positionY] = defenitions._ogre_stunned;
				hit_ogre=true;
			} else if (c.positionX < map.length && c.positionY + 1 < map[0].length
					&& map[c.positionX][c.positionY + 1].equals(defenitions._crazy_ogre)) {
				map[c.positionX][c.positionY + 1] = defenitions._ogre_stunned;
				hit_ogre=true;
			} else if (c.positionX - 1 < map.length && c.positionY < map[0].length
					&& map[c.positionX - 1][c.positionY].equals(defenitions._crazy_ogre)) {
				map[c.positionX - 1][c.positionY] = defenitions._ogre_stunned;
				hit_ogre=true;
			} else if (c.positionX < map.length && c.positionY - 1 < map[0].length
					&& map[c.positionX][c.positionY - 1].equals(defenitions._crazy_ogre)) {
				map[c.positionX][c.positionY - 1] = defenitions._ogre_stunned;
				hit_ogre=true;
			}
		}

		for (Guard guard : guards) {
			if (map[guard.positionX][guard.positionY].equals(defenitions._ogre_stunned) && hit_ogre) {
				guard.stunned = 1;
			}
		}

		for (Guard guard : guards) {

			if (!(map[guard.positionX][guard.positionY].equals(defenitions._guard_sleep)
					|| map[guard.positionX][guard.positionY].equals(defenitions._ogre_stunned))) {
				if (map[guard.positionX + 1][guard.positionY].equals(defenitions._hero) || map[guard.positionX + 1][guard.positionY].equals(defenitions._hero_with_arm) || map[guard.positionX + 1][guard.positionY].equals(defenitions._hero_at_key))
					return true;
				if (map[guard.positionX - 1][guard.positionY].equals(defenitions._hero) || map[guard.positionX - 1][guard.positionY].equals(defenitions._hero_with_arm) || map[guard.positionX - 1][guard.positionY].equals(defenitions._hero_at_key))
					return true;
				if (map[guard.positionX][guard.positionY + 1].equals(defenitions._hero) || map[guard.positionX][guard.positionY + 1].equals(defenitions._hero_with_arm) || map[guard.positionX][guard.positionY + 1].equals(defenitions._hero_at_key))
					return true;
				if (map[guard.positionX][guard.positionY - 1].equals(defenitions._hero) || map[guard.positionX][guard.positionY - 1].equals(defenitions._hero_with_arm) || map[guard.positionX][guard.positionY - 1].equals(defenitions._hero_at_key))
					return true;
			}

			for (Club club : guard.clubs) {

				if (map[club.positionX + 1][club.positionY].equals(defenitions._hero) || map[club.positionX + 1][club.positionY].equals(defenitions._hero_with_arm) || map[club.positionX + 1][club.positionY].equals(defenitions._hero_at_key))
					return true;
				if (map[club.positionX - 1][club.positionY].equals(defenitions._hero) || map[club.positionX - 1][club.positionY].equals(defenitions._hero_with_arm) || map[club.positionX - 1][club.positionY].equals(defenitions._hero_at_key))
					return true;
				if (map[club.positionX][club.positionY + 1].equals(defenitions._hero) || map[club.positionX][club.positionY + 1].equals(defenitions._hero_with_arm) || map[club.positionX][club.positionY + 1].equals(defenitions._hero_at_key))
					return true;
				if (map[club.positionX][club.positionY - 1].equals(defenitions._hero) || map[club.positionX][club.positionY - 1].equals(defenitions._hero_with_arm) || map[club.positionX][club.positionY - 1].equals(defenitions._hero_at_key))
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
		boolean has_moved;
		if(this.map[hero.positionX][hero.positionY].equals(defenitions._hero_at_key))
		{

			has_moved = push_remove(defenitions._hero_at_key, toX, toY, hero.positionX, hero.positionY);
		}
		else
			has_moved = push_remove(defenitions._hero_with_arm, toX, toY, hero.positionX, hero.positionY);

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

			System.out.println("\nStunned = " + guard.stunned);

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
						has_moved = push_remove(defenitions._crazy_ogre, guard.positionX, guard.positionY, guard.positionX, guard.positionY);
						has_moved = push_remove(defenitions._crazy_ogre, toX, toY, guard.positionX, guard.positionY);

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
		if (this.checkGuard() == true) {
			return 2;
		}

		return 0;
	}

}

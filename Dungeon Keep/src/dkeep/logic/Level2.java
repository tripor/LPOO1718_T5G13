package dkeep.logic;

import java.util.ArrayList;

import dkeep.character.Club;
import dkeep.character.Guard;

public class Level2 extends GameMap {

	@Override
	public void markPositions() {
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

		// guard = new Ogre();
		this.current_level= new Maps(CurrentLevel.Level.SECOND);
		updateMap();
		guards = new ArrayList<Guard>();

		hero.positionX = 7;
		hero.positionY = 1;
		this.hero.setMy_char("A");
		

		Club hero_club = new Club(map);

		int heroClubPos[] = hero_club.clubNextPosition(hero, this);

		hero_club.positionX = heroClubPos[0];
		hero_club.positionY = heroClubPos[1];


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
	public boolean checkGuard() {
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
		boolean has_moved;
		has_moved = this.hero.push_remove(toX, toY, this);

		for (Club club : hero.clubs) {

			int[] pos = new int[2];

			pos[0] = 0;
			pos[1] = 0;

			for (;;) {

				pos = club.clubNextPosition(hero, this);
				has_moved = club.push_remove(pos[0], pos[1], this);

				if (has_moved) {
					break;
				}
			}
		}

		for (Guard guard : guards) {

			if (guard.stunned > 2) {
				// 0 = no stun;
				// 1 = just stun (1st round);
				// 2 = stun 2nd round;
				guard.stunned = 0;
			}

			if (guard.stunned > 0) {
				guard.stunned++;
			}

			if (guard.stunned == 0) {

				for (;;) {

					int[] guard_new_pos = guard.guardNextPosition(this);

					toX = guard_new_pos[0];
					toY = guard_new_pos[1];

					has_moved = guard.push_remove(toX, toY, this);

					if (has_moved) {
						break;
					}
				}
			}

			// if level 2, move the club also.
			for (Club club : guard.clubs) {

				int[] pos = new int[2];
				pos[0] = 0;
				pos[1] = 0;

				for (;;) {

					pos = club.clubNextPosition(guard, this);
					has_moved = club.push_remove(pos[0], pos[1], this);

					if (has_moved) {
						break;
					}
				}
			}

		}

		if (this.checkGuard() == true) {
			return 2;
		}

		return 0;
	}
	public boolean placeHero(int posX,int posY)
	{
		return this.hero.push_remove(posX, posY, this);
	}

	

}

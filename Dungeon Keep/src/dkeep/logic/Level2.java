package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

import dkeep.character.Club;
import dkeep.character.Guard;
import dkeep.character.Ogre;

public class Level2 extends GameMap {

	@Override
	public void markPositions() {
		map[hero.positionX][hero.positionY] = hero.getMy_char();
		// map[guard.positionX][guard.positionY] = defenitions._crazy_ogre;

		for (Guard g : guards) {
			map[g.positionX][g.positionY] = defenitions._ogre_at_key;

			for (Club c : g.clubs) {
				map[c.positionX][c.positionY] = c.getMy_char();
			}
		}

		for (Club c : hero.clubs) {
			map[c.positionX][c.positionY] = c.getMy_char();
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
		this.hero.setMy_char(defenitions._hero_with_arm);
		

		Club hero_club = new Club(map);
		hero_club.clubNextPosition(this, hero);

		hero.clubs.add(hero_club);
		Random rand = new Random();
		int rand_result = rand.nextInt((2-1) + 1) + 1;
		for(int i=1;i<=rand_result;i++)
		{

			this.getRandomGuard();
		}
		markPositions();
	}

	@Override
	public boolean checkGuard() {
		for (Club c : hero.clubs) {
			for(Guard g:this.guards)
			{
				System.out.println(c.positionX + " " + c.positionY + ":" + g.positionX+ " "+g.positionY);
				if (g.positionX==c.positionX+1 && g.positionY==c.positionY) {
					g.stunned=1;
					g.setMy_char(defenitions._ogre_stunned);
					this.setMap(defenitions._ogre_stunned, g.positionX, g.positionY);
				} else if (g.positionX==c.positionX && g.positionY==c.positionY+1) {
					g.stunned=1;
					g.setMy_char(defenitions._ogre_stunned);
					this.setMap(defenitions._ogre_stunned, g.positionX, g.positionY);
				} else if (g.positionX==c.positionX-1 && g.positionY==c.positionY) {
					g.stunned=1;
					g.setMy_char(defenitions._ogre_stunned);
					this.setMap(defenitions._ogre_stunned, g.positionX, g.positionY);
				} else if (g.positionX==c.positionX && g.positionY==c.positionY-1) {
					g.stunned=1;
					g.setMy_char(defenitions._ogre_stunned);
					this.setMap(defenitions._ogre_stunned, g.positionX, g.positionY);
				}
				System.out.println(g.stunned);
			}
		}

		for (Guard guard : guards) {

			if (!map[guard.positionX][guard.positionY].equals(defenitions._ogre_stunned)) {
				System.out.println(map[guard.positionX + 1][guard.positionY] + " " + map[guard.positionX - 1][guard.positionY]+ " "+ map[guard.positionX][guard.positionY + 1]+ " "+map[guard.positionX][guard.positionY - 1]);
				if (map[guard.positionX + 1][guard.positionY].equals(defenitions._hero_with_arm) || map[guard.positionX + 1][guard.positionY].equals(defenitions._hero_at_key))
					return true;
				if (map[guard.positionX - 1][guard.positionY].equals(defenitions._hero_with_arm) || map[guard.positionX - 1][guard.positionY].equals(defenitions._hero_at_key))
					return true;
				if (map[guard.positionX][guard.positionY + 1].equals(defenitions._hero_with_arm) || map[guard.positionX][guard.positionY + 1].equals(defenitions._hero_at_key))
					return true;
				if (map[guard.positionX][guard.positionY - 1].equals(defenitions._hero_with_arm) || map[guard.positionX][guard.positionY - 1].equals(defenitions._hero_at_key))
					return true;
			}

			for (Club club : guard.clubs) {

				if (map[club.positionX + 1][club.positionY].equals(defenitions._hero_with_arm) || map[club.positionX + 1][club.positionY].equals(defenitions._hero_at_key))
					return true;
				if (map[club.positionX - 1][club.positionY].equals(defenitions._hero_with_arm) || map[club.positionX - 1][club.positionY].equals(defenitions._hero_at_key))
					return true;
				if (map[club.positionX][club.positionY + 1].equals(defenitions._hero_with_arm) || map[club.positionX][club.positionY + 1].equals(defenitions._hero_at_key))
					return true;
				if (map[club.positionX][club.positionY - 1].equals(defenitions._hero_with_arm) || map[club.positionX][club.positionY - 1].equals(defenitions._hero_at_key))
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
			club.clubNextPosition(this, this.hero);
		}

		for (Guard guard : guards) {

			if (guard.stunned > 2) {
				// 0 = no stun;
				// 1 = just stun (1st round);
				// 2 = stun 2nd round;
				guard.stunned = 0;
				guard.setMy_char(defenitions._crazy_ogre);
			}

			if (guard.stunned > 0) {
				guard.stunned++;
				guard.push_remove(guard.positionX, guard.positionY, this);
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
					club.clubNextPosition(this,guard);
				
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

	@Override
	protected void getRandomGuard() {
		Guard g;
		g= new Ogre(map);
		g.positionX=1;
		g.positionY=7;
		
		Club c = new Club(map);
		
		c.clubNextPosition(this, g);

		g.clubs.add(c);

		guards.add(g);
		
	}

	

}

package dkeep.logic;

import java.util.ArrayList;
import java.util.Random;

import dkeep.logic.character.Club;
import dkeep.logic.character.Guard;
import dkeep.logic.character.Ogre;
/**
 * 
 * Class for the Level 2
 *
 */
public class Level2 extends GameMap {

	private int x=1,y=7;
	/**
	 * Sets the position of the hero and the guards on the top layer map
	 */
	public void markPositions() {
		map[hero.positionX][hero.positionY] = hero.getMy_char();
		// map[guard.positionX][guard.positionY] = defenitions._crazy_ogre;

		for (Guard g : guards) {
			map[g.positionX][g.positionY] = g.getMy_char();

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
	/**
	 * Constructor of level 2. Creates a hero and a random number from 1-5 of guards and marks them on the map
	 */
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
		

		Club hero_club = new Club();
		hero_club.clubNextPosition(this, hero);

		hero.clubs.add(hero_club);
		Random rand = new Random();
		int rand_result = rand.nextInt((5-1) + 1) + 1;
		for(int i=1;i<=rand_result;i++)
		{

			this.getRandomGuard();
		}
		markPositions();
	}
	/**
	 * Cronstructor of Level 2. Creates a hero and a amount of guards and marks them on the map
	 * @param guard_count the quantity of guards i want to create
	 */
	public Level2(int guard_count) {
		super();

		// guard = new Ogre();
		this.current_level= new Maps(CurrentLevel.Level.SECOND);
		updateMap();
		guards = new ArrayList<Guard>();

		hero.positionX = 7;
		hero.positionY = 1;
		this.hero.setMy_char(defenitions._hero_with_arm);
		

		Club hero_club = new Club();
		hero_club.clubNextPosition(this, hero);

		hero.clubs.add(hero_club);
		for(int i=1;i<=guard_count;i++)
		{

			this.getRandomGuard();
		}
		markPositions();
	}

	/**
	 * Check is the guard has caught the hero or the hero has been hit by a ogre club and checks if the hero club has it the guard updating it's situation
	 * @return true if caught or false if not
	 */
	public boolean checkGuard() {
		for (Club c : hero.clubs) {
			for(Guard g:this.guards)
			{
				if (g.positionX==c.positionX+1 && g.positionY==c.positionY) {
					g.stunned=1;
					this.setMap(defenitions._ogre_stunned, g.positionX, g.positionY);
				} else if (g.positionX==c.positionX && g.positionY==c.positionY+1) {
					g.stunned=1;
					this.setMap(defenitions._ogre_stunned, g.positionX, g.positionY);
				} else if (g.positionX==c.positionX-1 && g.positionY==c.positionY) {
					g.stunned=1;
					this.setMap(defenitions._ogre_stunned, g.positionX, g.positionY);
				} else if (g.positionX==c.positionX && g.positionY==c.positionY-1) {
					g.stunned=1;
					this.setMap(defenitions._ogre_stunned, g.positionX, g.positionY);
				}
			}
		}

		for (Guard guard : guards) {

			if (!guard.sleep) {
				if (hero.positionX==guard.positionX+1 && hero.positionY==guard.positionY)
					return true;
				if (hero.positionX==guard.positionX-1 && hero.positionY==guard.positionY)
					return true;
				if (hero.positionX==guard.positionX && hero.positionY==guard.positionY+1)
					return true;
				if (hero.positionX==guard.positionX && hero.positionY==guard.positionY-1)
					return true;
			}

			for (Club club : guard.clubs) {

				if (hero.positionX==club.positionX+1 && hero.positionY==club.positionY)
					return true;
				if (hero.positionX==club.positionX-1 && hero.positionY==club.positionY)
					return true;
				if (hero.positionX==club.positionX && hero.positionY==club.positionY+1)
					return true;
				if (hero.positionX==club.positionX && hero.positionY==club.positionY-1)
					return true;
			}

		}
		return false;
	}
	/**
	 * Moves the hero. 1-UP 2-DOWN 3-LEFT 4-RIGHT . Also updates the position of guards and clubs to their next position
	 * @param type_movement the type of movement of the guard 1-4
	 * @return returns 0 if able to move, 1 if it left the map, 2 if caught 
	 */
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
		if (toX < 0 || toY < 0 || toX>this.map.length || toY>this.map[0].length) {
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
	/**
	 * Places the hero in the position i want to
	 * @param posX the X position i want the hero to be
	 * @param posY the Y position i want the hero to be
	 */
	public void placeHero(int posX,int posY)
	{
		this.hero.push_remove(posX, posY, this);
	}
	/**
	 * Places all the guards in a position i want
	 * @param posX The X position i want the guards to be in
	 * @param posY The Y position i want the guards to be in
	 */
	public void placeGuard(int posX,int posY)
	{
		for (Guard guard : this.guards)
		{
			guard.push_remove(posX, posY,this);
		}
	}

	/**
	 * Creates a guard with a random position that doesn't overlap the hero area of movement. Also create the guards club
	 */
	public void getRandomGuard() {
		Guard g;
		g= new Ogre();
		Random rand = new Random();
		int posX = rand.nextInt(this.getMap().length-2) + 1;
		int posY = rand.nextInt(this.getMap()[0].length-2) + 1;
		while(posY >= hero.positionY-1 && hero.positionY+1 >= posY && posX >= hero.positionX-1 && hero.positionX+1 >= posX)
		{
			posX = rand.nextInt(this.getMap().length-2) + 1;
			posY = rand.nextInt(this.getMap()[0].length-2) + 1;
		}
		g.positionX=posX;
		g.positionY=posY;
		x++;
		
		Club c = new Club();
		
		c.clubNextPosition(this, g);

		g.clubs.add(c);

		guards.add(g);
		
	}

	

}

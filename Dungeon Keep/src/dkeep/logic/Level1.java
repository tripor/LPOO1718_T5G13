package dkeep.logic;

import java.util.Random;

import dkeep.logic.character.Drunken;
import dkeep.logic.character.Guard;
import dkeep.logic.character.Rookie;
import dkeep.logic.character.Suspicious;
/**
 * 
 * Class for the Level 1
 *
 */
public class Level1 extends GameMap {

	/**
	 * Creates a random guard of 3 types: Rookie, Drunken, Suspicious; and adds it to the ArrayList
	 */
	public void getRandomGuard() {
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
			g = new Rookie();
			// // it is the default value of g.
			break;
		case 2:
			g = new Drunken();
			break;
		case 3:
			g = new Suspicious();
			break;
		default:
			g = new Rookie();
		}

		guards.add(g);
	}
	/**
	 * Creates a guard and add it to the ArrayList guards
	 * @param guard String:the type of guard i want to create. 
	 */
	private void getSelectedGuard(String guard)
	{
		Guard g;
		if(guard.equals("Rookie"))
		{
			g = new Rookie();
		}
		else if(guard.equals("Drunken"))
		{
			g = new Drunken();
		}
		else if(guard.equals("Suspicious"))
		{
			g = new Suspicious();
		}
		else
		{
			g = new Rookie();
		}
		
		guards.add(g);
	}
	/**
	 * Sets the position of the hero and the guards on the top layer map
	 */
	public void markPositions() {

		// mark default position in the map, for hero & guard
		map[hero.positionX][hero.positionY] = defenitions._hero;
		// level 1

		// map[guard.positionX][guard.positionY] = defenitions._guard;

		for (Guard g : guards) {
			map[g.positionX][g.positionY] = defenitions._guard;

		}

	}
	/**
	 * Constructor of level 1. Creates a hero and a random guard
	 */
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
	/**
	 * Constructor of level 1. Creates a hero and a guard 
	 * @param guard_type String: the type of guard i want to create
	 */
	public Level1(String guard_type) {
		current_level = new Maps();
		this.updateMap();

		hero.positionX = 1;
		hero.positionY = 1;

		// LV 1, 1 Guard, Random Role
		this.getSelectedGuard(guard_type);

		guards.get(0).positionX = 1;
		guards.get(0).positionY = 8;

		// guard.positionX = 1;
		// guard.positionY = 8;

		this.markPositions();
	}

	/**
	 * Checks if the hero has been caught by the guard
	 * @return true if caught or false if not
	 */
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

	/**
	 * Moves the hero. 1-UP 2-DOWN 3-LEFT 4-RIGHT . Also updates the position of the guard
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
		if (toX < 0 || toY < 0) {
			return 1;
		}
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
	/**
	 * Places the hero in a position that i want
	 * @param posX position X where i want the hero to be
	 * @param posY position Y where i want the hero to be
	 */
	public void placeHero(int posX,int posY)
	{
		this.hero.push_remove(posX, posY,this);
	}
	/**
	 * Places all the guards in the i want to
	 * @param posX position X where i want the guards to be
	 * @param posY position Y where i want the guards to be
	 */
	public void placeGuard(int posX,int posY)
	{
		for (Guard guard : this.guards)
		{
			guard.push_remove(posX, posY,this);
		}
	}

}

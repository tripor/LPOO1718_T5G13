package dkeep.test;


import dkeep.character.Club;
import dkeep.logic.*;

public class TestLevel2 extends Level2 {
	
	
	public TestLevel2()
	{
		super();
	}

	public boolean placeHero(int posX,int posY)
	{
		boolean has_moved = push_remove(defenitions._hero_with_arm, posX, posY, hero.positionX, hero.positionY);

		if (has_moved) {
			hero.positionX = posX;
			hero.positionY = posY;
			return true;
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
		return 0;
	}
	
	public int moveHeroToWithClub(int type_movement)
	{
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
		return 0;
	}
}

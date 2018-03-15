package dkeep.test;

import dkeep.logic.*;

public class TestLevel1 extends Level1 {
	
	public TestLevel1()
	{
		super();
	}
	
	/*public boolean placeHero(int posX,int posY)
	{
		boolean has_moved = push_remove(defenitions._hero, posX, posY, hero.positionX, hero.positionY);

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

		boolean has_moved = push_remove(defenitions._hero, toX, toY, hero.positionX, hero.positionY);

		if (has_moved) {
			hero.positionX = toX;
			hero.positionY = toY;
		}
		
		return 0;
	}*/
}

package dkeep.character;

import dkeep.logic.*;

public class Club extends Ogre {

	public int[] clubNextPosition(Character guard, GameMap gamearea) {

		System.out.print("\n[Club] clubNextPosition(" + guard.positionX + "," + guard.positionY + ")");
		return guardNextPosition(guard, gamearea);
	}

	public Club(String[][] map){
		super(map);
	}
}

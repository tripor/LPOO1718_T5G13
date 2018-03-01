package dkeep.character;

import dkeep.logic.*;

public class Club extends Ogre {

	public int[] clubNextPosition(Guard guard, GameMap gamearea) {

		return guardNextPosition(guard, gamearea);
	}

	public Club(String[][] map){
		super(map);
	}
}

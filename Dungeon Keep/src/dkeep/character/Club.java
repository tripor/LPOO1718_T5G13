package dkeep.character;

import dkeep.logic.*;

public class Club extends Guard {

	public int[] clubNextPosition(Guard guard, GameMap gamearea) {

		return guardNextPosition(guard, gamearea);
	}

	public Club(){
		super();
	}
}

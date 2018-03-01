package dkeep.character;

import java.util.ArrayList;

public abstract class Character {
	
	public int positionX;
	public int positionY;

	public ArrayList<Club> clubs = new ArrayList<Club>();

	public Character(){
		System.out.print("\nInit Character.\n");
	}
	
}

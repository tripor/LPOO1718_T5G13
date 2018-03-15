package dkeep.character;

import java.util.ArrayList;

import dkeep.logic.GameMap;

public abstract class Character {
	
	public String getMy_char() {
		return my_char;
	}
	public void setMy_char(String my_char) {
		this.my_char = my_char;
	}
	public int positionX;
	public int positionY;
	protected String my_char;

	public ArrayList<Club> clubs = new ArrayList<Club>();

	// Move and delete the "trail"
	public abstract boolean push_remove(int byeX, int byeY, GameMap game);
	public Character(){
		System.out.println("Init Character.");
	}
	
}

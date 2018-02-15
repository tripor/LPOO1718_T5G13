package code;

public class Character {
	
	public int positionX;
	public int positionY;
	
	private String[] path= {"L","D","D","D","D","L","L","L","L","L","L","D","R","R","R","R","R","R","R","U","U","U","U","U"};
	private int position_path=0;
	/*
	 * 1 UP
	 * 2 Down
	 * 3 Left
	 * 4 Right
	 */
	public int guardNextPosition() {
		position_path++;
		if(position_path>path.length)position_path=1;
		switch (path[position_path-1]) {
		case "L":
			return 3;
		case "D":
			return 2;
		case "R":
			return 4;
		case "U":
			return 1;
		default:
			return 0;
		}
	}
	
	public Character()
	{
		
	}
	
}

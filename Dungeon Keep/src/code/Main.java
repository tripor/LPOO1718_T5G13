package code;

public class Main {

	static String[][] map = new String[10][10];

	public static void push_remove(String str, int atX, int atY, int byeX, int byeY){
		
		if(atX >= 0){
			System.out.print("\nPush: " + atX + ", " + atY);
			map[atX][atY] = str;
		}
		if(byeX >= 0){
			System.out.print("\nRemove: " + byeX + ", " + byeY);
			map[byeX][byeY] = " ";
		}
	}

	public static void push(String str, int atX, int atY){
		push_remove(str, atX, atY, -1, -1);
	}

	public static void remove(int byeX, int byeY){
		push_remove("", -1, -1, byeX, byeY);
	}

	public static void render(){
		
<<<<<<< Updated upstream
		while(sair==false)
		{
			printscreen();
			System.out.print("What is the movement you want to make?(W-Up,S-Down,A-Left,D-Right,P-Exit): ");
			input=s.nextLine();
			int movement=transformInput(input);
			if (movement == 0)// exit case
				break;
			else if (movement == -1)//error state
			{
				System.out.println("An error has ocurred during the input. Please provide a valid input.");
				continue;
			}
			switch(movement)
			{
			///asads
=======
		System.out.print("\n\n\n\n");

		for (int i=0; i<map.length; i++) {
			for (int j=0; j<map[i].length; j++) {

				if(map[i][j] == null){
					map[i][j] = " ";
				}
				System.out.print(map[i][j] + "|");
>>>>>>> Stashed changes
			}
			System.out.print("\n");
		}
	}

	public static void main(String[] args) {
		push("X", 2,2);
		push("I", 4,5);
		render();
		remove(2,2);
		render();
		push_remove("X", 7,3, 4,5);
		render();
	}

}

package code;

public class Main {

	public static void main(String[] args) {
		String[][] map= {{"X","X","X","X","X","X","X","X","X","X"},
						 {"X","H"," "," ","I"," ","X"," ","G","X"},
						 {"X","X","X"," ","X","X","X"," "," ","X"},
						 {"X"," ","I"," ","I"," ","X"," "," ","X"},
						 {"X","X","X"," ","X","X","X"," "," ","X"},
						 {"I"," "," "," "," "," "," "," "," ","X"},
						 {"I"," "," "," "," "," "," "," "," ","X"},
						 {"X","X","X"," ","X","X","X","X"," ","X"},
						 {"X"," ","I"," ","I"," ","X"," "," ","X"},
						 {"X","X","X","X","X","X","X","X","X","X"}};
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(map[i][j]);
			}
			System.out.print("\n");
		}

	}

}

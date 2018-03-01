package dkeep.main;

import dkeep.logic.*;
import dkeep.cli.*;

public class Main {

	public static void main(String[] args) {

		GameMap map = new GameMap();
		UserInput user = new UserInput();

		boolean sair = false;

		// FOR TESTING
		map.current_level.changeLevel(CurrentLevel.Level.SECOND);
		map.updateLevel();
		
		while(sair==false)
		{
			map.printscreen();
			
			//user input handler
			int movement=user.handler();
			//-1=invalid input
			if(movement==-1)
			{
				continue;
			}//0=exit program
			else if(movement==0)
			{
				break;
			}
			else
			{
				int state=map.moveHeroTo(movement);
				if(state==1)
				{
					if(map.current_level.game_level.getValue()==1){
						map.current_level.changeLevel(CurrentLevel.Level.SECOND);
						map.updateLevel();
					}
					else{
						System.out.println("Victory");
						break;
					}
				}
				else if(state==2)
				{
					map.printscreen();
					user.closeReader();
					System.out.println("Defeat. You were caught!!!");
					break;
				}
			}
			
		}
	}

}

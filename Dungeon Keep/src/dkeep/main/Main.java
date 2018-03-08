package dkeep.main;

import dkeep.logic.*;
import dkeep.cli.*;

public class Main {

	public static void main(String[] args) {

		System.out.println("Main");
		
		GameMap map = new Level1();
		UserInput user = new UserInput();

		boolean sair = false;

		
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
					if(map.getCurrent_level().game_level.getValue()==1){
						map= new Level2();
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

package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.*;

public class TestDungeonGameLogic {
	
	@Test
	public void testChangeLevel()
	{
		CurrentLevel current_level=new Maps();
		assertEquals("The game doesn't star at level 1",CurrentLevel.Level.FIRST,current_level.game_level);
		current_level.changeLevel(CurrentLevel.Level.SECOND);
		assertEquals("The game didn't change to level 2",CurrentLevel.Level.SECOND,current_level.game_level);
		current_level.changeLevel(CurrentLevel.Level.TEST);
		assertEquals("The game didn't change to test level",CurrentLevel.Level.TEST,current_level.game_level);
	}
	
	@Test
	public void testMoveHeroIntoToFreeCell()
	{
		GameMap game=new GameMap();
		game.current_level.changeLevel(CurrentLevel.Level.TEST);
		game.updateLevel();
		
		
	}

}

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
	}
	
	@Test
	public void testMoveHeroIntoToFreeCell()
	{
		CurrentLevel current_level=new Maps();
		
	}

}
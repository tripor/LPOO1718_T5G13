package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.*;
/*Transforms the input:
 * W-1 UP
 * S-2 Down
 * A-3 Left
 * D-4 Right
 * P(Exit)-0
 * Error- -1
 */
public class TestDungeonGameLogic {	
	@Test
	public void testChangeLevel()
	{
		CurrentLevel current_level=new Maps();
		assertEquals(CurrentLevel.Level.FIRST,current_level.game_level);
		current_level.changeLevel(CurrentLevel.Level.SECOND);
		assertEquals(CurrentLevel.Level.SECOND,current_level.game_level);
		current_level.changeLevel(CurrentLevel.Level.TEST1);
		assertEquals(CurrentLevel.Level.TEST1,current_level.game_level);
		current_level.changeLevel(CurrentLevel.Level.TEST2);
		assertEquals(CurrentLevel.Level.TEST2,current_level.game_level);
	}
	
	@Test
	public void testMoveHeroIntoToFreeCellLevel1()
	{
		TestLevel1 jogo=new TestLevel1();
		assertEquals(defenitions._hero,jogo.getMap()[1][1]);
		assertEquals(1,jogo.getHero().positionX);
		assertEquals(1,jogo.getHero().positionY);
		jogo.moveHeroTo(4);//Move right
		assertEquals(defenitions._empty_cell,jogo.getMap()[1][1]);
		assertEquals(defenitions._hero,jogo.getMap()[1][2]);
		assertEquals(1,jogo.getHero().positionX);
		assertEquals(2,jogo.getHero().positionY);
	}
	@Test
	public void testMoveHeroIntoToFreeCellLevel2()
	{
		TestLevel2 jogo=new TestLevel2();
		assertEquals(defenitions._hero_with_arm,jogo.getMap()[7][1]);
		assertEquals(7,jogo.getHero().positionX);
		assertEquals(1,jogo.getHero().positionY);
		jogo.moveHeroTo(4);
		assertEquals(defenitions._empty_cell,jogo.getMap()[7][1]);
		assertEquals(defenitions._hero_with_arm,jogo.getMap()[7][2]);   
		assertEquals(7,jogo.getHero().positionX);
		assertEquals(2,jogo.getHero().positionY); 
	}
	
	@Test
	public void testMoveHeroToWallLevel1()
	{
		TestLevel1 jogo=new TestLevel1();
		assertEquals(defenitions._hero,jogo.getMap()[1][1]);
		jogo.moveHeroTo(3);//Move left
		assertEquals(defenitions._hero,jogo.getMap()[1][1]); 
	}
	@Test
	public void testMoveHeroToWallLevel2()
	{
		TestLevel2 jogo=new TestLevel2();
		assertEquals(defenitions._hero_with_arm,jogo.getMap()[7][1]);
		jogo.moveHeroTo(3);
		assertEquals(defenitions._hero_with_arm,jogo.getMap()[7][1]); 
	}
	
	@Test
	public void testGuardCaughtLevel1()
	{
		TestLevel1 jogo=new TestLevel1();
		assertTrue(jogo.placeHero(2,8)); 
		assertEquals(defenitions._hero,jogo.getMap()[2][8]); 
		assertEquals(defenitions._guard,jogo.getMap()[1][8]);
		assertTrue(jogo.checkGuard());
		
	}
	@Test
	public void testGuardCaughtLevel2()
	{
		TestLevel2 jogo=new TestLevel2();
		assertTrue(jogo.placeHero(2, 6));
		assertEquals(defenitions._hero_with_arm,jogo.getMap()[2][6]);
		assertEquals(0,jogo.moveHeroTo(4));
		assertEquals(defenitions._hero_with_arm,jogo.getMap()[2][7]);
		assertEquals(defenitions._crazy_ogre,jogo.getMap()[1][7]);
		assertTrue(jogo.checkGuard());
	}

}
package dkeep.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import dkeep.character.Club;
import dkeep.character.Guard;
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
	public void testChangeLevel() {
		CurrentLevel current_level = new Maps();
		assertEquals(CurrentLevel.Level.FIRST, current_level.game_level);
		current_level.changeLevel(CurrentLevel.Level.SECOND);
		assertEquals(CurrentLevel.Level.SECOND, current_level.game_level);
	}

	@Test
	public void testMoveHeroIntoToFreeCellLevel1() {
		Level1 jogo = new Level1();
		jogo.markPositions();
		assertEquals(defenitions._hero, jogo.getMap()[1][1]);
		assertEquals(defenitions._hero,jogo.getHero().getMy_char());
		assertEquals(1, jogo.getHero().positionX);
		assertEquals(1, jogo.getHero().positionY);
		jogo.moveHeroTo(4);// Move right
		assertEquals(defenitions._empty_cell, jogo.getMap()[1][1]);
		assertEquals(defenitions._hero, jogo.getMap()[1][2]);
		assertEquals(1, jogo.getHero().positionX);
		assertEquals(2, jogo.getHero().positionY);
		jogo.moveHeroTo(3);
		assertEquals(defenitions._empty_cell, jogo.getMap()[1][2]);
		assertEquals(defenitions._hero, jogo.getMap()[1][1]);
		assertEquals(1, jogo.getHero().positionX);
		assertEquals(1, jogo.getHero().positionY);
		jogo.moveHeroTo(4);
		jogo.moveHeroTo(4);
		jogo.moveHeroTo(2);
		assertEquals(defenitions._hero, jogo.getMap()[2][3]);
		assertEquals(2, jogo.getHero().positionX);
		assertEquals(3, jogo.getHero().positionY);
		jogo.moveHeroTo(1);
		assertEquals(defenitions._hero, jogo.getMap()[1][3]);
		assertEquals(1, jogo.getHero().positionX);
		assertEquals(3, jogo.getHero().positionY);
		
		jogo = new Level1("Rookie");
		jogo.markPositions();
		assertEquals(defenitions._hero, jogo.getMap()[1][1]);
		assertEquals(defenitions._hero,jogo.getHero().getMy_char());
		assertEquals(1, jogo.getHero().positionX);
		assertEquals(1, jogo.getHero().positionY);
		for(Guard it:jogo.getGuards())
		{
			assertEquals(1,it.positionX);
			assertEquals(8,it.positionY);
			assertEquals("rookie",it.typeGuard());
		}
		
		jogo = new Level1("Suspicious");
		jogo.markPositions();
		assertEquals(defenitions._hero, jogo.getMap()[1][1]);
		assertEquals(defenitions._hero,jogo.getHero().getMy_char());
		assertEquals(1, jogo.getHero().positionX);
		assertEquals(1, jogo.getHero().positionY);
		for(Guard it:jogo.getGuards())
		{
			assertEquals(1,it.positionX);
			assertEquals(8,it.positionY);
			assertEquals("suspicious",it.typeGuard());
		}
		
		jogo = new Level1("Drunken");
		jogo.markPositions();
		assertEquals(defenitions._hero, jogo.getMap()[1][1]);
		assertEquals(defenitions._hero,jogo.getHero().getMy_char());
		assertEquals(1, jogo.getHero().positionX);
		assertEquals(1, jogo.getHero().positionY);
		for(Guard it:jogo.getGuards())
		{
			assertEquals(1,it.positionX);
			assertEquals(8,it.positionY);
			assertEquals("drunken",it.typeGuard());
		}
	}

	@Test
	public void testMoveHeroIntoToFreeCellLevel2() {
		Level2 jogo = new Level2();
		jogo.markPositions();
		jogo.getHero().clubs.clear();
		jogo.getGuards().clear();
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[7][1]);
		assertEquals(defenitions._hero_with_arm, jogo.getHero().getMy_char());
		assertEquals(7, jogo.getHero().positionX);
		assertEquals(1, jogo.getHero().positionY);
		assertEquals(0,jogo.moveHeroTo(4));
		assertEquals(defenitions._empty_cell, jogo.getMap()[7][1]);
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[7][2]);
		assertEquals(7, jogo.getHero().positionX);
		assertEquals(2, jogo.getHero().positionY);
		assertEquals(0,jogo.moveHeroTo(3));
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[7][1]);
		assertEquals(7, jogo.getHero().positionX);
		assertEquals(1, jogo.getHero().positionY);
		assertEquals(0,jogo.moveHeroTo(1));
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[6][1]);
		assertEquals(6, jogo.getHero().positionX);
		assertEquals(1, jogo.getHero().positionY);
		assertEquals(0,jogo.moveHeroTo(2));
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[7][1]);
		assertEquals(7, jogo.getHero().positionX);
		assertEquals(1, jogo.getHero().positionY);
	}

	@Test
	public void testMoveHeroToWallLevel1() {
		Level1 jogo = new Level1();
		assertEquals(defenitions._hero, jogo.getMap()[1][1]);
		assertEquals(0,jogo.moveHeroTo(3));
		assertEquals(defenitions._hero, jogo.getMap()[1][1]);
		jogo.moveHeroTo(1);
		assertEquals(defenitions._hero, jogo.getMap()[1][1]);
		jogo.moveHeroTo(2);
		assertEquals(defenitions._hero, jogo.getMap()[1][1]);
		jogo.moveHeroTo(4);
		jogo.moveHeroTo(4);
		jogo.moveHeroTo(4);
		assertEquals(defenitions._hero, jogo.getMap()[1][3]);
	}

	@Test
	public void testMoveHeroToWallLevel2() {
		Level2 jogo = new Level2();
		jogo.getHero().clubs.clear();
		jogo.getGuards().clear();
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[7][1]);
		assertEquals(0,jogo.moveHeroTo(3));
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[7][1]);
		jogo.moveHeroTo(2);
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[7][1]);
		assertTrue(jogo.placeHero(1, 1));
		jogo.moveHeroTo(1);
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[1][1]);
		assertTrue(jogo.placeHero(7, 7));
		jogo.moveHeroTo(4);
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[7][7]);
	}

	@Test
	public void testGuardCaughtLevel1() {
		Level1 jogo = new Level1();
		assertTrue(jogo.placeHero(2, 8));
		assertTrue(jogo.placeGuard(1, 8));
		assertEquals(defenitions._hero, jogo.getMap()[2][8]);
		assertEquals(defenitions._guard, jogo.getMap()[1][8]);
		assertTrue(jogo.checkGuard());
		jogo = new Level1();
		assertTrue(jogo.placeHero(1, 7));
		assertTrue(jogo.placeGuard(1, 8));
		assertEquals(defenitions._hero, jogo.getMap()[1][7]);
		assertEquals(defenitions._guard, jogo.getMap()[1][8]);
		assertTrue(jogo.checkGuard());
		jogo = new Level1();
		assertTrue(jogo.placeHero(2, 8));
		assertTrue(jogo.placeGuard(2, 7));
		assertEquals(defenitions._hero, jogo.getMap()[2][8]);
		assertEquals(defenitions._guard, jogo.getMap()[2][7]);
		assertTrue(jogo.checkGuard());
		jogo = new Level1();
		assertTrue(jogo.placeHero(2, 8));
		assertTrue(jogo.placeGuard(3, 8));
		assertEquals(defenitions._hero, jogo.getMap()[2][8]);
		assertEquals(defenitions._guard, jogo.getMap()[3][8]);
		assertTrue(jogo.checkGuard());
		
		jogo= new Level1("Rookie");
		assertTrue(jogo.placeHero(2, 8));
		assertEquals(2,jogo.moveHeroTo(3));

	}

	@Test
	public void testGuardCaughtLevel2() {
		Level2 jogo = new Level2();
		jogo.getHero().clubs.clear();
		for(Guard it:jogo.getGuards())
		{
			it.clubs.clear();
		}
		assertTrue(jogo.placeHero(4, 5));
		assertTrue(jogo.placeGuard(5, 5));
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[4][5]);
		assertEquals(defenitions._crazy_ogre, jogo.getMap()[5][5]);
		assertTrue(jogo.checkGuard());
		assertTrue(jogo.placeHero(6, 5));
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[6][5]);
		assertEquals(defenitions._crazy_ogre, jogo.getMap()[5][5]);
		assertTrue(jogo.checkGuard());
		assertTrue(jogo.placeHero(5, 4));
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[5][4]);
		assertEquals(defenitions._crazy_ogre, jogo.getMap()[5][5]);
		assertTrue(jogo.checkGuard());
		assertTrue(jogo.placeHero(5, 6));
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[5][6]);
		assertEquals(defenitions._crazy_ogre, jogo.getMap()[5][5]);
		assertTrue(jogo.checkGuard());
		
		jogo = new Level2();
		jogo.getHero().clubs.clear();
		for(Guard it:jogo.getGuards())
		{
			for(Club et:it.clubs)
			{
				et.positionX=5;
				et.positionY=5;
			}
		}
		assertTrue(jogo.placeHero(4, 5));
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[4][5]);
		assertTrue(jogo.checkGuard());
		assertTrue(jogo.placeHero(6, 5));
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[6][5]);
		assertTrue(jogo.checkGuard());
		assertTrue(jogo.placeHero(5, 4));
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[5][4]);
		assertTrue(jogo.checkGuard());
		assertTrue(jogo.placeHero(5, 6));
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[5][6]);
		assertTrue(jogo.checkGuard());
	}

	@Test
	public void testHeroCantExitLevel1() {
		Level1 jogo = new Level1();
		jogo.placeHero(5, 1);
		jogo.moveHeroTo(3);
		assertEquals(defenitions._hero, jogo.getMap()[5][1]);
	}

	@Test
	public void testHeroCantExitLevel2() {
		Level2 jogo = new Level2();
		jogo.placeHero(1, 1);
		jogo.moveHeroTo(3);
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[1][1]);
	}

	@Test
	public void testOpenDoorsLevel1() {
		Level1 jogo = new Level1();
		jogo.placeHero(8, 8);
		jogo.moveHeroTo(3);
		assertEquals(defenitions._opened_door, jogo.getMap()[5][0]);
		assertEquals(defenitions._opened_door, jogo.getMap()[6][0]);
	}

	@Test
	public void testPickKeyLevel2() {
		Level2 jogo = new Level2();
		jogo.setCopied_map("k", 1, 2);
		jogo.setMap("k", 1, 2);
		assertEquals("k", jogo.getMap()[1][2]);
		assertEquals("k", jogo.getCopied_map()[1][2]);
		jogo.placeHero(1, 1);
		jogo.moveHeroTo(4);
		assertEquals(defenitions._hero_at_key, jogo.getMap()[1][2]);
		jogo.moveHeroTo(3);
		if(jogo.getMap()[1][2].equals(defenitions._empty_cell) || jogo.getMap()[1][2].equals(defenitions._hero_club))
		{
			
		}
		else
		{
			System.out.println("failed pick level 2: "+jogo.getMap()[1][2]);
			fail();
		}
		assertEquals(defenitions._hero_at_key, jogo.getMap()[1][1]);

	}

	@Test
	public void testHeroLeaveLevel1() {
		Level1 jogo = new Level1();
		jogo.placeHero(8, 8);
		jogo.moveHeroTo(3);
		jogo.placeHero(5, 1);
		jogo.moveHeroTo(3);
		assertEquals(defenitions._hero, jogo.getMap()[5][0]);
		assertEquals(1, jogo.moveHeroTo(3));

	}

	@Test
	public void testHeroLeaveLevel2() {
		Level2 jogo = new Level2();

		jogo.setCopied_map("k", 1, 2);
		jogo.setMap("k", 1, 2);
		jogo.placeHero(1, 1);
		jogo.moveHeroTo(4);
		jogo.moveHeroTo(3);
		jogo.moveHeroTo(3);
		assertEquals(defenitions._hero_at_key, jogo.getMap()[1][1]);
		assertEquals(defenitions._opened_door, jogo.getMap()[1][0]);
		jogo.moveHeroTo(3);
		assertEquals(defenitions._hero_at_key, jogo.getMap()[1][0]);
		assertEquals(1, jogo.moveHeroTo(3));
	}

	@Test
	public void testGuardMovementLevel1() {
		int[] x= new int[] {1,1,2,3,4,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,5,4,3,2};
		int[] y= new int[] {8,7,7,7,7,7,6,5,4,3,2,1,1,2,3,4,5,6,7,8,8,8,8,8};
		Level1 jogo = new Level1("Rookie");
		for(int i=0;i<x.length;i++)
		{
			for(Guard it:jogo.getGuards())
			{
				assertEquals(x[i],it.positionX);
				assertEquals(y[i],it.positionY);
			}
			jogo.moveHeroTo(3);
		}
		jogo = new Level1("Suspicious");
		int i;
		boolean sair=false;
		for(i=0;;i++)
		{
			if(i==x.length) i=0;
			for(Guard it:jogo.getGuards())
			{
				if(x[i]!=it.positionX) {
					sair=true;
				}
				if(y[i]!=it.positionY) {
					sair=true;
				}
			}
			if(sair)break;
			jogo.moveHeroTo(3);
		}
		i--;
		i--;
		if(i<0)i=x.length-1;
		for(Guard it:jogo.getGuards())
		{
			assertFalse(it.foward_walking);
			assertEquals(x[i],it.positionX);
			assertEquals(y[i],it.positionY);
			sair=false;
			for(;;i--)
			{
				if(i<0) i=x.length-1;
				for(Guard et:jogo.getGuards())
				{
					if(x[i]!=et.positionX) {
						sair=true;
					}
					if(y[i]!=et.positionY) {
						sair=true;
					}
				}
				if(sair)break;
				jogo.moveHeroTo(3);
			}
		}
		i++;
		i++;
		if(i==x.length)i=0;
		for(Guard it:jogo.getGuards())
		{
			assertTrue(it.foward_walking);
			assertEquals(x[i],it.positionX);
			assertEquals(y[i],it.positionY);
		}
		jogo = new Level1("Drunken");
		sair=false;
		for(i=0;;i++)
		{
			if(i==x.length) i=0;
			for(Guard it:jogo.getGuards())
			{
				if(x[i]!=it.positionX) {
					sair=true;
				}
				if(y[i]!=it.positionY) {
					sair=true;
				}
			}
			if(sair)break;
			jogo.moveHeroTo(3);
		}
		i--;
		if(i<0)i=x.length-1;
		for(Guard it:jogo.getGuards())
		{
			assertTrue(it.sleep);
		}
		
	}

	@Test
	public void testRandomLevel2() {
		Level2 jogo = new Level2();
		int posX = jogo.getGuards().get(0).positionX, posY = jogo.getGuards().get(0).positionY;
		jogo.moveHeroTo(4);
		if (jogo.getMap()[posX][posY].equals(jogo.getCopied_map()[posX][posY])
				|| jogo.getMap()[posX][posY].equals(defenitions._club_at_key)
				|| jogo.getMap()[posX][posY].equals(defenitions._ogre_club)) {

		} else {
			jogo.printscreen();
			fail();
		}
		if (jogo.getMap()[posX][posY + 1].equals(defenitions._crazy_ogre)
				|| jogo.getMap()[posX][posY + 1].equals(defenitions._ogre_at_key)) {

		} else if (jogo.getMap()[posX][posY - 1].equals(defenitions._crazy_ogre)
				|| jogo.getMap()[posX][posY - 1].equals(defenitions._ogre_at_key)) {

		} else if (jogo.getMap()[posX + 1][posY].equals(defenitions._crazy_ogre)
				|| jogo.getMap()[posX + 1][posY].equals(defenitions._ogre_at_key)) {

		} else if (jogo.getMap()[posX - 1][posY].equals(defenitions._crazy_ogre)
				|| jogo.getMap()[posX - 1][posY].equals(defenitions._ogre_at_key)) {

		} else {
			jogo.printscreen();
			fail();
		}
		posX = jogo.getGuards().get(0).positionX;
		posY = jogo.getGuards().get(0).positionY;
		if (jogo.getMap()[posX][posY + 1].equals(defenitions._club_at_key)
				|| jogo.getMap()[posX][posY + 1].equals(defenitions._ogre_club)) {

		} else if (jogo.getMap()[posX][posY - 1].equals(defenitions._club_at_key)
				|| jogo.getMap()[posX][posY - 1].equals(defenitions._ogre_club)) {

		} else if (jogo.getMap()[posX + 1][posY].equals(defenitions._club_at_key)
				|| jogo.getMap()[posX + 1][posY].equals(defenitions._ogre_club)) {

		} else if (jogo.getMap()[posX - 1][posY].equals(defenitions._club_at_key)
				|| jogo.getMap()[posX - 1][posY].equals(defenitions._ogre_club)) {

		} else {
			jogo.printscreen();
			System.out.println(jogo.getGuards().get(0).clubs.get(0).positionX + " " + jogo.getGuards().get(0).clubs.get(0).positionY+ " " + jogo.getMap()[jogo.getGuards().get(0).clubs.get(0).positionX][jogo.getGuards().get(0).clubs.get(0).positionY]);
			System.out.println(jogo.getGuards().get(1).clubs.get(0).positionX + " " + jogo.getGuards().get(1).clubs.get(0).positionY+ " " + jogo.getMap()[jogo.getGuards().get(1).clubs.get(0).positionX][jogo.getGuards().get(1).clubs.get(0).positionY]);
			fail();
		}
		jogo.moveHeroTo(3);
		if (jogo.getMap()[posX][posY].equals(jogo.getCopied_map()[posX][posY])
				|| jogo.getMap()[posX][posY].equals(defenitions._club_at_key)
				|| jogo.getMap()[posX][posY].equals(defenitions._ogre_club)) {

		} else {
			jogo.printscreen();
			fail();
		}
		if (jogo.getMap()[posX][posY + 1].equals(defenitions._crazy_ogre)
				|| jogo.getMap()[posX][posY + 1].equals(defenitions._ogre_at_key)) {

		} else if (jogo.getMap()[posX][posY - 1].equals(defenitions._crazy_ogre)
				|| jogo.getMap()[posX][posY - 1].equals(defenitions._ogre_at_key)) {

		} else if (jogo.getMap()[posX + 1][posY].equals(defenitions._crazy_ogre)
				|| jogo.getMap()[posX + 1][posY].equals(defenitions._ogre_at_key)) {

		} else if (jogo.getMap()[posX - 1][posY].equals(defenitions._crazy_ogre)
				|| jogo.getMap()[posX - 1][posY].equals(defenitions._ogre_at_key)) {

		} else {
			jogo.printscreen();
			fail();
		}
		posX = jogo.getGuards().get(0).positionX;
		posY = jogo.getGuards().get(0).positionY;
		if (jogo.getMap()[posX][posY + 1].equals(defenitions._club_at_key)
				|| jogo.getMap()[posX][posY + 1].equals(defenitions._ogre_club)) {

		} else if (jogo.getMap()[posX][posY - 1].equals(defenitions._club_at_key)
				|| jogo.getMap()[posX][posY - 1].equals(defenitions._ogre_club)) {

		} else if (jogo.getMap()[posX + 1][posY].equals(defenitions._club_at_key)
				|| jogo.getMap()[posX + 1][posY].equals(defenitions._ogre_club)) {

		} else if (jogo.getMap()[posX - 1][posY].equals(defenitions._club_at_key)
				|| jogo.getMap()[posX - 1][posY].equals(defenitions._ogre_club)) {

		} else {
			jogo.printscreen();
			fail();
		}
	}
	@Test
	public void testStun()
	{
		Level2 jogo = new Level2(1);
		for(Club it:jogo.getHero().clubs)
		{
			it.positionX=4;
			it.positionY=5;
		}
		for(Guard it:jogo.getGuards())
		{
			it.positionX=5;
			it.positionY=5;
		}
		jogo.checkGuard();
		for(Guard it:jogo.getGuards())
		{
			it.sleep=true;
			assertEquals(defenitions._ogre_stunned,jogo.getMap()[it.positionX][it.positionY]);
		}
		
		jogo = new Level2(1);
		for(Club it:jogo.getHero().clubs)
		{
			it.positionX=6;
			it.positionY=5;
		}
		for(Guard it:jogo.getGuards())
		{
			it.positionX=5;
			it.positionY=5;
		}
		jogo.checkGuard();
		for(Guard it:jogo.getGuards())
		{
			it.sleep=true;
			assertEquals(defenitions._ogre_stunned,jogo.getMap()[it.positionX][it.positionY]);
		}
		
		jogo = new Level2(1);
		for(Club it:jogo.getHero().clubs)
		{
			it.positionX=5;
			it.positionY=4;
		}
		for(Guard it:jogo.getGuards())
		{
			it.positionX=5;
			it.positionY=5;
		}
		jogo.checkGuard();
		for(Guard it:jogo.getGuards())
		{
			it.sleep=true;
			assertEquals(defenitions._ogre_stunned,jogo.getMap()[it.positionX][it.positionY]);
		}
		
		jogo = new Level2(1);
		for(Club it:jogo.getHero().clubs)
		{
			it.positionX=5;
			it.positionY=6;
		}
		for(Guard it:jogo.getGuards())
		{
			it.positionX=5;
			it.positionY=5;
		}
		jogo.checkGuard();
		for(Guard it:jogo.getGuards())
		{
			it.sleep=true;
			assertEquals(defenitions._ogre_stunned,jogo.getMap()[it.positionX][it.positionY]);
		}
	}
	@Test
	public void testNumberGuard()
	{
		Level2 jogo = new Level2();
		assertEquals(1,jogo.getGuards().size(),5);
	}
	
	@Test
	public void testMaps()
	{
		String provar[][][] = new String[][][] {
				{ 		{ "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
						{ "X", " ", " ", " ", "I", " ", "X", " ", " ", "X" },
						{ "X", "X", "X", " ", "X", "X", "X", " ", " ", "X" },
						{ "X", " ", "I", " ", "I", " ", "X", " ", " ", "X" },
						{ "X", "X", "X", " ", "X", "X", "X", " ", " ", "X" },
						{ "I", " ", " ", " ", " ", " ", " ", " ", " ", "X" },
						{ "I", " ", " ", " ", " ", " ", " ", " ", " ", "X" },
						{ "X", "X", "X", " ", "X", "X", "X", "X", " ", "X" },
						{ "X", " ", "I", " ", "I", " ", "X", "k", " ", "X" },
						{ "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" } },

				{ 		{ "X", "X", "X", "X", "X", "X", "X", "X", "X" }, 
						{ "I", " ", " ", " ", " ", " ", " ", "k", "X" },
						{ "X", " ", " ", " ", " ", " ", " ", " ", "X" },
						{ "X", " ", " ", " ", " ", " ", " ", " ", "X" },
						{ "X", " ", " ", " ", " ", " ", " ", " ", "X" },
						{ "X", " ", " ", " ", " ", " ", " ", " ", "X" },
						{ "X", " ", " ", " ", " ", " ", " ", " ", "X" },
						{ "X", " ", " ", " ", " ", " ", " ", " ", "X" },
						{ "X", "X", "X", "X", "X", "X", "X", "X", "X" } }
				

		};
		for(int i=0;i<provar[0].length;i++)
		{
			for(int j=0;j<provar[0][i].length;j++)
			{
				assertEquals(provar[0][i][j],new Maps(CurrentLevel.Level.FIRST).getMap(CurrentLevel.Level.FIRST)[i][j]);
			}
		}
		for(int i=0;i<provar[1].length;i++)
		{
			for(int j=0;j<provar[1][i].length;j++)
			{
				assertEquals(provar[1][i][j],new Maps(CurrentLevel.Level.SECOND).getMap(CurrentLevel.Level.SECOND)[i][j]);
			}
		}
		for(int i=0;i<provar[0].length;i++)
		{
			for(int j=0;j<provar[0][i].length;j++)
			{
				assertEquals(provar[0][i][j],new Maps(CurrentLevel.Level.NADA).getMap(CurrentLevel.Level.NADA)[i][j]);
			}
		}
		for(int i=0;i<provar[0].length;i++)
		{
			for(int j=0;j<provar[0][i].length;j++)
			{
				assertEquals(provar[0][i][j],new Maps(CurrentLevel.Level.NADA).getMap()[i][j]);
			}
		}
	}
	
	@Test
	public void testPrint()
	{
		String provar[][][] = new String[][][] {
			{ 		{ "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" },
					{ "X", " ", " ", " ", "I", " ", "X", " ", " ", "X" },
					{ "X", "X", "X", " ", "X", "X", "X", " ", " ", "X" },
					{ "X", " ", "I", " ", "I", " ", "X", " ", " ", "X" },
					{ "X", "X", "X", " ", "X", "X", "X", " ", " ", "X" },
					{ "I", " ", " ", " ", " ", " ", " ", " ", " ", "X" },
					{ "I", " ", " ", " ", " ", " ", " ", " ", " ", "X" },
					{ "X", "X", "X", " ", "X", "X", "X", "X", " ", "X" },
					{ "X", " ", "I", " ", "I", " ", "X", "k", " ", "X" },
					{ "X", "X", "X", "X", "X", "X", "X", "X", "X", "X" } },

			{ 		{ "X", "X", "X", "X", "X", "X", "X", "X", "X" }, 
					{ "I", " ", " ", " ", " ", " ", " ", "k", "X" },
					{ "X", " ", " ", " ", " ", " ", " ", " ", "X" },
					{ "X", " ", " ", " ", " ", " ", " ", " ", "X" },
					{ "X", " ", " ", " ", " ", " ", " ", " ", "X" },
					{ "X", " ", " ", " ", " ", " ", " ", " ", "X" },
					{ "X", " ", " ", " ", " ", " ", " ", " ", "X" },
					{ "X", " ", " ", " ", " ", " ", " ", " ", "X" },
					{ "X", "X", "X", "X", "X", "X", "X", "X", "X" } }
			

		};
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	    
	    Level1 jogo=new Level1();
	    jogo.printscreen();
	    assertEquals("\n\n\n\nX|X|X|X|X|X|X|X|X|X|\nX|H| | |I| |X| |G|X|\nX|X|X| |X|X|X| | |X|\nX| |I| |I| |X| | |X|\nX|X|X| |X|X|X| | |X|\nI| | | | | | | | |X|\nI| | | | | | | | |X|\nX|X|X| |X|X|X|X| |X|\nX| |I| |I| |X|k| |X|\nX|X|X|X|X|X|X|X|X|X|\n",outContent.toString());
	    System.setOut(System.out);
	    System.setErr(System.err);
	}

}
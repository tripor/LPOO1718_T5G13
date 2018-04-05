package dkeep.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;

import dkeep.logic.*;
import dkeep.logic.character.Club;
import dkeep.logic.character.Drunken;
import dkeep.logic.character.Guard;

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
		jogo.placeHero(1, 1);
		jogo.moveHeroTo(1);
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[1][1]);
		jogo.placeHero(7, 7);
		jogo.moveHeroTo(4);
		assertEquals(defenitions._hero_with_arm, jogo.getMap()[7][7]);
	}

	@Test
	public void testGuardCaughtLevel1() {
		Level1 jogo = new Level1();
		jogo.placeHero(2, 8);
		jogo.placeGuard(1, 8);
		assertEquals(defenitions._hero, jogo.getMap()[2][8]);
		assertEquals(defenitions._guard, jogo.getMap()[1][8]);
		assertTrue(jogo.checkGuard());
		jogo = new Level1();
		jogo.placeHero(1, 7);
		jogo.placeGuard(1, 8);
		assertEquals(defenitions._hero, jogo.getMap()[1][7]);
		assertEquals(defenitions._guard, jogo.getMap()[1][8]);
		assertTrue(jogo.checkGuard());
		jogo = new Level1();
		jogo.placeHero(2, 8);
		jogo.placeGuard(2, 7);
		assertEquals(defenitions._hero, jogo.getMap()[2][8]);
		assertEquals(defenitions._guard, jogo.getMap()[2][7]);
		assertTrue(jogo.checkGuard());
		jogo = new Level1();
		jogo.placeHero(2, 8);
		jogo.placeGuard(3, 8);
		assertEquals(defenitions._hero, jogo.getMap()[2][8]);
		assertEquals(defenitions._guard, jogo.getMap()[3][8]);
		assertTrue(jogo.checkGuard());
		
		jogo= new Level1("Rookie");
		jogo.placeHero(2, 8);
		assertEquals(2,jogo.moveHeroTo(3));

	}

	@Test
	public void testGuardCaughtLevel2() {
		Level2 jogo = new Level2(1);
		jogo.getHero().clubs.clear();
		for(Guard it:jogo.getGuards())
		{
			it.clubs.clear();
		}
		jogo.placeGuard(5, 5);
		jogo.clearMap();
		jogo.markPositions();
		jogo.placeHero(4, 5);
		assertTrue(jogo.checkGuard());
		jogo.clearMap();
		jogo.markPositions();
		jogo.placeHero(6, 5);
		assertTrue(jogo.checkGuard());
		jogo.clearMap();
		jogo.markPositions();
		jogo.placeHero(5, 4);
		assertTrue(jogo.checkGuard());
		jogo.clearMap();
		jogo.markPositions();
		jogo.placeHero(5, 6);
		assertTrue(jogo.checkGuard());
		
		jogo = new Level2(1);
		jogo.getHero().clubs.clear();
		for(Guard it:jogo.getGuards())
		{
			for(Club et:it.clubs)
			{
				et.positionX=5;
				et.positionY=5;
			}
		}
		jogo.placeGuard(1, 1);
		jogo.clearMap();
		jogo.markPositions();
		jogo.placeHero(4, 5);
		assertTrue(jogo.checkGuard());
		jogo.clearMap();
		jogo.markPositions();
		jogo.placeHero(6, 5);
		assertTrue(jogo.checkGuard());
		jogo.clearMap();
		jogo.markPositions();
		jogo.placeHero(5, 4);
		assertTrue(jogo.checkGuard());
		jogo.clearMap();
		jogo.markPositions();
		jogo.placeHero(5, 6);
		assertTrue(jogo.checkGuard());
		
		jogo = new Level2(1);
		jogo.getHero().clubs.clear();
		for(Guard it:jogo.getGuards())
		{
			for(Club et:it.clubs)
			{
				et.positionX=5;
				et.positionY=5;
			}
		}
		jogo.placeGuard(5, 5);
		jogo.clearMap();
		jogo.markPositions();
		jogo.placeHero(4, 5);
		assertTrue(jogo.checkGuard());
		jogo.clearMap();
		jogo.markPositions();
		jogo.placeHero(6, 5);
		assertTrue(jogo.checkGuard());
		jogo.clearMap();
		jogo.markPositions();
		jogo.placeHero(5, 4);
		assertTrue(jogo.checkGuard());
		jogo.clearMap();
		jogo.markPositions();
		jogo.placeHero(5, 6);
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
		Level2 jogo = new Level2(1);
		jogo.getGuards().clear();
		jogo.clearMap();
		jogo.markPositions();
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
		Level2 jogo = new Level2(1);
		jogo.getGuards().clear();
		jogo.getHero().clubs.clear();
		jogo.clearMap();
		jogo.markPositions();
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
		Level2 jogo = new Level2(1);
		jogo.getGuards().clear();
		jogo.clearMap();
		jogo.markPositions();
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
		int i=0;
		boolean sair=false;
		int vezes_frente=0;
		int vezes_tras=0;
		for(int tempo=0;tempo<100000;tempo++)
		{
			jogo.moveHeroTo(3);
			for(Guard it:jogo.getGuards())
			{
				if(it.foward_walking)
				{
					i++;
					vezes_frente++;
				}
				else
				{
					vezes_tras++;
					i--;
				}
				if(i<0)i=x.length-1;
				if(i>=x.length)i=0;
				assertEquals(x[i],it.positionX);
				assertEquals(y[i],it.positionY);
			}
		}
		assertEquals(66,100*vezes_frente/100000.0,2);
		assertEquals(33,100*vezes_tras/100000.0,2);
		jogo = new Level1("Drunken");
		sair = false;
		for (i = 0;; i++) {
			if (i == x.length)
				i = 0;
			for (Guard it : jogo.getGuards()) {
				if (x[i] != it.positionX) {
					sair = true;
				}
				if (y[i] != it.positionY) {
					sair = true;
				}
			}
			if (sair)
				break;
			jogo.moveHeroTo(3);
		}
		i--;
		if (i < 0)
			i = x.length - 1;
		for (Guard it : jogo.getGuards()) {
			assertTrue(it.sleep);
			Drunken teste=(Drunken) it;
			assertTrue(teste.isSleep());
		}
		int vezes_acordado=0;
		int vezes_dormir=0;
		int vezes_para_frente=0;
		int vezes_para_tras=0;
		jogo = new Level1("Drunken");
		for (int tempo = 0; tempo < 100000; tempo++) {
			jogo.moveHeroTo(3);
			for(Guard it:jogo.getGuards())
			{
				if(it.sleep)
					vezes_dormir++;
				else
					vezes_acordado++;
				if(it.foward_walking)
					vezes_para_frente++;
				else
					vezes_para_tras++;
			}
		}
		assertEquals(37,100*vezes_dormir/100000.0,2);
		assertEquals(63,100*vezes_acordado/100000.0,2);
		assertEquals(67,100*vezes_para_frente/100000.0,4);
		assertEquals(33,100*vezes_para_tras/100000.0,4);

	}

	@Test
	public void testRandomLevel2() {
		Level2 jogo = new Level2(1);
		int i;
		for(Guard it:jogo.getGuards())
		{
			it.clubs.clear();
		}
		jogo.getHero().clubs.clear();
		jogo.clearMap();
		jogo.markPositions();
		int vezes_up=0;
		int vezes_down=0;
		int vezes_left=0;
		int vezes_right=0;
		for(int tempo=0;tempo<100000;tempo++)
		{
			jogo.placeGuard(5, 5);
			jogo.moveHeroTo(3);
			for(Guard it:jogo.getGuards())
			{
				if(it.positionX!=5 && it.positionY!=5)
					fail();
				switch(it.positionX)
				{
				case 4:vezes_up++;
				break;
				case 6:vezes_down++;
				break;
				}
				switch(it.positionY)
				{
				case 4:vezes_left++;
				break;
				case 6:vezes_right++;
				break;
				}
			}
		}
		assertEquals(25,100*vezes_up/100000,2);
		assertEquals(25,100*vezes_down/100000,2);
		assertEquals(25,100*vezes_left/100000,2);
		assertEquals(25,100*vezes_right/100000,2);
		jogo.placeGuard(5, 5);
		jogo.setCopied_map("X", 5, 6);
		jogo.setCopied_map("X", 5, 4);
		jogo.setCopied_map("X", 6, 5);
		jogo.setCopied_map("X", 4, 6);
		jogo.setCopied_map("X", 4, 4);
		jogo.setCopied_map("X", 3, 5);
		
		jogo.setMap("X", 5, 6);
		jogo.setMap("X", 5, 4);
		jogo.setMap("X", 6, 5);
		jogo.setMap("X", 4, 6);
		jogo.setMap("X", 4, 4);
		jogo.setMap("X", 3, 5);
		jogo.moveHeroTo(3);
		for(Guard it:jogo.getGuards())
		{
			assertEquals(4,it.positionX);
			assertEquals(5,it.positionY);
		}
		
		jogo = new Level2(1);
		for(Guard it:jogo.getGuards())
		{
			it.clubs.clear();
		}
		jogo.getHero().clubs.clear();
		jogo.clearMap();
		jogo.markPositions();
		jogo.placeGuard(5, 5);
		jogo.setCopied_map("X", 6, 5);
		jogo.setCopied_map("X", 4, 5);
		jogo.setCopied_map("X", 5, 4);
		jogo.setCopied_map("X", 4, 6);
		jogo.setCopied_map("X", 6, 6);
		jogo.setCopied_map("X", 5, 7);
		
		jogo.setMap("X", 6, 5);
		jogo.setMap("X", 4, 5);
		jogo.setMap("X", 5, 4);
		jogo.setMap("X", 4, 6);
		jogo.setMap("X", 6, 6);
		jogo.setMap("X", 5, 7);
		jogo.moveHeroTo(3);
		for(Guard it:jogo.getGuards())
		{
			assertEquals(5,it.positionX);
			assertEquals(6,it.positionY);
		}
		
		jogo = new Level2(1);
		for(Guard it:jogo.getGuards())
		{
			it.clubs.clear();
		}
		jogo.getHero().clubs.clear();
		jogo.clearMap();
		jogo.markPositions();
		jogo.placeGuard(5, 5);
		jogo.setCopied_map("X", 6, 5);
		jogo.setCopied_map("X", 4, 5);
		jogo.setCopied_map("X", 5, 6);
		jogo.setCopied_map("X", 4, 4);
		jogo.setCopied_map("X", 6, 4);
		jogo.setCopied_map("X", 5, 3);
		
		jogo.setMap("X", 6, 5);
		jogo.setMap("X", 4, 5);
		jogo.setMap("X", 5, 6);
		jogo.setMap("X", 4, 4);
		jogo.setMap("X", 6, 4);
		jogo.setMap("X", 5, 3);
		jogo.moveHeroTo(3);
		for(Guard it:jogo.getGuards())
		{
			assertEquals(5,it.positionX);
			assertEquals(4,it.positionY);
		}
		
		jogo = new Level2(1);
		for(Guard it:jogo.getGuards())
		{
			it.clubs.clear();
		}
		jogo.getHero().clubs.clear();
		jogo.clearMap();
		jogo.markPositions();
		jogo.placeGuard(5, 5);
		jogo.setCopied_map("X", 5, 4);
		jogo.setCopied_map("X", 5, 6);
		jogo.setCopied_map("X", 4, 5);
		jogo.setCopied_map("X", 6, 4);
		jogo.setCopied_map("X", 6, 6);
		jogo.setCopied_map("X", 7, 5);
		
		jogo.setMap("X", 5, 4);
		jogo.setMap("X", 5, 6);
		jogo.setMap("X", 4, 5);
		jogo.setMap("X", 6, 4);
		jogo.setMap("X", 6, 6);
		jogo.setMap("X", 7, 5);
		jogo.moveHeroTo(3);
		for(Guard it:jogo.getGuards())
		{
			assertEquals(6,it.positionX);
			assertEquals(5,it.positionY);
		}
		
		jogo = new Level2(1);
		jogo.getHero().clubs.clear();
		jogo.clearMap();
		jogo.markPositions();
		jogo.placeGuard(5, 5);

		vezes_up=0;
		vezes_down=0;
		vezes_left=0;
		vezes_right=0;
		for(Guard it:jogo.getGuards())
		{
			for(Club c:it.clubs)
			{
				for(int tempo=0;tempo<100000;tempo++)
				{
					c.clubNextPosition(jogo, it);
					switch (c.positionX) {
					case 4:
						vezes_up++;
						break;
					case 6:
						vezes_down++;
						break;
					}
					switch (c.positionY) {
					case 4:
						vezes_left++;
						break;
					case 6:
						vezes_right++;
						break;
					}
					jogo.clearMap();
				}
			}
		}
		assertEquals(25,100*vezes_up/100000,2);
		assertEquals(25,100*vezes_down/100000.0,2);
		assertEquals(25,100*vezes_left/100000.0,2);
		assertEquals(25,100*vezes_right/100000.0,2);
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
		/*String provar[][][] = new String[][][] {
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
			

		};*/
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
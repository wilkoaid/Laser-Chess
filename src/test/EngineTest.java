package test;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.Colour;
import engine.File;
import engine.Rank;
import engine.board.Board;
import engine.board.Tile;
import engine.piece.King;

public class EngineTest {

	// test converting between rank/file and board array index
	@Test
	public void test1() {
		Board board = new Board();
		File file1 = File.A;
		Rank rank1 = Rank.ONE;
		// A1
		int expected1 = 13;
		int actual1 = board.getArrayIndex(file1,rank1);
		// C1
		File file2 = File.C;
		Rank rank2 = Rank.ONE;
		int expected2 = 15;
		int actual2 = board.getArrayIndex(file2,rank2);
		// E4
		File file3 = File.E;
		Rank rank3 = Rank.FOUR;
		int expected3 = 53;
		int actual3 = board.getArrayIndex(file3,rank3);
		
		assertEquals(expected1,actual1);
		assertEquals(expected2,actual2);
		assertEquals(expected3,actual3);
	}

	// test creating offboard tile
	@Test
	public void test2() {
		Tile tile = new Tile();
		assertTrue(tile.isOffboard());
	}
	
	// test creating empty onboard tile
	@Test
	public void test3() {
		Tile tile = new Tile(File.A,Rank.FIVE);
		assertTrue(tile.isEmpty());
		assertFalse(tile.isOffboard());
	}
	
	// test creating occupied onboard tile
	@Test
	public void test4() {
		Tile tile = new Tile(new King(Colour.WHITE,180),File.A,Rank.FIVE);
		String expected = "A5";
		String actual = tile.toString();
		
		assertFalse(tile.isEmpty());
		assertFalse(tile.isOffboard());
		assertEquals(expected,actual);
	}
}

package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import engine.*;
import engine.action.*;
import engine.board.*;
import engine.piece.*;

public class EngineTest {

	// test converting between rank/file and board array index
	@Test
	public void test1() {
		Board board = new Board(Colour.WHITE);
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
		Tile tile = new Tile(new King(Colour.WHITE, 180),File.A,Rank.FIVE);
		String expected = "A5";
		String actual = tile.toString();
		
		assertFalse(tile.isEmpty());
		assertFalse(tile.isOffboard());
		assertEquals(expected,actual);
	}
	
	// test board initialisation
	// test list of active tiles
	@Test
	public void test5() {
		Board board = new Board(Colour.WHITE);
		Set<String> expected = new HashSet<String>();
		expected.add("A1");
		expected.add("E1");
		expected.add("F1");
		expected.add("G1");
		expected.add("H1");
		expected.add("C2");
		expected.add("A4");
		expected.add("E4");
		expected.add("F4");
		expected.add("H4");
		expected.add("A5");
		expected.add("H5");
		expected.add("G6");
		
		Set<Tile> actual = board.getOccupiedTilesBlack();
		
		for(Tile tile : actual) {
			assertTrue(expected.contains(tile.toString()));
		}
	}
	
	
	//test firing laser
	@Test
	public void test6() {
		Board board = new Board(Colour.WHITE);
		Tile[] arr = board.getBoard();
		
		// put piece on tile next to laser and fire laser
		int index = board.getArrayIndex(File.J,Rank.SEVEN);
		arr[index].setPiece(new Deflector(Colour.WHITE, 45));
		arr[index].setEmpty(false);
		Action action = new Rotate(arr[index], Rotation.ANTICLOCKWISE, board);
		action.fireLaser();
		// piece is destroyed by laser, piece is removed
		
		Tile testTile = new Tile(File.J,Rank.SEVEN);
		String expected = testTile.toString();
		String actual = arr[index].toString();
		
		assertEquals(expected,actual);
	}
	
	// test signedDifference method in Action class
	@Test
	public void test7() {
		Board board = new Board(Colour.WHITE);
		
		Tile tile = new Tile();
		
		Action action = new Rotate(tile, Rotation.CLOCKWISE, board);
		
		int expected1 = 135;
		int expected2 = -135;
		int expected3 = -45;
		int expected4 = 45;
		
		int actual1 = action.signedDifference(180, 45);
		int actual2 = action.signedDifference(180, 315);
		int actual3 = action.signedDifference(0, 45);
		int actual4 = action.signedDifference(0, 315);
		
		assertEquals(expected1,actual1);
		assertEquals(expected2,actual2);
		assertEquals(expected3,actual3);
		assertEquals(expected4,actual4);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import engine.*;
import engine.action.*;
import engine.board.*;
import engine.piece.*;
import javafx.scene.image.ImageView;

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
		Tile tile = new Tile(new King(Colour.WHITE, 180, new ImageView("resources/pieces/white_king.png")),File.A,Rank.FIVE);
		String expected = "A5 (King)";
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
		expected.add("A1 (Laser)");
		expected.add("E1 (Defender)");
		expected.add("F1 (King)");
		expected.add("G1 (Defender)");
		expected.add("H1 (Deflector)");
		expected.add("C2 (Deflector)");
		expected.add("A4 (Deflector)");
		expected.add("E4 (Switch)");
		expected.add("F4 (Switch)");
		expected.add("H4 (Deflector)");
		expected.add("A5 (Deflector)");
		expected.add("H5 (Deflector)");
		expected.add("G6 (Deflector)");
		
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
		arr[index].setPiece(new Deflector(Colour.WHITE, 45, new ImageView("resources/pieces/white_deflector.png")));
		arr[index].setEmpty(false);
		Action action = new Rotate(arr[index], Rotation.ANTICLOCKWISE, board);
		action.fireLaser();
		// piece is destroyed by laser, piece is removed
		
		Tile testTile = new Tile(File.J,Rank.SEVEN);
		String expected = testTile.toString();
		String actual = arr[index].toString();
		
		assertEquals(expected,actual);
	}
	
	// test firing laser and redirect onto deflector to take the piece
	@Test
	public void test7() {
		Board board = new Board(Colour.WHITE);
		Tile[] arr = board.getBoard();
		
		// put test pieces on board
		int index1 = board.getArrayIndex(File.J, Rank.SEVEN);
		int index2 = board.getArrayIndex(File.I, Rank.SEVEN);
		arr[index1].setPiece(new Deflector(Colour.WHITE, 225, new ImageView("resources/pieces/white_deflector.png")));
		arr[index1].setEmpty(false);
		arr[index2].setPiece(new Deflector(Colour.WHITE, 315, new ImageView("resources/pieces/white_deflector.png")));
		arr[index2].setEmpty(false);
		Action action = new Rotate(arr[index2], Rotation.ANTICLOCKWISE, board);
		action.fireLaser();
		
		Tile testTile = new Tile(File.I, Rank.SEVEN);
		String expected = testTile.toString();
		String actual = arr[index2].toString();
		
		assertEquals(expected, actual);
	}

	// test signedDifference method in Action class
	@Test
	public void test8() {
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

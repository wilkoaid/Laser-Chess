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
	
	// test board initialisation
	// test list of active tiles
	@Test
	public void test5() {
		Board board = new Board();
		
		Set<Tile> expected = new HashSet<Tile>();
		
		expected.add(new Tile(new Laser(Colour.BLACK, 90),File.A,Rank.ONE));
		expected.add(new Tile(new Defender(Colour.BLACK, 180),File.E,Rank.ONE));
		expected.add(new Tile(new King(Colour.BLACK, 180),File.F,Rank.ONE));
		expected.add(new Tile(new Defender(Colour.BLACK, 180),File.G,Rank.ONE));
		expected.add(new Tile(new Deflector(Colour.BLACK, 135),File.H,Rank.ONE));
		expected.add(new Tile(new Deflector(Colour.BLACK, 225),File.C,Rank.TWO));
		expected.add(new Tile(new Deflector(Colour.BLACK, 45),File.A,Rank.FOUR));
		expected.add(new Tile(new Switch(Colour.BLACK, 45),File.E,Rank.FOUR));
		expected.add(new Tile(new Switch(Colour.BLACK, 135),File.F,Rank.FOUR));
		expected.add(new Tile(new Deflector(Colour.BLACK, 135),File.H,Rank.FOUR));
		expected.add(new Tile(new Deflector(Colour.BLACK, 135),File.A,Rank.FIVE));
		expected.add(new Tile(new Deflector(Colour.BLACK, 45),File.H,Rank.FIVE));
		expected.add(new Tile(new Deflector(Colour.BLACK, 135),File.G,Rank.SIX));
		
		Set<Tile> actual = board.getActiveTilesBlack();
		
		assertEquals(expected,actual);
	}
	
	
	//test firing laser
	@Test
	public void test6() {
		Board board = new Board();
		
		// put piece on tile next to laser and fire laser
		int takenPieceIndex = board.getArrayIndex(File.B,Rank.ONE);
		board.getBoard()[takenPieceIndex].setPiece(new Deflector(Colour.WHITE,135));
		board.getBoard()[takenPieceIndex].setEmpty(false);
		Action action = new Move(board.getBoard()[takenPieceIndex],board.getBoard()[takenPieceIndex]);
		action.fireLaser();
		// piece is destroyed by laser, piece is removed
		board.getBoard()[takenPieceIndex].setPiece(null);
		board.getBoard()[takenPieceIndex].setEmpty(true);
		
		Tile expected = new Tile(File.B,Rank.ONE);
		Tile actual = board.getBoard()[takenPieceIndex];
		
		assertEquals(expected,actual);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

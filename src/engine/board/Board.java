package engine.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import engine.Colour;
import engine.File;
import engine.Rank;

public class Board {
	private final Tile[] board;
	private Set<Tile> occupiedTilesWhite;
	private Set<Tile> occupiedTilesBlack;
	private Colour turn;
	
	public Board() {
		this.turn = Colour.WHITE;
		board = new Tile[120];
		initialiseBoardACE();
	}
	
	/**
	 * Initialise the board Tiles according to the "ACE" setup
	 */
	private void initialiseBoardACE() {
		
	}

	/**
	 * Take Rank and File as input and output the corresponding board array index.
	 * E.g. A1 -> 13 
	 * @param file
	 * @param rank
	 * @return
	 */
	public int getArrayIndex(File file, Rank rank) {
		
		return 0;
	}
	
	/**
	 * Re-calculate the set of Tiles that are currently occupied
	 * 
	 * ****Do I need this?****
	 * 
	 */
	public void calculateOccupiedTiles() {
		
	}

	public void setTurn(Colour turn) {
		this.turn = turn;
	}

	public Tile[] getBoard() {
		return board;
	}

	public Set<Tile> getActiveTilesWhite() {
		return occupiedTilesWhite;
	}

	public Set<Tile> getActiveTilesBlack() {
		return occupiedTilesBlack;
	}

	public Colour getTurn() {
		return turn;
	}
}

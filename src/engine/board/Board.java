package engine.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import engine.Colour;
import engine.File;
import engine.Rank;

public class Board {
	private final Tile[] board;
	private Set<Tile> activeTilesWhite;
	private Set<Tile> activeTilesBlack;
	private Colour turn;
	
	public Board() {
		this.turn = Colour.WHITE;
		board = new Tile[120];
		initialiseBoard();
	}
	
	private void initialiseBoard() {
		
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
	
	public void calculateActiveTiles() {
		
	}
	
	public void setActiveTilesWhite(Set<Tile> activeTilesWhite) {
		this.activeTilesWhite = activeTilesWhite;
	}
	
	public void setActiveTilesBlack(Set<Tile> activeTilesBlack) {
		this.activeTilesBlack = activeTilesBlack;
	}

	public void setTurn(Colour turn) {
		this.turn = turn;
	}

	public Tile[] getBoard() {
		return board;
	}

	public Set<Tile> getActiveTilesWhite() {
		return activeTilesWhite;
	}

	public Set<Tile> getActiveTilesBlack() {
		return activeTilesBlack;
	}

	public Colour getTurn() {
		return turn;
	}
}

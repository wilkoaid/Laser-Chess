package engine.board;

import java.util.ArrayList;
import java.util.List;

import engine.Colour;
import engine.File;
import engine.Rank;

public class Board {
	private final Tile[] board;
	private List<Tile> activeTilesWhite;
	private List<Tile> activeTilesBlack;
	private Colour turn;
	
	public void setActiveTilesWhite(List<Tile> activeTilesWhite) {
		this.activeTilesWhite = activeTilesWhite;
	}

	public void setActiveTilesBlack(List<Tile> activeTilesBlack) {
		this.activeTilesBlack = activeTilesBlack;
	}

	public void setTurn(Colour turn) {
		this.turn = turn;
	}

	public Tile[] getBoard() {
		return board;
	}

	public List<Tile> getActiveTilesWhite() {
		return activeTilesWhite;
	}

	public List<Tile> getActiveTilesBlack() {
		return activeTilesBlack;
	}

	public Colour getTurn() {
		return turn;
	}

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
}

package engine.board;

import java.util.ArrayList;
import java.util.List;

import engine.Colour;
import engine.File;
import engine.Rank;

public class Board {
	Tile[] board = new Tile[120];
	List<Tile> activeTilesWhite;
	List<Tile> activeTilesBlack;
	Colour turn;
	
	public Board() {
		this.turn = Colour.WHITE;
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
	private int getArrayIndex(File file, Rank rank) {
		
		return 0;
	}
	
}

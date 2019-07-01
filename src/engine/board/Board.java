package engine.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import engine.Colour;
import engine.File;
import engine.Rank;
import engine.action.Action;

public class Board {
	private final Tile[] board;
	private Set<Tile> occupiedTilesWhite;
	private Set<Tile> occupiedTilesBlack;
	private Colour turn;
	
	public boolean whiteKing;
	public boolean blackKing;
	
	public Board() {
		board = new Tile[120];
		initialiseBoardACE();
		this.turn = Colour.WHITE;
		this.whiteKing = true;
		this.blackKing = true;
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
	
	public void printActions() {
		// loop over occupied tiles for white or black, calculate actions
		if(turn == Colour.WHITE) {
			for(Tile tile : this.occupiedTilesWhite) {
				List<Action> actions = tile.getPiece().calculateActions(tile,this.board);
				/*
				 *  print to terminal the moves in format:
				 *  e.g. 
				 *       Rotate J8 (Laser) clockwise
				 *       Rotate J8 (Laser) anti-clockwise
				 *       Move H7 (Deflector) to G7
				 *       Swap F5 (Switch) with G6 (BLACK Defender)
				 *       etc.
				 */
				for(Action action : actions) {
					System.out.println(action);
				}
			}
		} else {
			for(Tile tile : this.occupiedTilesBlack) {
				List<Action> actions = tile.getPiece().calculateActions(tile,this.board);
				/*
				 *  print to terminal the moves in format:
				 *  e.g. 
				 *       Rotate J8 (Laser) clockwise
				 *       Rotate J8 (Laser) anti-clockwise
				 *       Move H7 (Deflector) to G7
				 *       Swap F5 (Switch) with G6 (BLACK Defender)
				 *       etc.
				 */
				for(Action action : actions) {
					System.out.println(action);
				}
			}
		}
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

package engine.board;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import engine.Colour;
import engine.Direction;
import engine.File;
import engine.Rank;
import engine.action.Action;
import engine.piece.*;

public class Board {
	private final Tile[] board;
	private Set<Tile> occupiedTilesWhite;
	private Set<Tile> occupiedTilesBlack;
	private Colour turn;
	
	private List<Action> actions;
	private Tile whiteLaser;
	private Tile blackLaser;
	
	public boolean whiteKing;
	public boolean blackKing;
	
	public Board(Colour startingColour) {
		this.board = new Tile[120];
		this.occupiedTilesBlack = new HashSet<Tile>();
		this.occupiedTilesWhite = new HashSet<Tile>();
		this.actions = new ArrayList<Action>();
		initialiseBoardACE();
		calculateOccupiedTiles();
		this.turn = startingColour;
		this.whiteKing = true;
		this.blackKing = true;
	}
	
	/**
	 * Initialise the board Tiles according to the "ACE" setup
	 */
	private void initialiseBoardACE() {
		// set whole board to off-board tiles
		for(int i = 0; i < this.board.length; i++) {
			Tile tile = new Tile();
			this.board[i] = tile;
		}
		
		// set on-board tiles to empty tiles
		for(File file : File.values()) {
			for(Rank rank : Rank.values()) {
				int index = getArrayIndex(file,rank);
				this.board[index] = new Tile(file,rank);
			}
		}
		
		// set white pieces
		Tile j8 = new Tile(new Laser(Colour.WHITE, Direction.NORTH),File.J,Rank.EIGHT);
		this.board[getArrayIndex(File.J,Rank.EIGHT)] = j8;
		this.whiteLaser = j8;
		Tile f8 = new Tile(new Defender(Colour.WHITE, Direction.NORTH),File.F,Rank.EIGHT);
		this.board[getArrayIndex(File.F,Rank.EIGHT)] = f8;
		Tile e8 = new Tile(new King(Colour.WHITE, Direction.NORTH),File.E,Rank.EIGHT);
		this.board[getArrayIndex(File.E,Rank.EIGHT)] = e8;
		Tile d8 = new Tile(new Defender(Colour.WHITE, Direction.NORTH),File.D,Rank.EIGHT);
		this.board[getArrayIndex(File.D,Rank.EIGHT)] = d8;
		Tile c8 = new Tile(new Deflector(Colour.WHITE, Direction.NORTHWEST),File.C,Rank.EIGHT);
		this.board[getArrayIndex(File.C,Rank.EIGHT)] = c8;
		Tile h7 = new Tile(new Deflector(Colour.WHITE, Direction.NORTHEAST),File.H,Rank.SEVEN);
		this.board[getArrayIndex(File.H,Rank.SEVEN)] = h7;
		Tile j5 = new Tile(new Deflector(Colour.WHITE, Direction.SOUTHWEST),File.J,Rank.FIVE);
		this.board[getArrayIndex(File.J,Rank.FIVE)] = j5;
		Tile f5 = new Tile(new Switch(Colour.WHITE, Direction.NORTHEAST),File.F,Rank.FIVE);
		this.board[getArrayIndex(File.F,Rank.FIVE)] = f5;
		Tile e5 = new Tile(new Switch(Colour.WHITE, Direction.SOUTHEAST),File.E,Rank.FIVE);
		this.board[getArrayIndex(File.E,Rank.FIVE)] = e5;
		Tile c5 = new Tile(new Deflector(Colour.WHITE, Direction.NORTHWEST),File.C,Rank.FIVE);
		this.board[getArrayIndex(File.C,Rank.FIVE)] = c5;
		Tile j4 = new Tile(new Deflector(Colour.WHITE, Direction.NORTHWEST),File.J,Rank.FOUR);
		this.board[getArrayIndex(File.J,Rank.FOUR)] = j4;
		Tile c4 = new Tile(new Deflector(Colour.WHITE, Direction.SOUTHWEST),File.C,Rank.FOUR);
		this.board[getArrayIndex(File.C,Rank.FOUR)] = c4;
		Tile d3 = new Tile(new Deflector(Colour.WHITE, Direction.NORTHWEST),File.D,Rank.THREE);
		this.board[getArrayIndex(File.D,Rank.THREE)] = d3;
		
		
		// set black pieces
		Tile a1 = new Tile(new Laser(Colour.BLACK, Direction.SOUTH),File.A,Rank.ONE);
		this.board[getArrayIndex(File.A,Rank.ONE)] = a1;
		this.blackLaser = a1;
		Tile e1 = new Tile(new Defender(Colour.BLACK, Direction.SOUTH),File.E,Rank.ONE);
		this.board[getArrayIndex(File.E,Rank.ONE)] = e1;
		Tile f1 = new Tile(new King(Colour.BLACK, Direction.SOUTH),File.F,Rank.ONE);
		this.board[getArrayIndex(File.F,Rank.ONE)] = f1;
		Tile g1 = new Tile(new Defender(Colour.BLACK, Direction.SOUTH),File.G,Rank.ONE);
		this.board[getArrayIndex(File.G,Rank.ONE)] = g1;
		Tile h1 = new Tile(new Deflector(Colour.BLACK, Direction.SOUTHEAST),File.H,Rank.ONE);
		this.board[getArrayIndex(File.H,Rank.ONE)] = h1;
		Tile c2 = new Tile(new Deflector(Colour.BLACK, Direction.SOUTHWEST),File.C,Rank.TWO);
		this.board[getArrayIndex(File.C,Rank.TWO)] = c2;
		Tile a4 = new Tile(new Deflector(Colour.BLACK, Direction.NORTHEAST),File.A,Rank.FOUR);
		this.board[getArrayIndex(File.A,Rank.FOUR)] = a4;
		Tile e4 = new Tile(new Switch(Colour.BLACK, Direction.NORTHEAST),File.E,Rank.FOUR);
		this.board[getArrayIndex(File.E,Rank.FOUR)] = e4;
		Tile f4 = new Tile(new Switch(Colour.BLACK, Direction.SOUTHEAST),File.F,Rank.FOUR);
		this.board[getArrayIndex(File.F,Rank.FOUR)] = f4;
		Tile h4 = new Tile(new Deflector(Colour.BLACK, Direction.SOUTHEAST),File.H,Rank.FOUR);
		this.board[getArrayIndex(File.H,Rank.FOUR)] = h4;
		Tile a5 = new Tile(new Deflector(Colour.BLACK, Direction.SOUTHEAST),File.A,Rank.FIVE);
		this.board[getArrayIndex(File.A,Rank.FIVE)] = a5;
		Tile h5 = new Tile(new Deflector(Colour.BLACK, Direction.NORTHEAST),File.H,Rank.FIVE);
		this.board[getArrayIndex(File.H,Rank.FIVE)] = h5;
		Tile g6 = new Tile(new Deflector(Colour.BLACK, Direction.SOUTHEAST),File.G,Rank.SIX);
		this.board[getArrayIndex(File.G,Rank.SIX)] = g6;
	}

	/**
	 * Take Rank and File as input and output the corresponding board array index.
	 * E.g. A1 -> 13 
	 * @param file
	 * @param rank
	 * @return
	 */
	public int getArrayIndex(File file, Rank rank) {
		return file.getVal() + 12 * rank.getVal();
	}
	
	
	/**
	 * Calculate the set of Tiles that are currently occupied
	 */
	public void calculateOccupiedTiles() {
		for(Tile tile : board) {
			if(tile.getPiece() != null) {
				if(tile.getPiece().getColour() == Colour.BLACK) {
					this.occupiedTilesBlack.add(tile);
				}
				if (tile.getPiece().getColour() == Colour.WHITE) {
					this.occupiedTilesWhite.add(tile);
				}
			}
		}
	}
	
	public void printActions() {
		/*
		 *  loop over occupied tiles for white or black, calculate actions.
		 *  
		 *  print to terminal the moves in format:
		 *  e.g. 
		 *       Rotate J8 (Laser) clockwise
		 *       Rotate J8 (Laser) anti-clockwise
		 *       Move H7 (Deflector) to G7
		 *       Swap F5 (Switch) with G6 (BLACK Defender)
		 *       etc.
		 */
		this.actions.clear(); // clear previous list
		if(turn == Colour.WHITE) {
			for(Tile tile : this.occupiedTilesWhite) {
				List<Action> actions = tile.getPiece().calculateActions(tile,this);
				for(Action action : actions) {
					System.out.println(action);
				}
				this.actions.addAll(actions);
			}
		} else {
			for(Tile tile : this.occupiedTilesBlack) {
				List<Action> actions = tile.getPiece().calculateActions(tile,this);
				for(Action action : actions) {
					System.out.println(action);
				}
				this.actions.addAll(actions);
			}
		}
	}

	public void setTurn(Colour turn) {
		this.turn = turn;
	}

	public Tile[] getBoard() {
		return this.board;
	}

	public Set<Tile> getOccupiedTilesWhite() {
		return this.occupiedTilesWhite;
	}

	public Set<Tile> getOccupiedTilesBlack() {
		return this.occupiedTilesBlack;
	}

	public Colour getTurn() {
		return this.turn;
	}
	
	public Tile getWhiteLaser() {
		return this.whiteLaser;
	}
	
	public Tile getBlackLaser() {
		return this.blackLaser;
	}
}

package engine.board;

import java.util.*;

import engine.Colour;
import engine.File;
import engine.Rank;
import engine.action.Action;
import engine.piece.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Board {
	private final Tile[] board;
	private Set<Tile> occupiedTilesWhite;
	private Set<Tile> occupiedTilesBlack;
	private Colour turn;
	
	private List<Action> actions;
	private Tile whiteLaserTile;
	private Tile blackLaserTile;
	
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
	public void initialiseBoardACE() {
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
		Tile j8 = new Tile(new Laser(Colour.WHITE, 0, new Image("resources/pieces/white_laser.png")), File.J, Rank.EIGHT);
		this.board[getArrayIndex(File.J,Rank.EIGHT)] = j8;
		this.whiteLaserTile = j8;
		
		Tile f8 = new Tile(new Defender(Colour.WHITE, 0, new Image("resources/pieces/white_defender.png")) ,File.F,Rank.EIGHT);
		this.board[getArrayIndex(File.F,Rank.EIGHT)] = f8;
		
		Tile e8 = new Tile(new King(Colour.WHITE, 0, new Image("resources/pieces/white_king.png")),File.E,Rank.EIGHT);
		this.board[getArrayIndex(File.E,Rank.EIGHT)] = e8;
		
		Tile d8 = new Tile(new Defender(Colour.WHITE, 0, new Image("resources/pieces/white_defender.png")) ,File.D,Rank.EIGHT);
		this.board[getArrayIndex(File.D,Rank.EIGHT)] = d8;
		
		Tile c8 = new Tile(new Deflector(Colour.WHITE, 315, new Image("resources/pieces/white_deflector.png")),File.C,Rank.EIGHT);
		this.board[getArrayIndex(File.C,Rank.EIGHT)] = c8;
		
		Tile h7 = new Tile(new Deflector(Colour.WHITE, 45, new Image("resources/pieces/white_deflector.png")),File.H,Rank.SEVEN);
		this.board[getArrayIndex(File.H,Rank.SEVEN)] = h7;
		
		Tile j5 = new Tile(new Deflector(Colour.WHITE, 225, new Image("resources/pieces/white_deflector.png")),File.J,Rank.FIVE);
		this.board[getArrayIndex(File.J,Rank.FIVE)] = j5;
		
		Tile f5 = new Tile(new Switch(Colour.WHITE, 45, new Image("resources/pieces/white_switch.png")),File.F,Rank.FIVE);
		this.board[getArrayIndex(File.F,Rank.FIVE)] = f5;
		
		Tile e5 = new Tile(new Switch(Colour.WHITE, 135, new Image("resources/pieces/white_switch.png")),File.E,Rank.FIVE);
		this.board[getArrayIndex(File.E,Rank.FIVE)] = e5;
		
		Tile c5 = new Tile(new Deflector(Colour.WHITE, 315, new Image("resources/pieces/white_deflector.png")),File.C,Rank.FIVE);
		this.board[getArrayIndex(File.C,Rank.FIVE)] = c5;
		
		Tile j4 = new Tile(new Deflector(Colour.WHITE, 315, new Image("resources/pieces/white_deflector.png")),File.J,Rank.FOUR);
		this.board[getArrayIndex(File.J,Rank.FOUR)] = j4;
		
		Tile c4 = new Tile(new Deflector(Colour.WHITE, 225, new Image("resources/pieces/white_deflector.png")),File.C,Rank.FOUR);
		this.board[getArrayIndex(File.C,Rank.FOUR)] = c4;
		
		Tile d3 = new Tile(new Deflector(Colour.WHITE, 315, new Image("resources/pieces/white_deflector.png")),File.D,Rank.THREE);
		this.board[getArrayIndex(File.D,Rank.THREE)] = d3;
		
		
		// set black pieces
		Tile a1 = new Tile(new Laser(Colour.BLACK, 180, new Image("resources/pieces/black_laser.png")),File.A,Rank.ONE);
		this.board[getArrayIndex(File.A,Rank.ONE)] = a1;
		this.blackLaserTile = a1;
		
		Tile e1 = new Tile(new Defender(Colour.BLACK, 180, new Image("resources/pieces/black_defender.png")),File.E,Rank.ONE);
		this.board[getArrayIndex(File.E,Rank.ONE)] = e1;
		
		Tile f1 = new Tile(new King(Colour.BLACK, 180, new Image("resources/pieces/black_king.png")),File.F,Rank.ONE);
		this.board[getArrayIndex(File.F,Rank.ONE)] = f1;
		
		Tile g1 = new Tile(new Defender(Colour.BLACK, 180, new Image("resources/pieces/black_defender.png")),File.G,Rank.ONE);
		this.board[getArrayIndex(File.G,Rank.ONE)] = g1;
		
		Tile h1 = new Tile(new Deflector(Colour.BLACK, 135, new Image("resources/pieces/black_deflector.png")),File.H,Rank.ONE);
		this.board[getArrayIndex(File.H,Rank.ONE)] = h1;
		
		Tile c2 = new Tile(new Deflector(Colour.BLACK, 225, new Image("resources/pieces/black_deflector.png")),File.C,Rank.TWO);
		this.board[getArrayIndex(File.C,Rank.TWO)] = c2;
		
		Tile a4 = new Tile(new Deflector(Colour.BLACK, 45, new Image("resources/pieces/black_deflector.png")),File.A,Rank.FOUR);
		this.board[getArrayIndex(File.A,Rank.FOUR)] = a4;
		
		Tile e4 = new Tile(new Switch(Colour.BLACK, 45, new Image("resources/pieces/black_switch.png")),File.E,Rank.FOUR);
		this.board[getArrayIndex(File.E,Rank.FOUR)] = e4;
		
		Tile f4 = new Tile(new Switch(Colour.BLACK, 135, new Image("resources/pieces/black_switch.png")),File.F,Rank.FOUR);
		this.board[getArrayIndex(File.F,Rank.FOUR)] = f4;
		
		Tile h4 = new Tile(new Deflector(Colour.BLACK, 135, new Image("resources/pieces/black_deflector.png")),File.H,Rank.FOUR);
		this.board[getArrayIndex(File.H,Rank.FOUR)] = h4;
		
		Tile a5 = new Tile(new Deflector(Colour.BLACK, 135, new Image("resources/pieces/black_deflector.png")),File.A,Rank.FIVE);
		this.board[getArrayIndex(File.A,Rank.FIVE)] = a5;
		
		Tile h5 = new Tile(new Deflector(Colour.BLACK, 45, new Image("resources/pieces/black_deflector.png")),File.H,Rank.FIVE);
		this.board[getArrayIndex(File.H,Rank.FIVE)] = h5;
		
		Tile g6 = new Tile(new Deflector(Colour.BLACK, 135, new Image("resources/pieces/black_deflector.png")),File.G,Rank.SIX);
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
		this.occupiedTilesBlack.clear();
		this.occupiedTilesWhite.clear();
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
	
	public void calculateAllActions() {
		// loop over occupied tiles for white or black, calculate list of all actions.
		this.actions.clear();  // clear previous list
		List<Action> actionsWhite = null;
		List<Action> actionsBlack = null;
		for(Tile tile : this.occupiedTilesWhite) {
			actionsWhite = tile.getPiece().calculateActions(tile,this);
		}
		for(Tile tile : this.occupiedTilesBlack) {
			actionsBlack = tile.getPiece().calculateActions(tile,this);
		}
		if(turn == Colour.WHITE) {
			this.actions.addAll(actionsWhite);
		} else {
			this.actions.addAll(actionsBlack);
		}
	}
	
	public void printActions() {
		/*
		 * print to terminal the moves in format:
		 * 1. Rotate J8 (Laser) clockwise
		 * 2. otate J8 (Laser) anti-clockwise 
		 * 3. Move H7 (Deflector) to G7 
		 * 4. Swap F5 (Switch) with G6 (BLACK Defender) etc.
		 */
		for (int i=0; i<this.actions.size(); i++) {
			System.out.println((i+1) + ". " + this.actions.get(i));
		}
	}
	
	public void commandLineRun(Board board) {
		while(board.blackKing == true && board.whiteKing == true){
			System.out.println("\n\n------------------\n"
					+ "It is now " + board.getTurn() + "'s turn. \n");
			System.out.println("Choose from the following actions: ");
			board.calculateAllActions();
			board.printActions();
			
			boolean confirm = false;
			while(!confirm) {
				System.out.println("Enter the number corresponding to your choice: ");
				Scanner in = new Scanner(System.in);
				int i = in.nextInt();
				Action action = board.getActions().get(i - 1);
				System.out.println("You chose to: " + action);
				System.out.println("Are you sure? [Y/N]");
				String s = in.next();
				s = s.toLowerCase();
				if (s.equals("y")) {
					confirm = true;
					// perform action
					action.makeAction();
					if(board.getTurn() == Colour.WHITE) {
						board.setTurn(Colour.BLACK);
					} else {
						board.setTurn(Colour.WHITE);
					}
					break;
				} else if (s.equals("n")) {
					continue;
				} else {
					System.out.println("Please enter 'Y' to confirm or 'N' to cancel.");
				}
				in.close();
			}
		}
		if(board.blackKing == false) {
			System.out.println("WHITE wins!");
		} else {
			System.out.println("BLACK wins!");
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
		return this.whiteLaserTile;
	}
	
	public Tile getBlackLaser() {
		return this.blackLaserTile;
	}

	public void setWhiteKing(boolean whiteKing) {
		this.whiteKing = whiteKing;
	}

	public void setBlackKing(boolean blackKing) {
		this.blackKing = blackKing;
	}

	public List<Action> getActions() {
		return this.actions;
	}
}

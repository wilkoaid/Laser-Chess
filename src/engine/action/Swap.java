package engine.action;

import engine.board.Board;
import engine.board.Tile;
import engine.piece.King;
import engine.piece.Piece;
import engine.piece.Switch;

public class Swap extends Action {
	private Tile destinationTile;
	
	public Swap(Tile sourceTile, Tile destinationTile, Board board) {
		super(sourceTile,board);
		this.destinationTile = destinationTile;
	}


	@Override
	public void makeAction() {
		// check for illegal Swaps
		if(!(this.sourceTile.getPiece() instanceof Switch)) {
			System.out.println("Swap action can only be performed by Switch piece");
			return;
		}
		if((this.destinationTile.getPiece() instanceof Switch) || 
				(this.destinationTile.getPiece() instanceof King)) {
			System.out.println("Swap action cannot be performed on another Switch, or a King");
			return;
		}
		// perform Swap
		Piece temp = this.sourceTile.getPiece();
		this.destinationTile.setPiece(this.sourceTile.getPiece());
		this.sourceTile.setPiece(temp);
		
		// fire laser to finish action
		this.fireLaser();
		
		// recalculate occupied tiles
		this.board.calculateOccupiedTiles();
	}
	
	public String toString() {
		return "Swap " + this.sourceTile.toString() + " with " + this.destinationTile.toString(); 
	}
}

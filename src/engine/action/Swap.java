package engine.action;

import engine.board.Tile;
import engine.piece.King;
import engine.piece.Switch;

public class Swap extends Action {
	private Tile destinationTile;
	
	public Swap(Tile sourceTile, Tile destinationTile) {
		super(sourceTile);
		this.destinationTile = destinationTile;
	}


	@Override
	void makeMove() {
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
		
		// fire laser to finish action
		this.fireLaser();
	}
}

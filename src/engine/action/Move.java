package engine.action;

import engine.board.Board;
import engine.board.Tile;

public class Move extends Action {
	private Tile destination;

	public Move(Tile sourceTile, Tile destination, Board board) {
		super(sourceTile,board);
		this.destination = destination;
	}

	public Tile getDestination() {
		return destination;
	}

	@Override
	public void makeAction() {
		// move to tile
		this.destination.setPiece(this.sourceTile.getPiece());
		this.sourceTile.setPiece(null);
		
		// recalculate occupied tiles
		this.board.calculateOccupiedTiles();
		
		// fire laser to finish action
		this.fireLaser();
	}
	
	public String toString() {
		return "Move " + this.sourceTile.toString() + " (" + this.sourceTile.getPiece().toString() +
				") to " + this.destination.toString(); 
	}
	
}

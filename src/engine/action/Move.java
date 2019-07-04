package engine.action;

import engine.board.Tile;

public class Move extends Action {
	private Tile destination;
	
	

	public Move(Tile sourceTile, Tile destination) {
		super(sourceTile);
		this.destination = destination;
	}

	public Tile getDestination() {
		return destination;
	}

	@Override
	public void makeMove() {
		// move to tile
		//destination.getPiece() = ;
		
		// fire laser to finish action
		this.fireLaser();
	}
	
	public String toString() {
		return "Move " + this.sourceTile.toString() + " (" + this.sourceTile.getPiece().toString() +
				") to " + this.destination.toString(); 
	}
	
}

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
	void makeMove() {
		// move to tile
		/* CODE HERE  */
		
		// fire laser to finish action
		this.fireLaser();
	}
	
}

package engine.action;

import engine.Direction;
import engine.board.Tile;

public class Rotate extends Action {
	private Direction rotationDirection;
	
	
	public Rotate(Tile sourceTile, Direction rotationDirection) {
		super(sourceTile);
		this.rotationDirection = rotationDirection;
	}

	
	@Override
	void makeMove() {
		// rotate piece
		/* CODE HERE  */
				
		// fire laser to finish action
		this.fireLaser();
	}
	
}

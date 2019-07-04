package engine.action;

import engine.Direction;
import engine.board.Tile;
import engine.piece.Piece;

public class Rotate extends Action {
	private Direction rotationDirection;
	
	
	public Rotate(Tile sourceTile, Direction rotationDirection) {
		super(sourceTile);
		this.rotationDirection = rotationDirection;
	}

	
	@Override
	public void makeMove() {
		// rotate piece
		Piece piece = this.sourceTile.getPiece();
		if(this.rotationDirection == Direction.CLOCKWISE) {
			piece.setDirection((360 + piece.getDirection() + 90) % 360);
		} else {
			piece.setDirection((360 + piece.getDirection() - 90) % 360);
		}
				
		// fire laser to finish action
		this.fireLaser();
	}
	
	public String toString() {
		return "Rotate " + this.sourceTile.toString() + " (" + this.sourceTile.getPiece().toString() +
				") " + this.rotationDirection.toString();
	}
	
}

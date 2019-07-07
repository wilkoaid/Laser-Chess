package engine.action;

import engine.Rotation;
import engine.board.Board;
import engine.board.Tile;
import engine.piece.Piece;

public class Rotate extends Action {
	private Rotation rotationDirection;
	
	
	public Rotate(Tile sourceTile, Rotation rotationDirection, Board board) {
		super(sourceTile,board);
		this.rotationDirection = rotationDirection;
	}

	
	@Override
	public void makeAction() {
		// rotate piece
		Piece piece = this.sourceTile.getPiece();
		if(this.rotationDirection == Rotation.CLOCKWISE) {
			piece.setDirection((360 + piece.getDirection() + 90) % 360);
			//piece.setDirection(nextDirection(2));
		} else {
			piece.setDirection((360 + piece.getDirection() - 90) % 360);
			//piece.setDirection(nextDirection(-2));
		}
				
		// fire laser to finish action
		this.fireLaser();
		
		// recalculate occupied tiles
		this.board.calculateOccupiedTiles();
	}
	
//	public Direction nextDirection(int a) {
//		int index = this.rotationDirection.ordinal();
//		int nextIndex = index + a;
//		Direction[] directions = Direction.values();
//		nextIndex %= directions.length;
//		return directions[nextIndex];
//	}
	
	public String toString() {
		return "Rotate " + this.sourceTile.toString() + " " + this.rotationDirection.toString();
	}
	
}

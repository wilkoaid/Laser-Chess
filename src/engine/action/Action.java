package engine.action;

import engine.Colour;
import engine.Direction;
import engine.board.Board;
import engine.board.Tile;
import engine.piece.Piece;

public abstract class Action {
	protected Tile sourceTile;
	protected Board board;
	
	public Action(Tile sourceTile, Board board) {
		this.sourceTile = sourceTile;
		this.board = board;
	}

	/**
	 * Each different action will implement this in a different way 
	 */
	public abstract void makeAction();
	
	public void fireLaser() {
		Colour turn = board.getTurn();
		Piece laser;
		if(turn == Colour.WHITE) {
			laser = board.getWhiteLaser().getPiece();
		} else {
			laser = board.getBlackLaser().getPiece();
		}
		Direction direction = laser.getDirection();
		if(direction == Direction.SOUTH) {
			
		} else if(direction == Direction.EAST) {
			// do other stuff
		} else if(direction == Direction.NORTH) {
			//  do other stuff
		} else { // WEST
			
		}
	}
	
}

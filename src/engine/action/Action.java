package engine.action;

import engine.Colour;
import engine.Direction;
import engine.board.Board;
import engine.board.Tile;
import engine.piece.Defender;
import engine.piece.Deflector;
import engine.piece.King;
import engine.piece.Piece;
import engine.piece.Switch;

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
		Tile tile;
		Piece laser;
		if(turn == Colour.WHITE) {
			tile = board.getWhiteLaser();
			laser = tile.getPiece();
		} else {
			tile = board.getBlackLaser();
			laser = tile.getPiece();
		}
		Direction direction = laser.getDirection();
		scanTiles(direction,tile);
	}
	
	public void scanTiles(Direction direction, Tile tile) {
		while (!tile.isOffboard()) {
			int boardIndex = board.getArrayIndex(tile.getFile(), tile.getRank());
			if (direction == Direction.SOUTH) {
				boardIndex += 12;
			} else if (direction == Direction.EAST) {
				boardIndex += 1;
			} else if (direction == Direction.NORTH) {
				boardIndex -= 12;
			} else { // WEST
				boardIndex -= 1;
			}
			tile = board.getBoard()[boardIndex];

			if (!tile.isEmpty()) {
				Piece piece = tile.getPiece();
				if (piece instanceof Deflector) {
					// get new direction based on current direction and facing direction of deflector
					// finish action and remove deflector if laser kills deflector
					Direction deflectorDirection = piece.getDirection();
					if (direction == Direction.SOUTH) {
						if(deflectorDirection == Direction.NORTHEAST) {
							direction = Direction.EAST;
						} else if(deflectorDirection == Direction.NORTHWEST) {
							direction = Direction.WEST;
						} else {
							// laser kills piece
							tile.setPiece(null);
							// finish action
							return;
						}
					} else if (direction == Direction.EAST) {
						if (deflectorDirection == Direction.NORTHWEST) {
							direction = Direction.NORTH;
						} else if (deflectorDirection == Direction.SOUTHWEST) {
							direction = Direction.SOUTH;
						} else {
							// laser kills piece
							tile.setPiece(null);
							// finish action
							return;
						}
					} else if (direction == Direction.NORTH) {
						if (deflectorDirection == Direction.SOUTHEAST) {
							direction = Direction.EAST;
						} else if (deflectorDirection == Direction.SOUTHWEST) {
							direction = Direction.WEST;
						} else {
							// laser kills piece
							tile.setPiece(null);
							// finish action
							return;
						}
					} else {
						// direction == west
						if (deflectorDirection == Direction.NORTHEAST) {
							direction = Direction.NORTH;
						} else if (deflectorDirection == Direction.SOUTHEAST) {
							direction = Direction.SOUTH;
						} else {
							// laser kills piece
							tile.setPiece(null);
							// finish action
							return;
						}
					}
				} else if (piece instanceof Defender) {
					// finish action and remove defender if laser kills defender
					Direction defenderDirection = piece.getDirection();
				} else if (piece instanceof King) {
					// finish game and declare winner based on colour of king
				} else if (piece instanceof Switch) {
					// get new direction based on current direction and facing direction of switch piece
				} else { // piece == laser
					// cannot kill laser piece
					// finish action.
				}
			}
		}
	}
}


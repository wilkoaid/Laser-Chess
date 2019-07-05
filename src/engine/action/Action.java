package engine.action;

import engine.Colour;
import engine.board.Board;
import engine.board.Tile;
import engine.piece.*;

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
		int direction = laser.getDirection();
		
		/*
		 * simulate laser path by searching through tiles and change the laser 
		 * direction if reflecting off a mirror.  Destroy a piece if it is encountered
		 * or finish the method without doing anything if the laser goes offboard
		 */
		
		int boardIndex = board.getArrayIndex(tile.getFile(), tile.getRank());
		boardIndex = getNextIndex(direction, boardIndex);
		tile = board.getBoard()[boardIndex];
		System.out.println("The laser takes the following path: ");
		while (!tile.isOffboard()) {
			System.out.println(tile);
			if (!tile.isEmpty()) {
				Piece piece = tile.getPiece();
				int angleDelta = signedDifference(direction, piece.getDirection());
				if (piece instanceof Deflector) {
					// get new direction based on current direction and facing direction of deflector
					// finish action and remove deflector if laser kills deflector
					if(angleDelta == 135) {
						direction = (direction - 90 + 360) % 360;
					} else if(angleDelta == -135) {
						direction = (direction + 90) % 360;
					} else {
						tile.setPiece(null);
						return;
					}
				} else if (piece instanceof Defender) {
					// finish action and remove defender if laser kills defender
					if(angleDelta == 180 || angleDelta == -180) {
						return;
					} else {
						tile.setPiece(null);
						return;
					}
				} else if (piece instanceof King) {
					// finish game and declare winner based on colour of king
					if(piece.getColour() == Colour.WHITE) {
						board.setWhiteKing(false);
						tile.setPiece(null);
						return;
					} else {
						board.setBlackKing(false);
						tile.setPiece(null);
						return;
					}
				} else if (piece instanceof Switch) {
					// get new direction based on current direction and facing direction of switch piece
					if(angleDelta == 135 || angleDelta == -45) {
						direction = (direction - 90 + 360) % 360;
					} else if(angleDelta == -135 || angleDelta == 45) {
						direction = (direction + 90) % 360;
					}
				} else { // piece == laser
					// cannot kill laser piece
					// finish action.
					return;
				}
		
			}
			
			boardIndex = getNextIndex(direction, boardIndex);
			tile = board.getBoard()[boardIndex];
		}
		System.out.println("The laser shoots offboard");
	}

	public int getNextIndex(int direction, int boardIndex) {
		if (direction == 180) {
			boardIndex += 12;
		} else if (direction == 90) {
			boardIndex += 1;
		} else if (direction == 0) {
			boardIndex -= 12;
		} else { // WEST
			boardIndex -= 1;
		}
		return boardIndex;
	}
	
	/**
	 * Takes as inputs the direction of laser approach, the facing direction of the piece, and outputs
	 * the signed difference between these two angles. E.g. laser beam approaching south (180 degrees)
	 * and a Deflector with mirror facing north-east returns 135.  If the Deflector is facing
	 * north-west then -135 is returned instead.
	 * @param a direction of laser approach
	 * @param b facing direction of piece
	 * @return signed difference between a and b
	 */
	public int signedDifference(int a, int b) {
		int d = Math.abs(a - b) % 360;
		int r = d > 180 ? 360 - d : d;
		
		int sign = ((a - b >= 0 && a - b <= 180) || (a - b <= -180 && a - b >= -360)) ? 1 : -1;
		return r * sign;
	}
	
	
	
	
}


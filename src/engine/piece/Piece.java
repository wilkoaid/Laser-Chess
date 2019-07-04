package engine.piece;

import java.util.ArrayList;
import java.util.List;

import engine.Colour;
import engine.Rotation;
import engine.action.Action;
import engine.action.Move;
import engine.action.Rotate;
import engine.board.Board;
import engine.board.Tile;

public abstract class Piece {
	protected int direction; /* angle in degrees
	 							0:   north
	 							45:  north-east
	 							90:  east
	 							135: south-east
	 							180: south
	 							225: south-west
	 							270: west
	 							315: north-west */
	private final Colour colour;
	
	
	protected Piece(Colour colour, int direction) {
		this.colour = colour;
		this.direction = direction;
	}
	
	public int getDirection() {
		return direction;
	}

	public Colour getColour() {
		return colour;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public abstract List<Action> calculateActions(Tile thisTile, Board board);
	
	public List<Action> calculateMoves(Tile thisTile, Board board){
		List<Action> moves = new ArrayList<Action>();
		int thisTileIndex = board.getArrayIndex(thisTile.getFile(), thisTile.getRank());
		for(int i=-1; i<=1; i++) {
			for(int j=-1; j<=1; j++) {
				int destTileIndex = thisTileIndex + 12 * i + j;
				Tile destinationTile = board.getBoard()[destTileIndex];
				if(board.getBoard()[destTileIndex].isOffboard() ||
						!board.getBoard()[destTileIndex].isEmpty()) {
					continue;
				} else {
					Action move = new Move(thisTile, destinationTile,board);
					moves.add(move);
				}
			}
		}
		return moves;
	}
	
	public List<Action> calculateRotations(Tile thisTile, Board board) {
		List<Action> rotations = new ArrayList<Action>();
		
		rotations.add(new Rotate(thisTile, Rotation.CLOCKWISE));
		rotations.add(new Rotate(thisTile, Rotation.ANTICLOCKWISE));
		
		return rotations;
	}
	

	
	
	
	
	
	
	
	
	
	
}

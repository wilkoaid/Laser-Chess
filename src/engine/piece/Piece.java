package engine.piece;

import java.util.List;

import engine.Colour;
import engine.action.Action;
import engine.board.Tile;

public abstract class Piece {
	int direction; /* angle in degrees
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

	public abstract List<Action> calculateActions(Tile thisTile, Tile[] board);
	
	
	
}

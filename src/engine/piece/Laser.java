package engine.piece;

import java.util.ArrayList;
import java.util.List;

import engine.Colour;
import engine.Direction;
import engine.Rotation;
import engine.action.Action;
import engine.action.Rotate;
import engine.board.Board;
import engine.board.Tile;

public class Laser extends Piece {

	public Laser(Colour colour, Direction direction) {
		super(colour, direction);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Action> calculateActions(Tile thisTile, Board board) {
		List<Action> actions = new ArrayList<Action>();
		
		// calculate rotation actions
		if(thisTile.getPiece().direction == Direction.NORTH ||
				thisTile.getPiece().direction == Direction.SOUTH) {
			actions.add(new Rotate(thisTile, Rotation.ANTICLOCKWISE, board));
		} else {
			actions.add(new Rotate(thisTile, Rotation.CLOCKWISE, board));
		}
		
		return actions;
	}
	
	public String toString() {
		return "Laser";
	}
	
}

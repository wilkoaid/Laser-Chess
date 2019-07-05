package engine.piece;

import java.util.ArrayList;
import java.util.List;

import engine.Colour;
import engine.Rotation;
import engine.action.Action;
import engine.action.Rotate;
import engine.board.Board;
import engine.board.Tile;

public class Laser extends Piece {

	public Laser(Colour colour, int direction) {
		super(colour, direction);
	}

	@Override
	public List<Action> calculateActions(Tile thisTile, Board board) {
		List<Action> actions = new ArrayList<Action>();
		
		// calculate rotation actions
		if(thisTile.getPiece().direction == 0 ||
				thisTile.getPiece().direction == 180) {
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

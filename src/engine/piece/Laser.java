package engine.piece;

import java.util.ArrayList;
import java.util.List;

import engine.Colour;
import engine.Direction;
import engine.action.Action;
import engine.action.Rotate;
import engine.board.Board;
import engine.board.Tile;

public class Laser extends Piece {

	public Laser(Colour colour, int direction) {
		super(colour, direction);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Action> calculateActions(Tile thisTile, Board board) {
		List<Action> actions = new ArrayList<Action>();
		
		// calculate rotation actions
		if(thisTile.getPiece().direction == 0 ||
				thisTile.getPiece().direction == 180) {
			actions.add(new Rotate(thisTile, Direction.ANTICLOCKWISE));
		} else {
			actions.add(new Rotate(thisTile, Direction.CLOCKWISE));
		}
		
		return actions;
	}
	
}

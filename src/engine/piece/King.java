package engine.piece;

import java.util.ArrayList;
import java.util.List;

import engine.Colour;
import engine.Direction;
import engine.action.Action;
import engine.board.Board;
import engine.board.Tile;

public class King extends Piece {

	public King(Colour colour, Direction direction) {
		super(colour, direction);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Action> calculateActions(Tile thisTile, Board board) {
		List<Action> actions = new ArrayList<Action>();
		// calculate movement actions
		actions.addAll(calculateMoves(thisTile, board));
		return actions;
	}

	public String toString() {
		return "King";
	}
}

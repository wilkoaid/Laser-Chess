package engine.piece;

import java.util.ArrayList;
import java.util.List;

import engine.Colour;
import engine.Direction;
import engine.action.Action;
import engine.action.Move;
import engine.board.Board;
import engine.board.Tile;

public class Defender extends Piece {

	public Defender(Colour colour, int direction) {
		super(colour, direction);
	}

	@Override
	public List<Action> calculateActions(Tile thisTile, Board board) {
		List<Action> actions = new ArrayList<Action>();
		// calculate movement actions
		actions.addAll(calculateMoves(thisTile, board));
		// calculate rotation actions
		actions.addAll(calculateRotations(thisTile, board));
		return actions;
	}
	
	public String toString() {
		return "Defender";
	}
}

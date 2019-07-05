package engine.piece;

import java.util.ArrayList;
import java.util.List;

import engine.Colour;
import engine.Direction;
import engine.action.Action;
import engine.board.Board;
import engine.board.Tile;

public class Deflector extends Piece {

	public Deflector(Colour colour, int direction) {
		super(colour, direction);
		// TODO Auto-generated constructor stub
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
		return "Deflector";
	}

}

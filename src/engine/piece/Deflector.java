package engine.piece;

import java.util.ArrayList;
import java.util.List;

import engine.Colour;
import engine.action.Action;
import engine.board.Board;
import engine.board.Tile;
import javafx.scene.image.ImageView;

public class Deflector extends Piece {

	public Deflector(Colour colour, int direction, ImageView image) {
		super(colour, direction, image);
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

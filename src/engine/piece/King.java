package engine.piece;

import java.util.ArrayList;
import java.util.List;

import engine.Colour;
import engine.action.Action;
import engine.board.Board;
import engine.board.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class King extends Piece {

	public King(Colour colour, int direction, Image image) {
		super(colour, direction, image);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Action> calculateActions(Tile thisTile, Board board) {
		List<Action> actions = new ArrayList<Action>();
		// calculate movement actions
		actions.addAll(calculateMoves(thisTile, board));
		this.actions.addAll(actions);
		return actions;
	}

	public String toString() {
		return "King";
	}
}

package engine.piece;

import java.util.List;

import engine.Colour;
import engine.action.Action;
import engine.board.Tile;

public class Laser extends Piece {

	protected Laser(Colour colour, int direction) {
		super(colour, direction);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Action> calculateMoves(Tile thisTile, List<Tile> occupiedTiles) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

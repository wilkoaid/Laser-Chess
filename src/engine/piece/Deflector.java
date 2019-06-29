package engine.piece;

import java.util.List;

import engine.Colour;
import engine.File;
import engine.Rank;
import engine.action.Action;
import engine.board.Tile;

public class Deflector extends Piece {

	public Deflector(Colour colour, int direction) {
		super(colour, direction);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Action> calculateMoves(Rank rank, File file, List<Tile> occupiedTiles) {
		// TODO Auto-generated method stub
		return null;
	}

}

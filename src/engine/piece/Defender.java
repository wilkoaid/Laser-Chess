package engine.piece;

import java.util.ArrayList;
import java.util.List;

import engine.Colour;
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
		int thisTileIndex = board.getArrayIndex(thisTile.getFile(), thisTile.getRank());
		
		// calculate movement actions
		for(int i=-1; i<=1; i++) {
			for(int j=-1; j<=1; j++) {
				int destTileIndex = thisTileIndex + 12 * i + j;
				Tile destinationTile = board.getBoard()[destTileIndex];
				if(board.getBoard()[destTileIndex].isOffboard() ||
						!board.getBoard()[destTileIndex].isEmpty()) {
					continue;
				} else {
					Action action = new Move(thisTile, destinationTile);
					actions.add(action);
				}
			}
			
		}
		
		return actions;
	}

}

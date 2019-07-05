package engine.piece;

import java.util.ArrayList;
import java.util.List;

import engine.Colour;
import engine.Direction;
import engine.action.Action;
import engine.action.Move;
import engine.action.Swap;
import engine.board.Board;
import engine.board.Tile;

public class Switch extends Piece {

	public Switch(Colour colour, int direction) {
		super(colour, direction);
	}

	@Override
	public List<Action> calculateActions(Tile thisTile, Board board) {
		List<Action> actions = new ArrayList<Action>();

		// calculate rotation actions
		actions.addAll(calculateRotations(thisTile, board));
		
		// calculate swap/move actions
		List<Action> moves = new ArrayList<Action>();
		int thisTileIndex = board.getArrayIndex(thisTile.getFile(), thisTile.getRank());
		for(int i=-1; i<=1; i++) {
			for(int j=-1; j<=1; j++) {
				int destTileIndex = thisTileIndex + 12 * i + j;
				Tile destinationTile = board.getBoard()[destTileIndex];
				if(board.getBoard()[destTileIndex].isOffboard()) {
					continue;
				} else if (board.getBoard()[destTileIndex].isEmpty()) {
					moves.add(new Move(thisTile, destinationTile,board));
				} else {
					if(!(board.getBoard()[destTileIndex].getPiece() instanceof King) &&
							!(board.getBoard()[destTileIndex].getPiece() instanceof Switch)) {
						moves.add(new Swap(thisTile, destinationTile, board));
					}
				}
			}
		}
		actions.addAll(moves);
		
		
		return actions;
	}
	
	public String toString() {
		return "Switch";
	}
}

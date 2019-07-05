package engine.action;

import engine.board.Board;
import engine.board.Tile;

public abstract class Action {
	protected Tile sourceTile;
	protected Board board;
	
	public Action(Tile sourceTile, Board board) {
		this.sourceTile = sourceTile;
		this.board = board;
	}

	/**
	 * Each different action will implement this in a different way 
	 */
	public abstract void makeAction();
	
	public void fireLaser() {
		//  CODE HERE
	}
	
}

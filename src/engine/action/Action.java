package engine.action;

import engine.board.Tile;

public abstract class Action {
	protected Tile sourceTile;
	
	public Action(Tile sourceTile) {
		this.sourceTile = sourceTile;
	}

	/**
	 * Each different action will implement this in a different way 
	 */
	abstract void makeMove();
	
	public void fireLaser() {
		//  CODE HERE
	}
	
	public String toString() {
		return "";
	}
}

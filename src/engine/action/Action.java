package engine.action;

import engine.board.Tile;

public abstract class Action {
	private Tile sourceTile;
	
	public Action(Tile sourceTile) {
		super();
		this.sourceTile = sourceTile;
	}

	/**
	 * Each different action will implement this in a different way 
	 */
	abstract void makeMove();
	
	public void fireLaser() {}
}

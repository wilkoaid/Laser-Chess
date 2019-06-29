package engine.action;

import engine.board.Tile;

public abstract class Action {
	Tile sourceTile;
	
	/**
	 * Each different action will implement this in a different way 
	 */
	abstract void makeMove();
	
	void fireLaser() {}
}

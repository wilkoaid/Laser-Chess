package engine.action;

import engine.board.Tile;

public class Move extends Action {
	Tile destination;
	
	

	public Move(Tile sourceTile, Tile destination) {
		super(sourceTile);
		this.destination = destination;
	}



	@Override
	void makeMove() {
		// TODO Auto-generated method stub
		
	}
	
}

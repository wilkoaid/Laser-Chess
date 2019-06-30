package engine.action;

import engine.board.Tile;

public class Move extends Action {
	private Tile destination;
	
	

	public Move(Tile sourceTile, Tile destination) {
		super(sourceTile);
		this.destination = destination;
	}



	public Tile getDestination() {
		return destination;
	}



	@Override
	void makeMove() {
		// TODO Auto-generated method stub
		
	}
	
}

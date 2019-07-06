package engine;

import engine.board.Board;

public class LaserChessMain {
	public static void main(String[] args) {
		
		Board board = new Board(Colour.WHITE);
		
		board.commandLineRun(board);
	}

	

}


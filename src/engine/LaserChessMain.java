package engine;

import engine.board.*;
import engine.action.*;
import engine.piece.*;

public class LaserChessMain {

	public static void main(String[] args) {
		
		Board board = new Board(Colour.WHITE);
		
		System.out.println("It is now " + board.getTurn() + "'s turn");
		
		

	}

}

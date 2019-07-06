package engine;

import engine.action.Action;
import engine.board.*;

import java.util.Scanner;

public class LaserChessMain {
	public static void main(String[] args) {
		
		Board board = new Board(Colour.WHITE);
		
		board.commandLineRun(board);
	}

	

}


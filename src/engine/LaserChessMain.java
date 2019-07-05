package engine;

import engine.action.Action;
import engine.board.*;

import java.util.Scanner;

public class LaserChessMain {

	public static void main(String[] args) {
		
		Board board = new Board(Colour.WHITE);
		
		System.out.println("It is now " + board.getTurn() + "'s turn");
		System.out.println("Choose from the following actions: ");
		board.printActions();
		
		boolean confirm = false;
		while(!confirm) {
			System.out.println("Enter the number corresponding to your choice: ");
			Scanner in = new Scanner(System.in);
			int i = in.nextInt();
			Action action = board.getActions().get(i - 1);
			System.out.println("You chose to: " + action);
			System.out.println("Are you sure? [Y/N]");
			//in = new Scanner(System.in);
			String s = in.next();
			s = s.toLowerCase();
			if (s.equals("y")) {
				confirm = true;
				// perform action
				action.makeAction();
			} else if (s.equals("n")) {
				continue;
			} else {
				System.out.println("Please enter 'Y' to confirm or 'N' to cancel.");
			}
			board.setTurn(Colour.BLACK);
			System.out.println("It is now " + board.getTurn() + "'s turn.");
			in.close();
		}
		
	}

}

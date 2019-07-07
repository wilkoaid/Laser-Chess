package engine.board;

import engine.File;
import engine.Rank;
import engine.piece.Piece;

public class Tile {
	private final File file;  // A,B,C, ... ,J
	private final Rank rank;  // 1,2,3, ... ,8
	private final boolean offboard; 
	
	private boolean empty;
	private Piece piece;
	
	public Tile(Piece piece, File file, Rank rank) {
		this.offboard = false;
		this.empty = false;
		this.piece = piece;
		this.file = file;
		this.rank = rank;
	}
	
	public Tile(File file, Rank rank) {
		this.offboard = false;
		this.empty = true;
		this.piece = null;
		this.file = file;
		this.rank = rank;
	}
	
	public Tile() {
		this.offboard = true;
		this.empty = false;
		this.piece = null;
		this.file = null;
		this.rank = null;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public boolean isOffboard() {
		return offboard;
	}

	public Piece getPiece() {
		return piece;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Rank getRank() {
		return rank;
	}

	public File getFile() {
		return file;
	}
	
	public String toString() {
		if(this.isOffboard()) {
			return "Offboard";
		}
		String str = this.file.getString() + this.rank.getString();
		if(this.piece != null) {
			return str + " (" + piece.toString() + ")";
		} else {
			return str;
		}
	}
}

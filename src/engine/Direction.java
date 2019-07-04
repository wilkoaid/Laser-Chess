package engine;

public enum Direction {
	CLOCKWISE("clockwise"),
	ANTICLOCKWISE("anticlockwise");
	
	private String string;
	
	private Direction(String str) {
		this.string = str;
	}
	
	public String toString() {
		return this.string;
	}
}

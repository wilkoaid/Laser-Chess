package engine;

public enum Rotation {
	CLOCKWISE("clockwise"),
	ANTICLOCKWISE("anticlockwise");
	
	private String string;
	
	private Rotation(String str) {
		this.string = str;
	}
	
	public String toString() {
		return this.string;
	}
}

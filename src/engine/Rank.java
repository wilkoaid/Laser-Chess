package engine;

public enum Rank {
	ONE(1,"1"),
	TWO(2,"2"),
	THREE(3,"3"),
	FOUR(4,"4"),
	FIVE(5,"5"),
	SIX(6,"6"),
	SEVEN(7,"7"),
	EIGHT(8,"8");
	
	private int val;
	private String string;
	
	private Rank(int val, String string) {
		this.val = val;
		this.string = string;
	}
	
	public int getVal() {
        return this.val;
    }
	
	public String getString() {
		return this.string;
	}
}

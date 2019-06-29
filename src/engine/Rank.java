package engine;

public enum Rank {
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8);
	
	private int val;
	
	private Rank(int val) {
		this.val = val;
	}
	
	public int getVal() {
        return this.val;
    }
}

package engine;


public enum File {
	A(1),
	B(2),
	C(3),
	D(4),
	E(5),
	F(6),
	G(7),
	H(8),
	I(9),
	J(10);
	
	private int val;
	
	private File(int val) {
		this.val = val;
	}
	
	public int getVal() {
        return this.val;
    }
}

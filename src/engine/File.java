package engine;


public enum File {
	A(1,"A"),
	B(2,"B"),
	C(3,"C"),
	D(4,"D"),
	E(5,"E"),
	F(6,"F"),
	G(7,"G"),
	H(8,"H"),
	I(9,"I"),
	J(10,"J");
	
	private int val;
	private String string;
	
	private File(int val, String string) {
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

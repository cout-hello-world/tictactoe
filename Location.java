public class Location {
	private final int r;
	private final int c;

	public int getRow() {
		return r;
	}

	public int getColumn() {
		return c;
	}

	public Location(int row, int column) {
		r = row;
		c = column;
	}
}

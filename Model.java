public class Model {
	private int[][] board;
	private boolean gameOver;
	private boolean winnerCalled;
	private int winner = Position.CONTINUE;

	public Model() {
		board = new int[3][3];
	}

	public void select(Location square) {
		int row = square.getRow();
		int col = square.getColumn();
		if (!gameOver && board[row][col] == 0 &&
			        row >= 0 && row < 3 && col >= 0 && col < 3) {
			board[row][col] = Position.X;
			Position response = new Position(board, true, square);
			Location move = response.getMove();
			if (move != null) {
				board[move.getRow()][move.getColumn()] = Position.O;
			}
		}
		int evaluation = Position.eval(board);
		if (evaluation != Position.CONTINUE) {
			winner = evaluation;
			gameOver = true;
		}
	}

	public String getLabel(Location square) {
		int row = square.getRow();
		int col = square.getColumn();
		int val = board[row][col];
		switch (val) {
		case 0:
			return "";
		case Position.X:
			return "X";
		case Position.O:
			return "O";
		default:
			throw new IllegalStateException(
			        "The board has the invalid entry " + val + ".");
		}
	}

	public boolean gameIsOver() {
		return gameOver;
	}

	public boolean getWinnerCalled() {
		return winnerCalled;
	}

	public int getWinner() {
		winnerCalled = true;
		return winner;
	}
}

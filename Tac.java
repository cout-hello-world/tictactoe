import java.util.Scanner;

public class Tac {
	private static int[][] board = new int[3][3];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (!Position.isFull(board) && in.hasNext()) {
			String line = in.nextLine();
			int row = line.charAt(0) - '0';
			int column = line.charAt(1) - '0';
			board[row][column] = Position.X;
			Position response = new Position(board, true, new Location(row, column));
			Location move = response.getMove();
			if (move == null) {
				print(board);
				break;
			}
			board[move.getRow()][move.getColumn()] = Position.O;
			print(board);
		}
		in.close();
	}

	public static void print(int[][] board) {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				if (board[r][c] == Position.X) {
					System.out.print("X");
				} else if (board[r][c] == Position.O) {
					System.out.print("O");
				} else {
					System.out.print("-");
				}
			}
			System.out.println();
		}
	}
}

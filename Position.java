import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.security.SecureRandom;

public class Position implements Comparable<Position> {
	private final int value;
	private final Location toHere;
	private final Location move;

	public Location getMove() {
		return move;
	}

	@Override
	public int compareTo(Position other) {
		return Integer.compare(value, other.value);
	}

	public Position(int[][] representation, boolean toMove, Location toThisPosition) {
		toHere = toThisPosition;
		int[][] rep =
		        new int[representation.length][representation[0].length];
		for (int r = 0; r < representation.length; r++) {
			for (int c = 0; c < representation[r].length; c++) {
				rep[r][c] = representation[r][c];
			}
		}

		int val = eval(rep);
		if (val != CONTINUE) {
			value = val;
			move = null;
		} else {
			List<Position> positions;
			positions = getPositions(rep, toMove);
			Collections.sort(positions);
			Position bestPosition;
			if (toMove) {
				bestPosition = randomHigh(positions);
			} else {
				bestPosition = randomLow(positions);
			}
			value = bestPosition.value;
			move = bestPosition.toHere;
		}
	}

	public static boolean isFull(int[][] rep) {
		for (int r = 0; r < rep.length; r++) {
			for (int c = 0; c < rep[r].length; c++) {
				if (rep[r][c] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	private static List<Position> getPositions(int[][] rep, boolean toMove) {
		List<Position> positions = new ArrayList<Position>();
		for (int r = 0; r < rep.length; r++) {
			for (int c = 0; c < rep[0].length; c++) {
				if (rep[r][c] == 0) {
					rep[r][c] = toMove ? O : X;
					positions.add(new Position(rep, !toMove, new Location(r, c)));
					rep[r][c] = 0;
				}
			}
		}
		return positions;
	}

	public static int eval(int[][] rep) {
		if (hasWon(rep, O)) {
			return WIN;
		} else if (hasWon(rep, X)) {
			return LOSS;
		} else if (isFull(rep)) {
			return DRAW;
		} else {
			return CONTINUE;
		}
	}

	private static boolean hasWon(int[][] rep, int player) {
		if (hasRow(rep, player) || hasColumn(rep, player) ||
			hasDiagonal(rep, player)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean hasRow(int[][] rep, int player) {
		int count = 0;
		for (int r = 0; r < rep.length; r++) {
			for (int c = 0; c < rep[r].length; c++) {
				if (rep[r][c] == player) {
					count++;
				}
			}
			if (count == rep[r].length) {
				return true;
			}
			count = 0;
		}
		return false;
	}

	private static boolean hasColumn(int[][] rep, int player) {
		int count = 0;
		for (int c = 0; c < rep[0].length; c++) {
			for (int r = 0; r < rep.length; r++) {
				if (rep[r][c] == player) {
					count++;
				}
			}
			if (count == rep.length) {
				return true;
			}
			count = 0;
		}
		return false;
	}

	private static boolean hasDiagonal(int[][] rep, int player) {
		int mainCount = 0;
		int otherCount = 0;
		for (int r = 0; r < rep.length; r++) {
			for (int c = 0; c < rep[r].length; c++) {
				if (r == c) {
					if (rep[r][c] == player) {
						mainCount++;
					}
				}
				if (r + c == rep.length - 1) {
					if (rep[r][c] == player) {
						otherCount++;
					}
				}
			}
		}
		return mainCount == rep.length || otherCount == rep.length;
	}

	private static SecureRandom rand = new SecureRandom();

	private static Position randomHigh(List<Position> positions) {
		int targetValue = positions.get(positions.size() - 1).value;
		int i;
		for (i = positions.size() - 1; i >= 0; i--) {
			if (positions.get(i).value != targetValue) {
				break;
			}
		}
		return positions.get(i + 1 + rand.nextInt(positions.size() - 1 - i));
	}

	private static Position randomLow(List<Position> positions) {
		int targetValue = positions.get(0).value;
		int i;
		for (i = 0; i < positions.size(); i++) {
			if (positions.get(i).value != targetValue) {
				break;
			}
		}
		return positions.get(rand.nextInt(i));
	}

	public static final int X = -1;
	public static final int O = 1;

	public static final int LOSS = -1;
	public static final int DRAW = 0;
	public static final int WIN = 1;
	public static final int CONTINUE = 42;
}

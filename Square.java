import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Square extends JButton {
	private final Location here;
	private TicTacToe win;

	public Square(TicTacToe window, Location coordinates) {
		here = coordinates;
		win = window;
		addActionListener(new SquareListener());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setText(win.getModel().getLabel(here));
	}

	private class SquareListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Model model = win.getModel();
			model.select(here);
			win.repaint();
			if (!model.getWinnerCalled() && model.gameIsOver()) {
				String message = "";
				int winner = model.getWinner();
				switch (winner) {
				case Position.LOSS:
					message = "You have won; report the bug to Henry Elliott.";
					break;
				case Position.DRAW:
					message = "The game was a draw.";
					break;
				case Position.WIN:
					message = "You lost.";
					break;
				default:
					throw new IllegalStateException(
					        "getWinner() returned illegal value " +
					        winner + ".");
				}
				String[] choices = {"Yes", "No", "Exit"};
				int choice = JOptionPane.showOptionDialog(null,
				        message + "\nWould you like to play again?",
				        "Game Over", JOptionPane.YES_NO_CANCEL_OPTION,
				        JOptionPane.QUESTION_MESSAGE, null, choices,
				        choices[0]);
				switch (choice) {
				case YES:
					win.reset();
					break;
				case NO:
					// Do nothing.
					break;
				case EXIT:
					System.exit(0);
					break;
				default: // "X" pressed.
					//Do nothing.
					break;
				}
				win.repaint();
			}
		}

		private static final int YES = 0;
		private static final int NO = 1;
		private static final int EXIT = 2;
	}
}

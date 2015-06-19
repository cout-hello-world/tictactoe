import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Area extends JPanel {
	private TicTacToe win;

	public Area(TicTacToe window) {
		win = window;
		setLayout(new GridLayout(3, 3));
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				add(new Square(win, new Location(r, c)));
			}
		}
	}
}

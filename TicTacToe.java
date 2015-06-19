import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame {
	private Model model;

	public TicTacToe() {
		model = new Model();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(300, 300));
		setTitle("TicTacToe");
		initMenu();
		add(new Area(this));
		setVisible(true);
	}

	public Model getModel() {
		return model;
	}

	public void reset() {
		model = new Model();
	}

	private void initMenu() {
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		bar.add(fileMenu);
		JMenuItem newItem = new JMenuItem("New");
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(newItem);
		fileMenu.add(exitItem);
		add(bar);
		setJMenuBar(bar);
		newItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
				repaint();
			}
		});
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		new TicTacToe();
	}
}

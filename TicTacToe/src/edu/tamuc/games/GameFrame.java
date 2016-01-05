package edu.tamuc.games;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * MyTicTacToeFrame.java
 * 
 * @author Mohammed Abdul Aziz Syed
 * @CWID   50143871
 * @Course CSCI 531 HW5
 * 
 */

public class GameFrame extends JFrame {

	public JButton[][] gameBoard = new JButton[3][3];
	JTextField statusBar;
	GamePanel gamePanel;
	int playerID;
	GameListener ticTacToeListener = new GameListener(this);
	int count;
	boolean isGameOver = false;

	public GameFrame() {
		setLayout(new BorderLayout());

		gamePanel = new GamePanel(this);
		add(gamePanel, BorderLayout.CENTER);

		statusBar = new JTextField("Player 1's Turn");
		statusBar.setEditable(false);
		add(statusBar, BorderLayout.SOUTH);

		setTitle("Tic Tac Toe");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 400, 300, 300);
	}

	void initComponents() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				gameBoard[i][j]
						.putClientProperty("CELL_POSITION", new Integer[] { i, j });
				gameBoard[i][j].putClientProperty("PLAYER", null);
				gameBoard[i][j].setIcon(null);
				gameBoard[i][j].setEnabled(true);
				playerID = 1;
				count = 0;
				statusBar.setText("Player 1's Turn");

			}
	}

	void initComponents(String statusText) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				gameBoard[i][j]
						.putClientProperty("CELL_POSITION", new Integer[] { i, j });
				gameBoard[i][j].putClientProperty("PLAYER", null);
				gameBoard[i][j].setIcon(null);
				gameBoard[i][j].setEnabled(true);
				playerID = 1;
				count = 0;
				statusBar.setText(statusText);

			}
	}

	public JTextField getStatusBar() {
		return statusBar;
	}

	public void setStatusBar(JTextField statusBar) {
		this.statusBar = statusBar;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public GameListener getTicTacToeListener() {
		return ticTacToeListener;
	}

	public void setTicTacToeListener(GameListener ticTacToeListener) {
		this.ticTacToeListener = ticTacToeListener;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

}

package edu.tamuc.games;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * TicTacToeListener.java
 * 
 * @author Mohammed Abdul Aziz Syed
 * @CWID   50143871
 * @Course CSCI 531 HW5
 * 
 */

public class GameListener implements ActionListener {
	
	private GameFrame myTicTacToeFrame;
	
	public GameListener(GameFrame myTicTacToeFrame) {
		this.myTicTacToeFrame = myTicTacToeFrame;
	}
	
	public void actionPerformed(ActionEvent e) {
		myTicTacToeFrame.setCount(myTicTacToeFrame.getCount()+1);
		JButton b = (JButton) e.getSource();
		Integer[] index = (Integer[]) b.getClientProperty("INDEX");
		if (myTicTacToeFrame.isGameOver) {
			JOptionPane.showMessageDialog(null, "Game Over!!");
			return;
		}
		if (b.getText().equals("O") || b.getText().equals("X")) {
			return;
		}

		b.putClientProperty("OWNER", myTicTacToeFrame.getPlayerID());
		String previousPlayer = null;
		if (myTicTacToeFrame.getPlayerID() == 1) {
			previousPlayer = "Player 1 Won";
			b.setText("O");
			b.setFont(new Font("Arial", Font.BOLD, 24));
			b.setForeground(Color.BLACK);
			b.setBackground(Color.YELLOW);
			myTicTacToeFrame.setPlayerID(2);
			myTicTacToeFrame.getStatusBar().setText("Player 2's Turn");
		} else {
			previousPlayer = "Player 2 Won";
			b.setText("X");
			b.setFont(new Font("Arial", Font.BOLD, 24));
			b.setForeground(Color.RED);
			b.setBackground(Color.GREEN);
			myTicTacToeFrame.setPlayerID(1);
			myTicTacToeFrame.getStatusBar().setText("Player 1's Turn");
		}
		boolean result = isGameWon(index);
		if (result) {
			myTicTacToeFrame.initComponents(previousPlayer);
			myTicTacToeFrame.isGameOver = true;
		}
		if (myTicTacToeFrame.getCount() == 9) {
			myTicTacToeFrame.initComponents("Match is a draw!");
		}

	}

	Integer getPlayersCell(JButton b) {
		return (Integer) b.getClientProperty("OWNER");
	}

	void printBoardMap(Integer[][] bMap) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				System.out.print(bMap[i][j] + " ");
			System.out.println("");
		}
	}

	boolean isGameWon(Integer[] index) {
		Integer a = index[0];
		Integer b = index[1];
		int i;

		for (i = 0; i < 3; i++) {
			if (getPlayersCell(myTicTacToeFrame.gameBoard[a][i]) != getPlayersCell(myTicTacToeFrame.gameBoard[a][b]))
				break;
		}
		if (i == 3)
			return true;

		for (i = 0; i < 3; i++) {
			if (getPlayersCell(myTicTacToeFrame.gameBoard[i][b]) != getPlayersCell(myTicTacToeFrame.gameBoard[a][b]))
				break;
		}
		if (i == 3)
			return true;

		if ((a == 2 && b == 2) || (a == 0 && b == 0) || (a == 1 && b == 1)
				|| (a == 0 && b == 2) || (a == 2 && b == 0)) {
			for (i = 0; i < 3; i++)
				if (getPlayersCell(myTicTacToeFrame.gameBoard[i][i]) != getPlayersCell(myTicTacToeFrame.gameBoard[a][b]))
					break;
			if (i == 3)
				return true;

			if ((getPlayersCell(myTicTacToeFrame.gameBoard[0][2]) == getPlayersCell(myTicTacToeFrame.gameBoard[a][b]))
					&& (getPlayersCell(myTicTacToeFrame.gameBoard[1][1]) == getPlayersCell(myTicTacToeFrame.gameBoard[a][b]))
					&& (getPlayersCell(myTicTacToeFrame.gameBoard[2][0]) == getPlayersCell(myTicTacToeFrame.gameBoard[a][b])))
				return true;

		}

		return false;

	}
}

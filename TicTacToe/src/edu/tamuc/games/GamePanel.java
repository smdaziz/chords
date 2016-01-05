package edu.tamuc.games;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * TicTacToePanel.java
 * 
 * @author Mohammed Abdul Aziz Syed
 * @CWID   50143871
 * @Course CSCI 531 HW5
 * 
 */

public class GamePanel extends JPanel {

	public GamePanel(GameFrame myTicTacToeFrame) {
		setLayout(new GridLayout(3, 3));
		myTicTacToeFrame.setPlayerID(1);
		myTicTacToeFrame.setCount(0);
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				myTicTacToeFrame.gameBoard[i][j] = new JButton();
				myTicTacToeFrame.gameBoard[i][j].putClientProperty("INDEX", new Integer[] { i,
						j });
				myTicTacToeFrame.gameBoard[i][j].putClientProperty("OWNER", null);
				myTicTacToeFrame.gameBoard[i][j].addActionListener(myTicTacToeFrame.getTicTacToeListener());
				add(myTicTacToeFrame.gameBoard[i][j]);
			}
	}
}

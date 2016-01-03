package com.nlp.english;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
* 
* This class handles the GUI part of the application.
*
*
* @author	Syed, Mohammed Abdul Aziz
* @CWID		50143871
* @Term		May Mini - 2015
* @Course	Automata Theory
* @version	1.0
* @since	06-01-2015
* 
*/

public class NLPJFrame extends JFrame {

	private static final long serialVersionUID = -2191498085912574416L;

	public NLPJFrame(int gridSize, Set<String>[][] cnfMatrix, String sentence[]) {
		super("Syed Mohammed Abdul Aziz, CWID : 50143871");
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(gridSize+1, gridSize));
        for(int i = 0; i < sentence.length; i++) {
        	JButton jButton = new JButton(sentence[i]);
        	jButton.setBackground(Color.LIGHT_GRAY);
        	p.add(jButton);
        }
        for(int i = 1; i <= cnfMatrix.length; i++) {
        	for(int j = 0; j < cnfMatrix[0].length; j++) {
        		if(cnfMatrix[i-1][j] != null) {
        			JButton jButton = new JButton(cnfMatrix[i-1][j].toString().replace("[", "").replace("]", ""));
        			if(i == 1) {
        				boolean isAccepted = false;
        				if(cnfMatrix[i-1][j].contains("S")) {
        					jButton.setBackground(Color.GREEN);
        					isAccepted = true;
        				} else {
        					jButton.setBackground(Color.RED);
        					isAccepted = false;
        				}
        				
        				String msg = "\"";
        				for(int k = 0; k <= j; k++)
        					msg += " "+sentence[k];
        				final String message = msg+(isAccepted?" \" is Accepted...!!!":" \" is Not Accepted...!!!");
        				jButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								JOptionPane.showMessageDialog(null,
									    message);
							}
						});
        			}
        			p.add(jButton);
        		}
        		else {
        			JButton jButton = new JButton();
        			p.add(jButton);
        			jButton.setVisible(false);
        		}
        	}
        }
        add(p);
	}

}

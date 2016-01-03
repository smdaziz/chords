package com.nlp.english;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;

/**
* 
* This class implements CYG Recognition Algorithm for Natural Language Processing
* It applies the grammar rules defined CNF Grammar, constructs a parse table 
* for each input sentence using Dynamic Programming Technique and decides whether
* the sentence is Accepted or Not Accepted.
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

public class NLP {
	
	private static String grammar = GrammarReader.readGrammar("Grammar.txt");

	public static void main(String[] args) {
		NLPJFrame nlpJFrame = null;
		System.out.println("Please enter a Natural Language statement:");
		while(true) {
			Scanner scanner = new Scanner(System.in);
			String englishStatement = scanner.nextLine();

			if(null != nlpJFrame)
				nlpJFrame.dispose();
			CNFGrammar ckyGrammar_NLP = new CNFGrammar(grammar);
			String stmtArray[] = englishStatement.split(" ");
			int rows = stmtArray.length, columns = stmtArray.length;
			@SuppressWarnings("unchecked")
			Set<String>[][] cnfDP = new HashSet[rows][columns];

			for(int k = 1; k <= rows; k++) {
				for(int i = 0; i < columns; i++) {
					int j = i+k-1;
					if(j < columns) {
						cnfDP[i][j] = new HashSet<String>();
					}
				}
			}

			for(int k = 1; k <= rows; k++) {
				for(int i = 0; i < columns; i++) {
					int j = i+k-1;
					if(j < columns) {
						if(i != j) {
							for(int l = i; l < j; l++) {
								String[] s1 = new String[cnfDP[i][l].size()];
								String[] s2 = new String[cnfDP[l+1][j].size()];
								int idx = 0;
								for(String temp: cnfDP[i][l])
									s1[idx++] = temp;
								idx = 0;
								for(String temp: cnfDP[l+1][j])
									s2[idx++] = temp;
								int s1Length = s1.length, s2Length = s2.length;
								for (String[] nonTerminals : ckyGrammar_NLP.lexicons) {
									for (int idx1 = 0; idx1 < s1Length; idx1++) {
										for (int idx2 = 0; idx2 < s2Length; idx2++) {
											if (nonTerminals[1].equalsIgnoreCase(s1[idx1]) && nonTerminals[2].equalsIgnoreCase(s2[idx2])) {
												cnfDP[i][j].add(nonTerminals[0]);
											}
										}
									}
								}
							}
						} else {
							String terminal = stmtArray[i];
							for(String[] terminals : ckyGrammar_NLP.grammar) {
								if(terminals[1].equalsIgnoreCase(terminal))
									cnfDP[i][j].add(terminals[0]);
							}
						}
					}
				}
			}

			/*for(int i = 0; i < rows; i++) {
				for(int j = 0; j < columns; j++) {
					if(null != cnfDP[i][j])
						System.out.print(cnfDP[i][j]);
					else
						System.out.print("  ");
				}
				System.out.println();
			}*/
			if(cnfDP[0][columns-1].contains("S"))
				System.out.println("Statement Accepted...!!!");
			else
				System.out.println("Statement Not Accepted...!!!");
			nlpJFrame = new NLPJFrame(rows, cnfDP, stmtArray);
			nlpJFrame.pack();
	        nlpJFrame.setLocationRelativeTo(null);
			nlpJFrame.setVisible(true);
			nlpJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	}
	
}


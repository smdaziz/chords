package com.nlp.english;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
* 
* This class reads the Grammar and Lexicons
* from text file, constructs a string and returns.
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

public class GrammarReader {
	
	public static String readGrammar(String fileName) {
		String grammar = "";
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while((line = br.readLine()) != null) {
				grammar+=line+"\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return grammar;
	}
	
}

package com.nlp.english;
import java.util.ArrayList;
import java.util.List;

/**
* 
* This class holds the Grammar and Lexicons.
* An instance of this class maintains a list of grammar
* symbols and lexicon symbols. In addition to these, 
* it also maintains the start symbol of the grammar rules.
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

public class CNFGrammar {

	List<String[]> grammar;
	List<String[]> lexicons;
	String startSymbol;
	
	CNFGrammar(String ckyGrammar) {
	    this.grammar = new ArrayList<String[]>();
	    this.lexicons = new ArrayList<String[]>();

	    String[] ckyGrammarSplitted = ckyGrammar.split("\n");
	
	    for (int i = 0; i < ckyGrammarSplitted.length; ++i) {
	        String rule = ckyGrammarSplitted[i];
	        if (rule.length() == 0) continue;
	        rule = rule.replaceAll("  ", " ");
	        rule = rule.replaceAll("-> ", "");
	        String[] arr = rule.split(" ");
	        if (arr == null) {
	        	System.out.println("bad rule syntax: " + rule);
	        }
	
	        if (arr.length > 2) {
	            this.lexicons.add(arr);
	            if (this.startSymbol == null) this.startSymbol = new String(arr[0]);
	        } else {
	            this.grammar.add(arr);
	        }
	    }
	}

    String start_symbol() {
        return this.startSymbol;
    }

}

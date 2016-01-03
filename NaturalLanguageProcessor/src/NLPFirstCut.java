import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

/**
* 
* This program implements CYG Recognition Algorithm for Natural Language Processing
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
* @since	05-30-2015
* 
*/

public class NLPFirstCut {
	
	//R Grammar
//	private static String grammar = "S  ->  Subject Predicate\nS  ->  Aux XA\nXA -> Subject Predicate\nSubject -> Pronoun Predicate\nSubject -> Definite Subject\nPredicate -> Verb Subject\nPredicate -> Preposition Subject\nPredicate -> Adverb Predicate\nPredicate -> Verb XB\nXB -> Adjective Predicate\nS -> Aux Subject\nPredicate -> there \nPredicate -> again\nPredicate -> soon\nPredicate -> quickly\nPredicate -> here\nPredicate -> else\nPredicate -> Verb Adjective\nSubject -> where\nSubject -> what\nSubject -> when\nSubject -> which\nSubject -> how\nSubject -> hope\nSubject -> prefer\nSubject -> order\nSubject -> is\nSubject -> sit\nSubject -> want\nSubject -> try\nSubject -> combo\nSubject -> choice\nSubject -> thank\nSubject -> discount\nSubject -> people\nSubject -> group\nSubject -> hello\nSubject -> reservation\nSubject -> seating\nSubject -> seat\nSubject -> type\nSubject -> day\nSubject -> restaurant\nSubject -> menu\nSubject -> chef\nSubject -> number\nSubject -> specialities\nSubject -> place\nSubject -> preference\nSubject -> table\nPredicate -> are\nPredicate -> have\nPredicate -> get\nPredicate -> hope\nPredicate -> had\nPredicate -> would\nPredicate -> visit\nPredicate -> discount\nPredicate -> pleasure\nPredicate -> serve\nPredicate -> drinks\nPredicate -> like\nPredicate -> prefer\nPredicate -> order\nPredicate -> is\nPredicate -> sit\nPredicate -> want\nPredicate -> Subject Predicate\nPredicate -> Aux XA\nPredicate -> Aux Subject\nPredicate -> do\nS -> Pronoun Predicate\nS -> Definite Subject\nSubject -> you\nSubject -> this\nSubject -> that\nSubject -> we\nSubject -> us\nSubject -> it\nSubject -> yourself\nSubject -> everyone\nSubject -> anything\nS -> where \nS -> what \nS -> when\nS -> which\nS -> how\nS -> hello\nS -> combo\nS -> choice\nS -> discount\nS -> people\nS -> group\nS -> doing\nS -> today\nS -> drink\nS -> reservation\nS -> today\nS -> fun\nS -> time\nS -> seating\nS -> seat\nS -> hello\nS -> type\nS -> day\nS -> restaurant\nS -> menu\nS -> chef\nS -> number\nS -> specialities\nS -> place\nS -> preference\nS -> table\nS -> you\nS -> this\nS -> that\nS -> we\nS -> us\nS -> it\nS -> yourself\nS -> everyone\nS -> anything\nPredicate -> Pronoun Predicate\nPredicate -> Definite Subject\nPredicate -> where\nPredicate -> where\nPredicate -> what\nPredicate -> when \nPredicate -> which\nPredicate -> how\nPredicate -> hello\nPredicate -> combo\nPredicate -> choice\nPredicate -> discount\nPredicate -> people\nPredicate -> group\nPredicate -> doing\nPredicate -> today\nPredicate -> drink\nPredicate -> reservation\nPredicate -> today\nPredicate -> fun\nPredicate -> time\nPredicate -> seating\nPredicate -> seat\nPredicate -> type\nPredicate -> thank\nPredicate -> day\nPredicate -> restaurant\nPredicate -> menu\nPredicate -> chef\nPredicate -> number\nPredicate -> specialities\nPredicate -> place\nPredicate -> preference\nPredicate -> table\nPredicate -> you\nPredicate -> this\nPredicate -> that\nPredicate -> we\nPredicate -> us\nPredicate -> it\nPredicate -> yourself\nPredicate -> everyone\nPredicate -> anything\nInterrogative -> where\nInterrogative -> what\nInterrogative -> how\nInterrogative -> which\nInterrogative -> when\nPronoun -> you\nPronoun -> this\nPronoun -> that\nPronoun -> we\nPronoun -> us\nPronoun -> it\nPronoun -> yourself\nPronoun -> everyone\nPronoun -> anything\nNoun -> hello\nNoun -> combo\nNoun -> choice\nNoun -> discount\nNoun -> people\nNoun -> group\nNoun -> doing\nNoun -> today\nNoun -> drink\nNoun -> reservation\nNoun -> thank\nNoun -> today\nNoun -> fun\nNoun -> time\nNoun -> seating\nNoun -> seat\nNoun -> type\nNoun -> day\nNoun -> restaurant\nNoun -> menu\nNoun -> chef\nNoun -> number\nNoun -> specialities\nNoun -> place\nNoun -> preference\nNoun -> table\nAux -> do\nAdverb -> there \nAdverb -> again\nAdverb -> soon\nAdverb -> here\nAdverb -> else\nVerb -> are\nVerb -> have\nVerb -> get\nVerb -> hope\nVerb -> had\nVerb -> would\nVerb -> visit\nVerb -> discount\nVerb -> pleasure\nVerb -> serve\nVerb -> drinks\nVerb -> like\nVerb -> prefer\nVerb -> order\nverb -> is\nverb -> sit\nverb -> want\nVerb -> thank\nVerb -> try\nVerb -> was\nDefinite -> the \nDefinite -> a\nPreposition -> to\nPreposition -> with\nPreposition -> on\nPreposition -> for\nPreposition -> in\nAdjective -> good\nAdjective -> ordered\nAdjective -> welcome\nAdjective -> coming\nAdjective -> our\nAdjective -> your\nAdjective -> ready\nAdjective -> many\nAdjective -> specials";
	//W Grammar
//	private static String grammar = "S -> NP VP\nS -> X1 VP\nX1 -> Aux NP\nS -> Verb NP\nS -> X2 PP\nX2 -> Verb NP\nS -> Verb PP\nS -> VP PP\nNP -> book\nNP -> class \nNP -> Texas\nNP -> answer \nNP -> English\nNP -> American \nNP -> airline \nNP -> symbol  \nNP -> I  \nNP -> my  \nNP -> their\nNP -> Dallas  \nNP -> Det Nominal\nNP -> movie\nNP -> vacation\nNP -> your\nNP -> you\nNP -> destination\nNP -> No\nNP -> tomorrow\nNP -> arrivals\nNP -> southwest\nNP -> attendant\nNP -> flight367\nNP -> Anderson\nNP -> airbus\nNP -> no\nNP -> side\nNP -> right\nNP -> Chicago\nNP -> Miami\nNP -> this\nNP -> flight\nNP -> passengers\nNP -> terminalA\nNP -> Pronoun Nominal\nNP -> PP Noun\nNP -> Nominal NP\nNP -> NP X5\nX5 -> Adjective Noun\nNominal -> class\nNominal -> vacation\nNominal -> American \nNominal -> airline \nNominal -> symbol  \nNominal -> flight\nNominal -> destination\nNominal -> No\nNominal -> tomorrow\nNominal -> baggage\nNominal -> attendant\nNominal -> pilot\nNominal -> airbus\nNominal -> biggest\nNominal -> ticket\nNominal -> stopover\nNominal -> restroom\nNominal -> Nominal Noun\nNominal -> Nominal PP\nVP -> send \nVP -> read\nVP -> can \nVP -> listen\nVP -> completed\nVP -> closed\nVP -> go\nVP -> flew  \nVP -> went\nVP -> brought  \nVP -> drinks  \nVP -> water  \nVP -> are  \nVP -> found\nVP -> find\nVP -> book\nVP -> need\nVP -> delayed\nVP -> refunds\nVP -> checkin\nVP -> addressed\nVP -> politely\nVP -> have\nVP -> flies\nVP -> call\nVP -> Verb NP\nVP -> X2 PP\nVP -> X4 NP\nX4 -> verb VP\nX2 -> verb NP\nVP -> Verb PP\nVP -> VP PP\nVP -> Verb VP\nVP -> VP X3\nX3 -> Adj PP\nPP -> Preposition NP\nNoun -> class \nNoun -> American  \nNoun -> airline  \nNoun -> symbol \nNoun -> book\nNoun -> ticket\nNoun -> movie\nNoun -> English\nNoun -> vacation\nNoun -> flight\nNoun -> tomorrow\nNoun -> arrivals\nNoun -> southwest\nNoun -> here\nNoun -> flight367\nNoun -> airplane\nNoun -> no\nNoun -> side\nNoun -> pilot\nNoun -> Chicago\nNoun -> Miami\nNoun -> restroom\nNoun -> passengers\nNoun -> terminalA\nNoun -> ProperNoun\nProperNoun -> Dallas  \nProperNoun -> Texas\nProperNoun -> Anderson\nProperNoun -> Chicago\nProperNoun -> Miami\nProperNoun -> terminalA\nVerb -> send \nVerb -> is \nVerb -> book\nVerb -> called  \nVerb -> found\nVerb -> need\nVerb -> select\nVerb -> delayed\nVerb -> refunds\nVerb -> find\nVerb -> flies\nVerb -> checkin\nVerb -> addressed\nVerb -> have\nVerb -> call\nVerb -> confirmed\nVerb -> boarding\nVerb -> Preposition Verb\nVerb -> Adverb Verb\nVerb -> Verb Adverb\nAdverb -> please\nAdverb -> politely\nAdverb -> final\nAdjective -> right\nAdjective -> final\nDet -> the  \nDet -> a  \nDet -> that\nDet -> an\nPreposition -> about\nPreposition -> from\nPreposition -> for\nPreposition -> to  \nPreposition -> into  \nPreposition -> with  \nPreposition -> of  \nPreposition -> as  \nPreposition -> in \nPreposition -> on\nPronoun -> I  \nPronoun -> us\nPronoun -> my  \nPronoun -> their\nPronoun -> your\nPronoun -> here\nPronoun -> this\nPronoun -> you";
	private static String grammar = "S -> NP VP\nS -> X1 VP\nS -> Aux NP\nS -> book\nS -> include\nS -> prefer\nS -> Verb NP\nS -> X2 PP\nS -> Verb PP\nS -> VP PP\nNP -> I\nNP -> she\nNP -> me\nNP -> TWA\nNP -> Houston\nNP -> Det Nominal\nNominal -> book\nNominal -> flight\nNominal -> meal\nNominal -> money\nNominal -> Nominal Noun\nNominal -> Nominal PP\nVP -> book\nVP -> include\nVP -> prefer\nVp -> Verb NP\nVP -> X2 PP\nX2 -> Verb NP\nVP -> Verb PP\nVP -> VP PP\nPP -> Preposition NP\nDet -> that\nDet -> this\nDet -> a\nDet -> the\nNoun -> book\nNoun -> flight\nNoun -> meal\nNoun -> money\nVerb -> book\nVerb -> include\nVerb -> prefer\nPronoun -> I\nPronoun -> she\nPronoun -> me\nProperNoun -> Houston\nProperNoun -> NWA\nAux -> does\nPreposition -> from\nPreposition -> to\nPreposition -> on\nPreposition -> near\nPreposition -> through";

	public static void main(String[] args) {
		//R Inputs
//		String stmt = "is there a table";
//		String stmt = "there is a table";
//		String stmt = "where is the waiter";
//		String stmt = "where is the chef";
		//W Inputs
//		String stmt = "book the ticket";
//		String stmt = "i need to book the ticket";
//		String stmt = "I need to book the ticket";
//		String stmt = "Select your destination";
//		String stmt = "select your destination";
//		String stmt = "the flight is delayed";
//		String stmt = "no refunds tomorrow";
//		String stmt = "No refunds from tomorrow";
//		String stmt = "find the baggage on arrivals";
//		String stmt = "please checkin for southwest here";
//		String stmt = "the attendant addressed politely";
//		String stmt = "Anderson is the pilot for flight367";
//		String stmt = "the airbus is the biggest airplane";
//		String stmt = "find the restroom on your right side";
//		String stmt = "you have a stopover in Chicago";
		//special case start
//		String stmt = "this is final call for flight to Miami";
//		String stmt = "this is final call";
//		String stmt = "this is final call for flight";
		//special case end
//		String stmt = "the ticket is confirmed for tomorrow";
//		String stmt = "find terminalA";
//		String stmt = "flight is boarding passengers";
		String stmt = "book the flight through houston";

		CNFGrammar1 ckyGrammar_NLP = new CNFGrammar1(grammar);
		String stmtArray[] = stmt.split(" ");
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
	}
	
}

class CNFGrammar1 {

	List<String[]> grammar;
	List<String[]> lexicons;
	String startSymbol;
	
	CNFGrammar1(String ckyGrammar) {
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


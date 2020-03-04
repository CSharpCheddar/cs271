
/**
 * This data structure stores a misspelled word an possible suggestions for it.
 * 
 * @author Martin Mueller
 */
public class DictionarySuggestion {
	//instance variables
	String misspelledWord;
	String[] suggestions;
	//constructor
	public DictionarySuggestion(String misspelledWord, String[] suggestions) {
		this.misspelledWord = misspelledWord;
		if(suggestions[0].equals("N/A")) {
			this.suggestions = null;
		} else {
			this.suggestions = suggestions;
		}
	}
	//accessors
	public String getMisspelledWord() {
		return misspelledWord;
	}
	public String[]	getSuggestions() {
		return suggestions;
	}
}

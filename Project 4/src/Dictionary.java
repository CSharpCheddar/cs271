
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {
	private String[] dictionary;  	//primary array storing the integers
    private int[] status;	//secondary array that tells us whether a slot is occupied
    private int size;       //number of elements in the hash table
    private BufferedReader reader;
	/*	0 status means a slot is available and was never used.
		1 status means a slot is taken and is not available.
		2 status means a slot used to be taken but is now free. Why is this important?
		*/
    
    @Override
    public String toString() {
    	return "Number of elements: "+size+"\nTable capacity: "+dictionary.length;
    }
    
    public Dictionary(String path){
        dictionary = new String[11];
        status = new int[11];
        size = 0;
        try {
			reader = new BufferedReader(new FileReader(path));
			String word = reader.readLine();
	        while(word != null) {
	        	insert(word);
	        	word = reader.readLine();
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private Dictionary() {
    	dictionary = new String[11];
        status = new int[11];
        size = 0;
    }
    
    //insert an entry into the hashtable
	private void insert(String word){
		if(size + 1 == dictionary.length) {
			resize(); //implement this method, resizing when we only have 1 element left to insert
		}
		int i = 0;
		//find the next open slot, status either 0 or 2.
		int hashCode = word.hashCode();
		if(hashCode < 0) {
			hashCode *= -1;
		}
        while(status[(hashCode+i)%status.length]==1){
            i++;
        }
        dictionary[(hashCode+i)%dictionary.length] = word;	//add key to table
        status[(hashCode+i)%status.length] = 1;	//add 1 to status array denoting the slot is "taken"
		size++;
    }
	
	//deletes an entry from the hashtable
	@SuppressWarnings("unused")
	private void delete(String key){
        int deleteIndex = search(key);
		//search never found the key
        if(deleteIndex == -1){
            throw new ArrayIndexOutOfBoundsException("key did not exist");
        }
		//status[deleteIndex] is set to 2. table[deleteIndex] is now available.
        status[deleteIndex] = 2;
		size--;		
    }
	
	//returns if the hashtable is empty
	public boolean isEmpty() {
		return size == 0 ? true : false;
	}
	
    public int search(String word){
    	int i = 0;
    	int hashCode = word.hashCode();
    	if(hashCode < 0) {
    		hashCode *= -1;
    	}
        while(dictionary[(hashCode+i)%dictionary.length] == null || !dictionary[(hashCode+i)%dictionary.length].equals(word)){
            if(i == dictionary.length) {
            	return -1;
            }
            i++;
        }
        return (hashCode+i)%dictionary.length;
    }
    
    //determines if the dictionary contains a given word
    public boolean contains(String word) {
    	if(search(word) != -1) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    //returns all elements in the hashTable in a String array
    //...or null if it's empty
    public String[] getAllElements() {
    	if(isEmpty()) {
    		return null;
    	}
    	String[] output = new String[size];
    	int j = 0;
    	for(int i=0; i<status.length; i++) {
    		if(status[i] == 1) {
    			output[j] = dictionary[i];
    			j++;
    		}
    	}
    	return output;
    }
    
    //resizes the hash table to the next prime number greater than double its current size
    private void resize() {
    	int newLength = 2*dictionary.length + 1;
		int limit = (int)Math.sqrt(newLength) + 1;
		for(int i=3; i<limit; i+=2) {
			if(newLength%i == 0) {
				newLength += 2;
				limit = (int)Math.sqrt(newLength) + 1;
			}
		}
		String oldTable[] = dictionary;
		int oldStatus[] = status;
		dictionary = new String[newLength];
		size = 0;
		status = new int[newLength];
		for(int i=0; i<oldTable.length; i++) {
			if(oldStatus[i] == 1) {
				insert(oldTable[i]);
			}
		}
    }
    
    //finds misspelled words in a given string and offers suggestions
    public DictionarySuggestion[] getSuggestions(String text) {
    	String[] words = text.split(" ");
    	String suggestions = "";
    	for(String word : words) {
    		word = word.replaceAll("[^a-zA-Z]", "");
    		if(!word.equals("")) {
    			if(!contains(word)) {
    				suggestions += word+"%";
    				Dictionary suggestedWords = new Dictionary();
    				//find suggestions
    				//check to see if it's missing a letter somewhere
    				for(int i=0; i<word.length()+1; i++) {
    					char[] array = word.toCharArray();
    					char[] newWord = new char[array.length+1];
    					for(int j=0,k=0; j<newWord.length; j++) {
    						if(j != i) {
    							newWord[j] = array[k];
    							k++;
    						}
    					}
    					//check for uppercase letters
    					for(char c='A'; c<='Z'; c++) {
    						newWord[i] = c;
    						String string = new String(newWord);
    						if(contains(string) && !suggestedWords.contains(string)) {
    							suggestedWords.insert(string);
    						}
    					}
    					//check for lowercase letters
    					for(char c='a'; c<='z'; c++) {
    						newWord[i] = c;
    						String string = new String(newWord);
    						if(contains(string) && !suggestedWords.contains(string)) {
    							suggestedWords.insert(string);
    						}
    					}
    				}
    				//check to see if consecutive letters are swapped
    				for(int i=0; i<word.length()-1; i++) {
    					char[] array = word.toCharArray();
    					char temp = array[i];
    					array[i] = array[i+1];
    					array[i+1] = temp;
    					String newWord = new String(array);
    					if(contains(newWord) && !suggestedWords.contains(newWord)) {
    						suggestedWords.insert(newWord);
    					}
    				}
    				//check to see if it has an extra letter somewhere
    				for(int i=0; word.length() > 1 && i<word.length(); i++) {
    					char[] array = word.toCharArray();
    					String smaller = "";
    					for(int j=0; j<array.length; j++) {
    						if(j != i) {
    							smaller += array[j];
    						}
    					}
    					if(contains(smaller) && !suggestedWords.contains(smaller)) {
    						suggestedWords.insert(smaller);
    					}
    				}
    				if(suggestedWords.isEmpty()) {
    					suggestions += "N/A:";
    				} else {
    					for(String suggestion : suggestedWords.getAllElements()) {
    						suggestions += suggestion+":";
    					}
    				}
    				suggestions += "%";
    			}
    		}
    	}
    	if(suggestions.equals("")) {
    		return null;
    	} else {
    		String[] entries = suggestions.split("%");
    		DictionarySuggestion[] output = new DictionarySuggestion[entries.length/2];
    		for(int i=0; i<entries.length; i+=2) {
    			output[i/2] = new DictionarySuggestion(entries[i], entries[i+1].split(":"));
    		}
    		return output;
    	}
    }
}

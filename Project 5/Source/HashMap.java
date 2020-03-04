
/**
 * Creates a HashMap that functions similarly to the built-in one.
 * @author muellm79
 *
 * @param <K> key type
 * @param <V> value type
 */
public class HashMap<K, V> {
	private Entry<K, V>[] table;  	//primary array storing the integers
	private int[] status;	//secondary array that tells us whether a slot is occupied
	private int size;       //number of elements in the hash table
	/*	0 status means a slot is available and was never used.
		1 status means a slot is taken and is not available.
		2 status means a slot used to be taken but is now free. Why is this important?
	 */
	/**
	 * Constructor. Initializes the instance variables to some default values.
	 */
	@SuppressWarnings("unchecked")
	public HashMap() {
		table = new Entry[11];
		status = new int[11];
		size = 0;
	}

	/**
	 * Inserts Entries into the HashMap.
	 * @param key The unique key of the Entry
	 * @param value The data to be stored in the Entry
	 */
	public void insert(K key, V value){
		Entry<K, V> entry = new Entry<>(key, value);
		if(size + 1 == table.length) {
			resize(); //implement this method, resizing when we only have 1 element left to insert
		}
		int i = 0;
		//find the next open slot, status either 0 or 2.
		int hashCode = key.hashCode();
		if(hashCode < 0) {
			hashCode *= -1;
		}
		while(status[(hashCode+i)%status.length]==1){
			i++;
		}
		table[(hashCode+i)%table.length] = entry;	//add key to table
		status[(hashCode+i)%status.length] = 1;	//add 1 to status array denoting the slot is "taken"
		size++;
	}

	/**
	 * Deletes an Entry from the HashMap.
	 * @param key The key of the Entry to be deleted
	 */
	@SuppressWarnings("unused")
	public void delete(K key){
		int deleteIndex = search(key);
		//search never found the key
		if(deleteIndex == -1){
			throw new ArrayIndexOutOfBoundsException("key did not exist");
		}
		//status[deleteIndex] is set to 2. table[deleteIndex] is now available.
		status[deleteIndex] = 2;
		size--;		
	}

	/**
	 * Determines if the HashMap is empty.
	 * @return Whether the HashMap is empty
	 */
	public boolean isEmpty() {
		return size == 0 ? true : false;
	}

	/**
	 * Returns the index of a particular Entry.
	 * @param key The key of the Entry we're looking for
	 * @return The index of the Entry we're looking for
	 */
	private int search(K key){
		int i = 0;
		int hashCode = key.hashCode();
		if(hashCode < 0) {
			hashCode *= -1;
		}
		while(table[(hashCode+i)%table.length] == null || !table[(hashCode+i)%table.length].getKey().equals(key)){
			if(i == table.length) {
				return -1;
			}
			i++;
		}
		return (hashCode+i)%table.length;
	}

	/**
	 * Determines of the HashMap contains an Entry with a given key.
	 * @param key The key of the Entry
	 * @return Whether the Entry with that key exists in the table
	 */
	public boolean contains(K key) {
		if(search(key) != -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets all the Entries in the HashMap.
	 * @return All the Entries in the HashMap
	 */
	@SuppressWarnings("unchecked")
	public Entry<K, V>[] getEntries() {
		if(isEmpty()) {
			return null;
		}
		Entry<K, V>[] output = new Entry[size];
		int j = 0;
		for(int i=0; i<status.length; i++) {
			if(status[i] == 1) {
				output[j] = table[i];
				j++;
			}
		}
		return output;
	}

	/**
	 * Resizes the HashMap if it gets full.
	 * Its new size is always changed to the next largest prime number.
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		int newLength = 2*table.length + 1;
		int limit = (int)Math.sqrt(newLength) + 1;
		for(int i=3; i<limit; i+=2) {
			if(newLength%i == 0) {
				newLength += 2;
				limit = (int)Math.sqrt(newLength) + 1;
			}
		}
		Entry<K, V> oldTable[] = table;
		int oldStatus[] = status;
		table = new Entry[newLength];
		size = 0;
		status = new int[newLength];
		for(int i=0; i<oldTable.length; i++) {
			if(oldStatus[i] == 1) {
				insert((K)oldTable[i].getKey(), (V)oldTable[i].getValue());
			}
		}
	}

	/**
	 * Gets the value stored by an Entry with a given key.
	 * @param key The key of the Entry
	 * @return The value of/data stored in the Entry
	 */
	public V getValue(K key) {
		return table[search(key)].getValue();
	}

	/**
	 * Determines how many elements are in the HashMap.
	 * @return The number of elements in the HashMap
	 */
	public int getSize() {
		return size;
	}
}

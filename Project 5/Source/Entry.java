
/**
 * HashMap Entry class
 * @author muellm79
 *
 * @param <K> key type
 * @param <V> value type
 */
public class Entry<K, V> {
	//instance variables
	K key;
	V value;
	/**
	 * Constructor.
	 * @param key The key
	 * @param value The value/data in the entry
	 */
	public Entry(K key, V value) {
		setKey(key);
		setValue(value);
	}
	//accessors
	/**
	 * Gets the Entry's key.
	 * @return The Entry's key
	 */
	public K getKey() {
		return key;
	}
	/**
	 * Gets the data stored in the Entry.
	 * @return The data stored in the Entry
	 */
	public V getValue() {
		return value;
	}
	//mutators
	/**
	 * Sets the key of the Entry.
	 * @param key The new key of the Entry
	 */
	public void setKey(K key) {
		this.key = key;
	}
	/**
	 * Sets the data/value of the Entry.
	 * @param value The new value of the Entry
	 */
	public void setValue(V value) {
		this.value = value;
	}
}
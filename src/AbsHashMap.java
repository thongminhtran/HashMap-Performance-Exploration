import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public abstract class AbsHashMap<Key, Value> {
    // current capacity of array list (protected because I want MyHashMap can access to it)
    protected int initialCapacity;
    // current size of array list (protected because I want realSize can access to it)
    protected int realSize;

    /**
     * Constructor to initialize capacity and create an empty chains
     * @param initialCapacity: initial capacity for the hash table
     */
    public AbsHashMap(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.realSize = 0;
    }

    /**
     * @return the number of entries in the map
     */
    abstract public int size();

    /**
     * @return a Boolean indicating whether the map is empty
     */
    abstract public boolean isEmpty();

    /**
     * @param k: key k
     * @return value v associated with key k if such entry exists, otherwise return null
     */
    abstract public Value get(Key k);

    /**
     * If the map doesn't have an entry with Key k, then adds entry (key, value) to it and return null
     * Else replaces with value the existing value of the entry with key equal to k and returns the old value
     * @param key: key k
     * @param value: Value v
     * @return null when doesn't have an entry with key k, or returns the old value
     */
    abstract public AbstractEntry<Key, Value> put(Key key, Value value) throws InterruptedException, TimeoutException, ExecutionException;

    /**
     * Removes from the map the entry with key equal to k, and returns its value; if the map has no entry it returns null
     * @param key: provided key to remove the entry
     * @return null if no such entry, return value if there is
     */
    abstract public Value remove(Key key);
}

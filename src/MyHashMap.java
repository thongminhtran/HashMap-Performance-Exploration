import java.util.ArrayList;

public class MyHashMap extends AbsHashMap<Integer, Integer> {
    // Buckets is used to store array of chains/buckets
    private ArrayList<Entry> buckets;
    private int size;
    private int capacity;
    /**
     * for the hash table as a parameter and uses the function h(k) = k mod N as the hash (compression) function
     * Constructor to initialize capacity and create an empty chains
     *
     * @param initialCapacity : initial capacity for the hash table
     */
    public MyHashMap(int initialCapacity) {
        super(initialCapacity);
        buckets = new ArrayList<>();
        this.capacity = initialCapacity;
        size = 0;
        for(int i=0;i<capacity;i++){
            buckets.add(null);
        }
    }

    public int getCapacity() {
        return capacity;
    }

    private int getIndexByKey (Integer key){
        int hashCode = key.hashCode();
        int index = hashCode % capacity;
        index = index < 0 ? index * -1 : index; //Make index > 0 to be valid
        return index;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param k: key k that we want to get the entry
     * @return null if cannot find entry or value if can find the entry
     */
    @Override
    public Integer get(Integer k) {
       int bucketIndex = getIndexByKey(k);
       Entry head = buckets.get(bucketIndex);
       while(head!=null){
           if(head.key.equals(k))
               return head.value;
           head = (Entry) head.next;
       }
       return null;
    }

    @Override
    public Integer put(Integer key, Integer value) {
        //Reference: https://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/
        int bucketIndex = getIndexByKey(key);
        Entry head = buckets.get(bucketIndex);
        // Using this loop to find if
        while(head!=null){
            if(head.key.equals(key)){
                // If the key exists already, set it be the existing value and return the old value
                Integer temp = head.value;   //Create temporary value to store the old value
                head.value = value;
                return temp;
            }
            head = (Entry) head.next;
        }
        size++;
        head = buckets.get(bucketIndex);
        Entry newEntry = new Entry(key,value);
        newEntry.next = head;
        buckets.set(bucketIndex,newEntry);
        // If the load factor goes more than threshold, then double hash table size
        if((1.0*size)/capacity>=0.7){
            ArrayList<Entry> temp = buckets;
            buckets = new ArrayList<>();
            capacity = 2 * capacity;
            size = 0;
            for(int i=0;i<capacity;i++)
                buckets.add(null);
            for(Entry headEntry : temp){
                while(headEntry!=null){
                    put(headEntry.key,headEntry.value);
                    headEntry = (Entry) headEntry.next;
                }
            }
        }
        return null;
    }

    @Override
    public Integer remove(Integer key) {
        int bucketIndex = getIndexByKey(key);
        Entry head = buckets.get(bucketIndex);

        //Search for the key in its chain
        Entry prev = null;
        while(head!=null){
            if(head.key.equals(key))
                break;
            prev = head;
            head = (Entry) head.next;
        }
        if(head == null)
            return null;
        size--;
        if(prev!=null)
            prev.next = head.next;
        else
            buckets.set(bucketIndex, (Entry) head.next);
        return head.value;
    }
}

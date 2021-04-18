import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class HashMapDriver {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        validate();
        experiment_interpret(150);
    }

    public static void validate() {
        System.out.println("==================Starting Validate() function ===================");
        // Create data of 50 random entries
        ArrayList<Entry> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(new Entry());  //Add random Entry elements into the list
        }

        // Create the HashMap object using 100 as initial capacity
        MyHashMap myHashMap = new MyHashMap(100);
        //Add 50 entries from data to map
        for (Entry entry : data) {
            System.out.println("== Starting put() method with key " + entry.getKey() + " and value " + entry.getValue() + " ==");
            long startTime = System.nanoTime();
            System.out.println("== The size of the table: " + myHashMap.getCapacity() + " ==");
            myHashMap.put(entry.key, entry.value);
            System.out.println("== Size of a table after putting an entry " + myHashMap.getCapacity() + " ==");
            System.out.println("== The number of items in the bucket storing v " + myHashMap.size() + " ===");
            long stopTime = System.nanoTime();
            TimeHelper.convertMillisecondsToMinute((stopTime - startTime));
        }
        //Run to get on of 50 elements in data
        callMyHashMapGetMethod(myHashMap, data);
        //Run remove for first 25 keys and follow by get for each of 50 keys
        System.out.println("Removing 25 keys");
        for (int i = 0; i < 25; i++) {
            Entry removedEntry = data.get(i);
            System.out.println("== Starting remove() method with key " + removedEntry.getKey() + " ==");
            long startTime = System.nanoTime();
            Integer removedValue = myHashMap.remove(removedEntry.getKey());
            long stopTime = System.nanoTime();
            System.out.println("== Removed value " + removedValue + " ==");
            TimeHelper.convertMillisecondsToMinute((stopTime - startTime));
        }
        // Run to get each key again
        callMyHashMapGetMethod(myHashMap, data);
    }
    private static void callMyHashMapGetMethod(MyHashMap myHashMap, ArrayList<Entry> data) {
        for (Entry entry : data) {
            System.out.println("== Starting get() method with key " + entry.getKey() + " ==");
            long startTime = System.nanoTime();
            Integer getResult = myHashMap.get(entry.getKey());
            long stopTime = System.nanoTime();
            System.out.println("== Get value " + getResult + " ==");
            TimeHelper.convertMillisecondsToMinute((stopTime - startTime));
        }
    }
    public static void experiment_interpret(int n){
        // Create a hashmap of initial capacity 100
        MyHashMap myHashMap = new MyHashMap(100);
        // Create a data with 150 random values
        ArrayList<Entry> data = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            data.add(new Entry());
        }
        for (int i = 0; i < n; i++) {
            Entry entry = data.get(i);
            System.out.println("== Starting put() method with key " + entry.getKey() + " and value " + entry.getValue() + " ==");
            long startTime = System.nanoTime();
            System.out.println("== The size of the table: " + myHashMap.getCapacity() + " ==");
            myHashMap.put(entry.key, entry.value);
            System.out.println("== Size of a table after putting an entry " + myHashMap.getCapacity() + " ==");
            System.out.println("== The number of items in the bucket storing v " + myHashMap.size() + " ===");
            long stopTime = System.nanoTime();
            TimeHelper.convertMillisecondsToMinute((stopTime - startTime));
            myHashMap.put(entry.key, entry.value);
        }
    }


}

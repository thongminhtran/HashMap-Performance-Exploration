import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class HashMapDriver {

    public static void main(String[] args) {
	    try{
            validate();
        }catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            System.exit(0);
        }

//	    try{
//	        experiment_interpret();
//        }catch(Exception e) {
//            System.out.println("Error " + e.getMessage());
//            System.exit(0);
//        }
    }

    /**
     */
    private static void validate() throws ExecutionException, InterruptedException, TimeoutException {
        // create an array list of 50 random Key Value (key is random with construct Entry (value),
        ArrayList<Entry> data = new ArrayList<>();
        for(int i = 0; i < 50; i++) {
            data.add(new Entry());
        }
        // create a MyHashMap object using 100 as initial capacity
        MyHashMap myHashMap = new MyHashMap(100);

        // Add 50 entries from data to map
        for (Entry entry: data) {
            myHashMap.put(entry.getKey(), entry.getValue());
        }

        // Run get on each of 50 elements in data
        for (Entry entry: data) {
            Integer getResult = myHashMap.get(entry.getKey());
        }

        // Run remove for first 25 keys and follow by get for each of 50 keys
        for (int i = 0; i < 25; i++) {
            myHashMap.remove(data.get(i).getKey());
        }
        for (Entry entry: data) {
            Integer getResult = myHashMap.get(entry.getKey());
        }
    }


//    private static void experiment_interpret() {
//        // Create a hashmap of initial capacity 100
//        MyHashMap myHashMap = new MyHashMap(100);
//
//        // Create a local array list data of 150 random
//        ArrayList<Entry> data = new ArrayList<>();
//        for (int i = 0; i < 150; i++) {
//            data.add(new Entry());
//        }
//
//        // Next you do yourself, I don't understand kaka @todo here
//    }
}

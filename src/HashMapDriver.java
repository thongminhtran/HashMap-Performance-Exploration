import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class HashMapDriver {
    public static int n = 150;

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

//        validate();
        experiment_interpret();
    }

    public static void validate() throws InterruptedException, ExecutionException, TimeoutException {
        ArrayList<Entry> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(new Entry());  //Add random Entry elements into the list
        }
        System.out.println(data.size());

        // Create the HashMap object using 100 as initial capacity
        MyHashMap myHashMap = new MyHashMap(100);
        //Add 50 entries from data to map
        for (Entry entry : data) {
            myHashMap.put(entry.key, entry.value);
        }
        // After added 50 entries
        System.out.println(myHashMap.size());

        //Run get on of 50 elements in data
        System.out.println("Getting 50 entries");
        for (Entry entry : data) {
            Integer getResult = myHashMap.get(entry.getKey());
            System.out.println(getResult);
        }

        //Run remove for first 25 keys and follow by get for each of 50 keys
        System.out.println("Removing 25 keys");
        for (int i = 0; i < 25; i++) {
            System.out.println(myHashMap.remove(data.get(i).getKey()));
        }

        System.out.println("Size after removing 25 keys: " + myHashMap.size());
        for (Entry entry : data) {
            Integer getResult = myHashMap.get(entry.getKey());
            System.out.println(getResult);
        }

    }

    public static void experiment_interpret() throws InterruptedException, ExecutionException, TimeoutException {
        //TODO: Measure put method running time using this function.
        MyHashMap myHashMap = new MyHashMap(100);
        ArrayList<Entry> data = new ArrayList<>(150);
        for (int i = 0; i < n; i++) {
            data.add(new Entry());
        }
        double startTime = System.nanoTime();
        for (Entry entry : data) {
            myHashMap.put(entry.key, entry.value);
        }
        double endTime = System.nanoTime();
        double timeElapsed = (endTime-startTime);
        System.out.println("Running time of adding "+n+" elements is "+timeElapsed+" nanoseconds.");
        System.out.println("my hashmap size is " + myHashMap.size());

    }


}

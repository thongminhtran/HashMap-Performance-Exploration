import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class HashMapDriver {
    public static int n = 100;
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
//        System.out.println("hello");
//        MyHashMap myHashMap = new MyHashMap(10);
//        myHashMap.put(234, 35654);
//        myHashMap.put(9429, 239491);
//        myHashMap.put(123, 3543);
//        myHashMap.put(31, 23423);
//        System.out.println(myHashMap.size());
//        if (!myHashMap.isEmpty()) {
//            System.out.println("my map is not empty");
//        }
//        System.out.println(myHashMap.remove(234));
//        System.out.println(myHashMap.size());
        validate();
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
    public static void experiment_interpret(){
        //TODO: Measure put method running time using this function.
        MyHashMap myHashMap = new MyHashMap(100);
        ArrayList<Entry> data = new ArrayList<>(150);
        for(int i=0;i<n;i++){
            data.add(new Entry());
        }

    }


}

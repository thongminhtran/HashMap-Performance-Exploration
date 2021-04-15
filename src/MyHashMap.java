import java.util.ArrayList;
import java.util.concurrent.*;

public class MyHashMap extends AbsHashMap<Integer, Integer> {
    // Buckets is used to store array of chains/buckets
    private ArrayList<Entry> buckets;
    // 10 minutes is maximum for one function
    private static final long maximumExecutionTime = 10;
    /**
     * todo: Implement the logic: The class must include a constructor that accepts the initial capacity
     * for the hash table as a parameter and uses the function h(k) = k mod N as the hash (compression) function
     * Constructor to initialize capacity and create an empty chains
     *
     * @param initialCapacity : initial capacity for the hash table
     */
    public MyHashMap(int initialCapacity) {
        super(initialCapacity);
        this.buckets = new ArrayList<>();
        // Create empty chains
        for (int i = 0; i < this.initialCapacity; i++) {
            this.buckets.add(null);
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * todo: implement the method, remember the time thread execution as put method below
     * @param k: key k that we want to get the entry
     * @return null if cannot find entry or value if can find the entry
     */
    @Override
    public Integer get(Integer k) {
        return null;
    }

    /**
     * todo: Please implement the below
     * We use thread because it should print the time used to run the method and handle with a suitable exception
     * @param key: key of a modifying entry
     * @param value : value of a modify entry
     * @return null or the old entry
     * @throws InterruptedException if error happens
     * @throws TimeoutException if exceeding 10 minutes
     * @throws ExecutionException if error happens
     */
    @Override
    public AbstractEntry<Integer, Integer> put(Integer key, Integer value) throws InterruptedException, TimeoutException, ExecutionException {
        Runnable putProcess = new Thread() {
            @Override
            public void run() {
                // todo: do your stuff inside this function
                printInformation();
            }
        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(putProcess);
        executorService.shutdown();
        try{
            future.get(maximumExecutionTime, TimeUnit.MINUTES);
        }catch(InterruptedException interruptedException) {
            interruptedException.printStackTrace();
            throw interruptedException;
        }catch(TimeoutException timeoutException) {
            timeoutException.printStackTrace();
            throw timeoutException;
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw e;
        }
        if (!executorService.isTerminated()) {
            executorService.shutdownNow();
        }

        return null; // todo Modify to return correctly here
    }

    /**
     * todo: implement here, remember to implement thread (refer put method above)
     * @param key: key of the entry to be removed
     * @return null or entry (read abstract method)
     */
    @Override
    public Integer remove(Integer key) {
        return null;
    }

    /**
     * todo printInformation
     * Equip the class to print: size of the table, number of elements in the table after the method has finished process(k,v) entry
     * The number of keys that resulted in a collision
     * The number of items in the bucket storing v
     */
    private void printInformation() {

    }
}

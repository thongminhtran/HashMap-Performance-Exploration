import javax.swing.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.*;

public class MyHashMap extends AbsHashMap<Integer, Integer> {
    // Buckets is used to store array of chains/buckets
    private Entry[] buckets;
    private int size;
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
        buckets = new Entry[initialCapacity];
        size=0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    /**
     * todo: implement the method, remember the time thread execution as put method below
     * @param k: key k that we want to get the entry
     * @return null if cannot find entry or value if can find the entry
     */
    @Override
    public Integer get(Integer k) {
        if(k == null){
            throw new IllegalArgumentException("key cannot be null");
        }
        int hash = k.hashCode()%buckets.length;
        Entry current = buckets[hash];
        // If the entrymap does not exist
        if(current==null){
            throw new java.util.NoSuchElementException("Hashmap does not contain this key!!!");
        }
        int i=0;
        int hashTemp = hash;
        int mapSize=0;
        while((current!=null)&&(mapSize<buckets.length)){
            if(current.getKey().equals(k)){
                return current.getValue();
            }
            i++;
            hashTemp = (Math.abs(k.hashCode())+i)%buckets.length;
            current = buckets[hashTemp];
            mapSize++;
        }
        throw new java.util.NoSuchElementException("The hashmap does not contain the key");
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
                if(key == null || value == null){
                    throw new IllegalArgumentException("Key or value cannot be null");
                }
                int index = Math.abs(key.hashCode())%buckets.length;
                int ogIndex = index;
                Entry entry = new Entry(key,value);
                entry.setRemoved(false);
                boolean entered = false;
                Integer inBucket = null;
                int quadratic = 1;
                while(!entered){
                    if(buckets[inBucket]==null){
                        buckets[index] = entry;
                        entered = true;
                    } else{
                        if(buckets[index].isRemoved()){
                            inBucket = checkDuplicate(entry,quadratic,ogIndex);
                            if(inBucket!=null){
                                size--;
                                entered = true;
                            } else{
                                buckets[index] = entry;
                                entered = true;
                            }
                        } else if(buckets[index].getKey().equals(key)){
                            inBucket = buckets[index].getValue();
                            buckets[index]=entry;
                            size--;
                            entered = true;
                        } else{
                            index = ogIndex+quadratic*quadratic;
                            quadratic++;
                            if(index>=buckets.length){
                                int factor = index/buckets.length;
                                index= index - buckets.length*factor;
                            }
                            if(quadratic>buckets.length){
                                entered = true;
                                try {
                                    regrow();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (TimeoutException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    put(key,value);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (TimeoutException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
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

        return null;
    }
    private Integer checkDuplicate(Entry entry,int quadratic, int ogIndex){
        int index = ogIndex + quadratic*quadratic;
        if(index >= buckets.length){
            int factor = index/ buckets.length;
            index = index - buckets.length*factor;
        }
        for(int i=0;i< buckets.length*5;i++){
            if(buckets[index]==null){
                return null;
            } else if(buckets[index].getKey().equals(entry.getKey())){
                if(buckets[index].isRemoved()){
                    return null;
                } else{
                    Integer temp = buckets[index].getValue();
                    buckets[index] = entry;
                    return temp;
                }
            } else{
                index = ogIndex+quadratic*quadratic;
                quadratic++;
                if(index>=buckets.length){
                    int factor = index / buckets.length;
                    index = index- buckets.length*factor;
                }
            }
        }
        return null;
    }
    private void regrow() throws InterruptedException, ExecutionException, TimeoutException {
        Entry[] tempBuckets = buckets;
        int currentSize = size;
        buckets = new Entry[buckets.length*2+1];
        for(int i=0;i< tempBuckets.length;i++){
            if(tempBuckets[i]!=null&&!tempBuckets[i].isRemoved()){
                put(tempBuckets[i].getKey(),tempBuckets[i].getValue());
            }
        }
        size = currentSize;
    }

    /**
     * todo: implement here, remember to implement thread (refer put method above)
     * @param key: key of the entry to be removed
     * @return null or entry (read abstract method)
     */
    @Override
    public Integer remove(Integer key) {
       // Check if null
        if(key == null){
            throw  new IllegalArgumentException("Key cannot be null");
        }
        int index = Math.abs(key.hashCode())% buckets.length;
        int ogIndex = index;
        Integer inBucket = null;
        int quadratic = 1;
        boolean removed = false;
        while(!removed){
            if(buckets[index]==null){
                throw new NoSuchElementException("key does not exist");
            } else if(buckets[index].getKey().equals(key)){
                if(buckets[index].isRemoved()){
                    throw new NoSuchElementException("key does not exist");
                } else{
                    inBucket = buckets[index].getValue();
                    buckets[index].setRemoved(true);
                    removed = true;
                }
            } else{
                index = ogIndex + quadratic*quadratic;
                quadratic++;
                if(index >= buckets.length){
                    int factor = index/buckets.length;
                    index = index - buckets.length*factor;
                }
            }
        }
        this.size--;
        return inBucket;
    }

    /**
     * todo printInformation
     * Equip the class to print: size of the table, number of elements in the table after the method has finished process(k,v) entry
     * The number of keys that resulted in a collision
     * The number of items in the bucket storing v
     */
    private void printInformation() {
        System.out.println("Size of table is "+this.size);
        System.out.println("");
    }
}

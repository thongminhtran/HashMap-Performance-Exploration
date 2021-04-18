import java.util.Random;

public class Entry extends AbstractEntry<Integer, Integer> {
    public Entry(Integer key, Integer value) {
        super(key, value);
    }
    /**
     * A constructor with key is generated randomly and value is randomly generated
     */
    public Entry() {
        Random random = new Random();
        this.key = random.nextInt();
        this.value = random.nextInt();
    }
    public boolean equals(Object o){
        if(!(o instanceof Entry)){
            return false;
        } else{
            Entry that = (Entry) o;
            return that.getKey().equals(key) && that.getValue().equals(value);
        }
    }

    @Override
    public int hashCode() {
        //Reference: https://www.baeldung.com/java-hashcode
        int result = (int) (key^(key>>>32));
        result = 31*result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}

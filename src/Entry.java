import java.util.Random;

public class Entry extends AbstractEntry<Integer, Integer>{
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

    // todo: Implement as it is mentioned Hash functions, page 411 (Now is for example)
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.value.hashCode();
        return result;
    }
}

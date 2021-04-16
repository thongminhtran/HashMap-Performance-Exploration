public abstract class AbstractEntry<Key, Value> {
    Key key;
    Value value;
    AbstractEntry<Key,Value> next;
    public AbstractEntry() {}
    public AbstractEntry(Key key, Value value)
    {
        this.key = key;
        this.value = value;
    }
    public Key getKey() {
        return key;
    }

    public Value getValue() {
        return value;
    }
}

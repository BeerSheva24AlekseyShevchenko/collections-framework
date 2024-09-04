package telran.util;

public class HashMap<K, V> extends AbstractMap<K, V> {

    public HashMap () {
        entrySet = new HashSet<>();
    }

    @Override
    protected Set<K> getEmptyKeySet() {
        return new HashSet<>();
    }

}

package telran.util;

import java.util.Iterator;
import java.util.Objects;

@SuppressWarnings("unchecked")
public abstract class AbstractMap<K, V> implements Map<K, V> {
    protected Set<Entry<K, V>> entrySet;
    private int size = 0;

    protected abstract Set<K> getEmptyKeySet();

    @Override
    public V get(Object key) {
        Entry<K, V> pattern = new Entry<>((K)key, null);
        Entry<K,V> entry = entrySet.get(pattern);

        return entry == null ? null : entry.getValue();
    }

    @Override
    public V put(K key, V value) {
        Entry<K, V> pattern = new Entry<>((K)key, null);
        Entry<K, V> entry = entrySet.get(pattern);
        V res = null;
    
        if (entry == null) {
            entrySet.add(new Entry<>(key, value));
            size++;
        } else {
            res = entry.getValue();
            entry.setValue(value);
        }

        return res;
    }

    @Override
    public boolean containsKey(Object key) {
        return entrySet.contains(new Entry<K, V>((K) key, null));
    }

    @Override
    public boolean containsValue(Object value) {
        Iterator<Entry<K, V>> iterator = entrySet.iterator();
        boolean res = false;
        while (iterator.hasNext() && !res) {
            Entry<K, V> entry = iterator.next();
            res = Objects.equals(value, entry.getValue());
        }
        return res;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = getEmptyKeySet();
        entrySet.forEach(i -> keySet.add(i.getKey()));
        return keySet;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return entrySet;
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> arr = new ArrayList<>();
        entrySet.forEach(i -> arr.add(i.getValue()));
        return arr;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}

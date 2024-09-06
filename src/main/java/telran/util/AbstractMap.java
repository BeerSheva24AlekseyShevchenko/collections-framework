package telran.util;

import java.util.Objects;

@SuppressWarnings("unchecked")
public abstract class AbstractMap<K, V> implements Map<K, V> {
    protected Set<Entry<K, V>> entrySet;

    protected abstract Set<K> getEmptyKeySet();

    @Override
    public V get(Object key) {
        Entry<K, V> entry = getEntry(key);

        return entry == null ? null : entry.getValue();
    }

    @Override
    public V put(K key, V value) {
        Entry<K, V> entry = getEntry(key);
        V res = null;
    
        if (entry == null) {
            entrySet.add(new Entry<>(key, value));
        } else {
            res = entry.getValue();
            entry.setValue(value);
        }

        return res;
    }

    @Override
    public boolean containsKey(Object key) {
        return entrySet.contains(getPattern(key));
    }

    @Override
    public boolean containsValue(Object value) {
        return entrySet.stream().anyMatch(i -> Objects.equals(value, i.getValue()));
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
        return entrySet.size();
    }

    @Override
    public boolean isEmpty() {
        return entrySet.isEmpty();
    }

    @Override
    public V remove(K key) {
        Entry<K, V> entry = getEntry(key);
        V res = null;
        if (entry != null) {
            entrySet.remove(entry);
            res = entry.getValue();
        }
        return res;
    }

    private Entry<K, V> getEntry(Object key) {
        Entry<K, V> pattern = getPattern(key);
        Entry<K,V> entry = entrySet.get(pattern);
        return entry;
    }

    private Entry<K, V> getPattern(Object key) {
        return new Entry<>((K)key, null);
    }
}

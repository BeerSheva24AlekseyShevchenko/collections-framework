package telran.util;

@SuppressWarnings("unchecked")
public interface Map<K, V> {
    public static class Entry<K, V> implements Comparable<Entry<K, V>> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public int compareTo(Entry<K, V> o) {
            return ((Comparable<K>) key).compareTo(o.getKey());
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Entry<K,V> entry = (Entry<K,V>) obj;

            return key.equals(entry.key);
        }
    }
    V get(Object key);
    V put(K key, V value);
    boolean containsKey(Object key);
    boolean containsValue(Object key);
    Set<K> keySet();
    Set<Entry<K,V>> entrySet();
    Collection<V> values();
    int size();
    boolean isEmpty();
    V remove(K key);

    default V getOrDefault(Object key, V defaultValue) {
        V res = get(key);
        return res == null ? defaultValue : res; 
    }

    default V putIfAbsent(K key, V value) {
        V res = get(key);
        if (res == null) res = put(key, value);
        return res;
    }
}

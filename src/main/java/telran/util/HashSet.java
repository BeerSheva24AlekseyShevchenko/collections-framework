package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class HashSet<T> implements Set<T> {
    private static final int DEFAULT_HASH_TABLE_LENGTH = 16;
    private static final float DEFAULT_FACTOR = 0.75f;
    List<T>[] hashTable;
    float factor;
    int size;

    public HashSet(int hashTableLength, float factor) {
        hashTable = new List[hashTableLength];
        this.factor = factor;
    }

    public HashSet() {
        this(DEFAULT_HASH_TABLE_LENGTH, DEFAULT_FACTOR);
    }

    private class HashSetIterator implements Iterator<T> {
        Iterator<T> currentIterator = null;
        Iterator<T> prevIterator = null;
        int indexIterator;

        public HashSetIterator() {
            setNextIterator(0);
        }

        private void setNextIterator(int index) {
            Iterator<T> nextIterator = null;
            while (nextIterator == null && index < hashTable.length) {
                if (hashTable[index] == null || hashTable[index].isEmpty()) {
                    index++;
                } else {
                    nextIterator = hashTable[index].iterator();
                }
            }

            currentIterator = nextIterator;
            indexIterator = index;
        }

        @Override
        public boolean hasNext() {
            return currentIterator != null && currentIterator.hasNext();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            prevIterator = currentIterator;
            T obj = currentIterator.next();
            
            if (!hasNext()) {
                setNextIterator(indexIterator + 1);
            }

            return obj;
        }

        @Override
        public void remove() {
            if (prevIterator == null) {
                throw new IllegalStateException();
            }

            prevIterator.remove();
            prevIterator = null;
            size--;
        }
    }

    @Override
    public boolean add(T obj) {
        boolean res = false;
        if (!contains(obj)) {
            res = true;
            if (size >= hashTable.length * factor) {
                hashTableReallocation();
            }

            addObjInHashTable(obj, hashTable);
            size++;
        }
        return res;
    }

    private void addObjInHashTable(T obj, List<T>[] table) {
        int index = getIndex(obj, table.length);
        List<T> list = table[index];
        if (list == null) {
            list = new ArrayList<>(3);
            table[index] = list;
        }
        list.add(obj);
    }

    private int getIndex(T obj, int length) {
        int hashCode = obj.hashCode();
        return Math.abs(hashCode % length);
    }

    private void hashTableReallocation() {
       List<T>[] tempTable = new List[hashTable.length * 2];
       for (List<T> list: hashTable) {
            if (list != null) {
                list.forEach(obj -> addObjInHashTable(obj, tempTable));
                list.clear();
            }
       }
       hashTable = tempTable;

    }

    @Override
    public boolean remove(T pattern) {
        return removeObjFromHashTable(pattern, hashTable);
    }

    private boolean removeObjFromHashTable(T obj, List<T>[] table) {
        boolean res = false;
        int index = getIndex(obj, table.length);
        List<T> list = table[index];
        if (list != null && (res = list.remove(obj))) size--;
        return res;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
       return size == 0;
    }

    @Override
    public boolean contains(T pattern) {
        int index = getIndex(pattern, hashTable.length);
        List<T> list = hashTable[index];
        return list != null && list.contains(pattern);
    }

    @Override
    public Iterator<T> iterator() {
        return new HashSetIterator();
    }

    @Override
    public T get(Object pattern) {
        T res = null;
        int index = getIndex((T) pattern, hashTable.length);
        List<T> list = hashTable[index];

        if (list != null && list.contains((T) pattern)) {
            res = list.get(list.indexOf((T) pattern));
        }

        return res;
    }

}

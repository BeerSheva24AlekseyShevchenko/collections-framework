package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private Object[] array;
    private int size = 0;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        array = new Object[capacity];
    }

    @Override
    public boolean add(T obj) {
        add(size, obj);

        return true;
    }

    private void reallocate() {
        array = Arrays.copyOf(array, array.length * 2);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean removeIf(Predicate<T> predicate) {
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (predicate.negate().test((T) array[i])) {
                array[j++] = array[i];
            }
        }
        return size != (size = j);
    }

    @Override
    public boolean remove(T pattern) {
        int index = indexOf(pattern);
        if (index >= 0) {
            remove(index);
        }

        return index >= 0;
    }

    @Override
    public boolean contains(T pattern) {
        return indexOf(pattern) >= 0;
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
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current = 0;
            private boolean flNext = false;

            @Override
            public boolean hasNext() {
                flNext = true;
                return current < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return (T) array[current++];
            }

            @Override
            public void remove() {
                if(!flNext) {
                    throw new IllegalStateException();
                }
                ArrayList.this.remove(--current);
                flNext = false;
            }
        };
    }

    @Override
    public void add(int index, T obj) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (size == array.length) {
            reallocate();
        }

        System.arraycopy(array, index, array, index + 1, size++ - index);
        array[index] = obj;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        T removedItem = (T) array[index];
        System.arraycopy(array, index + 1, array, index, --size - index);

        return removedItem;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return (T) array[index];
    }

    @Override
    public int indexOf(T pattern) {
        int res = -1;
        int i = 0;
        while (res < 0 && i < size) {
            if (isEqual(pattern, array[i])) {
                res = i;
            }
            i++;
        }

        return res;
    }

    @Override
    public int lastIndexOf(T pattern) {
        int res = -1;
        int i = size;
        while (res < 0 && i > 0) {
            i--;
            if (isEqual(pattern, array[i])) {
                res = i;
            }
        }

        return res;
    }

    private boolean isEqual(Object o1, Object o2) {
        return o1 == null && o2 == null || o1.equals(o2);
    }

}

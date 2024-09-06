package telran.util;

import java.util.Iterator;

import telran.util.LinkedList.Node;

public class LinkedHashSet<T> implements Set<T> {
    private LinkedList<T> list = new LinkedList<>();
    private HashMap<T, Node<T>> map = new HashMap<>();

    private class LinkedHashSetIterator implements Iterator<T> {
        Iterator<T> iterator = list.iterator();
        T prev = null;

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public T next() {
            return prev = iterator.next();
        }
        
        @Override
        public void remove() {
            if (prev == null) {
                throw new IllegalStateException();
            }
            Node<T> node = map.remove(prev);
            list.removeNode(node);
            prev = null;
        }
    }

    @Override
    public boolean add(T obj) {
        boolean res = false;
        if (!contains(obj)) {
            res = true;
            Node<T> node = new Node<>(obj);
            list.addNode(node, list.size());
            map.put(obj, node);
        }
        return res;
    }

    @Override
    public boolean remove(T pattern) {
        boolean res = false;
        Node<T> node = map.remove(pattern);

        if (node != null) {
            res = true;
            list.removeNode(node);
        }

        return res;
    }

    @Override
    public boolean contains(T pattern) {
        return map.containsKey(pattern);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedHashSetIterator();
    }

    @Override
    public T get(Object pattern) {
        Node<T> node = map.get(pattern);
        return node == null ? null : node.obj;
    }

}

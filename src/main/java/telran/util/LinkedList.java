package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> implements List<T> {
    private static class Node<T> {
        T obj;
        Node<T> next;
        Node<T> prev;

        Node(T obj) {
            this.obj = obj;
        }
    }

    Node<T> head;
    Node<T> tail;
    int size = 0;

    public Node<T> getNode(int index) {
        return index < size / 2 ? getNodeFromHead(index) : getNodeFromTail(index);
    }

    private Node<T> getNodeFromHead(int index) {
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    private Node<T> getNodeFromTail(int index) {
        Node<T> current = tail;
        for (int i = size - 1; i > index; i--) {
            current = current.prev;
        }
        
        return current;
    }

    public void addNode(Node<T> node, int index) {
        if (index == 0) {
            addHead(node);
        } else if (index == size) {
            addTail(node);
        } else {
            addMiddle(node, index);
        }
        size++;
    }

    private void addHead(Node<T> node) {
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    private void addMiddle(Node<T> nodeToInsert, int index) {
        Node<T> nodeAfter = getNode(index);
        Node<T> nodeBefore = nodeAfter.prev;
        nodeToInsert.next = nodeAfter;
        nodeToInsert.prev = nodeBefore;
        nodeAfter.prev = nodeToInsert;
        nodeBefore.next = nodeToInsert;
    }

    private void addTail(Node<T> node) {
        tail.next = node;
        node.prev = tail;
        node.next = null;
        tail = node;
    }

    private void checkIndex(int index, boolean sizeInclusive) {
        int limit = sizeInclusive ? size : size - 1;
        if (index < 0 || index > limit) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    @Override
    public boolean add(T obj) {
        Node<T> node = new Node<>(obj);
        addNode(node, size);
        return true;
    }

    @Override
    public boolean remove(T pattern) {
        boolean res = false;
        int index = indexOf(pattern);
        if (index >= 0) {
            res = true;
            remove(index);
        }
        return res;
    }

    @Override
    public boolean contains(T pattern) {
        return indexOf(pattern) > -1;
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
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                T obj = current.obj;
                current = current.next;
                return obj;
            }
        };
    }

    @Override
    public void add(int index, T obj) {
        checkIndex(index, true);
        Node<T> node = new Node<>(obj);
        addNode(node, index);
    }

    @Override
    public T remove(int index) {
        checkIndex(index, false);
        T res;
        if (index == 0) {
            res = removeHead();
        } else if (index == size - 1) {
            res = removeTail();
        } else {
            res = removeMiddle(index);
        }
        size--;

        return res;
    }

    private T removeHead() {
        T res = head.obj;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
        }
        return res;
    }

    private T removeMiddle(int index) {
        Node<T> node = getNode(index);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node.obj;
    }

    private T removeTail() {
        T res = tail.obj;
        tail = tail.prev;
        return res;
    }

    @Override
    public T get(int index) {
        checkIndex(index, false);
        return getNode(index).obj;
    }

    @Override
    public int indexOf(T pattern) {
        Node<T> current = head;
        int index = 0;

        while (index < size && !Objects.equals(current.obj, pattern)) {
            index++;
            current = current.next;
        }
        return index == size ? -1 : index;
    }

    @Override
    public int lastIndexOf(T pattern) {
        Node<T> current = tail;
        int index = size - 1;
        while (index >= 0 && !Objects.equals(current.obj, pattern)) {
            index--;
            current = current.prev;
        }

        return index;
    }
}

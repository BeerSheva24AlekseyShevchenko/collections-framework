package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class TreeSet<T> implements Set<T> {
    private static class Node<T> {
        T obj;
        Node<T> parent;
        Node<T> left;
        Node<T> right;
        Node (T obj) {
            this.obj = obj;
        }
    }

    private class TreeSetIterator implements Iterator<T> {
        Node<T> current = null;
        Node<T> prevNode = null;

        TreeSetIterator() {
            current = getMinNode(root);
        }

        private void setNextCurrent() {
            current = current.right == null ?
                getFirstHigherParent(current) :
                getMinNode(current.right);
        }

        private Node<T> getFirstHigherParent(Node<T> node) {
            Node<T> res = node;
            while (res.parent != null && res.parent.right == res) {
                res = res.parent;
            }
            return res.parent;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            prevNode = current;
            T res = current.obj;
            setNextCurrent();

            return res;
        }

        @Override
        public void remove() {
            if (prevNode == null) {
                throw new IllegalStateException();
            }
            removeNode(prevNode);
            prevNode = null;
        }
    }

    private Node<T> root;
    private Comparator<T> comparator;
    private int size;

    public TreeSet() {
        this((Comparator<T>) Comparator.naturalOrder());
    }

    public TreeSet(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean add(T obj) {
        boolean res = false;
        if (!contains(obj)) {
            res = true;
            Node<T> node = new Node<>(obj);
            if (root == null) {
                addRoot(node);
            } else {
                addAfterParent(node);
            }
            size++;
        }
        return res;
    }

    private void addRoot(Node<T> node) {
        root = node;
        // if (node != null) node.parent = null;
    }

    private void addAfterParent(Node<T> node) {
        Node<T> parent = getParent(node.obj);
        if (comparator.compare(node.obj, parent.obj) > 0) {
            parent.right = node;
        } else {
            parent.left = node;
        }
        node.parent = parent;
    }

    @Override
    public boolean remove(T pattern) {
        boolean res = false;
        Node<T> node = getNode(pattern);
        if (node != null) {
            res = true;
            removeNode(node);
        }
        return res;
    }

    private void removeNode(Node<T> node) {
        removeNodeBase(node);
        size--;
    }

    private void removeNodeBase(Node<T> node) {
        if (node.left == null && node.right == null) {
            changeNode(node, null, false);
        } else if (node.left == null) {
            changeNode(node, node.right, false);
        } else if (node.right == null) {
            changeNode(node, node.left, false);
        } else {
            Node<T> minNode = getMinNode(node.right);
            removeNodeBase(minNode);
            changeNode(node, minNode, true);
        }
    }

    private void changeNode(Node<T> prev, Node<T> next, boolean isLeafSync) {
        changeNodeSyncParent(prev, next);
        changeNodeSyncRoot(prev, next);
        if (isLeafSync) changeNodeSyncLeaf(prev, next);
    }

    private void changeNodeSyncParent(Node<T> prev, Node<T> next) {
        if (next != null) {
            next.parent = prev.parent;
        }

        if (prev.parent != null) {
            if (prev.parent.left == prev) prev.parent.left = next;
            if (prev.parent.right == prev) prev.parent.right = next;
        }
    }

    private void changeNodeSyncRoot(Node<T> prev, Node<T> next) {
        if (prev == root) addRoot(next);
    }

    private void changeNodeSyncLeaf(Node<T> prev, Node<T> next) {
        next.right = prev.right;
        if (prev.right != null) prev.right.parent = next;
        next.left = prev.left;
        if (prev.left != null) prev.left.parent = next;
    }

    private Node<T> getMinNode(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public boolean contains(T pattern) {
        Node<T> node = getNode(pattern);
        return node != null;
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
        return new TreeSetIterator();
    }

    @Override
    public T get(Object pattern) {
        Node<T> node = getNode((T) pattern);
        return node == null ? null : node.obj;
    }

    private Node<T> getParentOrNode(T pattern) {
        Node<T> current = root;
        Node<T> parent = null;
        int compRes = 0;
        while (current != null && (compRes = comparator.compare(pattern, current.obj)) != 0) {
            parent = current;
            current = compRes > 0 ? current.right : current.left;
        }
        return current == null ? parent : current;
    }

    private Node<T> getNode(T pattern) {
        Node<T> res = getParentOrNode(pattern);

        return (res != null && comparator.compare(pattern, res.obj) == 0) ? res : null;
    }

    private Node<T> getParent(T pattern) {
        Node<T> res = getParentOrNode(pattern);

        return (res != null && comparator.compare(pattern, res.obj) != 0) ? res : null;
    }
}

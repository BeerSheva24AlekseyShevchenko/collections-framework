package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class TreeSetStandard<T> implements SortedSet<T>{
    java.util.TreeSet<T> tree;
    Comparator<T> comparator;

    public TreeSetStandard(Comparator<T> comparator){
        this.comparator = comparator;
        tree = new java.util.TreeSet<>(comparator);
    }
   
    public TreeSetStandard() {
        this((Comparator<T>)Comparator.naturalOrder());
    }

    @Override
    public T get(Object pattern) {
        T floorObj = tree.floor((T)pattern);
        T res = null;
        if(floorObj != null) {
            res = Objects.equals(pattern, floorObj) ? floorObj : null;
        }
        return res;
        
    }

    @Override
    public boolean add(T obj) {
        return tree.add(obj);
    }

    @Override
    public boolean remove(T pattern) {
        return tree.remove(pattern);
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public boolean contains(T pattern) {
        return tree.contains(pattern);
    }

    @Override
    public Iterator<T> iterator() {
        return tree.iterator();
    }

    @Override
    public T first() {
       return tree.first();
    }

    @Override
    public T last() {
        return tree.last();
    }

    @Override
    public T floor(T key) {
        return tree.floor(key);
    }

    @Override
    public T ceiling(T key) {
        return tree.ceiling(key);
    }

    @Override
    public SortedSet<T> subSet(T keyFrom, T keyTo) {
        TreeSet<T> result = new TreeSet<>(comparator);
        tree.subSet(keyFrom, keyTo).forEach(result::add);
        return result;
    }

}
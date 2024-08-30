package telran.util;

import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Collection<T> extends Iterable<T> {

    boolean add(T obj);

    boolean remove(T pattern);

    boolean contains(T pattern);

    int size();

    boolean isEmpty();

    default Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    };

    default Stream<T> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    };

    default boolean removeIf(Predicate<T> predicate) {
        int oldSize = size();
        Iterator<T> it = iterator();
        while(it.hasNext()) {
            T obj = it.next();
            if (predicate.test(obj)) {
                it.remove();
            }
        }
        return size() < oldSize;
    }

    default void clear() {
        removeIf(n -> true);
    }

}

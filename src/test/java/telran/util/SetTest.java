
package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public abstract class SetTest extends CollectionTest {
    Set<Integer> set;
    @Override
    void setUp(){
        super.setUp();
        set = (Set<Integer>) collection;
    }

    @Test
    void getPatternTest() {
        assertEquals(10, set.get(10));
        assertNull(set.get(1000000));
    }

    @Test
    void uniqueValuesTest() {
        for (Integer item: arr) {
            set.add(item);
        }
        assertEquals(collection.size(), arr.length);
    }
}

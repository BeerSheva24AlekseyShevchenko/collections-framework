package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public abstract class SortedSetTest extends SetTest {
    SortedSet<Integer> sortedSet;

    @Override
    void setUp() {
        super.setUp();
        sortedSet = (SortedSet<Integer>) collection;        
    }

    @Test
    void firstTest() {
        assertEquals(-10,  sortedSet.first());
    }

    @Test
    void lastTest() {
        assertEquals(100,  sortedSet.last());
    }

    @Test
    void floorTest() {
        assertEquals(10,  sortedSet.floor(10));
        assertNull(sortedSet.floor(-11));
        assertEquals(10,  sortedSet.floor(11));
        assertEquals(100,  sortedSet.floor(101));

        sortedSet.clear();
        assertNull(sortedSet.floor(10));
    }

    @Test
    void ceilingTest() {
        assertEquals(10,  sortedSet.ceiling(10));
        assertNull(sortedSet.ceiling(101));
        assertEquals(17,  sortedSet.ceiling(11));
        assertEquals(-10,  sortedSet.ceiling(-11));
    }

    @Test
    void subSetTest() {
        Integer[] expected = {10, 17};
        Integer[] actual = sortedSet.subSet(10, 20).stream().toArray(Integer[]::new);
        assertArrayEquals(expected, actual);
    }
}

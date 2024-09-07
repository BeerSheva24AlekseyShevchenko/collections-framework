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

        Integer[] expected2 = {100};
        Integer[] actual2 = sortedSet.subSet(100, 200).stream().toArray(Integer[]::new);
        assertArrayEquals(expected2, actual2);

        Integer[] expected3 = {-10};
        Integer[] actual3 = sortedSet.subSet(-20, -5).stream().toArray(Integer[]::new);
        assertArrayEquals(expected3, actual3);

        Integer[] actual4 = sortedSet.subSet(-2000, -1000).stream().toArray(Integer[]::new);
        assertEquals(0, actual4.length);

        Integer[] actual5 = sortedSet.subSet(1000, 2000).stream().toArray(Integer[]::new);
        assertEquals(0, actual5.length);

        assertThrows(IllegalArgumentException.class, () -> sortedSet.subSet(20, 10));
    }
}

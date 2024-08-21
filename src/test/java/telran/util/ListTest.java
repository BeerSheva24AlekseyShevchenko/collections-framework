package telran.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public abstract class ListTest extends CollectionTest {
    List<Integer> list;

    @Override
    void setUp() {
        super.setUp();
        list = (List<Integer>) super.collection;
    }

    @Test
    void getTest() {
        assertEquals(3, list.get(0));
        assertEquals(1, list.get(3));
        assertEquals(null, list.get(8));
    }

    @Test
    void addTest() {
        list.add(3, 17);
        list.add(9, 200);
        assertEquals(17, list.get(3));
        assertEquals(200, list.get(9));
        assertEquals(arr.length + 2, list.size());
    }

    @Test
    void removeTest() {
        assertEquals(1, list.remove(3));
        assertEquals(arr.length - 1, list.size());
    }

    @Test
    void indexOfTest() {
        assertEquals(1, list.indexOf(10));
    }

    @Test
    void lastIndexOfTest() {
        assertEquals(4, list.lastIndexOf(10));
    }
}

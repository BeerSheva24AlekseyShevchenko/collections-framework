package telran.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public abstract class ListTest extends CollectionTest {
    List<Integer> list;

    @Override
    void setUp() {
        super.setUp();
        list = (List<Integer>) super.collection;
    }

    @Override
    <T> void runTest(T[] expected, T[] actual) {
        assertArrayEquals(expected, actual);
        assertEquals(expected.length, collection.size());
    }

    @Test
    void listGetTest() {
        assertEquals(3, list.get(0));
        assertEquals(1, list.get(3));

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(100));
    }

    @Test
    void listAddTest() {
        list.add(3, 17);
        list.add(list.size(), 200);
        assertEquals(17, list.get(3));
        assertEquals(200, list.get(list.size() - 1));
        assertEquals(arr.length + 2, list.size());

        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(100, 1));
    }

    @Test
    void listRemoveTest() {
        assertEquals(1, list.remove(3));
        assertEquals(arr.length - 1, list.size());

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(100));
    }

    @Test
    void listIndexOfTest() {
        assertEquals(4, list.indexOf(10));
    }

    @Test
    void listLastIndexOfTest() {
        list.add(10);
        assertEquals(list.size() - 1, list.lastIndexOf(10));
    }

    @Test
    void duplicateValuesTest() {
        for (Integer item: arr) {
            list.add(item);
        }
        assertEquals(collection.size(), arr.length * 2);
    }
}

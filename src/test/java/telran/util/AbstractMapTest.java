package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public abstract class AbstractMapTest {
    Map<Integer, Integer> map;
    Integer missingKey = 1000;
    Integer[] arr = {3, 10, 20, 1, 8, 100, 17};

    void setUp() {
        Arrays.stream(arr).forEach(i -> map.put(i, createValue(i)));
    }

    abstract <T> void runTest(T[] expected, T[] actual);

    private Integer createValue(Integer key) {
        return key * key;
    }

    @Test
    void getTest () {
        assertEquals(createValue(arr[0]), map.get(arr[0]));
        assertEquals(createValue(arr[2]), map.get(arr[2]));
        assertNull(map.get(missingKey));
    }

    @Test
    void putTest () {
        Integer value1 = missingKey + 1;
        Integer value2 = missingKey + 2;
        assertNull(map.put(missingKey, value1));
        assertEquals(value1, map.get(missingKey));
        assertEquals(value1, map.put(missingKey, value2));
        assertEquals(value2, map.get(missingKey));
    }

    @Test
    void containsKeyTest() {
        assertTrue(map.containsKey(arr[0]));
        assertTrue(map.containsKey(arr[2]));
        assertFalse(map.containsKey(missingKey));
    }

    @Test
    void containsValueTest() {
        assertTrue(map.containsValue(createValue(arr[0])));
        assertTrue(map.containsValue(createValue(arr[2])));
        assertFalse(map.containsKey(createValue(missingKey)));
    }

    @Test
    void keySetTest() {
        Integer[] keys = map.keySet().stream().toArray(Integer[]::new);
        runTest(arr, keys);
    }

    @Test
    void entrySetTest() {
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        for (Integer i : arr) {
            Map.Entry<Integer, Integer> entry = new Map.Entry<>(i, createValue(i));
            assertTrue(entrySet.contains(entry));
        }

        assertFalse(entrySet.contains(new Map.Entry<>(missingKey, null)));
        assertEquals(arr.length, entrySet.size());
    }

    @Test
    void valuesTest() {
        Integer[] expected = Arrays.stream(arr).map(this::createValue).toArray(Integer[]::new);
        Integer[] values = map.values().stream().toArray(Integer[]::new);

        runTest(expected, values);
    }

    @Test
    void sizeTest() {
        assertEquals(arr.length, map.size());
    }

    @Test
    void isEmptyTest() {
        assertFalse(map.isEmpty());
    }
}

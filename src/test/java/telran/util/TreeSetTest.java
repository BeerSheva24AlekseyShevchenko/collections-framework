package telran.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeSetTest extends SetTest {
    @BeforeEach
    @Override
    void setUp() {
        collection = new TreeSet<>();
        super.setUp();
    }

    @Test
    protected void sortedTest() {
        Integer[] actual = collection.stream().toArray(Integer[]::new);

        Arrays.sort(arr);
        assertArrayEquals(arr, actual);
        assertEquals(arr.length, collection.size());
    }
}

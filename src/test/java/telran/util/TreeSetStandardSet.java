package telran.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

public class TreeSetStandardSet extends SortedSetTest {

    @BeforeEach
    @Override
    void setUp() {
        collection = new TreeSet<>();
        super.setUp();
    }

    @Override
    <T> void runTest(T[] expected, T[] actual) {
        T[] expectedSorted = Arrays.copyOf(expected, expected.length);
        Arrays.sort(expectedSorted);
        assertArrayEquals(expectedSorted, actual);
        assertEquals(expected.length, collection.size());
    }

}

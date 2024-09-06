package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

public class HashSetTest extends SetTest {
    @BeforeEach
    @Override
    void setUp() {
        collection = new HashSet<>();
        super.setUp();
    }

    @Override
    <T> void runTest(T[] expected, T[] actual) {
        T[] expectedSorted = Arrays.copyOf(expected, expected.length);
        Arrays.sort(expectedSorted);
        T[] actualSorted = Arrays.copyOf(expected, expected.length);
        Arrays.sort(actualSorted);
        assertArrayEquals(expectedSorted, actualSorted);
        assertEquals(expected.length, collection.size());
    }
}

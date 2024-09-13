package telran.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeSetTest extends SortedSetTest {
    private TreeSet<Integer> treeSet;

    @BeforeEach
    @Override
    void setUp() {
        collection = new TreeSet<>();
        super.setUp();
        this.treeSet = (TreeSet<Integer>) collection;
    }

    @Override
    <T> void runTest(T[] expected, T[] actual) {
        T[] expectedSorted = Arrays.copyOf(expected, expected.length);
        Arrays.sort(expectedSorted);
        assertArrayEquals(expectedSorted, actual);
        assertEquals(expected.length, collection.size());
    }

    @Test
    void displayTreeRotationTest() {
        treeSet.setSymbolPerLvl(5);
        treeSet.displayTreeRotation();
    }

    @Test
    void displayTreeParentChildrenTest() {
        treeSet.setSymbolPerLvl(5);
        treeSet.displayTreeParentChildren();
    }

    @Test
    void widthTest() {
        assertEquals(4, treeSet.with());
    }

    @Test
    void heightTest() {
        assertEquals(4, treeSet.height());
    }

    @Test
    void inversionTest() {
        Integer[] expected = {100, 20, 17, 10, 8, 3, 1, -10};
        treeSet.inversion();
        Integer[] actual = treeSet.stream().toArray(Integer[]::new);
        assertArrayEquals(expected, actual);
        assertTrue(treeSet.contains(100));
    }
}

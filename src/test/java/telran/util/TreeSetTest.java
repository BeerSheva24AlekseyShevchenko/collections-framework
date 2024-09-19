package telran.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.IntStream;

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
        assertEquals(4, treeSet.width());
    }

    @Test
    void heightTest() {
        assertEquals(4, treeSet.height());
    }

    @Test
    void inversionTest() {
        Integer[] expected = { 100, 20, 17, 10, 8, 3, 1, -10 };
        treeSet.inversion();
        Integer[] actual = treeSet.stream().toArray(Integer[]::new);
        assertArrayEquals(expected, actual);
        assertTrue(treeSet.contains(100));
    }

    @Test
    void extremeCasesTest() {
        TreeSet<Integer> tree = new TreeSet<>();
        IntStream.rangeClosed(1, 7).boxed().forEach(tree::add);
        assertEquals(7, tree.height());
        assertEquals(1, tree.width());

        tree.clear();
        Integer[] balancedArr = { 4, 2, 1, 3, 6, 5, 7 };
        Arrays.stream(balancedArr).forEach(tree::add);
        assertEquals(3, tree.height());
        assertEquals(4, tree.width());
    }

    @Test
    void balanceTestCW() {
        TreeSet<Integer> tree = new TreeSet<>();
        Integer[] array = getBigArrayCW();
        Arrays.stream(array).forEach(tree::add);
        tree.balance();
        assertEquals(20, tree.height());
        assertEquals((N_ELEMENTS + 1) / 2, tree.width());
    }

    @Test
    void balanceTestHW() {
        TreeSet<Integer> tree = new TreeSet<>();
        Integer[] array = getBigArrayHW();
        Arrays.stream(array).forEach(tree::add);
        assertEquals(20, tree.height());
        assertEquals((N_ELEMENTS + 1) / 2, tree.width());
    }
}

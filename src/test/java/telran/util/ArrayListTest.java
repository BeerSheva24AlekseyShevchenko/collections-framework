package telran.util;

import org.junit.jupiter.api.BeforeEach;

public class ArrayListTest extends ListTest {
    @BeforeEach
    @Override
    void setUp() {
        collection = new ArrayList<>(3);
        super.setUp();
    }
}
package telran.util;

import org.junit.jupiter.api.BeforeEach;

public class HashSetTest extends SetTest {
    @BeforeEach
    @Override
    void setUp() {
        collection = new HashSet<>();
        super.setUp();
    }
}

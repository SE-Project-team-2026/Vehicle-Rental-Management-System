package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Manager}.
 */
class ManagerTest {

    @Test
    void testManagerGetters() {
        Manager manager = new Manager(1, "ahmad", "1234");

        assertEquals(1, manager.getId());
        assertEquals("ahmad", manager.getEmail());
        assertEquals("1234", manager.getPassword());
    }
}
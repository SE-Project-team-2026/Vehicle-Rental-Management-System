package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerTest {

    @Test
    void testCustomerGettersAndSetters() {

        Customer customer = new Customer();

        customer.setId(1);
        customer.setName("Thekra");
        customer.setPhone("0599999999");
        customer.setDrivingLicense("DL123");
        customer.setAge(22);

        assertEquals(1, customer.getId());
        assertEquals("Thekra", customer.getName());
        assertEquals("0599999999", customer.getPhone());
        assertEquals("DL123", customer.getDrivingLicense());
        assertEquals(22, customer.getAge());
    }
}
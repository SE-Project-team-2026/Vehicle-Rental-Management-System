package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import enums.VehicleStatus;

class RentalTest {

    @Test
    void testRentalMethods() {

        Customer customer =
                new Customer(1, "Ali", "123", "DL1", 25);

        Vehicle vehicle =
                new Vehicle(1, "Toyota", "Corolla", 50, VehicleStatus.AVAILABLE);

        Rental rental = new Rental();

        rental.setCustomer(customer);
        rental.setVehicle(vehicle);

        rental.setStartDate(LocalDate.now());
        rental.setEndDate(LocalDate.now().plusDays(3));
        rental.setReturnDate(LocalDate.now().plusDays(3));

        assertEquals(customer, rental.getCustomer());
        assertEquals(vehicle, rental.getVehicle());
        assertNotNull(rental.getStartDate());
        assertNotNull(rental.getEndDate());
        assertNotNull(rental.getReturnDate());

        rental.closeRental();

        assertFalse(rental.isActive());
    }
}
package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import enums.VehicleStatus;

class RentalTest {

    @Test
    void testRentalMethods() {
        // 1. تجهيز البيانات (Arrange)
        Customer customer = new Customer(1, "Ali", "123", "DL1", 25);

        Vehicle vehicle = new Car.CarBuilder(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE)
        	.setLicensePlate("ABC-1234")
			.setYear(2022)
			.setColor("White")
			.setFuelType("Petrol")
			.setTransmission("Automatic")
			.build();

        Rental rental = new Rental();

        // 2. تنفيذ العمليات (Act)
        rental.setRentalId(7);
        rental.setCustomer(customer);
        rental.setVehicle(vehicle);
        rental.setStartDate(LocalDate.now());
        rental.setEndDate(LocalDate.now().plusDays(3));
        rental.setReturnDate(LocalDate.now().plusDays(3));

        // 3. التحقق من النتائج (Assert)
        assertEquals(7, rental.getRentalId());
        assertEquals(customer, rental.getCustomer());
        assertEquals(vehicle, rental.getVehicle());
        assertNotNull(rental.getStartDate());
        assertNotNull(rental.getEndDate());
        assertNotNull(rental.getReturnDate());

        // اختبار إغلاق التأجير
        rental.closeRental();
        assertFalse(rental.isActive());
    }

    @Test
    void testParameterizedRentalConstructor() {
        Customer customer = new Customer(2, "Sara", "456", "DL2", 30);
        Vehicle vehicle = new Car.CarBuilder(2, "BMW", "X5", 150.0, VehicleStatus.AVAILABLE).build();
        LocalDate start = LocalDate.of(2026, 8, 1);
        LocalDate end = LocalDate.of(2026, 8, 5);

        Rental rental = new Rental(3, customer, vehicle, start, end);

        assertEquals(3, rental.getRentalId());
        assertEquals(customer, rental.getCustomer());
        assertEquals(vehicle, rental.getVehicle());
        assertEquals(start, rental.getStartDate());
        assertEquals(end, rental.getEndDate());
        assertTrue(rental.isActive());
        assertNull(rental.getReturnDate());
    }
}
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

        Vehicle vehicle = new Car(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE, 
                                  "ABC-1234", 2022, "White", "Petrol", "Automatic");

        Rental rental = new Rental();

        // 2. تنفيذ العمليات (Act)
        rental.setCustomer(customer);
        rental.setVehicle(vehicle);
        rental.setStartDate(LocalDate.now());
        rental.setEndDate(LocalDate.now().plusDays(3));
        rental.setReturnDate(LocalDate.now().plusDays(3));

        // 3. التحقق من النتائج (Assert)
        assertEquals(customer, rental.getCustomer());
        assertEquals(vehicle, rental.getVehicle());
        assertNotNull(rental.getStartDate());
        assertNotNull(rental.getEndDate());
        assertNotNull(rental.getReturnDate());

        // اختبار إغلاق التأجير
        rental.closeRental();
        assertFalse(rental.isActive());
    }
}
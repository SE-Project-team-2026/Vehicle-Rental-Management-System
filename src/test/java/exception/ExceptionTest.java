package exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the custom exceptions.
 */
class ExceptionTest {

    @Test
    void testInvalidRentalPeriodExceptionMessage() {
        InvalidRentalPeriodException ex = new InvalidRentalPeriodException("Invalid period");
        assertEquals("Invalid period", ex.getMessage());
    }

    @Test
    void testVehicleNotAvailableExceptionMessage() {
        VehicleNotAvailableException ex = new VehicleNotAvailableException("Vehicle already rented");
        assertEquals("Vehicle already rented", ex.getMessage());
    }
}
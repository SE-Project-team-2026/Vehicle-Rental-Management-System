package service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import domain.Customer;
import domain.Rental;
import domain.Vehicle;
import enums.VehicleStatus;
import exception.VehicleNotAvailableException;
import repository.RentalRepository;
import repository.VehicleRepository;

import java.time.LocalDate;


class RentalServiceTest {

    @Test
    void testRentVehicleSuccessfully() {

        VehicleRepository vehicleRepository = new VehicleRepository();
        RentalRepository rentalRepository = new RentalRepository();

        RentalService rentalService = new RentalService(rentalRepository, vehicleRepository);

        Vehicle vehicle = new Vehicle(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE);
        vehicleRepository.save(vehicle);

        Customer customer = new Customer(1, "Test User", "123", "DL1", 25);

        Rental rental = rentalService.rentVehicle(
                customer,
                vehicle,
                LocalDate.now(),
                LocalDate.now().plusDays(2)
        );

        assertNotNull(rental);
        assertEquals(VehicleStatus.RENTED, vehicle.getStatus());
    }

    @Test
    void testDoubleBooking() {

        VehicleRepository vehicleRepository = new VehicleRepository();
        RentalRepository rentalRepository = new RentalRepository();

        RentalService rentalService = new RentalService(rentalRepository, vehicleRepository);

        Vehicle vehicle = new Vehicle(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE);
        vehicleRepository.save(vehicle);

        Customer customer = new Customer(1, "Test User", "123", "DL1", 25);

        // first rental (should pass)
        rentalService.rentVehicle(
                customer,
                vehicle,
                LocalDate.now(),
                LocalDate.now().plusDays(2)
        );

        // second rental (should fail)
        assertThrows(VehicleNotAvailableException.class, () -> {
            rentalService.rentVehicle(
                    customer,
                    vehicle,
                    LocalDate.now(),
                    LocalDate.now().plusDays(1)
            );
        });
    }
}
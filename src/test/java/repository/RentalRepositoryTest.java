package repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Car;
import domain.Customer;
import domain.Rental;
import domain.Vehicle;
import enums.VehicleStatus;

class RentalRepositoryTest {

    private RentalRepository rentalRepository;

    @BeforeEach
    void setUp() {
        rentalRepository = new RentalRepository();
    }

    @Test
    void testSaveAndFindAll() {
        Customer customer = new Customer(1, "Ali", "123", "DL1", 25);

        Vehicle vehicle = new Car.CarBuilder(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE)
        		  .setLicensePlate("ABC-1234")
        	        .setYear(2022)
        	        .setColor("White")
        	        .setFuelType("Petrol")
        	        .setTransmission("Automatic")
        	        .build();

        Rental rental = new Rental(1, customer, vehicle, LocalDate.now(), LocalDate.now().plusDays(3));

        rentalRepository.save(rental);

        assertEquals(1, rentalRepository.findAll().size());
        assertEquals(rental, rentalRepository.findAll().get(0));
    }

    @Test
    void testFindActiveRentalByVehicleId_ReturnRental() {
        Customer customer = new Customer(1, "Ali", "123", "DL1", 25);

        Vehicle vehicle = new Car.CarBuilder(10, "Toyota", "Corolla", 50.0, VehicleStatus.RENTED)
                                  .setLicensePlate("XYZ-9876")
                                  .setYear(2023)
                                  .setColor("Black")
                                  .setFuelType("Hybrid")
                                  .setTransmission("Automatic")
                                  .build();
        Rental rental = new Rental(1, customer, vehicle, LocalDate.now(), LocalDate.now().plusDays(2));

        rentalRepository.save(rental);

        Rental result = rentalRepository.findActiveRentalByVehicleId(10);

        assertNotNull(result);
        assertEquals(rental, result);
    }

    @Test
    void testFindActiveRentalByVehicleId_ReturnNull() {
        Rental result = rentalRepository.findActiveRentalByVehicleId(999);
        assertNull(result);
    }

    @Test
    void testFindActiveRentalByVehicleId_WhenRentalClosed() {
        Customer customer = new Customer(1, "Ali", "123", "DL1", 25);

        Vehicle vehicle = new Car.CarBuilder(5, "Toyota", "Corolla", 50.0, VehicleStatus.RENTED) 
        		  .setLicensePlate("DEF-5678")
        	        .setYear(2021)
        	        .setColor("Silver")
        	        .setFuelType("Petrol")
        	        .setTransmission("Manual")
        	        .build();

        Rental rental = new Rental(1, customer, vehicle, LocalDate.now(), LocalDate.now().plusDays(2));

        rental.closeRental();

        rentalRepository.save(rental);

        Rental result = rentalRepository.findActiveRentalByVehicleId(5);

        assertNull(result);
    }
}
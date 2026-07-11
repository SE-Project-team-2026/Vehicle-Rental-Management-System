package service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import exception.InvalidRentalPeriodException;
import domain.Car;
import domain.Customer;
import domain.Rental;
import domain.Vehicle;
import enums.VehicleStatus;
import exception.VehicleNotAvailableException;
import observer.Observer;
import repository.RentalRepository;
import repository.VehicleRepository;
import java.time.LocalDate;


class RentalServiceTest {
    @Test
    void testRentVehicleSuccessfully() {

        VehicleRepository vehicleRepository = new VehicleRepository();
        RentalRepository rentalRepository = new RentalRepository();

        RentalService rentalService =
                new RentalService(rentalRepository, vehicleRepository);

        Vehicle vehicle =
                new Car(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE, "ABC-1234", 2022, "White", "petrol","Automatic");

        vehicleRepository.save(vehicle);

        Customer customer =
                new Customer(1, "Test User", "123", "DL1", 25);


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

        RentalService rentalService =
                new RentalService(rentalRepository, vehicleRepository);


        Vehicle vehicle =
                new Car(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE, "ABC-1234", 2022, "White", "petrol","Automatic");

        vehicleRepository.save(vehicle);


        Customer customer =
                new Customer(1, "Test User", "123", "DL1", 25);



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



    /**
     * Tests Observer Pattern using Mockito.
     * Verifies that notification is sent after successful rental.
     */
    @Test
    void testNotificationSentAfterSuccessfulRental() {


        VehicleRepository vehicleRepository = new VehicleRepository();
        RentalRepository rentalRepository = new RentalRepository();


        RentalService rentalService =
                new RentalService(
                        rentalRepository,
                        vehicleRepository
                );


        // Create Mockito mock observer
        Observer observer = mock(Observer.class);


        // Register observer
        rentalService.addObserver(observer);



        Vehicle vehicle =
                new Car(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE, "ABC-1234", 2022, "White", "petrol","Automatic");

        vehicleRepository.save(vehicle);



        Customer customer =
                new Customer(
                        1,
                        "Test User",
                        "123",
                        "DL1",
                        25
                );



        rentalService.rentVehicle(
                customer,
                vehicle,
                LocalDate.now(),
                LocalDate.now().plusDays(3)
        );



        // Verify that observer received notification
        verify(observer, times(1))
                .update(anyString());

    }
    @Test
    void testInvalidRentalPeriod() {

        VehicleRepository vehicleRepository = new VehicleRepository();
        RentalRepository rentalRepository = new RentalRepository();

        RentalService rentalService =
                new RentalService(
                        rentalRepository,
                        vehicleRepository
                );


        Vehicle vehicle =
                new Car(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE, "ABC-1234", 2022, "White", "petrol","Automatic");

        vehicleRepository.save(vehicle);


        Customer customer =
                new Customer(
                        1,
                        "Test User",
                        "123",
                        "DL1",
                        25
                );

        assertThrows(InvalidRentalPeriodException.class, () -> {

            rentalService.rentVehicle(
                    customer,
                    vehicle,
                    LocalDate.now(),
                    LocalDate.now().minusDays(1)
            );

        });
    }    
    @Test
    void testReturnVehicleSuccessfully() {

        VehicleRepository vehicleRepository = new VehicleRepository();
        RentalRepository rentalRepository = new RentalRepository();

        RentalService rentalService =
                new RentalService(rentalRepository,vehicleRepository);

        Vehicle vehicle =new Car(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE, "ABC-1234", 2022, "White", "petrol","Automatic");

        vehicleRepository.save(vehicle);

        Customer customer =new Customer(1,"Test User","123","DL1",25);

        Rental rental = rentalService.rentVehicle(customer,vehicle,LocalDate.now(),LocalDate.now().plusDays(3));

        LocalDate returnDate = LocalDate.now().plusDays(2);

        rentalService.returnVehicle(rental,returnDate);

        assertEquals(VehicleStatus.AVAILABLE,vehicle.getStatus());

        assertFalse(rental.isActive());
        assertEquals(returnDate,rental.getReturnDate());
    }
}
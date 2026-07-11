package presentation;

import java.time.LocalDate;

import domain.Car;
import domain.Customer;
import domain.Rental;
import domain.Vehicle;
import enums.VehicleStatus;
import observer.NotificationObserver;
import observer.Observer;
import repository.RentalRepository;
import repository.VehicleRepository;
import service.EmailNotification;
import service.RentalService;
import service.SMSNotification;

public class Main {


    public static void main(String[] args) {

        VehicleRepository vehicleRepository = new VehicleRepository();
        RentalRepository rentalRepository = new RentalRepository();
        
        RentalService rentalService = new RentalService(rentalRepository, vehicleRepository);

        Observer emailObserver = new EmailNotification("customer@example.com");
        Observer smsObserver = new SMSNotification("+1234567890");

        rentalService.addObserver(emailObserver);
        rentalService.addObserver(smsObserver);

        Vehicle vehicle = new Car(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE, "ABC-1234", 2022, "White", "petrol","Automatic");
        vehicleRepository.save(vehicle);

        Customer customer = new Customer(1, "Thekra", "0599999999", "DL123", 22);

        System.out.println("--- Attempting First Rental ---");
        Rental rental = rentalService.rentVehicle(
                customer,
                vehicle,
                LocalDate.now(),
                LocalDate.now().plusDays(3)
        );

        System.out.println("\nRental created successfully!");
        System.out.println("Customer: " + rental.getCustomer().getName());
        System.out.println("Vehicle: " + rental.getVehicle().getBrand());
        System.out.println("Status: " + rental.getVehicle().getStatus());

        System.out.println("\n--- Attempting Second Rental (Double Booking) ---");
        try {
            Rental rental2 = rentalService.rentVehicle(
                    customer,
                    vehicle,
                    LocalDate.now(),
                    LocalDate.now().plusDays(2)
            );
            System.out.println("Second rental created (ERROR - This should not happen!)");
        } catch (Exception e) {
            System.out.println("Double booking prevented successfully!");
            System.out.println("Error: " + e.getMessage());
        }
    }
}
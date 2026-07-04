package presentation;

import domain.Customer;
import domain.Vehicle;
import domain.Rental;
import enums.VehicleStatus;
import repository.RentalRepository;
import repository.VehicleRepository;
import service.RentalService;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        // repositories
        VehicleRepository vehicleRepository = new VehicleRepository();
        RentalRepository rentalRepository = new RentalRepository();

        // service
        RentalService rentalService = new RentalService(rentalRepository, vehicleRepository);

        // create vehicle
        Vehicle vehicle = new Vehicle(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE);
        vehicleRepository.save(vehicle);

        // create customer
        Customer customer = new Customer(1, "Thekra", "0599999999", "DL123", 22);

        // first rental
        Rental rental = rentalService.rentVehicle(
                customer,
                vehicle,
                LocalDate.now(),
                LocalDate.now().plusDays(3)
        );

        System.out.println("Rental created successfully!");
        System.out.println("Customer: " + rental.getCustomer().getName());
        System.out.println("Vehicle: " + rental.getVehicle().getBrand());
        System.out.println("Status: " + rental.getVehicle().getStatus());

        // =========================
        // DOUBLE BOOKING TEST
        // =========================
        try {
            Rental rental2 = rentalService.rentVehicle(
                    customer,
                    vehicle,
                    LocalDate.now(),
                    LocalDate.now().plusDays(2)
            );

            System.out.println("Second rental created (ERROR)");
        } catch (Exception e) {
            System.out.println("\nDouble booking prevented successfully!");
            System.out.println("Error: " + e.getMessage());
        }
    }
}
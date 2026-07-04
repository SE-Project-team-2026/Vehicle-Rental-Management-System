package service;

import domain.Customer;
import domain.Rental;
import domain.Vehicle;
import enums.VehicleStatus;
import exception.InvalidRentalPeriodException;
import exception.VehicleNotAvailableException;
import repository.RentalRepository;
import repository.VehicleRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentalService {

    private RentalRepository rentalRepository;
    private VehicleRepository vehicleRepository;

    public RentalService(RentalRepository rentalRepository,
                         VehicleRepository vehicleRepository) {
        this.rentalRepository = rentalRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public Rental rentVehicle(Customer customer, Vehicle vehicle,
                              LocalDate startDate, LocalDate endDate) {

        if (vehicle.getStatus() == VehicleStatus.RENTED) {
            throw new VehicleNotAvailableException("Vehicle already rented");
        }

        if (endDate.isBefore(startDate)) {
            throw new InvalidRentalPeriodException("Invalid rental period");
        }

        long days = ChronoUnit.DAYS.between(startDate, endDate);
        if (days <= 0) {
            throw new InvalidRentalPeriodException("Rental must be at least 1 day");
        }

        Rental rental = new Rental();
        rental.setCustomer(customer);
        rental.setVehicle(vehicle);
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);

        vehicle.setStatus(VehicleStatus.RENTED);
        vehicleRepository.update(vehicle);

        rentalRepository.save(rental);

        return rental;
    }
}
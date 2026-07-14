package service;

import domain.Customer;
import domain.Rental;
import domain.Vehicle;
import enums.VehicleStatus;
import exception.InvalidRentalPeriodException;
import exception.VehicleNotAvailableException;
import observer.Observer;
import observer.Subject;
import repository.RentalRepository;
import repository.VehicleRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for managing vehicle rental operations.
 *
 * <p>This class provides the core business logic for the Vehicle Rental
 * Management System. It handles the rental process, validates rental
 * requests, updates vehicle availability, stores rental records,
 * and notifies registered observers when rental events occur.</p>
 *
 * <p>The class also implements the Observer design pattern through the
 * {@link Subject} interface, allowing notification services to be informed
 * whenever a vehicle is successfully rented.</p>
 *
 */
public class RentalService implements Subject {

    /** Repository responsible for storing rental records. */
    private RentalRepository rentalRepository;

    /** Repository responsible for managing vehicle data. */
    private VehicleRepository vehicleRepository;

    /** List of registered observers that receive rental notifications. */
    private List<Observer> observers = new ArrayList<>();

    /**
     * Creates a new RentalService instance.
     *
     * @param rentalRepository repository used to store rental information
     * @param vehicleRepository repository used to manage vehicle information
     */
    public RentalService(RentalRepository rentalRepository,
                         VehicleRepository vehicleRepository) {

        this.rentalRepository = rentalRepository;
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Creates a new rental after validating the rental request.
     *
     * <p>This method performs the following operations:</p>
     * <ul>
     *   <li>Checks whether the selected vehicle is available.</li>
     *   <li>Validates the rental period.</li>
     *   <li>Creates a new rental record.</li>
     *   <li>Updates the vehicle status to RENTED.</li>
     *   <li>Saves the rental in the repository.</li>
     *   <li>Notifies all registered observers.</li>
     * </ul>
     *
     * @param customer the customer renting the vehicle
     * @param vehicle the vehicle to be rented
     * @param startDate the rental start date
     * @param endDate the rental end date
     * @return the created Rental object
     * @throws VehicleNotAvailableException if the selected vehicle is already rented
     * @throws InvalidRentalPeriodException if the rental period is invalid
     */
    public Rental rentVehicle(Customer customer,
                              Vehicle vehicle,
                              LocalDate startDate,
                              LocalDate endDate) {

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

        notifyObservers("Vehicle rented successfully: " + vehicle.getBrand());

        return rental;
    }

    /**
     * Registers a new observer to receive rental notifications.
     *
     * @param observer the observer to be added
     */
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes an existing observer from the notification list.
     *
     * @param observer the observer to be removed
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Sends a notification message to all registered observers.
     *
     * @param message the notification message
     */
    @Override
    public void notifyObservers(String message) {

        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
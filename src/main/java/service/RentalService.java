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


public class RentalService implements Subject {

    private RentalRepository rentalRepository;
    private VehicleRepository vehicleRepository;

    private List<Observer> observers = new ArrayList<>();


    public RentalService(RentalRepository rentalRepository,
                         VehicleRepository vehicleRepository) {

        this.rentalRepository = rentalRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public Rental rentVehicle(Customer customer,
                              Vehicle vehicle,
                              LocalDate startDate,
                              LocalDate endDate) {

        if (vehicle.getStatus() == VehicleStatus.RENTED) {
            throw new VehicleNotAvailableException(
                    "Vehicle already rented"
            );
        }


        if (endDate.isBefore(startDate)) {
            throw new InvalidRentalPeriodException(
                    "Invalid rental period"
            );
        }

        long days = ChronoUnit.DAYS.between(startDate, endDate);

        if (days <= 0) {
            throw new InvalidRentalPeriodException(
                    "Rental must be at least 1 day"
            );
        }

        Rental rental = new Rental();

        rental.setCustomer(customer);
        rental.setVehicle(vehicle);
        rental.setStartDate(startDate);
        rental.setEndDate(endDate);


        vehicle.setStatus(VehicleStatus.RENTED);

        vehicleRepository.update(vehicle);

        rentalRepository.save(rental);


        // Observer Pattern notification
        notifyObservers(
                "Vehicle rented successfully: "
                + vehicle.getBrand()
        );


        return rental;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
   
    @Override
    public void notifyObservers(String message) {

        for (Observer observer : observers) {
            observer.update(message);
        }
    }
    public Rental returnVehicle(Rental rental, LocalDate returnDate) {

        rental.setReturnDate(returnDate);

        rental.closeRental();

        Vehicle vehicle = rental.getVehicle();

        vehicle.setStatus(VehicleStatus.AVAILABLE);

        vehicleRepository.update(vehicle);

        notifyObservers("Vehicle returned successfully: "+ vehicle.getBrand());


        return rental;
    }    
}
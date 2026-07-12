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
import strategy.RentalValidationStrategy;
import java.util.Map;
import java.util.HashMap;

public class RentalService implements Subject {

    private RentalRepository rentalRepository;
    private VehicleRepository vehicleRepository;

    private List<Observer> observers = new ArrayList<>();
    private Map<String, RentalValidationStrategy> validationStrategies;

    public RentalService(RentalRepository rentalRepository,
            VehicleRepository vehicleRepository,
            Map<String, RentalValidationStrategy> validationStrategies) {
this.rentalRepository = rentalRepository;
this.vehicleRepository = vehicleRepository;
this.validationStrategies = validationStrategies;
}
    
    public Rental rentVehicle(Customer customer, Vehicle vehicle, 
            LocalDate startDate, LocalDate endDate) {

// 1. التحقق من حالة المركبة
if (vehicle.getStatus() == VehicleStatus.RENTED) {
throw new VehicleNotAvailableException("Vehicle already rented");
}

// 2. التحقق من التواريخ
if (endDate.isBefore(startDate)) {
throw new InvalidRentalPeriodException("Invalid rental period");
}

long days = ChronoUnit.DAYS.between(startDate, endDate);
if (days <= 0) {
throw new InvalidRentalPeriodException("Rental must be at least 1 day");
}

// 3. التحقق من الاستراتيجية (Strategy Pattern) ⭐ هنا
String vehicleType = vehicle.getVehicleType();
RentalValidationStrategy strategy = validationStrategies.get(vehicleType);
if (strategy != null && !strategy.validate(customer, vehicle)) {
throw new IllegalArgumentException(
"Rental validation failed for " + vehicleType
);
}

// 4. إنشاء Rental فقط بعد نجاح كل التحقق
Rental rental = new Rental();
rental.setCustomer(customer);
rental.setVehicle(vehicle);
rental.setStartDate(startDate);
rental.setEndDate(endDate);

// 5. تحديث الحالة والحفظ
vehicle.setStatus(VehicleStatus.RENTED);
vehicleRepository.update(vehicle);
rentalRepository.save(rental);

// 6. الإشعار
notifyObservers("Vehicle rented successfully: " + vehicle.getBrand());

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
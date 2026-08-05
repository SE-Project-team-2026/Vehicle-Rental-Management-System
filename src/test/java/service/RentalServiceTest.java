package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import domain.Car;
import domain.Customer;
import domain.ElectricVehicle;
import domain.Motorcycle;
import domain.Rental;
import domain.Truck;
import domain.Vehicle;
import enums.VehicleStatus;
import exception.InvalidRentalPeriodException;
import exception.VehicleNotAvailableException;
import observer.Observer;
import repository.RentalRepository;
import repository.VehicleRepository;
import strategy.ElectricVehicleValidationStrategy;
import strategy.MotorcycleValidationStrategy;
import strategy.RentalValidationStrategy;
import strategy.TruckValidationStrategy;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

class RentalServiceTest {
    
    private VehicleRepository vehicleRepository;
    private RentalRepository rentalRepository;
    private RentalService rentalService;
    private Customer customer;
    
    @BeforeEach
    void setUp() {
        vehicleRepository = new VehicleRepository();
        rentalRepository = new RentalRepository();
        
        Map<String, RentalValidationStrategy> validationStrategies = new HashMap<>();
        
        rentalService = new RentalService(
            rentalRepository, 
            vehicleRepository, 
            validationStrategies
        );
        
        customer = new Customer(1, "Test User", "123", "DL1", 25);
    }
    
    @Test
    void testRentVehicleSuccessfully() {
    	 Vehicle vehicle = new Car.CarBuilder(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE)
  			 	.setLicensePlate("ABC-1234")
  				.setYear(2022)
  				.setColor("White")
  				.setFuelType("Petrol")
  				.setTransmission("Automatic")
  				.build();
        vehicleRepository.save(vehicle);
        
        Rental rental = rentalService.rentVehicle(
            customer, vehicle,
            LocalDate.now(), LocalDate.now().plusDays(2)
        );
        
        assertNotNull(rental);
        assertEquals(VehicleStatus.RENTED, vehicle.getStatus());
    }
    
    @Test
    void testDoubleBooking() {
    	 Vehicle vehicle = new Car.CarBuilder(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE)
  			 	.setLicensePlate("ABC-1234")
  				.setYear(2022)
  				.setColor("White")
  				.setFuelType("Petrol")
  				.setTransmission("Automatic")
  				.build();
        vehicleRepository.save(vehicle);
        
        rentalService.rentVehicle(
            customer, vehicle,
            LocalDate.now(), LocalDate.now().plusDays(2)
        );
        
        assertThrows(VehicleNotAvailableException.class, () -> {
            rentalService.rentVehicle(
                customer, vehicle,
                LocalDate.now(), LocalDate.now().plusDays(1)
            );
        });
    }
    
    @Test
    void testNotificationSentAfterSuccessfulRental() {
        Observer observer = mock(Observer.class);
        rentalService.addObserver(observer);
        
        Vehicle vehicle = new Car.CarBuilder(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE)
 			 	.setLicensePlate("ABC-1234")
 				.setYear(2022)
 				.setColor("White")
 				.setFuelType("Petrol")
 				.setTransmission("Automatic")
 				.build();
        vehicleRepository.save(vehicle);
        
        rentalService.rentVehicle(
            customer, vehicle,
            LocalDate.now(), LocalDate.now().plusDays(3)
        );
        
        verify(observer, times(1)).update(anyString());
    }
    
    @Test
    void testRemoveObserver_NoLongerNotified() {
        Observer observer = mock(Observer.class);
        rentalService.addObserver(observer);
        rentalService.removeObserver(observer);

        Vehicle vehicle = new Car.CarBuilder(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE)
                .setLicensePlate("ABC-1234")
                .setYear(2022)
                .setColor("White")
                .setFuelType("Petrol")
                .setTransmission("Automatic")
                .build();
        vehicleRepository.save(vehicle);

        rentalService.rentVehicle(
            customer, vehicle,
            LocalDate.now(), LocalDate.now().plusDays(3)
        );

        verify(observer, never()).update(anyString());
    }
    
    @Test
    void testInvalidRentalPeriod() {
    	 Vehicle vehicle = new Car.CarBuilder(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE)
 			 	.setLicensePlate("ABC-1234")
 				.setYear(2022)
 				.setColor("White")
 				.setFuelType("Petrol")
 				.setTransmission("Automatic")
 				.build();
        vehicleRepository.save(vehicle);
        
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        assertThrows(InvalidRentalPeriodException.class, () -> 
            rentalService.rentVehicle(customer, vehicle, today, yesterday)
        );
    }
    
    @Test
    void testRentVehicle_ShouldThrowException_WhenRentalPeriodIsZeroDays() {
    	 Vehicle vehicle = new Car.CarBuilder(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE)
 			 	.setLicensePlate("ABC-1234")
 				.setYear(2022)
 				.setColor("White")
 				.setFuelType("Petrol")
 				.setTransmission("Automatic")
 				.build();
        vehicleRepository.save(vehicle);
        
        LocalDate sameDate = LocalDate.now(); // startDate = endDate (0 days)
        
        assertThrows(InvalidRentalPeriodException.class, () -> {
            rentalService.rentVehicle(customer, vehicle, sameDate, sameDate);
        });
    }
    
    @Test
    void testRentVehicle_ShouldSucceed_WhenValidationStrategyPasses() {
        Motorcycle motorcycle = new Motorcycle(2, "Honda", "CBR600", 40.0,
                VehicleStatus.AVAILABLE, 600, 18);
        vehicleRepository.save(motorcycle);

        Customer adultCustomer = new Customer(2, "Adult User", "456", "DL2", 20);

        Map<String, RentalValidationStrategy> validationStrategies = new HashMap<>();
        validationStrategies.put("Motorcycle", new MotorcycleValidationStrategy());

        RentalService rentalServiceWithValidation = new RentalService(
            rentalRepository, vehicleRepository, validationStrategies
        );

        Rental rental = rentalServiceWithValidation.rentVehicle(
            adultCustomer, motorcycle,
            LocalDate.now(), LocalDate.now().plusDays(2)
        );

        assertNotNull(rental);
        assertEquals(VehicleStatus.RENTED, motorcycle.getStatus());
    }

    @Test
    void testReturnVehicleSuccessfully() {
        Vehicle vehicle = new Car.CarBuilder(1, "Toyota", "Corolla", 50.0, 
                                  VehicleStatus.AVAILABLE)
        		.setLicensePlate("ABC-1234")
        						.setYear(2022)
        						.setColor("White")
        						.setFuelType("Petrol")
        						.setTransmission("Automatic")
        						.build();
        
        vehicleRepository.save(vehicle);
        
        Rental rental = rentalService.rentVehicle(
            customer, vehicle,
            LocalDate.now(), LocalDate.now().plusDays(3)
        );
        
        LocalDate returnDate = LocalDate.now().plusDays(2);
        rentalService.returnVehicle(rental, returnDate);
        
        assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
        assertFalse(rental.isActive());
        assertEquals(returnDate, rental.getReturnDate());
    }
    
    @Test
    void testRentVehicle_ShouldThrowException_WhenElectricVehicleBatteryLow() {
        ElectricVehicle ev =new ElectricVehicle.ElectricVehicleBuilder(5, "Tesla", "Model 3", 80.0, 
                VehicleStatus.AVAILABLE)
.setBatteryLevel(25.0)
.setRange(350)
.setChargingTime(8.0)
.build();
        vehicleRepository.save(ev);
        
        Map<String, RentalValidationStrategy> validationStrategies = new HashMap<>();
        validationStrategies.put("ElectricVehicle", new ElectricVehicleValidationStrategy());
        
        RentalService rentalServiceWithValidation = new RentalService(
            rentalRepository, vehicleRepository, validationStrategies
        );
        
        assertThrows(IllegalArgumentException.class, () -> {
            rentalServiceWithValidation.rentVehicle(
                customer, ev,
                LocalDate.now(), LocalDate.now().plusDays(2)
            );
        });
    }
    
    @Test
    void testRentVehicle_ShouldThrowException_WhenMotorcycleAgeValidationFails() {
        Motorcycle motorcycle = new Motorcycle(2, "Honda", "CBR600", 40.0, 
                                               VehicleStatus.AVAILABLE, 600, 18);
        vehicleRepository.save(motorcycle);
        
        Customer youngCustomer = new Customer(2, "Young User", "456", "DL2", 16);
        
        Map<String, RentalValidationStrategy> validationStrategies = new HashMap<>();
        validationStrategies.put("Motorcycle", new MotorcycleValidationStrategy());
        
        RentalService rentalServiceWithValidation = new RentalService(
            rentalRepository, vehicleRepository, validationStrategies
        );
        
        assertThrows(IllegalArgumentException.class, () -> {
            rentalServiceWithValidation.rentVehicle(
                youngCustomer, motorcycle,
                LocalDate.now(), LocalDate.now().plusDays(2)
            );
        });
    }
    
    @Test
    void testRentVehicle_ShouldThrowException_WhenTruckLicenseValidationFails() {
        Truck truck = new Truck.TruckBuilder(3, "Ford", "F-150", 100.0, VehicleStatus.AVAILABLE)
        					.setMaxLoadCapacity(5.5)
			.setRequiresSpecialLicense(true)
			.setNumberOfAxles(2)
			.build();
        vehicleRepository.save(truck);
        
        Customer customerWithoutSpecialLicense = new Customer(3, "User", "789", "DL3", 25);
        
        Map<String, RentalValidationStrategy> validationStrategies = new HashMap<>();
        validationStrategies.put("Truck", new TruckValidationStrategy());
        
        RentalService rentalServiceWithValidation = new RentalService(
            rentalRepository, vehicleRepository, validationStrategies
        );
        
        assertThrows(IllegalArgumentException.class, () -> {
            rentalServiceWithValidation.rentVehicle(
                customerWithoutSpecialLicense, truck,
                LocalDate.now(), LocalDate.now().plusDays(2)
            );
        });
    }
    
    @Test
    void testValidate_ShouldReturnTrue_WhenCustomerAgeIsSufficient() {
        MotorcycleValidationStrategy strategy = new MotorcycleValidationStrategy();
        Customer customer = new Customer(2, "Motorcycle User", "456", "DL2", 20); 
        Motorcycle motorcycle = new Motorcycle(1, "Honda", "CBR600", 40.0, 
                                               VehicleStatus.AVAILABLE, 600, 18); 
        boolean isValid = strategy.validate(customer, motorcycle);
        assertTrue(isValid);
    }
    
    @Test
    void testValidate_ShouldReturnFalse_WhenCustomerAgeIsBelowMinimum() {
        MotorcycleValidationStrategy strategy = new MotorcycleValidationStrategy();
        Customer customer = new Customer(2, "Motorcycle User", "456", "DL2", 16); 
        Motorcycle motorcycle = new Motorcycle(1, "Honda", "CBR600", 40.0, 
                                               VehicleStatus.AVAILABLE, 600, 18); 
        boolean isValid = strategy.validate(customer, motorcycle);
        assertFalse(isValid);
    }
    
    @Test
    void testValidate_ShouldReturnTrue_WhenCustomerHasSpecialLicense() {
        TruckValidationStrategy strategy = new TruckValidationStrategy();
        Customer customer = new Customer(3, "Truck User", "789", "SP-3", 25); 
        Truck truck = new Truck.TruckBuilder(1, "Ford", "SP-150", 100.0, VehicleStatus.AVAILABLE)
        									.setMaxLoadCapacity(5.5)
								.setRequiresSpecialLicense(true)
								.setNumberOfAxles(2)
								.build();
        boolean isValid = strategy.validate(customer, truck);
        assertTrue(isValid);
    }
    
    @Test
    void testValidate_ShouldReturnFalse_WhenCustomerDoesNotHaveSpecialLicense() {
        TruckValidationStrategy strategy = new TruckValidationStrategy();
        Customer customer = new Customer(3, "Truck User", "789", "DL3", 25); 
        Truck truck = new Truck.TruckBuilder(1, "Ford", "SP-150", 100.0, VehicleStatus.AVAILABLE)
        		.setMaxLoadCapacity(5.5)
        						.setRequiresSpecialLicense(true)
        						.setNumberOfAxles(2)
        						.build();
        
        boolean isValid = strategy.validate(customer, truck);
        assertFalse(isValid);
    }
    
    @Test
    void testValidate_ShouldReturnTrue_WhenTruckDoesNotRequireSpecialLicense() {
        TruckValidationStrategy strategy = new TruckValidationStrategy();
        Customer customer = new Customer(3, "Truck User", "789", "DL3", 25); 
        
        Truck truck = new Truck.TruckBuilder(1, "Ford", "F-150", 100.0, VehicleStatus.AVAILABLE)
        		.setMaxLoadCapacity(5.5)
        		.setRequiresSpecialLicense(false)
        		.setNumberOfAxles(2)
        		.build();
        
        boolean isValid = strategy.validate(customer, truck);
        assertTrue(isValid); 
    }
    
    @Test
    void testValidate_ShouldReturnTrue_WhenBatteryLevelIsSufficient() {
        ElectricVehicleValidationStrategy strategy = new ElectricVehicleValidationStrategy();
        Customer customer = new Customer(4, "EV User", "012", "DL4", 25); 
        ElectricVehicle ev = new ElectricVehicle.ElectricVehicleBuilder(5, "Tesla", "Model 3", 80.0, 
                VehicleStatus.AVAILABLE)
.setBatteryLevel(75.0)
.setRange(350)
.setChargingTime(8.0)
.build();
        boolean isValid = strategy.validate(customer, ev);
        assertTrue(isValid);
    }
    
    @Test
    void testValidate_ShouldReturnFalse_WhenBatteryLevelIsLow() {
        ElectricVehicleValidationStrategy strategy = new ElectricVehicleValidationStrategy();
        Customer customer = new Customer(4, "EV User", "012", "DL4", 25); 
        ElectricVehicle ev =new ElectricVehicle.ElectricVehicleBuilder(5, "Tesla", "Model 3", 80.0, 
                VehicleStatus.AVAILABLE)
.setBatteryLevel(25.0)
.setRange(350)
.setChargingTime(8.0)
.build();
        boolean isValid = strategy.validate(customer, ev);
        assertFalse(isValid);
    }
}
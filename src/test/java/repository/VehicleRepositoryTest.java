package repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Car;
import domain.Vehicle;
import enums.VehicleStatus;

class VehicleRepositoryTest {

    private VehicleRepository vehicleRepository;

    // 1. Arrange 
    @BeforeEach
    void setUp() {
        vehicleRepository = new VehicleRepository();
        
        // إضافة مركبات متاحة
        Vehicle car1 = new Car.CarBuilder(1, "Toyota", "Corolla", 50.0, 
                               VehicleStatus.AVAILABLE)
        		  .setLicensePlate("ABC-1234")
        	        .setYear(2022)
        	        .setColor("White")
        	        .setFuelType("Petrol")
        	        .setTransmission("Automatic")
        	        .build();
        		
        
        Vehicle car2 = new Car.CarBuilder(2, "Honda", "Civic", 60.0, 
                               VehicleStatus.AVAILABLE)
        		 .setLicensePlate("XYZ-9876")
                 .setYear(2023)
                 .setColor("Black")
                 .setFuelType("Hybrid")
                 .setTransmission("Automatic")
                 .build();
        
        // إضافة مركبة مستأجرة (غير متاحة)
        Vehicle car3 = new Car.CarBuilder(3, "BMW", "X5", 150.0, 
                               VehicleStatus.RENTED)
        .setLicensePlate("DEF-5678")
        .setYear(2021)
        .setColor("Silver")
        .setFuelType("Petrol")
        .setTransmission("Automatic")
        .build();
        
        vehicleRepository.save(car1);
        vehicleRepository.save(car2);
        vehicleRepository.save(car3);
    }

    // 2. Act & Assert 
    @Test
    void testFindAvailable_ReturnsOnlyAvailableVehicles() {
        // Act: استدعاء الدالة
        List<Vehicle> availableVehicles = vehicleRepository.findAvailable();

        assertEquals(2, availableVehicles.size()); 

        for (Vehicle v : availableVehicles) {
            assertTrue(v.isAvailable()); 
        }
    }
}
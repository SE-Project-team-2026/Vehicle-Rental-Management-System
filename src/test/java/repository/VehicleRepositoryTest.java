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

    @Test
    void testFindById_ReturnsMatchingVehicle() {
        Vehicle found = vehicleRepository.findById(2);
        assertNotNull(found);
        assertEquals("Honda", found.getBrand());

        assertNull(vehicleRepository.findById(999));
    }

    @Test
    void testFindAll_ReturnsAllVehicles() {
        assertEquals(3, vehicleRepository.findAll().size());
    }

    @Test
    void testUpdate_ReplacesVehicleWithSameId() {
        Vehicle updated = new Car.CarBuilder(1, "Toyota", "Camry", 55.0,
                VehicleStatus.RENTED)
                .setLicensePlate("NEW-0001")
                .setYear(2024)
                .setColor("Red")
                .setFuelType("Petrol")
                .setTransmission("Automatic")
                .build();

        vehicleRepository.update(updated);

        Vehicle found = vehicleRepository.findById(1);
        assertNotNull(found);
        assertEquals("Camry", found.getModel());
        assertEquals(55.0, found.getPricePerDay());
        assertEquals(VehicleStatus.RENTED, found.getStatus());
    }
}
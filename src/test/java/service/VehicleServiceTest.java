package service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Car;
import enums.VehicleStatus;
import repository.VehicleRepository;

class VehicleServiceTest {

    VehicleService vehicleService;
    VehicleRepository vehicleRepository;
    
    @BeforeEach
    void setUp() throws Exception {
        vehicleRepository = new VehicleRepository();
        
        Car car1 = new Car(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE, 
                          "ABC-1234", 2022, "White", "Petrol", "Automatic");
        Car car2 = new Car(2, "BMW", "X5", 150.0, VehicleStatus.AVAILABLE, 
                          "XYZ-9876", 2024, "Black", "Hybrid", "Automatic");
        Car car3 = new Car(3, "Hyundai", "Elantra", 40.0, VehicleStatus.RENTED, 
                          "DEF-5678", 2021, "Silver", "Petrol", "Manual");
        
        vehicleRepository.save(car1);
        vehicleRepository.save(car2);
        vehicleRepository.save(car3);
        
        vehicleService = new VehicleService(vehicleRepository);
    }

    @Test
    void testGetAvailableVehicles() {
        assertEquals(2, vehicleService.getAvailableVehicles().size());
        
        assertTrue(vehicleService.getAvailableVehicles().stream()
                .allMatch(v -> v.getStatus() == VehicleStatus.AVAILABLE));
    }
}
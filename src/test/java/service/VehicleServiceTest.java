package service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Car;
import domain.Vehicle;
import enums.VehicleStatus;
import repository.VehicleRepository;

class VehicleServiceTest {

    VehicleService vehicleService;
    VehicleRepository vehicleRepository;
    
    @BeforeEach
    void setUp() throws Exception {
        vehicleRepository = new VehicleRepository();
        
        Car car1 = new Car.CarBuilder(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE)
 			 	.setLicensePlate("ABC-1234")
 				.setYear(2022)
 				.setColor("White")
 				.setFuelType("Petrol")
 				.setTransmission("Automatic")
 				.build();
       Car car2 = new Car.CarBuilder(1, "BMW", "X5", 150.0, VehicleStatus.AVAILABLE)
 			 	.setLicensePlate("XYZ-9876")
 				.setYear(2024)
 				.setColor("Black")
 				.setFuelType("Hybrid")
 				.setTransmission("Automatic")
 				.build();
        Car car3 = new Car.CarBuilder(1, "Hyundai", "Elantra",40.0, VehicleStatus.RENTED)
        		 			 	.setLicensePlate("DEF-5678")
        		 			 	.setYear(2021)
        		 			 	.setColor("Silver")
        		 			 	.setFuelType("Petrol")
        		 			 	.setTransmission("Manual")
        		 			 	.build();
        
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
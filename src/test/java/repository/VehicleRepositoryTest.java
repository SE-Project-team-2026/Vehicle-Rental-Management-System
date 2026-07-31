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
        Vehicle car1 = new Car(1, "Toyota", "Corolla", 50.0, 
                               VehicleStatus.AVAILABLE, "ABC-1234", 
                               2022, "White", "Petrol", "Automatic");
        
        Vehicle car2 = new Car(2, "Honda", "Civic", 60.0, 
                               VehicleStatus.AVAILABLE, "XYZ-9876", 
                               2023, "Black", "Hybrid", "Automatic");
        
        // إضافة مركبة مستأجرة (غير متاحة)
        Vehicle car3 = new Car(3, "BMW", "X5", 150.0, 
                               VehicleStatus.RENTED, "DEF-5678", 
                               2024, "Silver", "Petrol", "Automatic");
        
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
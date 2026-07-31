package domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import enums.VehicleStatus;

class VehicleDomainTest {

    @Test
    void testCarCreationAndAvailability() {
        Car car = new Car(1, "Toyota", "Corolla", 50.0, 
                         VehicleStatus.AVAILABLE, "ABC-1234", 
                         2022, "White", "Petrol", "Automatic");
        
        assertEquals("Car", car.getVehicleType());
        assertEquals("Toyota", car.getBrand());
        assertEquals("Corolla", car.getModel());
        assertEquals(50.0, car.getPricePerDay());
        assertEquals("ABC-1234", car.getLicensePlate());
        assertEquals(2022, car.getYear());
        assertEquals("White", car.getColor());
        assertEquals("Petrol", car.getFuelType());
        assertEquals("Automatic", car.getTransmission());
        assertTrue(car.isAvailable());
        
        car.setStatus(VehicleStatus.RENTED);
        assertFalse(car.isAvailable());
    }

    @Test
    void testMotorcycleCreationAndAvailability() {
        Motorcycle motorcycle = new Motorcycle(2, "Honda", "CBR600", 40.0, 
                                               VehicleStatus.AVAILABLE, 600, 18);
        
        assertEquals("Motorcycle", motorcycle.getVehicleType());
        assertEquals("Honda", motorcycle.getBrand());
        assertEquals("CBR600", motorcycle.getModel());
        assertEquals(40.0, motorcycle.getPricePerDay());
        assertEquals(600, motorcycle.getEngineCapacity());
        assertEquals(18, motorcycle.getMinimumAge());
        assertTrue(motorcycle.isAvailable());
        
        motorcycle.setStatus(VehicleStatus.RENTED);
        assertFalse(motorcycle.isAvailable());
    }

    @Test
    void testTruckCreationAndAvailability() {
        Truck truck = new Truck(3, "Ford", "F-150", 100.0, 
                               VehicleStatus.AVAILABLE, 5.5, true, 2);
        
        assertEquals("Truck", truck.getVehicleType());
        assertEquals("Ford", truck.getBrand());
        assertEquals("F-150", truck.getModel());
        assertEquals(100.0, truck.getPricePerDay());
        assertEquals(5.5, truck.getMaxLoadCapacity());
        assertTrue(truck.isRequiresSpecialLicense());
        assertEquals(2, truck.getNumberOfAxles());
        assertTrue(truck.isAvailable());
        
        truck.setStatus(VehicleStatus.RENTED);
        assertFalse(truck.isAvailable());
    }

    @Test
    void testVanCreationAndAvailability() {
        Van van = new Van(4, "Toyota", "Hiace", 80.0, 
                         VehicleStatus.AVAILABLE, 15, 12, true);
        
        assertEquals("Van", van.getVehicleType());
        assertEquals("Toyota", van.getBrand());
        assertEquals("Hiace", van.getModel());
        assertEquals(80.0, van.getPricePerDay());
        assertEquals(15, van.getCargoCapacity());
        assertEquals(12, van.getPassengerCapacity());
        assertTrue(van.isHasSlidingDoor());
        assertTrue(van.isAvailable());
        
        van.setStatus(VehicleStatus.RENTED);
        assertFalse(van.isAvailable());
    }

    @Test
    void testElectricVehicleCreationAndAvailability() {
        ElectricVehicle ev = new ElectricVehicle(5, "Tesla", "Model 3", 120.0, 
                                                 VehicleStatus.AVAILABLE, 
                                                 75.0, 350, 8.0);
        
        assertEquals("ElectricVehicle", ev.getVehicleType());
        assertEquals("Tesla", ev.getBrand());
        assertEquals("Model 3", ev.getModel());
        assertEquals(120.0, ev.getPricePerDay());
        assertEquals(75.0, ev.getBatteryLevel());
        assertEquals(350, ev.getRange());
        assertEquals(8.0, ev.getChargingTime());
        assertTrue(ev.isAvailable());
        
        ev.setStatus(VehicleStatus.RENTED);
        assertFalse(ev.isAvailable());
    }

    @Test
    void testVehiclePolymorphism() {
        Vehicle[] vehicles = new Vehicle[] {
            new Car(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE, "ABC-1234", 2022, "White", "Petrol", "Automatic"),
            new Motorcycle(2, "Honda", "CBR600", 40.0, VehicleStatus.AVAILABLE, 600, 18),
            new Truck(3, "Ford", "F-150", 100.0, VehicleStatus.AVAILABLE, 5.5, true, 2),
            new Van(4, "Toyota", "Hiace", 80.0, VehicleStatus.AVAILABLE, 15, 12, true),
            new ElectricVehicle(5, "Tesla", "Model 3", 120.0, VehicleStatus.AVAILABLE, 75.0, 350, 8.0)
        };
        
        assertEquals(5, vehicles.length);
        
        for (Vehicle vehicle : vehicles) {
            assertNotNull(vehicle.getVehicleType());
            assertTrue(vehicle.isAvailable());
            assertTrue(vehicle.validateForRental());
        }
    }
}
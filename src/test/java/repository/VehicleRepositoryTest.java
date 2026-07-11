package repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Vehicle;

class VehicleRepositoryTest {

	private VehicleRepository vehicleRepository;
	private  List<Vehicle> vehicles;
	@BeforeEach
	void setUp() throws Exception {
		vehicleRepository = new VehicleRepository();
		vehicles = vehicleRepository.findAll();
	}

	@Test
	void testFindAvailable_ReturnsOnlyAvailableVehicles() {
		List<Vehicle> availableVehicles = vehicleRepository.findAvailable();

		assertEquals(2, availableVehicles.size());

		for (Vehicle v : availableVehicles) {
		    assertTrue(v.isAvailable());
		}
	}

}

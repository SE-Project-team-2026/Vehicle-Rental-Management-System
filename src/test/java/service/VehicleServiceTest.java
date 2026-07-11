package service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import repository.VehicleRepository;

class VehicleServiceTest {

	VehicleService vehicleService;
	VehicleRepository vehicleRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		vehicleRepository = new VehicleRepository();
		vehicleService = new VehicleService(vehicleRepository);
	}

	@Test
	void testGetAvailableVehicles() {
		assertEquals(2, vehicleService.getAvailableVehicles().size());
		assertTrue(vehicleService.getAvailableVehicles().stream().allMatch(v -> v.isAvailable()));
	}

}

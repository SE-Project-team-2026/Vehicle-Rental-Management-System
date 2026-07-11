package presentation;

import domain.Vehicle;
import repository.VehicleRepository;
import service.VehicleService;

public class VehicleView {

	public static void main(String[] args) {
		VehicleRepository vehicleRepository = new VehicleRepository();
		VehicleService vehicleService = new VehicleService(vehicleRepository);
		
		   System.out.println("=== Available Vehicles ===");
		   
		for (Vehicle vehicle : vehicleService.getAvailableVehicles()) {
			System.out.println("ID: " + vehicle.getId() + ", Brand: " + vehicle.getBrand() + ", Model: " + vehicle.getModel() + ", Price: " + vehicle.getPricePerDay() + ", Status: " + vehicle.getStatus());
		}
	}

}

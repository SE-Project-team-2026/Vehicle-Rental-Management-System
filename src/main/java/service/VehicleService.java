package service;

import domain.Vehicle;
import repository.VehicleRepository;
import java.util.List;

public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findAvailable();
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
}
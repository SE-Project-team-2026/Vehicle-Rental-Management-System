package service;

import domain.Vehicle;
import repository.VehicleRepository;
import java.util.List;

/**
 * Service that exposes vehicle catalog operations to the presentation layer.
 */
public class VehicleService {

    /** Repository responsible for managing vehicle data. */
    private final VehicleRepository vehicleRepository;

    /**
     * Creates a new VehicleService backed by the given repository.
     *
     * @param vehicleRepository the repository used to retrieve vehicles
     */
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * @return the list of vehicles currently available for rent (US1.3)
     */
    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findAvailable();
    }

    /**
     * @return the list of all vehicles
     */
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
}
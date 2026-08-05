package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Vehicle;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * In-memory repository for vehicles.
 *
 * <p>On creation, it attempts to load a seed list of vehicles from the
 * {@code /vehicles.json} resource. If loading fails, an empty list is used.</p>
 */
public class VehicleRepository {

    /** Logger for repository errors. */
    private static final Logger LOGGER = Logger.getLogger(VehicleRepository.class.getName());

    /** All vehicles stored in this repository. */
    private List<Vehicle> vehicles;

    /**
     * Creates a repository and loads the seed data from {@code vehicles.json}.
     */
    public VehicleRepository() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = getClass().getResourceAsStream("/vehicles.json");
            this.vehicles = mapper.readValue(inputStream, new TypeReference<List<Vehicle>>() {});
        } catch (Exception e) {
            LOGGER.severe("Failed to load vehicles data: " + e.getMessage());
            this.vehicles = new ArrayList<>();
        }
    }

    /**
     * Adds a vehicle to the repository.
     *
     * @param vehicle the vehicle to add
     */
    public void save(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    /**
     * Finds a vehicle by its identifier.
     *
     * @param id the vehicle identifier
     * @return the matching vehicle, or {@code null} if not found
     */
    public Vehicle findById(int id) {
        for (Vehicle v : vehicles) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    /**
     * @return the list of all vehicles
     */
    public List<Vehicle> findAll() {
        return vehicles;
    }

    /**
     * Replaces the stored vehicle that has the same identifier as the given one.
     *
     * @param vehicle the vehicle with updated data
     */
    public void update(Vehicle vehicle) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getId() == vehicle.getId()) {
                vehicles.set(i, vehicle);
                return;
            }
        }
    }

    /**
     * @return the list of vehicles that are currently available (US1.3)
     */
    public List<Vehicle> findAvailable() {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.isAvailable()) {
                result.add(v);
            }
        }
        return result;
    }
}
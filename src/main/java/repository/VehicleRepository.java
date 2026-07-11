package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Vehicle;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {

    private List<Vehicle> vehicles;

    public VehicleRepository() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = getClass().getResourceAsStream("/vehicles.json");
            this.vehicles = mapper.readValue(inputStream, new TypeReference<List<Vehicle>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            this.vehicles = new ArrayList<>(); 
        }
    }

    public void save(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Vehicle findById(int id) {
        for (Vehicle v : vehicles) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    public List<Vehicle> findAll() {
        return vehicles;
    }

    public void update(Vehicle vehicle) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getId() == vehicle.getId()) {
                vehicles.set(i, vehicle);
                return;
            }
        }
    }

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
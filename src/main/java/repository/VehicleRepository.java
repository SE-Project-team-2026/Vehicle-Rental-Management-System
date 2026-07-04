package repository;

import domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {

    private List<Vehicle> vehicles = new ArrayList<>();

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
            if (v.getStatus() != null && v.getStatus().toString().equals("AVAILABLE")) {
                result.add(v);
            }
        }
        return result;
    }
}
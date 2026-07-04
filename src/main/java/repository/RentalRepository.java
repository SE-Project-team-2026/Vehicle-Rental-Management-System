package repository;

import domain.Rental;

import java.util.ArrayList;
import java.util.List;

public class RentalRepository {

    private List<Rental> rentals = new ArrayList<>();

    public void save(Rental rental) {
        rentals.add(rental);
    }

    public List<Rental> findAll() {
        return rentals;
    }

    public Rental findActiveRentalByVehicleId(int vehicleId) {
        for (Rental r : rentals) {
            if (r.getVehicle() != null
                    && r.getVehicle().getId() == vehicleId
                    && r.isActive()) {
                return r;
            }
        }
        return null;
    }
}
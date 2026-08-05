package repository;

import domain.Rental;

import java.util.ArrayList;
import java.util.List;

/**
 * In-memory repository for rental records.
 */
public class RentalRepository {

    /** All rentals stored in this repository. */
    private List<Rental> rentals = new ArrayList<>();

    /**
     * Adds a rental record to the repository.
     *
     * @param rental the rental to add
     */
    public void save(Rental rental) {
        rentals.add(rental);
    }

    /**
     * @return the list of all rental records
     */
    public List<Rental> findAll() {
        return rentals;
    }

    /**
     * Finds the active rental for a given vehicle, if any.
     *
     * @param vehicleId the vehicle identifier
     * @return the active rental for the vehicle, or {@code null} if none
     */
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
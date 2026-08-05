package strategy;

import domain.Customer;
import domain.Vehicle;

/**
 * Strategy interface for vehicle rental validation rules (Strategy Pattern).
 *
 * <p>Each vehicle type can enforce its own validation rule by implementing
 * this interface (US5.2), e.g. age restrictions, special licenses or battery
 * level checks.</p>
 */
public interface RentalValidationStrategy {

    /**
     * Validates whether the given customer may rent the given vehicle.
     *
     * @param customer the customer attempting to rent
     * @param vehicle  the vehicle being rented
     * @return {@code true} if the rental is allowed, {@code false} otherwise
     */
    boolean validate(Customer customer, Vehicle vehicle);
}
package strategy;

import domain.Customer;
import domain.Motorcycle;
import domain.Vehicle;

/**
 * Validation strategy enforcing motorcycle rental rules (US5.2).
 *
 * <p>A motorcycle can only be rented by a customer whose age is at least the
 * motorcycle's minimum age requirement.</p>
 */
public class MotorcycleValidationStrategy implements RentalValidationStrategy {

    @Override
    public boolean validate(Customer customer, Vehicle vehicle) {
        Motorcycle motorcycle = (Motorcycle) vehicle;
        return customer.getAge() >= motorcycle.getMinimumAge();
    }
}
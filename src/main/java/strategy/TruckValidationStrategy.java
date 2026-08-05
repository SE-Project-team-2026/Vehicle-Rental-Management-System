package strategy;

import domain.Customer;
import domain.Truck;
import domain.Vehicle;

/**
 * Validation strategy enforcing truck rental rules (US5.2).
 *
 * <p>If a truck requires a special license, the customer must hold a driving
 * license that starts with the {@code SP-} prefix in order to rent it.</p>
 */
public class TruckValidationStrategy implements RentalValidationStrategy {

    @Override
    public boolean validate(Customer customer, Vehicle vehicle) {
        Truck truck = (Truck) vehicle;
        if (truck.isRequiresSpecialLicense()) {
            return customer.getDrivingLicense().startsWith("SP-");
        }
        return true;
    }
}
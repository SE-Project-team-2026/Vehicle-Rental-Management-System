package strategy;

import domain.Customer;
import domain.ElectricVehicle;
import domain.Vehicle;

/**
 * Validation strategy enforcing electric vehicle rental rules (US5.2).
 *
 * <p>An electric vehicle can only be rented when its battery level is at
 * least 50%, ensuring the vehicle can complete a rental without charging.</p>
 */
public class ElectricVehicleValidationStrategy implements RentalValidationStrategy {

    @Override
    public boolean validate(Customer customer, Vehicle vehicle) {
        ElectricVehicle ev = (ElectricVehicle) vehicle;
        return ev.getBatteryLevel() >= 50.0;
    }
}
package strategy;

import domain.Customer;
import domain.Vehicle;

public interface RentalValidationStrategy {
    boolean validate(Customer customer, Vehicle vehicle);
}
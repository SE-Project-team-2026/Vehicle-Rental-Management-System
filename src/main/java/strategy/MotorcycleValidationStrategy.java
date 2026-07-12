package strategy;

import domain.Customer;
import domain.Motorcycle;
import domain.Vehicle;

public class MotorcycleValidationStrategy implements RentalValidationStrategy {
    
    @Override
    public boolean validate(Customer customer, Vehicle vehicle) {
        if (!(vehicle instanceof Motorcycle)) {
            return true;
        }
        
        Motorcycle motorcycle = (Motorcycle) vehicle;
        return customer.getAge() >= motorcycle.getMinimumAge();
    }
}
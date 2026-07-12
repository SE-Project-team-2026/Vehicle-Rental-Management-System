package strategy; 

import domain.Customer;
import domain.Truck;
import domain.Vehicle;
import strategy.RentalValidationStrategy; 

public class TruckValidationStrategy implements RentalValidationStrategy {  

    @Override
    public boolean validate(Customer customer, Vehicle vehicle) {
        if (!(vehicle instanceof Truck)) {
            return true;
        }
        
        Truck truck = (Truck) vehicle;
        if (truck.isRequiresSpecialLicense()) {
            return customer.getDrivingLicense().startsWith("SP-");
        }
        return true;
    }
}
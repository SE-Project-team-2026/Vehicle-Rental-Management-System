package strategy;

import domain.Customer;
import domain.ElectricVehicle;
import domain.Vehicle;

public class ElectricVehicleValidationStrategy implements RentalValidationStrategy {

	@Override
	public boolean validate(Customer customer, Vehicle vehicle) {
	    if (!(vehicle instanceof ElectricVehicle)) {
	        return true;
	    }
	    
	    ElectricVehicle ev = (ElectricVehicle) vehicle;
	    return ev.getBatteryLevel() >= 50.0;
	}
	}


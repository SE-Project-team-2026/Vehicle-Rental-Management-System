package domain;

import enums.VehicleStatus;

/**
 * Represents a Motorcycle vehicle in the rental system.
 * Extends the base Vehicle class with motorcycle-specific attributes.
 */
public class Motorcycle extends Vehicle {
    
    private int engineCapacity;  // سعة المحرك 
    private int minimumAge;    
    
    /**
     * Constructs a new Motorcycle with all specified attributes.
     *
     * @param id             the unique identifier
     * @param brand          the brand of the motorcycle
     * @param model          the model of the motorcycle
     * @param dailyRate      the daily rental rate
     * @param status         the current status
     * @param engineCapacity the engine capacity in 
     * @param minimumAge     the minimum age requirement
     */
    public Motorcycle(int id, String brand, String model, double dailyRate, 
                      VehicleStatus status, int engineCapacity, int minimumAge) {
        super(id, brand, model, dailyRate, status);
        this.engineCapacity = engineCapacity;
        this.minimumAge = minimumAge;
    }
    
    @Override
    public String getVehicleType() {
        return "Motorcycle";
    }
    
    // Getters
    public int getEngineCapacity() {
        return engineCapacity;
    }
    
    public int getMinimumAge() {
        return minimumAge;
    }
    
    // Setters
    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
    
    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }
    
    @Override
    public String toString() {
        return "Motorcycle{" +
                "id=" + getId() +
                ", brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", dailyRate=" + getPricePerDay() +
                ", status=" + getStatus() +
                ", engineCapacity=" + engineCapacity +
                ", minimumAge=" + minimumAge +
                '}';
    }
}
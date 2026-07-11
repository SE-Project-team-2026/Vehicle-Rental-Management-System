package domain;

import enums.VehicleStatus;

/**
 * Represents a Truck vehicle in the rental system.
 * Extends the base Vehicle class with truck-specific attributes.
 * Trucks require special license validation for rental.
 */
public class Truck extends Vehicle {
    
    private double maxLoadCapacity;     // أقصى حمولة (طن)
    private boolean requiresSpecialLicense;  // يحتاج رخصة خاصة؟
    private int numberOfAxles;          // عدد المحاور
    
    /**
     * Constructs a new Truck with all specified attributes.
     *
     * @param id                     the unique identifier
     * @param brand                  the brand of the truck
     * @param model                  the model of the truck
     * @param dailyRate              the daily rental rate
     * @param status                 the current status
     * @param maxLoadCapacity        the maximum load capacity in tons
     * @param requiresSpecialLicense whether a special license is required
     * @param numberOfAxles          the number of axles
     */
    public Truck(int id, String brand, String model, double dailyRate, 
                 VehicleStatus status, double maxLoadCapacity, 
                 boolean requiresSpecialLicense, int numberOfAxles) {
        super(id, brand, model, dailyRate, status);
        this.maxLoadCapacity = maxLoadCapacity;
        this.requiresSpecialLicense = requiresSpecialLicense;
        this.numberOfAxles = numberOfAxles;
    }
    
    @Override
    public String getVehicleType() {
        return "Truck";
    }
    
    // Getters
    public double getMaxLoadCapacity() {
        return maxLoadCapacity;
    }
    
    public boolean isRequiresSpecialLicense() {
        return requiresSpecialLicense;
    }
    
    public int getNumberOfAxles() {
        return numberOfAxles;
    }
    
    // Setters
    public void setMaxLoadCapacity(double maxLoadCapacity) {
        this.maxLoadCapacity = maxLoadCapacity;
    }
    
    public void setRequiresSpecialLicense(boolean requiresSpecialLicense) {
        this.requiresSpecialLicense = requiresSpecialLicense;
    }
    
    public void setNumberOfAxles(int numberOfAxles) {
        this.numberOfAxles = numberOfAxles;
    }
    
    @Override
    public String toString() {
        return "Truck{" +
                "id=" + getId() +
                ", brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", dailyRate=" + getPricePerDay() +
                ", status=" + getStatus() +
                ", maxLoadCapacity=" + maxLoadCapacity +
                ", requiresSpecialLicense=" + requiresSpecialLicense +
                ", numberOfAxles=" + numberOfAxles +
                '}';
    }
}
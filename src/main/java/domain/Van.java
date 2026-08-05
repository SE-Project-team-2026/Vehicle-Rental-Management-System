package domain;

import enums.VehicleStatus;

/**
 * Represents a Van vehicle in the rental system.
 * Extends the base Vehicle class with van-specific attributes.
 */
public class Van extends Vehicle {
    
    /** Cargo capacity in litres. */
    private int cargoCapacity;       // سعة الشحن (لتر)
    
    /** Passenger capacity. */
    private int passengerCapacity;   // سعة الركاب
    
    /** Whether the van has a sliding door. */
    private boolean hasSlidingDoor;  // باب منزلق
    
    /**
     * Constructs a new Van with all specified attributes.
     *
     * @param id                the unique identifier
     * @param brand             the brand of the van
     * @param model             the model of the van
     * @param dailyRate         the daily rental rate
     * @param status            the current status
     * @param cargoCapacity     the cargo capacity in liters
     * @param passengerCapacity the passenger capacity
     * @param hasSlidingDoor    whether the van has a sliding door
     */
    public Van(int id, String brand, String model, double dailyRate, 
               VehicleStatus status, int cargoCapacity, int passengerCapacity, 
               boolean hasSlidingDoor) {
        super(id, brand, model, dailyRate, status);
        this.cargoCapacity = cargoCapacity;
        this.passengerCapacity = passengerCapacity;
        this.hasSlidingDoor = hasSlidingDoor;
    }
    
    @Override
    public String getVehicleType() {
        return "Van";
    }
    
    // Getters
    /**
     * @return the cargo capacity in litres
     */
    public int getCargoCapacity() {
        return cargoCapacity;
    }
    
    /**
     * @return the passenger capacity
     */
    public int getPassengerCapacity() {
        return passengerCapacity;
    }
    
    /**
     * @return {@code true} if the van has a sliding door
     */
    public boolean isHasSlidingDoor() {
        return hasSlidingDoor;
    }
    
    // Setters
    /**
     * Sets the cargo capacity.
     *
     * @param cargoCapacity the cargo capacity to set
     */
    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }
    
    /**
     * Sets the passenger capacity.
     *
     * @param passengerCapacity the passenger capacity to set
     */
    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
    
    /**
     * Sets whether the van has a sliding door.
     *
     * @param hasSlidingDoor {@code true} if the van has a sliding door
     */
    public void setHasSlidingDoor(boolean hasSlidingDoor) {
        this.hasSlidingDoor = hasSlidingDoor;
    }
    
    @Override
    public String toString() {
        return "Van{" +
                "id=" + getId() +
                ", brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", dailyRate=" + getPricePerDay() +
                ", status=" + getStatus() +
                ", cargoCapacity=" + cargoCapacity +
                ", passengerCapacity=" + passengerCapacity +
                ", hasSlidingDoor=" + hasSlidingDoor +
                '}';
    }
}
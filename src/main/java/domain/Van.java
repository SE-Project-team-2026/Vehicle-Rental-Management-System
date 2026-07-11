package domain;

import enums.VehicleStatus;

/**
 * Represents a Van vehicle in the rental system.
 * Extends the base Vehicle class with van-specific attributes.
 */
public class Van extends Vehicle {
    
    private int cargoCapacity;       // سعة الشحن (لتر)
    private int passengerCapacity;   // سعة الركاب
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
    public int getCargoCapacity() {
        return cargoCapacity;
    }
    
    public int getPassengerCapacity() {
        return passengerCapacity;
    }
    
    public boolean isHasSlidingDoor() {
        return hasSlidingDoor;
    }
    
    // Setters
    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }
    
    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
    
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
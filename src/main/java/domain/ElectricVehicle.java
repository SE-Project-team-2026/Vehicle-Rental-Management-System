package domain;

import enums.VehicleStatus;

/**
 * Represents an Electric Vehicle in the rental system.
 * Extends the base Vehicle class with electric-specific attributes.
 * Requires battery level checks before rental (US5.2).
 */
public class ElectricVehicle extends Vehicle {
    
    private double batteryLevel;   // نسبة الشحن المئوية (0.0 - 100.0)
    private int range;             // المدى بالكيلومترات
    private double chargingTime;   // وقت الشحن الكامل بالساعات
    
    /**
     * Constructs a new ElectricVehicle with all specified attributes.
     *
     * @param id            the unique identifier
     * @param brand         the brand of the vehicle
     * @param model         the model of the vehicle
     * @param dailyRate     the daily rental rate
     * @param status        the current status
     * @param batteryLevel  the current battery level percentage
     * @param range         the driving range in kilometers
     * @param chargingTime  the time required for a full charge in hours
     */
    public ElectricVehicle(int id, String brand, String model, double dailyRate, 
                           VehicleStatus status, double batteryLevel, int range, 
                           double chargingTime) {
        super(id, brand, model, dailyRate, status);
        this.batteryLevel = batteryLevel;
        this.range = range;
        this.chargingTime = chargingTime;
    }
    
    @Override
    public String getVehicleType() {
        return "ElectricVehicle";
    }
    
    // Getters
    public double getBatteryLevel() { return batteryLevel; }
    public int getRange() { return range; }
    public double getChargingTime() { return chargingTime; }
    
    // Setters
    public void setBatteryLevel(double batteryLevel) { this.batteryLevel = batteryLevel; }
    public void setRange(int range) { this.range = range; }
    public void setChargingTime(double chargingTime) { this.chargingTime = chargingTime; }
    
    @Override
    public String toString() {
        return "ElectricVehicle{" +
                "id=" + getId() +
                ", brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", dailyRate=" + getPricePerDay() +
                ", status=" + getStatus() +
                ", batteryLevel=" + batteryLevel + "%" +
                ", range=" + range + "km" +
                ", chargingTime=" + chargingTime + "h" +
                '}';
    }
}
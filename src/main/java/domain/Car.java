package domain;

import enums.VehicleStatus;

/**
 * Represents a Car vehicle in the rental system.
 * Extends the base Vehicle class with car-specific attributes.
 */
public class Car extends Vehicle {
    
    private String licensePlate;    // رقم اللوحة
    private int year;               // سنة الصنع
    private String color;           // اللون
    private String fuelType;        // نوع الوقود
    private String transmission;    // ناقل الحركة
    
    /**
     * Constructs a new Car with all specified attributes.
     *
     * @param id           the unique identifier of the vehicle
     * @param brand        the brand of the car
     * @param model        the model of the car
     * @param pricePerDay    the daily rental rate
     * @param status       the current status of the vehicle
     * @param licensePlate the license plate number
     * @param year         the manufacturing year
     * @param color        the color of the car
     * @param fuelType     the fuel type (e.g., Petrol, Diesel, Hybrid)
     * @param transmission the transmission type (e.g., Automatic, Manual)
     */
    public Car(int id, String brand, String model, double dailyRate, 
               VehicleStatus status, String licensePlate, int year, 
               String color, String fuelType, String transmission) {
        super(id, brand, model, dailyRate, status);
        this.licensePlate = licensePlate;
        this.year = year;
        this.color = color;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.setStatus(status); // Ensure the status is set correctly
        
    }
    
    @Override
    public String getVehicleType() {
        return "Car";
    }
  
    // Getters
    public String getLicensePlate() {
        return licensePlate;
    }
    
    
    public int getYear() {
        return year;
    }
    
    public String getColor() {
        return color;
    }
    
    public String getFuelType() {
        return fuelType;
    }
    
    public String getTransmission() {
        return transmission;
    }
    
    // Setters
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
   
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
    
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

	
}
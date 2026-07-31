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
    private Car(CarBuilder builder) {
      super(builder.id, builder.brand, builder.model, builder.dailyRate, builder.status);
        this.licensePlate = builder.licensePlate;
        this.year = builder.year;
        this.color = builder.color;
        this.fuelType =builder.fuelType;
        this.transmission = builder.transmission;        
    }
    
    public static class CarBuilder {
		private int id;
		private String brand;
		private String model;
		private double dailyRate;
		private VehicleStatus status;
		private String licensePlate;
		private int year;
		private String color;
		private String fuelType;
		private String transmission;
    public CarBuilder(int id, String brand, String model, double dailyRate, VehicleStatus status) {
	this.id = id;
	this.brand = brand;
	this.model = model;
	this.dailyRate = dailyRate;
	this.status = status;
		}

		public CarBuilder setLicensePlate(String licensePlate) {
			this.licensePlate = licensePlate;
			return this;
		}

		public CarBuilder setYear(int year) {
			this.year = year;
			return this;
		}

		public CarBuilder setColor(String color) {
			this.color = color;
			return this;
		}

		public CarBuilder setFuelType(String fuelType) {
			this.fuelType = fuelType;
			return this;
		}

		public CarBuilder setTransmission(String transmission) {
			this.transmission = transmission;
			return this;
		}

		public Car build() {
			return new Car(this);
		}
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
    
	
}
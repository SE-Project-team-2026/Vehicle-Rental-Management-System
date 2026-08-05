package domain;

import enums.VehicleStatus;

/**
 * Base class representing any vehicle that can be rented.
 *
 * <p>Concrete vehicle types (e.g. {@link Car}, {@link Motorcycle},
 * {@link Truck}, {@link Van}, {@link ElectricVehicle}) extend this class and
 * provide their own {@link #getVehicleType()} implementation, enabling
 * polymorphic handling of vehicles throughout the system.</p>
 */
public abstract class Vehicle {

    /** Unique identifier of the vehicle. */
    private int id;

    /** Brand or manufacturer of the vehicle. */
    private String brand;

    /** Model name of the vehicle. */
    private String model;

    /** Daily rental rate of the vehicle. */
    private double pricePerDay;

    /** Current rental status of the vehicle. */
    private VehicleStatus status;

    /**
     * Default constructor required by the JSON mapper.
     */
    

    /**
     * Creates a new vehicle.
     *
     * @param id          unique identifier of the vehicle
     * @param brand       brand of the vehicle
     * @param model       model of the vehicle
     * @param pricePerDay daily rental rate
     * @param status      current status of the vehicle
     */
    public Vehicle(int id, String brand, String model, double pricePerDay, VehicleStatus status) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.status = status;
    }

    /**
     * @return the unique identifier of the vehicle
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the vehicle.
     *
     * @param id the identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the brand of the vehicle
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand of the vehicle.
     *
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return the model of the vehicle
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model of the vehicle.
     *
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the daily rental rate of the vehicle
     */
    public double getPricePerDay() {
        return pricePerDay;
    }

    /**
     * Sets the daily rental rate of the vehicle.
     *
     * @param pricePerDay the daily rate to set
     */
    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    /**
     * @return the current status of the vehicle
     */
    public VehicleStatus getStatus() {
        return status;
    }

    /**
     * Sets the current status of the vehicle.
     *
     * @param status the status to set
     */
    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    /**
     * @return {@code true} if the vehicle is currently available for rent
     */
    public boolean isAvailable() {
        return this.status == VehicleStatus.AVAILABLE;
    }

    /**
     * @return the type of the vehicle (e.g. "Car", "Truck")
     */
    public abstract String getVehicleType();
}
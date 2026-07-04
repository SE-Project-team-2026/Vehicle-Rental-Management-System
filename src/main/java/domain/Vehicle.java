package domain;

import enums.VehicleStatus;

public class Vehicle {

    private int id;
    private String brand;
    private String model;
    private double pricePerDay;
    private VehicleStatus status;

    public Vehicle() {
    }

    public Vehicle(int id, String brand, String model, double pricePerDay, VehicleStatus status) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }
}
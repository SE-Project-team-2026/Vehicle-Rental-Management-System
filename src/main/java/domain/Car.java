package domain;

import enums.VehicleStatus;

/**
 * Represents a Car vehicle in the rental system (US5.1).
 *
 * <p>Extends the base {@link Vehicle} class with car-specific attributes and
 * is constructed through the {@link CarBuilder}.</p>
 */
public class Car extends Vehicle {

    /** License plate number. */
    private String licensePlate;

    /** Manufacturing year. */
    private int year;

    /** Color of the car. */
    private String color;

    /** Fuel type (e.g. Petrol, Diesel, Hybrid). */
    private String fuelType;

    /** Transmission type (e.g. Automatic, Manual). */
    private String transmission;

    /**
     * Creates a car from the values held by the builder.
     *
     * @param builder the builder containing the car data
     */
    private Car(CarBuilder builder) {
        super(builder.id, builder.brand, builder.model, builder.dailyRate, builder.status);
        this.licensePlate = builder.licensePlate;
        this.year = builder.year;
        this.color = builder.color;
        this.fuelType = builder.fuelType;
        this.transmission = builder.transmission;
    }

    /**
     * Builder for {@link Car} instances.
     */
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

        /**
         * Creates a builder with the required fields.
         *
         * @param id        unique vehicle identifier
         * @param brand     car brand
         * @param model     car model
         * @param dailyRate daily rental rate
         * @param status    initial vehicle status
         */
        public CarBuilder(int id, String brand, String model, double dailyRate, VehicleStatus status) {
            this.id = id;
            this.brand = brand;
            this.model = model;
            this.dailyRate = dailyRate;
            this.status = status;
        }

        /**
         * Sets the license plate number.
         *
         * @param licensePlate the license plate to set
         * @return this builder
         */
        public CarBuilder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        /**
         * Sets the manufacturing year.
         *
         * @param year the year to set
         * @return this builder
         */
        public CarBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        /**
         * Sets the color.
         *
         * @param color the color to set
         * @return this builder
         */
        public CarBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        /**
         * Sets the fuel type.
         *
         * @param fuelType the fuel type to set
         * @return this builder
         */
        public CarBuilder setFuelType(String fuelType) {
            this.fuelType = fuelType;
            return this;
        }

        /**
         * Sets the transmission type.
         *
         * @param transmission the transmission to set
         * @return this builder
         */
        public CarBuilder setTransmission(String transmission) {
            this.transmission = transmission;
            return this;
        }

        /**
         * Builds the final {@link Car} instance.
         *
         * @return the constructed car
         */
        public Car build() {
            return new Car(this);
        }
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }

    /**
     * @return the license plate number
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * @return the manufacturing year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @return the fuel type
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * @return the transmission type
     */
    public String getTransmission() {
        return transmission;
    }
}
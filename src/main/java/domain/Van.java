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
     * Private constructor used by the Builder.
     *
     * @param builder the builder containing the van data
     */
    private Van(VanBuilder builder) {
        super(builder.id, builder.brand, builder.model, builder.dailyRate, builder.status);
        this.cargoCapacity = builder.cargoCapacity;
        this.passengerCapacity = builder.passengerCapacity;
        this.hasSlidingDoor = builder.hasSlidingDoor;
    }
    
    /**
     * Builder for {@link Van} instances.
     */
    public static class VanBuilder {
        private int id;
        private String brand;
        private String model;
        private double dailyRate;
        private VehicleStatus status;
        private int cargoCapacity;
        private int passengerCapacity;
        private boolean hasSlidingDoor;

        /**
         * Creates a builder with the required fields.
         *
         * @param id        unique vehicle identifier
         * @param brand     vehicle brand
         * @param model     vehicle model
         * @param dailyRate daily rental rate
         * @param status    initial vehicle status
         */
        public VanBuilder(int id, String brand, String model, double dailyRate, VehicleStatus status) {
            this.id = id;
            this.brand = brand;
            this.model = model;
            this.dailyRate = dailyRate;
            this.status = status;
        }

        public VanBuilder setCargoCapacity(int cargoCapacity) {
            this.cargoCapacity = cargoCapacity;
            return this;
        }

        public VanBuilder setPassengerCapacity(int passengerCapacity) {
            this.passengerCapacity = passengerCapacity;
            return this;
        }

        public VanBuilder setHasSlidingDoor(boolean hasSlidingDoor) {
            this.hasSlidingDoor = hasSlidingDoor;
            return this;
        }

        public Van build() {
            return new Van(this);
        }
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

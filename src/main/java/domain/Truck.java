package domain;

import enums.VehicleStatus;

/**
 * Represents a Truck vehicle in the rental system.
 * Extends the base Vehicle class with truck-specific attributes.
 * Trucks require special license validation for rental.
 */
public class Truck extends Vehicle {
    
    /** Maximum load capacity in tons. */
    private double maxLoadCapacity;     // أقصى حمولة (طن)
    
    /** Whether the truck requires a special license. */
    private boolean requiresSpecialLicense;  // يحتاج رخصة خاصة؟
    
    /** Number of axles. */
    private int numberOfAxles;          // عدد المحاور
    
    /**
     * Private constructor used by the Builder.
     *
     * @param builder the builder containing the truck data
     */
    private Truck(TruckBuilder builder) {
        super(builder.id, builder.brand, builder.model, builder.dailyRate, builder.status);
        this.maxLoadCapacity = builder.maxLoadCapacity;
        this.requiresSpecialLicense = builder.requiresSpecialLicense;
        this.numberOfAxles = builder.numberOfAxles;
    }
    
    /**
     * Builder for {@link Truck} instances.
     */
    public static class TruckBuilder {
        private int id;
        private String brand;
        private String model;
        private double dailyRate;
        private VehicleStatus status;
        private double maxLoadCapacity;
        private boolean requiresSpecialLicense;
        private int numberOfAxles;

        /**
         * Creates a builder with the required fields.
         *
         * @param id        unique vehicle identifier
         * @param brand     vehicle brand
         * @param model     vehicle model
         * @param dailyRate daily rental rate
         * @param status    initial vehicle status
         */
        public TruckBuilder(int id, String brand, String model, double dailyRate, VehicleStatus status) {
            this.id = id;
            this.brand = brand;
            this.model = model;
            this.dailyRate = dailyRate;
            this.status = status;
        }

        public TruckBuilder setMaxLoadCapacity(double maxLoadCapacity) {
            this.maxLoadCapacity = maxLoadCapacity;
            return this;
        }

        public TruckBuilder setRequiresSpecialLicense(boolean requiresSpecialLicense) {
            this.requiresSpecialLicense = requiresSpecialLicense;
            return this;
        }

        public TruckBuilder setNumberOfAxles(int numberOfAxles) {
            this.numberOfAxles = numberOfAxles;
            return this;
        }

        public Truck build() {
            return new Truck(this);
        }
    }
    
    @Override
    public String getVehicleType() {
        return "Truck";
    }
    
    // Getters
    /**
     * @return the maximum load capacity in tons
     */
    public double getMaxLoadCapacity() {
        return maxLoadCapacity;
    }
    
    /**
     * @return {@code true} if the truck requires a special license
     */
    public boolean isRequiresSpecialLicense() {
        return requiresSpecialLicense;
    }
    
    /**
     * @return the number of axles
     */
    public int getNumberOfAxles() {
        return numberOfAxles;
    }
    
    // Setters
    /**
     * Sets the maximum load capacity.
     *
     * @param maxLoadCapacity the load capacity to set
     */
    public void setMaxLoadCapacity(double maxLoadCapacity) {
        this.maxLoadCapacity = maxLoadCapacity;
    }
    
    /**
     * Sets whether the truck requires a special license.
     *
     * @param requiresSpecialLicense {@code true} if a special license is required
     */
    public void setRequiresSpecialLicense(boolean requiresSpecialLicense) {
        this.requiresSpecialLicense = requiresSpecialLicense;
    }
    
    /**
     * Sets the number of axles.
     *
     * @param numberOfAxles the number of axles to set
     */
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

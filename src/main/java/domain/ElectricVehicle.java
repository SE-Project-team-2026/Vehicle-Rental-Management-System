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
     * @param builder the electric vehicle builder
     */
    private ElectricVehicle(ElectricVehicleBuilder builder) {
        super(builder.id, builder.brand, builder.model, builder.dailyRate, builder.status);
        this.batteryLevel = builder.batteryLevel;
        this.range = builder.range;
        this.chargingTime = builder.chargingTime;
    }
    
    public static class ElectricVehicleBuilder {

        private int id;
        private String brand;
        private String model;
        private double dailyRate;
        private VehicleStatus status;
        private double batteryLevel;
        private int range;
        private double chargingTime;

        /**
         * Creates a builder with the required fields.
         *
         * @param id        unique vehicle identifier
         * @param brand     vehicle brand
         * @param model     vehicle model
         * @param dailyRate daily rental rate
         * @param status    initial vehicle status
         */
        public ElectricVehicleBuilder(int id, String brand, String model, double dailyRate, VehicleStatus status) {
            this.id = id;
            this.brand = brand;
            this.model = model;
            this.dailyRate = dailyRate;
            this.status = status;
        }

        /**
         * Sets the battery level percentage.
         *
         * @param batteryLevel the battery level to set
         * @return this builder
         */
        public ElectricVehicleBuilder setBatteryLevel(double batteryLevel) {
            this.batteryLevel = batteryLevel;
            return this;
        }

        /**
         * Sets the driving range.
         *
         * @param range the range to set
         * @return this builder
         */
        public ElectricVehicleBuilder setRange(int range) {
            this.range = range;
            return this;
        }

        /**
         * Sets the charging time.
         *
         * @param chargingTime the charging time to set
         * @return this builder
         */
        public ElectricVehicleBuilder setChargingTime(double chargingTime) {
            this.chargingTime = chargingTime;
            return this;
        }

        /**
         * Builds the final {@link ElectricVehicle} instance.
         *
         * @return the constructed electric vehicle
         */
        public ElectricVehicle build() {
            return new ElectricVehicle(this);
        }
    }
    @Override
    public String getVehicleType() {
        return "ElectricVehicle";
    }
    
    // Getters
    /**
     * @return the current battery level percentage (0.0 - 100.0)
     */
    public double getBatteryLevel() { return batteryLevel; }

    /**
     * @return the driving range in kilometres
     */
    public int getRange() { return range; }

    /**
     * @return the charging time in hours
     */
    public double getChargingTime() { return chargingTime; }
    
    // Setters
    /**
     * Sets the battery level percentage.
     *
     * @param batteryLevel the battery level to set
     */
    public void setBatteryLevel(double batteryLevel) { this.batteryLevel = batteryLevel; }

    /**
     * Sets the driving range.
     *
     * @param range the range to set
     */
    public void setRange(int range) { this.range = range; }

    /**
     * Sets the charging time.
     *
     * @param chargingTime the charging time to set
     */
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

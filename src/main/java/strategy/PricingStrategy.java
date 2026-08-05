package strategy;

/**
 * Strategy interface for calculating rental pricing (Strategy Pattern).
 *
 * <p>Each vehicle type can provide its own pricing formula by implementing
 * this interface (US4.2).</p>
 */
public interface PricingStrategy {

    /**
     * Calculates the total price of a rental.
     *
     * @param dailyRate    the daily rental rate of the vehicle
     * @param numberOfDays the number of rental days
     * @return the total rental price
     */
    double calculatePrice(double dailyRate, int numberOfDays);
}
package strategy;


/**
 * Pricing strategy for motorcycles (Strategy Pattern).
 *
 * <p>Computes the rental cost for a motorcycle as the product of the daily
 * rate and the number of rental days (US4.2).</p>
 */
public class MotorcyclePricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(double dailyRate, int numberOfDays) {
        return dailyRate * numberOfDays;
    }
}
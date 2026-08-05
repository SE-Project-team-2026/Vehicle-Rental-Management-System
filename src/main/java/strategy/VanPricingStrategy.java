package strategy;


/**
 * Pricing strategy for vans (Strategy Pattern).
 *
 * <p>Computes the rental cost for a van as the product of the daily rate and
 * the number of rental days (US4.2).</p>
 */
public class VanPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(double dailyRate, int numberOfDays) {
        return dailyRate * numberOfDays;
    }
}

package strategy;


public class VanPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(double dailyRate, int numberOfDays) {
        return dailyRate * numberOfDays;
    }
}

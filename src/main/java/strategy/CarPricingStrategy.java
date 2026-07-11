package strategy;


public class CarPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(double dailyRate, int numberOfDays) {
        return dailyRate * numberOfDays;
    }
}
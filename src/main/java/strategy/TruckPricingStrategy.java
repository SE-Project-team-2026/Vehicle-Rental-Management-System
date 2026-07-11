package strategy;


public class TruckPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(double dailyRate, int numberOfDays) {
        return dailyRate * numberOfDays;
    }
}
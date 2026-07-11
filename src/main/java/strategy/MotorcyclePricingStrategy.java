package strategy;


public class MotorcyclePricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(double dailyRate, int numberOfDays) {
        return dailyRate * numberOfDays;
    }
}
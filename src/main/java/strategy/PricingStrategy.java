package strategy;

public interface PricingStrategy {

    double calculatePrice(double dailyRate, int numberOfDays);
}
package service;
import strategy.PricingStrategy;


public class BillingService {

    private PricingStrategy pricingStrategy;

   
    public BillingService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

   
    public double calculateRentalCost(double dailyRate, int numberOfDays) {
        return pricingStrategy.calculatePrice(dailyRate, numberOfDays);
    }
}
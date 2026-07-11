package service;

import domain.Rental;
import strategy.PricingStrategy;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class BillingService {

    private PricingStrategy pricingStrategy;


    public BillingService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }


    // Existing method - keep it for compatibility
    public double calculateRentalCost(double dailyRate, int numberOfDays) {

        return pricingStrategy.calculatePrice(
                dailyRate,
                numberOfDays
        );
    }


    // Sprint 4 - Calculate cost based on Rental
    public double calculateRentalCost(Rental rental) {

        long days = ChronoUnit.DAYS.between(
                rental.getStartDate(),
                rental.getEndDate()
        );


        return pricingStrategy.calculatePrice(
                rental.getVehicle().getPricePerDay(),
                (int) days
        );
    }
    public double calculateLatePenalty(
            Rental rental,
            LocalDate actualReturnDate,
            double penaltyPerDay) {


        long lateDays = ChronoUnit.DAYS.between(
                rental.getEndDate(),
                actualReturnDate
        );


        if (lateDays <= 0) {
            return 0;
        }


        return lateDays * penaltyPerDay;
    }
}
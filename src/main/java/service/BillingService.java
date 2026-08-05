package service;

import domain.Rental;
import strategy.PricingStrategy;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


/**
 * Service responsible for computing rental billing information (US4).
 *
 * <p>Uses the {@link PricingStrategy} to calculate rental costs and computes
 * late return penalties based on the difference between the expected and
 * actual return dates (US4.2, US4.3).</p>
 */
public class BillingService {

    /** Strategy used to calculate the rental price. */
    private PricingStrategy pricingStrategy;

    /**
     * Creates a new BillingService with the given pricing strategy.
     *
     * @param pricingStrategy the strategy used to compute rental costs
     */
    public BillingService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    /**
     * Calculates the total cost of a rental based on its duration (US4.2).
     *
     * @param rental the rental to be billed
     * @return the total rental cost
     */
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

    /**
     * Calculates the penalty for a late vehicle return (US4.3).
     *
     * @param rental          the rental record
     * @param actualReturnDate the actual date the vehicle was returned
     * @param penaltyPerDay    the penalty charged per late day
     * @return the total late penalty, or {@code 0} if not late
     */
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
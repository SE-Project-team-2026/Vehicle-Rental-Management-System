package domain;

import java.time.LocalDate;

/**
 * Represents a single rental transaction in the system.
 *
 * <p>A rental links a {@link Customer} to a {@link Vehicle} for a specific
 * period of time. Once a vehicle is returned, the rental is closed and the
 * vehicle becomes available again.</p>
 */
public class Rental {

    /** Unique identifier for this rental. */
    private int rentalId;

    /** The customer who rented the vehicle. */
    private Customer customer;

    /** The vehicle that was rented. */
    private Vehicle vehicle;

    /** The date on which the rental period starts. */
    private LocalDate startDate;

    /** The date on which the rental period ends. */
    private LocalDate endDate;

    /** The actual date the vehicle was returned, if any. */
    private LocalDate returnDate;

    /** Whether the rental is still active (vehicle not yet returned). */
    private boolean active;

    /**
     * Default constructor required by the JSON mapper.
     */
    public Rental() {
    }

    /**
     * Creates a new active rental.
     *
     * @param rentalId  unique identifier of the rental
     * @param customer  the customer making the rental
     * @param vehicle   the vehicle being rented
     * @param startDate the start date of the rental period
     * @param endDate   the end date of the rental period
     */
    public Rental(int rentalId, Customer customer, Vehicle vehicle,
                  LocalDate startDate, LocalDate endDate) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = true;
    }

    /**
     * @return the unique identifier of this rental
     */
    public int getRentalId() {
        return rentalId;
    }

    /**
     * Sets the unique identifier of this rental.
     *
     * @param rentalId the rental identifier to set
     */
    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    /**
     * @return the customer who made this rental
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer of this rental.
     *
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the vehicle associated with this rental
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Sets the vehicle of this rental.
     *
     * @param vehicle the vehicle to set
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * @return the start date of the rental period
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the rental period.
     *
     * @param startDate the start date to set
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the end date of the rental period
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the rental period.
     *
     * @param endDate the end date to set
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the actual return date, or {@code null} if not yet returned
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the actual return date of the vehicle.
     *
     * @param returnDate the return date to set
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * @return {@code true} if the rental is still active, {@code false} otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Closes the rental, marking it as no longer active.
     */
    public void closeRental() {
        this.active = false;
    }
}
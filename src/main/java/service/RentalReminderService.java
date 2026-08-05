package service;

import java.time.LocalDate;

import domain.Rental;
import observer.Subject;
import repository.RentalRepository;

/**
 * Service that sends reminders for rentals expiring soon (US3.1).
 *
 * <p>Checks all active rentals and, for those ending tomorrow, notifies the
 * registered notification subject so that email/SMS observers can deliver a
 * reminder.</p>
 */
public class RentalReminderService {

    /** Repository used to retrieve all rental records. */
    private RentalRepository rentalRepository;

    /** Subject that delivers reminder messages to notification observers. */
    private Subject notificationSubject;

    /**
     * Creates a new reminder service.
     *
     * @param rentalRepository    repository used to retrieve rentals
     * @param notificationSubject subject used to broadcast reminders
     */
    public RentalReminderService(RentalRepository rentalRepository, Subject notificationSubject) {
        this.rentalRepository = rentalRepository;
        this.notificationSubject = notificationSubject;
    }

    /**
     * Sends a reminder for every active rental that expires tomorrow.
     *
     * @param today the reference date used to determine due reminders
     */
    public void checkAndSendReminders(LocalDate today) {
        for (Rental rental : rentalRepository.findAll()) {
            if (rental.isActive() && rental.getEndDate().isEqual(today.plusDays(1))) {
                String message = "Reminder: Your rental for vehicle " + rental.getVehicle().getId()
                        + " is due tomorrow (" + rental.getEndDate() + ").";
                notificationSubject.notifyObservers(message);
            }
        }
    }
}
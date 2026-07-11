package service;

import java.time.LocalDate;

import domain.Rental;
import observer.Subject; 
import repository.RentalRepository;

public class RentalReminderService {
    private RentalRepository rentalRepository;
    private Subject notificationSubject; 
    
    public RentalReminderService(RentalRepository rentalRepository, Subject notificationSubject) {
        this.rentalRepository = rentalRepository;
        this.notificationSubject = notificationSubject;
    }
    
    public void checkAndSendReminders(LocalDate today) {
        for (Rental rental : rentalRepository.findAll()) {
            if (rental.isActive() && rental.getEndDate().isEqual(today.plusDays(1))) {
                String message = "Reminder: Your rental for vehicle " + rental.getVehicle().getId() + " is due tomorrow (" + rental.getEndDate() + ").";
                notificationSubject.notifyObservers(message);
            }
        }
    }
}
package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.Customer;
import domain.Rental;
import domain.Vehicle;
import enums.VehicleStatus;
import observer.Subject;
import repository.RentalRepository;

@ExtendWith(MockitoExtension.class)

class RentalReminderServiceTest {
	
	  @Mock
	    private RentalRepository mockRentalRepository;

	    @Mock
	    private Subject mockSubject;

	    private RentalReminderService reminderService;
	    
	@BeforeEach
	void setUp() throws Exception {
		reminderService = new RentalReminderService(mockRentalRepository, mockSubject);
	}

	@Test
	public void testReminderSentWhenRentalExpiresTomorrow() {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);
		
		   Vehicle vehicle = new Vehicle(1, "Toyota", "Corolla", 50.0, VehicleStatus.RENTED);
	        Customer customer = new Customer(1, "Ali", "123", "DL1", 25);
	        Rental activeRental = new Rental(1, customer, vehicle, today.minusDays(2), tomorrow);
	        
	        List<Rental> rentals = Arrays.asList(activeRental);

	        when(mockRentalRepository.findAll()).thenReturn(rentals);
	        reminderService.checkAndSendReminders(today);
	        

	        verify(mockSubject, times(1)).notifyObservers(anyString());
	}
	
 @Test
 void testNoReminderWhenRentalIsNotExpiring() {
	 LocalDate today = LocalDate.now();
	 LocalDate dayAfterTomorrow = today.plusDays(10);
	 
	 Vehicle vehicle = new Vehicle(1, "Toyota", "Corolla", 50.0, VehicleStatus.RENTED);
	 Customer customer = new Customer(1, "Ali", "123", "DL1", 25);
	 Rental activeRental = new Rental(1, customer, vehicle, today.minusDays(2), dayAfterTomorrow);
	 
	 List<Rental> rentals = Arrays.asList(activeRental);

	 when(mockRentalRepository.findAll()).thenReturn(rentals);

	 reminderService.checkAndSendReminders(today);

	 verify(mockSubject, never()).notifyObservers(anyString());
 }
	

}

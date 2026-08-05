package service;

import org.junit.jupiter.api.Test;
import strategy.PricingStrategy;
import strategy.CarPricingStrategy;
import domain.Car;
import domain.Customer;
import domain.Rental;
import domain.Vehicle;
import enums.VehicleStatus;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BillingServiceTest {

    @Test
    void testCalculateRentalCost() {
    	PricingStrategy strategy =
    	        new CarPricingStrategy();

        BillingService billingService =
                new BillingService(strategy);



        Vehicle vehicle =
                new Car.CarBuilder(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE)
                		.setLicensePlate("ABC-1234")
						.setYear(2022)
						.setColor("White")
						.setFuelType("Petrol")
						.setTransmission("Automatic")
						.build();

        Customer customer =
                new Customer(
                        1,
                        "Test User",
                        "123",
                        "DL1",
                        25
                );



        Rental rental = new Rental();

        rental.setVehicle(vehicle);
        rental.setCustomer(customer);

        rental.setStartDate(
                LocalDate.of(2026, 7, 1)
        );

        rental.setEndDate(
                LocalDate.of(2026, 7, 4)
        );

        double total =billingService.calculateRentalCost(rental);

        assertEquals(150.0,total);
    }
    @Test
    void testLateReturnPenalty() {


        PricingStrategy strategy =
                new CarPricingStrategy();


        BillingService billingService =
                new BillingService(strategy);



        Vehicle vehicle =
                new Car.CarBuilder(1, "Toyota", "Corolla", 50.0, VehicleStatus.RENTED)
        .setLicensePlate("ABC-1234")
        .setYear(2022)
        .setColor("White")
        .setFuelType("Petrol")
        .setTransmission("Automatic")
        .build();
        
        Customer customer =
                new Customer(
                        1,
                        "Test User",
                        "123",
                        "DL1",
                        25
                );


        Rental rental = new Rental();

        rental.setVehicle(vehicle);
        rental.setCustomer(customer);


        // Expected return date: 10/7
        rental.setEndDate(
                LocalDate.of(2026, 7, 10)
        );


        // Actual return date: 13/7
        LocalDate actualReturnDate =
                LocalDate.of(2026, 7, 13);



        double penalty =
                billingService.calculateLatePenalty(
                        rental,
                        actualReturnDate,
                        10.0
                );


        // 3 late days * 10 = 30
        assertEquals(
                30.0,
                penalty
        );

    }

    @Test
    void testNoLatePenaltyWhenReturnedOnTime() {
        PricingStrategy strategy = new CarPricingStrategy();
        BillingService billingService = new BillingService(strategy);

        Vehicle vehicle = new Car.CarBuilder(1, "Toyota", "Corolla", 50.0, VehicleStatus.RENTED)
                .setLicensePlate("ABC-1234")
                .setYear(2022)
                .setColor("White")
                .setFuelType("Petrol")
                .setTransmission("Automatic")
                .build();

        Customer customer = new Customer(1, "Test User", "123", "DL1", 25);

        Rental rental = new Rental();
        rental.setVehicle(vehicle);
        rental.setCustomer(customer);

        rental.setEndDate(LocalDate.of(2026, 7, 10));

        // Returned one day early -> no penalty
        LocalDate actualReturnDate = LocalDate.of(2026, 7, 9);

        double penalty = billingService.calculateLatePenalty(rental, actualReturnDate, 10.0);

        assertEquals(0.0, penalty);
    }
}
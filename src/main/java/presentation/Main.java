package presentation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import domain.Customer;
import domain.Rental;
import domain.Car;
import domain.Motorcycle;
import domain.Truck;
import domain.Van;
import domain.ElectricVehicle;
import enums.VehicleStatus;
import observer.Observer;
import repository.RentalRepository;
import repository.VehicleRepository;
import service.EmailNotification;
import service.RentalService;
import service.SMSNotification;
import strategy.RentalValidationStrategy;
import strategy.MotorcycleValidationStrategy;
import strategy.TruckValidationStrategy;
import strategy.ElectricVehicleValidationStrategy;

public class Main {

    public static void main(String[] args) {

        // 1. إنشاء الـ Repositories
        VehicleRepository vehicleRepository = new VehicleRepository();
        RentalRepository rentalRepository = new RentalRepository();
        
        // 2. إنشاء الـ Validation Strategies
        Map<String, RentalValidationStrategy> validationStrategies = new HashMap<>();
        validationStrategies.put("Motorcycle", new MotorcycleValidationStrategy());
        validationStrategies.put("Truck", new TruckValidationStrategy());
        validationStrategies.put("ElectricVehicle", new ElectricVehicleValidationStrategy());
        
        // 3. إنشاء الـ Service مع تمرير الـ Map
        RentalService rentalService = new RentalService(
                rentalRepository, 
                vehicleRepository,
                validationStrategies
        );

        // 4. إنشاء المراقبين (Observers)
        Observer emailObserver = new EmailNotification("customer@example.com");
        Observer smsObserver = new SMSNotification("+1234567890");

        // 5. تسجيل المراقبين
        rentalService.addObserver(emailObserver);
        rentalService.addObserver(smsObserver);

        // 6. إنشاء مركبات مختلفة
        Car car = new Car(1, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE, 
                         "ABC-1234", 2022, "White", "Petrol", "Automatic");
        
        Motorcycle motorcycle = new Motorcycle(2, "Honda", "CBR600", 40.0, 
                                               VehicleStatus.AVAILABLE, 600, 18);
        
        Truck truck = new Truck(3, "Ford", "F-150", 100.0, VehicleStatus.AVAILABLE, 
                               5.5, true, 2);
        
        ElectricVehicle ev = new ElectricVehicle(4, "Tesla", "Model 3", 80.0, 
                                                 VehicleStatus.AVAILABLE, 75.0, 350, 8.0);

        // حفظ المركبات
        vehicleRepository.save(car);
        vehicleRepository.save(motorcycle);
        vehicleRepository.save(truck);
        vehicleRepository.save(ev);

        // 7. إنشاء عملاء
        Customer regularCustomer = new Customer(1, "Ahmad", "123", "DL123", 25);
        Customer youngCustomer = new Customer(2, "Omar", "456", "DL456", 16); // صغير السن
        Customer specialLicenseCustomer = new Customer(3, "Khalid", "789", "SP-TRUCK-789", 30); // رخصة خاصة

        // 8. اختبار تأجير سيارة عادية (يجب أن ينجح)
        System.out.println("=== Test 1: Rent Car (Should Succeed) ===");
        Rental rental1 = rentalService.rentVehicle(
                regularCustomer, car,
                LocalDate.now(), LocalDate.now().plusDays(3)
        );
        System.out.println("Car rented successfully!\n");

        // 9. اختبار تأجير دراجة نارية لعميل صغير السن (يجب أن يفشل)
        System.out.println("=== Test 2: Rent Motorcycle for Young Customer (Should Fail) ===");
        try {
            rentalService.rentVehicle(
                    youngCustomer, motorcycle,
                    LocalDate.now(), LocalDate.now().plusDays(2)
            );
            System.out.println("ERROR: Should have failed!");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation failed as expected: " + e.getMessage() + "\n");
        }

        // 10. اختبار تأجير شاحنة لعميل برخصة خاصة (يجب أن ينجح)
        System.out.println("=== Test 3: Rent Truck with Special License (Should Succeed) ===");
        Rental rental3 = rentalService.rentVehicle(
                specialLicenseCustomer, truck,
                LocalDate.now(), LocalDate.now().plusDays(5)
        );
        System.out.println("Truck rented successfully!\n");

        // 11. اختبار تأجير مركبة كهربائية ببطارية منخفضة (يجب أن يفشل)
        System.out.println("=== Test 4: Rent Electric Vehicle with Low Battery (Should Fail) ===");
        ev.setBatteryLevel(30.0); // بطارية منخفضة
        try {
            rentalService.rentVehicle(
                    regularCustomer, ev,
                    LocalDate.now(), LocalDate.now().plusDays(2)
            );
            System.out.println("ERROR: Should have failed!");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation failed as expected: " + e.getMessage() + "\n");
        }
    }
}
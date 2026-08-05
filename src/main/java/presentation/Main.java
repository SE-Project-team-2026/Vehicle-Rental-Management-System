package presentation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import domain.Car;
import domain.Customer;
import domain.ElectricVehicle;
import domain.Motorcycle;
import domain.Truck;
import domain.Van;
import enums.VehicleStatus;
import observer.Observer;
import repository.ManagerRepository;
import repository.RentalRepository;
import repository.VehicleRepository;
import service.AuthService;
import service.EmailNotification;
import service.RentalService;
import service.SMSNotification;
import strategy.ElectricVehicleValidationStrategy;
import strategy.MotorcycleValidationStrategy;
import strategy.RentalValidationStrategy;
import strategy.TruckValidationStrategy;

/**
 * Demonstration entry point for the Vehicle Rental Management System.
 *
 * <p>This class wires together the repositories, services, validation
 * strategies and notification observers, then exercises the main use cases
 * to showcase how the system behaves (US1.1–US5.2).</p>
 */
public class Main {

    public static void main(String[] args) {

        // 1. إنشاء الـ Repositories
        VehicleRepository vehicleRepository = new VehicleRepository();
        RentalRepository rentalRepository = new RentalRepository();
        ManagerRepository managerRepository = new ManagerRepository();

        // 2. إنشاء الـ Validation Strategies (US5.2)
        Map<String, RentalValidationStrategy> validationStrategies = new HashMap<>();
        validationStrategies.put("Motorcycle", new MotorcycleValidationStrategy());
        validationStrategies.put("Truck", new TruckValidationStrategy());
        validationStrategies.put("ElectricVehicle", new ElectricVehicleValidationStrategy());

        // 3. إنشاء الخدمات
        RentalService rentalService = new RentalService(
                rentalRepository,
                vehicleRepository,
                validationStrategies
        );
        AuthService authService = new AuthService(managerRepository);

        // 4. إنشاء المراقبين (Observers) وتسجيلهم
        Observer emailObserver = new EmailNotification("emanthaher794@gmail.com");
        Observer smsObserver = new SMSNotification("+970599999999");
        rentalService.addObserver(emailObserver);
        rentalService.addObserver(smsObserver);

        // 5. تسجيل دخول المدير قبل أي عملية محمية (US1.1 / US1.2)
        authService.login("ahmad", "1234");
        authService.requireLogin();
        System.out.println("Manager logged in successfully.\n");

        // 6. إنشاء مركبات مختلفة (معرفات تبدأ من 5 لتجنب التصادم مع vehicles.json)
        Car car = new Car.CarBuilder(5, "Toyota", "Corolla", 50.0, VehicleStatus.AVAILABLE)
                .setLicensePlate("ABC-1234")
                .setYear(2022)
                .setColor("White")
                .setFuelType("Petrol")
                .setTransmission("Automatic")
                .build();

        Motorcycle motorcycle = new Motorcycle(6, "Honda", "CBR600", 40.0,
                VehicleStatus.AVAILABLE, 600, 18);

        Truck truck = new Truck(7, "Ford", "F-150", 100.0, VehicleStatus.AVAILABLE,
                5.5, true, 2);

        ElectricVehicle ev = new ElectricVehicle.ElectricVehicleBuilder(8, "Tesla", "Model 3", 80.0,
                VehicleStatus.AVAILABLE)
                .setBatteryLevel(80.0)
                .setRange(350)
                .setChargingTime(8.0)
                .build();

        Van van = new Van(9, "Toyota", "Hiace", 80.0, VehicleStatus.AVAILABLE,
                15, 12, true);

        // حفظ المركبات
        vehicleRepository.save(car);
        vehicleRepository.save(motorcycle);
        vehicleRepository.save(truck);
        vehicleRepository.save(ev);
        vehicleRepository.save(van);

        // 7. إنشاء عملاء
        Customer regularCustomer = new Customer(1, "Ahmad", "123", "DL123", 25);
        Customer youngCustomer = new Customer(2, "Omar", "456", "DL456", 16); // صغير السن
        Customer specialLicenseCustomer = new Customer(3, "Khalid", "789", "SP-TRUCK-789", 30); // رخصة خاصة

        // 8. تأجير سيارة عادية (يجب أن ينجح - US2.1)
        System.out.println("=== Test 1: Rent Car (Should Succeed) ===");
        rentalService.rentVehicle(
                regularCustomer, car,
                LocalDate.now(), LocalDate.now().plusDays(3)
        );
        System.out.println("Car rented successfully!\n");

        // 9. تأجير دراجة نارية لعميل صغير السن (يجب أن يفشل - US5.2)
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

        // 10. تأجير شاحنة لعميل برخصة خاصة (يجب أن ينجح - US5.2)
        System.out.println("=== Test 3: Rent Truck with Special License (Should Succeed) ===");
        rentalService.rentVehicle(
                specialLicenseCustomer, truck,
                LocalDate.now(), LocalDate.now().plusDays(5)
        );
        System.out.println("Truck rented successfully!\n");

        // 11. تأجير مركبة كهربائية ببطارية منخفضة (يجب أن يفشل - US5.2)
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

        // 12. تسجيل الخروج يمنع العمليات المحمية (US1.2)
        System.out.println("=== Test 5: Logout Blocks Protected Actions (Should Fail) ===");
        authService.logout();
        try {
            authService.requireLogin();
            System.out.println("ERROR: Should have been blocked!");
        } catch (IllegalStateException e) {
            System.out.println("Logout enforcement as expected: " + e.getMessage());
        }
    }
}
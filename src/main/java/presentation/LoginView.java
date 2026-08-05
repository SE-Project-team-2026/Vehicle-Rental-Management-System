package presentation;

import java.util.Scanner;

import repository.ManagerRepository;
import service.AuthService;

/**
 * Simple console login view (US1.1).
 *
 * <p>Prompts the manager for their email and password and reports whether the
 * credentials are valid.</p>
 */
public class LoginView {

    public static void main(String[] args) {

        ManagerRepository managerRepository = new ManagerRepository();
        AuthService authService = new AuthService(managerRepository);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Vehicle Rental System");
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        boolean isAuthenticated = authService.loginVerify(email, password);
        if (isAuthenticated) {
            System.out.println("Login successful! Welcome, " + email);
        } else {
            System.out.println("Login failed! Invalid email or password.");
        }

        scanner.close();
    }
}
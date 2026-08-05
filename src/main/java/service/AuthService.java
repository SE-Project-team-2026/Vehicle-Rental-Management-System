package service;

import domain.Manager;
import repository.ManagerRepository;

/**
 * Handles manager authentication within the system.
 *
 * <p>This service validates manager credentials and tracks the currently
 * logged-in manager. Protected actions should call {@link #requireLogin()}
 * so that any operation requiring authentication enforces a prior login
 * (US1.2 – Manager Logout).</p>
 */
public class AuthService {

    /** Repository used to look up manager accounts. */
    private ManagerRepository managerRepository;

    /** The manager that is currently logged in, if any. */
    private Manager currentManager;

    /**
     * Creates a new AuthService backed by the given repository.
     *
     * @param managerRepository repository used to retrieve manager accounts
     */
    public AuthService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    /**
     * Verifies whether the given credentials match a registered manager.
     *
     * @param email    the manager email to check
     * @param password the password to check
     * @return {@code true} if the credentials are valid, {@code false} otherwise
     */
    public boolean loginVerify(String email, String password) {
        Manager foundManager = managerRepository.findByEmail(email);
        return foundManager != null && foundManager.getPassword().equals(password);
    }

    /**
     * @return the manager currently logged in, or {@code null} if none
     */
    public Manager getCurrentManager() {
        return currentManager;
    }

    /**
     * Logs out the current manager, protecting further actions (US1.2).
     */
    public void logout() {
        currentManager = null;
    }

    /**
     * @return {@code true} if a manager is currently logged in
     */
    public boolean isLoggedIn() {
        return currentManager != null;
    }

    /**
     * Performs a login with the given credentials.
     *
     * @param email    the manager email
     * @param password the manager password
     * @throws IllegalArgumentException if the credentials are invalid
     */
    public void login(String email, String password) {
        if (loginVerify(email, password)) {
            currentManager = managerRepository.findByEmail(email);
        } else {
            throw new IllegalArgumentException("Invalid email or password");
        }
    }

    /**
     * Enforces that a login has occurred before executing a protected action.
     *
     * <p>Methods that mutate protected data should call this, which throws an
     * exception if no manager is currently logged in (US1.2).</p>
     *
     * @throws IllegalStateException if no manager is logged in
     */
    public void requireLogin() {
        if (!isLoggedIn()) {
            throw new IllegalStateException("A manager must be logged in to perform this action");
        }
    }
}
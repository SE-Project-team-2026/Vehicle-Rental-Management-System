package repository;

import domain.Manager;

import java.util.ArrayList;
import java.util.List;

/**
 * In-memory repository for manager accounts (US1.1).
 */
public class ManagerRepository {

    /** All manager accounts stored in this repository. */
    private List<Manager> managers = new ArrayList<>();

    /**
     * Creates a repository with a default manager for testing purposes.
     */
    public ManagerRepository() {
        managers.add(new Manager(1, "ahmad", "1234"));
    }

    /**
     * Finds a manager by their email address.
     *
     * @param email the manager email
     * @return the matching manager, or {@code null} if not found
     */
    public Manager findByEmail(String email) {
        for (Manager manager : managers) {
            if (manager.getEmail().equals(email)) {
                return manager;
            }
        }
        return null;
    }
}
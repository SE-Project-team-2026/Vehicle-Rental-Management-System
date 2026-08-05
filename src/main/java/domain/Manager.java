package domain;

/**
 * Represents a manager account that can access the system (US1.1).
 */
public class Manager {

    /** Unique identifier of the manager. */
    private int id;

    /** Email (login) of the manager. */
    private String email;

    /** Password of the manager. */
    private String password;

    /**
     * Creates a new manager.
     *
     * @param id       unique identifier
     * @param email    manager email
     * @param password manager password
     */
    public Manager(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    /**
     * @return the unique identifier of the manager
     */
    public int getId() {
        return id;
    }

    /**
     * @return the email of the manager
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the password of the manager
     */
    public String getPassword() {
        return password;
    }
}

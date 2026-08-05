package domain;

/**
 * Represents a customer of the vehicle rental system.
 *
 * <p>Stores the personal and licensing information required to rent a
 * vehicle, including any type-specific requirements such as a driving
 * license and age (used by the validation strategies).</p>
 */
public class Customer {

    /** Unique identifier of the customer. */
    private int id;

    /** Full name of the customer. */
    private String name;

    /** Contact phone number of the customer. */
    private String phone;

    /** Driving license identifier of the customer. */
    private String drivingLicense;

    /** Age of the customer. */
    private int age;

    /**
     * Default constructor required by the JSON mapper.
     */
    public Customer() {
    }

    /**
     * Creates a new customer.
     *
     * @param id             unique identifier
     * @param name           full name
     * @param phone          contact phone number
     * @param drivingLicense driving license identifier
     * @param age            age of the customer
     */
    public Customer(int id, String name, String phone, String drivingLicense, int age) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.drivingLicense = drivingLicense;
        this.age = age;
    }

    /**
     * @return the unique identifier of the customer
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the customer.
     *
     * @param id the identifier to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the full name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the contact phone number of the customer
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the contact phone number of the customer.
     *
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the driving license identifier of the customer
     */
    public String getDrivingLicense() {
        return drivingLicense;
    }

    /**
     * Sets the driving license identifier of the customer.
     *
     * @param drivingLicense the driving license to set
     */
    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    /**
     * @return the age of the customer
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the customer.
     *
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
}
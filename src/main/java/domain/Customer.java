package domain;

public class Customer {

    private int id;
    private String name;
    private String phone;
    private String drivingLicense;
    private int age;

    public Customer() {
    }

    public Customer(int id, String name, String phone, String drivingLicense, int age) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.drivingLicense = drivingLicense;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
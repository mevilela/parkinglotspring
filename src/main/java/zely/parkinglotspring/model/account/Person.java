package zely.parkinglotspring.model.account;

import jakarta.persistence.Embedded;
import zely.parkinglotspring.model.address.Address;

public class Person {
    private String name;

    @Embedded
    private Address address;
    private String phone;
    private String email;

    public Person() {
    }

    public Person(String name, Address address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}



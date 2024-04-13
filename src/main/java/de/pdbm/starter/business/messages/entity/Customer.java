package de.pdbm.starter.business.messages.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;

    private String city;

    private String email;
    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;
    private String phone;
//    private String address; loesch unnoetig




    private String state;
    private String street;
    @Column(name = "zip_code")
    private String zipcode;

//    public Customer(Integer id, String firstname, String lastname, String address, String phone, String email) {
//        this.id = id;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.address = address;
//        this.phone = phone;
//        this.email = email;
//    }

//    public Customer(Integer id, String city, String email, String firstname, String lastname, String phone, String state, String street, String zipcode) {
//        this.id = id;
//        this.city = city;
//        this.email = email;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.phone = phone;
//        this.state = state;
//        this.street = street;
//        this.zipcode = zipcode;
//    }

    public Customer(String city, String email, String firstname, String lastname, String phone, String state, String street, String zipcode) {
        this.city = city;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.state = state;
        this.street = street;
        this.zipcode = zipcode;
    }

    public Customer() {
    }

//    @Override
//    public String toString() {
//        return "Kunde{" +
//                "id=" + id +
//                ", vorname='" + firstname + '\'' +
//                ", nachname='" + lastname + '\'' +
//                ", adresse='" + address + '\'' +
//                ", telefonnummer='" + phone + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    } adress unnoetig

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}

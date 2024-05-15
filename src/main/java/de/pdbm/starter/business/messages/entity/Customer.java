package de.pdbm.starter.business.messages.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;
    @NotBlank(message = "Stadt darf nicht null sein")

    private String city;
    @Email(message = "email entspricht syntax nicht")

    private String email;
    @NotBlank(message = "Vorname darf nicht null sein")

    @Column(name = "first_name")
    private String firstname;
    @NotBlank(message = "Nachname darf nicht null sein")

    @Column(name = "last_name")
    private String lastname;
    @Size(min = 0, message = "Telephone Nummer kann leer sein")
    @Pattern(regexp = "^\\(\\d{3}\\)\\s\\d{3}-\\d{4}$", message = "Invalid telephone number format bitte geben Sie diese Format  (559) 628-2239 ein")
    private String phone;
    @Pattern(regexp = "^(BW|BY|BE|BB|HB|HH|HE|MV|NI|NW|RP|SL|SN|ST|SH|TH)$", message = "Bitte geben Sie eine deutsche Staat Abkürzung")

    private String state;
    @Pattern(regexp = "^\\d+\\s.*$" ,message = "bitte geben sie Zahl zuerst ein")

    private String street;
    @Pattern(regexp = "^\\d{5}$",message = "bitte geben Sie eine gültige Postleitzahl")

    @Column(name = "zip_code")
    private String zipcode;
    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Order> orders = new HashSet<>();
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

    // Konstruktor
    public Customer() {
    }

    // Getter und Setter
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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
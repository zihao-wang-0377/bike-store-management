package de.pdbm.starter.business.messages.boundary.form;

import de.pdbm.starter.business.messages.control.CustomerService;
import de.pdbm.starter.business.messages.entity.Customer;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CustomerForm implements Serializable {
    @Inject
    CustomerService customerService;

    @NotNull(message = "Vorname kann nicht leer sein")
    private String firstname;

    @NotNull(message = "Nachname kann nicht leer sein")
    private String lastname;

    @NotNull(message = "Adresse kann nicht leer sein")
    private String address;

    @NotNull(message = "Telefonnummer kann nicht leer sein")
    private String phone;

    @NotNull(message = "E-Mail kann nicht leer sein")
    private String email;

    public CustomerForm() {
    }

    public void save() {
        Customer customer = new Customer();
        customer.setFirstname(firstname);
        customer.setLastname(lastname);
        customer.setAddress(address);
        customer.setPhone(phone);
        customer.setEmail(email);
        customerService.save(customer);
        setFirstname(null);
        setLastname(null);
        setAddress(null);
        setPhone(null);
        setEmail(null);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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

    public String navigateToHomePage() {
        return "homePage.xhtml";
    }
}

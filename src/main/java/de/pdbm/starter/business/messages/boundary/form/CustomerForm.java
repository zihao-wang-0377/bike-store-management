package de.pdbm.starter.business.messages.boundary.form;

import de.pdbm.starter.business.messages.control.CustomerService;
import de.pdbm.starter.business.messages.entity.Customer;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Named
@ViewScoped
public class CustomerForm implements Serializable {
    @Inject
    CustomerService customerService;

    @NotBlank(message = "Vorname darf nicht leer sein!")
    private String firstname;

    @NotBlank(message = "Nachname darf nicht leer sein!")
    private String lastname;

    @NotBlank(message = "Adresse darf nicht leer sein!")
    private String address;

    @NotBlank(message = "Telefonnummer darf nicht leer sein!")
    @Pattern(regexp = "\\d+", message = "Telefonnummer kann nur Zahlen enthalten!")
    private String phone;

    @NotBlank(message = "E-Mail darf nicht leer sein!")
    //@Email(message = "Ungültige E-Mail-Adresse")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Ungültige E-Mail-Adresse!")
    private String email;

    public CustomerForm() {
    }

    public void save() {
        // Objekt wird erstellt und setze Vorname, Nachname, Adresse, Telefon und E-Mail gesetzt
        Customer customer = new Customer();
        customer.setFirstname(firstname);
        customer.setLastname(lastname);
//        customer.setAddress(address);
        customer.setPhone(phone);
        customer.setEmail(email);

        // Speichere den Kunden über den customerService
        customerService.save(customer);

        // Setze die Eingaben für Vorname, Nachname, Adresse, Telefon und E-Mail zurück
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
        return "homePage.xhtml?faces-redirect=true";
    }
}

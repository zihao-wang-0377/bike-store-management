package de.pdbm.starter.business.messages.boundary.table;

import de.pdbm.starter.business.messages.control.CustomerService;
import de.pdbm.starter.business.messages.entity.Customer;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class CustomerController {
private Integer customerId;
private String city;
private String email;

private String firstName;
private String lastName;

private String phone;

private String state;

private String street;

private String zipCode;

private List<Customer> customerList;
@Inject
    CustomerService customerService;
@PostConstruct
public void customerListInit() {
    customerList=customerService.findAll();
}

public List<Customer> getCustomerList(){
    return customerList;
}
    public String navigateToHomePage() {
        return "homePage.xhtml?faces-redirect=true";
    }
}

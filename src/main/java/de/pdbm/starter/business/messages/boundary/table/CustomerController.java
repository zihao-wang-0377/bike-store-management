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
private int currentPage = 0;
private int maxPage = 10;
@Inject
CustomerService customerService;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Customer> getCustomers() {
    if (customerList == null || customerList.isEmpty()) {
        loadCustomerList();
    }
    return customerList;
}
public void loadCustomerList(){
    this.customerList = customerService.findPaginated(currentPage, maxPage);
}
public void nextPage(){
    currentPage++;
    loadCustomerList();
}
public void prevPage(){
    currentPage--;
    loadCustomerList();
}

public List<Customer> getCustomerList(){
    return customerList;
}
    public String navigateToHomePage() {
        return "homePage.xhtml?faces-redirect=true";
    }
}

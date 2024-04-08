package de.pdbm.starter.business.messages.boundary.table;

import de.pdbm.starter.business.messages.control.CustomerService;
import de.pdbm.starter.business.messages.entity.Customer;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CustomerController implements Serializable {
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
private int pageSize = 10;
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
        this.customerList = customerService.findPaginated(currentPage, pageSize);
}
    public void nextPage(){
        currentPage++;
        loadCustomerList();
    }
    public void prevPage(){
        if(currentPage > 0){
            currentPage--;
            loadCustomerList();
       }

    }


    public String navigateToHomePage() {
        return "homePage.xhtml?faces-redirect=true";
    }
}

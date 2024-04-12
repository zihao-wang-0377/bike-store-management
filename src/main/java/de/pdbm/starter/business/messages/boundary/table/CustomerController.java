package de.pdbm.starter.business.messages.boundary.table;

import de.pdbm.starter.business.messages.control.CustomerService;
import de.pdbm.starter.business.messages.entity.Customer;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class CustomerController implements Serializable {
private Integer customerId;
@NotBlank(message = "Stadt darf nicht null sein")
private String city;
@Email(message = "email entspricht syntax nicht")
private String email;
@NotBlank(message = "Vorname darf nicht null sein")
private String firstName;
@NotBlank(message = "Nachname darf nicht null sein")

private String lastName;
@Size(min = 0, message = "Telephone Nummer kann leer sein")
@Pattern(regexp = "^\\(\\d{3}\\)\\s\\d{3}-\\d{4}$", message = "Invalid telephone number format bitte geben Sie diese Format  (559) 628-2239 ein")
private String phone;
//@Pattern(regexp = "^[A-Z]{2}$", message = "ungültige Staat Format")
@Pattern(regexp = "^(BW|BY|BE|BB|HB|HH|HE|MV|NI|NW|RP|SL|SN|ST|SH|TH)$", message = "Bitte geben Sie eine deutsche Staat Abkürzung")
private String state;

@Pattern(regexp = "^\\d+\\s.*$" ,message = "bitte geben sie Zahl zuerst ein")
private String street;
@Pattern(regexp = "^\\d{5}$",message = "bitte geben Sie eine gültige Postleitzahl")
private String zipCode;

private List<Customer> customerList;
private int currentPage = 1;
private int pageSize = 10;
private long totalRecords;

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
    public void firstPage(){
        currentPage = 1;
        loadCustomerList();
    }
    public void lastPage(){
        currentPage = getTotalPages();
        loadCustomerList();
    }
    public long getTotalRecords() {
        totalRecords = customerService.getCustomerCount();
        return totalRecords;
    }
    public int getTotalPages() {
        return (int) Math.ceil((double) totalRecords / pageSize);
    }
    public void setPage(int page){
        if(page >= 1 && page <= getTotalPages()){
            currentPage = page;
            loadCustomerList();
        }
    }
    public Integer[] getPageNumbers() {
        int startPage = Math.max(1, currentPage - 2);
        int endPage = Math.min(getTotalPages(), currentPage + 4);
        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            pageNumbers.add(i);
        }
        return pageNumbers.toArray(new Integer[0]);
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        loadCustomerList();
    }

    public String navigateToHomePage() {
        return "homePage.xhtml?faces-redirect=true";
    }
}

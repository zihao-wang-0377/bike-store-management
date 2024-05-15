package de.pdbm.starter.business.messages.boundary.control;

import de.pdbm.starter.business.messages.service.CustomerService;
import de.pdbm.starter.business.messages.entity.Customer;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.constraints.*;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Named
@ViewScoped
public class CustomerController implements Serializable {
    @Inject
    CustomerService customerService;
@Inject
Validator validator;
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

    private int currentPage = 1;

    private int pageSize = 10;

    private long totalRecords;

    private int clicks;

    // Konstruktor
    public CustomerController() {
    }

    public CustomerController(String city, String email, String firstName, String lastName, String phone, String state, String street, String zipCode) {
        this.city = city;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.state = state;
        this.street = street;
        this.zipCode = zipCode;
    }

    // Objekt erstellen und speichern
    public void save(){
        Customer customer = new Customer( city,  email,  firstName,  lastName,  phone,  state,  street,  zipCode);
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Customer> violation : violations) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, violation.getMessage(), null));
            }
            return; // 如果有验证错误，则不继续执行保存操作
        }
        customerService.save(customer);
    }
    public boolean isButtonDisplayed(){
        return clicks % 2 == 1;
    }

    public void incrementClicks(){
        clicks++;
    }

    // Paginierung-Methoden
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
        currentPage = 1;
        loadCustomerList();
    }

    public void goToPage() {
        if(currentPage < 1) {
            currentPage = 1;
            loadCustomerList();
        } else if(currentPage > getTotalPages()) {
            currentPage = getTotalPages();
            loadCustomerList();
        } else {
            loadCustomerList();
        }
    }

    // Getter und Setter
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }



    // Navigation fuer Zurueck Button
    public String navigateToHomePage() {
        return "homePage.xhtml?faces-redirect=true";
    }

    public void deleteCustomerRecord(Customer customer){
        customerService.delete(customer);
        loadCustomerList();
        getTotalRecords();
    }
}
package de.pdbm.starter.business.messages.boundary.control;

import de.pdbm.starter.business.messages.service.CustomerService;
import de.pdbm.starter.business.messages.entity.Customer;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
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
import java.util.stream.Collectors;

@Named
@SessionScoped
public class CustomerController implements Serializable {
    @Inject
    CustomerService customerService;
@Inject
Validator validator;
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

    private int clicks;

    private Customer selectedCustomer;
    private String lastnameString;

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
            return;
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
    public void searchByLastName() {
        this.customerList = customerService.findByLastName(lastnameString);
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

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public String getLastnameString() {
        return lastnameString;
    }

    public void setLastnameString(String lastnameString) {
        this.lastnameString = lastnameString;
    }

    public String showDetails(Customer selectedCustomer){
        this.selectedCustomer = selectedCustomer;
        return "customerDetail.xhtml?faces-redirect=true";
    }

    // Navigation fuer Zurueck Button

    public void deleteCustomerRecord(Customer customer){
        customerService.delete(customer);
        loadCustomerList();
        getTotalRecords();
    }

    public String updateCustomerRecord(){
        customerService.update(selectedCustomer);
        return "customerTable.xhtml?faces-redirect=true";
    }
}
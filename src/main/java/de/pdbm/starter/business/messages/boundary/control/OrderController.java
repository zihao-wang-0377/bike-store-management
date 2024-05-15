package de.pdbm.starter.business.messages.boundary.control;

import de.pdbm.starter.business.messages.service.CustomerService;
import de.pdbm.starter.business.messages.service.OrderService;
import de.pdbm.starter.business.messages.service.StaffService;
import de.pdbm.starter.business.messages.service.StoreService;
import de.pdbm.starter.business.messages.entity.Customer;
import de.pdbm.starter.business.messages.entity.Order;
import de.pdbm.starter.business.messages.entity.Staff;
import de.pdbm.starter.business.messages.entity.Store;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class OrderController implements Serializable {
    @Inject
    OrderService orderService;

    @Inject
    StaffService staffService;

    @Inject
    StoreService storeService;

    @Inject
    CustomerService customerService;

    private Integer orderId;

    @PastOrPresent(message = "das Bestelldatum muss in der Vergangheit oder Gegenwart sein")
    private LocalDate orderDate; // hier kann man eine heute funktion schreiben

    @Pattern(regexp = "^[1]$", message = "Order status muss unter 1(bestellt), 2(versendet), 3(zustellt),  4(zugestellt). sein,aber am Anfang soll es 1 heißt es erst bestellt")
    private String orderStatus; // hier schreibe ich das als String um Regex besser zu schreiben ansonst so man ein Validator schreiben

    @Future(message = "Erwartetes Lieferdatum muss in der Zukunft sein")
    private LocalDate requiredDate;

    private LocalDate shippedDate;

    @ForeignKeyExists(entity = Customer.class,customerMessage = "KundeId,die Sie eingegeben haben existiert nicht")
    private Integer customerId;

    @ForeignKeyExists(entity = Staff.class,customerMessage = "staffId,die Sie eingegeben haben existiert nicht")
    private Integer staffId;

    @ForeignKeyExists(entity = Store.class,customerMessage = "storeId,die Sie eingegeben haben existiert nicht")
    private Integer storeId;

    private List<Order> orderList;

    private int currentPage = 1;

    private int pageSize = 10;

    private long totalRecords;

    private int clicks;

    private Order selectedOrder;

    // Konstruktor
    public OrderController() {
    }

    // Objekt erstellen und speichern
    public void save(){
        Integer oderStatusInt = Integer.parseInt(orderStatus);
        Customer customer = customerService.findById(customerId);
        Staff staff = staffService.findStaffById(staffId);
        Store store = storeService.findStoreById(storeId);
        orderService.save(new Order( orderDate,  oderStatusInt,  requiredDate,  shippedDate,  customer,  staff,  store));
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

    public List<Order> getOrders() {
        if (orderList == null || orderList.isEmpty()) {
            loadOrderList();
        }
        return orderList;
    }

    public void loadOrderList(){
        this.orderList = orderService.findPaginated(currentPage, pageSize);
    }

    public void nextPage(){
        currentPage++;
        loadOrderList();
    }

    public void prevPage(){
        if(currentPage > 0){
            currentPage--;
            loadOrderList();
        }
    }

    public void firstPage(){
        currentPage = 1;
        loadOrderList();
    }

    public void lastPage(){
        currentPage = getTotalPages();
        loadOrderList();
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) totalRecords / pageSize);
    }

    public void setPage(int page){
        if(page >= 1 && page <= getTotalPages()){
            currentPage = page;
            loadOrderList();
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
        loadOrderList();
    }

    public void goToPage() {
        if(currentPage < 1) {
            currentPage = 1;
            loadOrderList();
        } else if(currentPage > getTotalPages()) {
            currentPage = getTotalPages();
            loadOrderList();
        } else {
            loadOrderList();
        }
    }

    public long getTotalRecords() {
        this.totalRecords = orderService.getOrderCount();
        return totalRecords;
    }

    // Getter und Setter
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDate requiredDate) {
        this.requiredDate = requiredDate;
    }

    public LocalDate getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDate shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public String showDetails(Order selectedOrder){
        this.selectedOrder = selectedOrder;
        return "orderDetail.xhtml?faces-redirect=true";
    }

    // Navigation fuer Zurueck Button
    public String navigateToHomePage() {
        return "homePage.xhtml?faces-redirect=true";
    }
}
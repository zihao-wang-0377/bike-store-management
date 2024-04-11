package de.pdbm.starter.business.messages.boundary.table;

import de.pdbm.starter.business.messages.control.OrderService;
import de.pdbm.starter.business.messages.entity.Customer;
import de.pdbm.starter.business.messages.entity.Order;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class OrderController implements Serializable {
private Integer orderId;
private LocalDate orderDate;
private Integer orderStatus;
private LocalDate requiredDate;
private LocalDate shippedDate;
private Integer customerId;
private Integer staffId;
private Integer storeId;
private List<Order> orderList;
private int currentPage = 1;
private int pageSize = 10;
private long totalRecords;
@Inject
OrderService orderService;

    public OrderController() {
    }

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
    public Integer[] getPageNumbers() {
        int startPage = Math.max(1, currentPage - 2);
        int endPage = Math.min(getTotalPages(), currentPage + 4);
        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            pageNumbers.add(i);
        }
        return pageNumbers.toArray(new Integer[0]);
    }
    public void setPage(int page){
        if(page >= 1 && page <= getTotalPages()){
            currentPage = page;
            loadOrderList();
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        loadOrderList();
    }

    public long getTotalRecords() {
        this.totalRecords = orderService.getOrderCount();
        return totalRecords;
    }

    public OrderController(Integer orderId, LocalDate orderDate, Integer orderStatus, LocalDate requiredDate, LocalDate shippedDate, Integer customerId, Integer staffId, Integer storeId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.customerId = customerId;
        this.staffId = staffId;
        this.storeId = storeId;
    }

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

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
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

    public String navigateToHomePage() {
        return "homePage.xhtml?faces-redirect=true";
    }

}

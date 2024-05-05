package de.pdbm.starter.business.messages.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "staffs")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Integer staffId;

    private Integer active;

    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String phone;
@Column(name = "manager_id")
    private Integer managerId;
@ManyToOne
@JoinColumn (name = "store_id")
    private Store store;

    // Konstruktor
    public Staff() {
    }

//    public Staff(Integer staffId, Integer active, String email, String firstName, String lastName, String phone, Integer managerId, Integer storeId) {
//        this.staffId = staffId;
//        this.active = active;
//        this.email = email;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phone = phone;

    public Staff(Integer active, String email, String firstName, String lastName, String phone, Integer managerId, Store store) {
        this.active = active;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.managerId = managerId;
        this.store = store;
    }
//        this.managerId = managerId;
//        this.storeId = storeId;
//    }

    // Getter und Setter
    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
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

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

//    public Integer getStoreId() {
//        return storeId;
//    }
//
//    public void setStoreId(Integer storeId) {
//        this.storeId = storeId;
//    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
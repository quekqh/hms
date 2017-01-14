/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.model;

import java.sql.Date;

/**
 *
 * @author Low Kang Li
 */
public class Customer {
    private int customerId;
    private String customerName;
    private Date dob;
    private String gender;
    private String remarks;
    private Date regDate;
    private String email;
    private User user;

    public Customer(int customerId, String customerName, Date dob, String gender, String remarks, Date regDate, String email, User user) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.dob = dob;
        this.gender = gender;
        this.remarks = remarks;
        this.regDate = regDate;
        this.email = email;
        this.user = user;
    }
    
    public Customer(int customerId, String customerName, Date dob, String gender, Date regDate, String email, User user) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.dob = dob;
        this.gender = gender;
        this.regDate = regDate;
        this.email = email;
        this.user = user;
    }    

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }    
    
    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public User getUser(){
        return user;
    }
     
    public void setUser(User user){
        this.user = user;
    }
}

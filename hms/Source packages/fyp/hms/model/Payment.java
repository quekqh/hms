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
public class Payment {
    private int apptId;
    private String username;
    private int customerId;
    private double payment;
    private String services;
    private Date paymentDate;
    private String remarks;
    private String comment;

    public Payment(int apptId, String username, int customerId, double payment, String services, Date paymentDate,String remarks, String comment) {
        this.apptId = apptId;
        this.username = username;
        this.customerId = customerId;
        this.payment = payment;
        this.services = services;
        this.paymentDate = paymentDate;
        this.remarks = remarks;
        this.comment = comment;
    }

    public int getApptId() {
        return apptId;
    }

    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
        
    
}

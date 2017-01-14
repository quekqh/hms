/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.model;

import java.sql.Timestamp;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Asus
 */
public class Appointment {
    private int appointmentId;
    private int customerId;
    private String hairStylistUsername;
    private String customerName;
    private Date appointmentDate;
    private Time appointmentTime;
    private Time apptEndTime;
    private String remarks;
    private String status;

    public Appointment(int appointmentId, int customerId, String hairStylistUsername, String customerName, Date appointmentDate, Time appointmentTime, Time apptEndTime, String remarks, String status) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.hairStylistUsername = hairStylistUsername;
        this.customerName = customerName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.apptEndTime = apptEndTime;
        this.remarks = remarks;
        this.status = status;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getHairStylistUsername() {
        return hairStylistUsername;
    }

    public void setHairStylistUsername(String hairStylistUsername) {
        this.hairStylistUsername = hairStylistUsername;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Time appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Time getApptEndTime() {
        return apptEndTime;
    }

    public void setApptEndTime(Time apptEndTime) {
        this.apptEndTime = apptEndTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}

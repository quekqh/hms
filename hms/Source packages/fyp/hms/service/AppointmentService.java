/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.service;

import fyp.hms.dao.AppointmentDAO;
import fyp.hms.dao.CustomerDAO;
import fyp.hms.model.Appointment;
import fyp.hms.model.Customer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.TreeMap;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */
@Service("appointmentService")
public class AppointmentService {
    @Autowired
    HttpSession session;
    
    public Customer retrieveCust(int customerId) {
        Customer customer = CustomerDAO.retrieveCustomer(customerId);
        
        if (customer != null) {
            return customer;  
        }
        
        return null;
    }
    
    public Customer retrieveCustbyName(String custName) {
        Customer customer = CustomerDAO.retrieveCustbyName(custName);
        //System.out.println("appointmentservice customer " + customer);
        if (customer != null) {
            return customer;  
        }
        
        return null;
    }
    
    public boolean addAppointment(int customerId, String hairStylistUsername, String customerName, Date appointmentDate,Time time, Time endTime, String remarks, String status) {
        
        boolean isAdded = AppointmentDAO.insertAppointment(customerId,hairStylistUsername, customerName, appointmentDate, time,endTime, remarks, status);
        return isAdded;
    }
    
    public List<Appointment> retrieveAppointment(String hairStylistUsername) {
        return AppointmentDAO.retrieveAppointmentByHairStylist(hairStylistUsername);      
    }
    
    public List<Appointment> retrieveAllAppointment() {
        return AppointmentDAO.retrieveAllAppts();      
    }
    
    public void changeApptStatus(String status, int apptId) {
        AppointmentDAO.changeApptStatus(status, apptId);     
    }
    
    public Appointment retrieveApptStatus(int apptId) {
        return AppointmentDAO.retrieveApptStatus(apptId);    
    }
    
    public List<Appointment> retrieveApptDateInOder() {
        return AppointmentDAO.retrieveApptDateInOder();      
    }
    
    public List<Appointment> retrieveApptDateInOder(String username) {
        return AppointmentDAO.retrieveApptDateInOder(username);      
    }
    
    public List<Appointment> retrieveCustomerInOder() {
        return AppointmentDAO.retrieveCustomerInOder();      
    }
    
    public List<Appointment> retrieveCustomerInOder(String username) {
        return AppointmentDAO.retrieveCustomerInOder(username);      
    }
    
    public List<Appointment> retrieveStaffInOder() {
        return AppointmentDAO.retrieveStaffInOder();      
    }
    
    public List<Appointment> retrieveAppointmentBySearch(String search) {
        return AppointmentDAO.retrieveAppointmentBySearch(search);      
    }
    
    public List<Appointment> retrieveAppointmentBySearchForStaff(String search, String username) {
        return AppointmentDAO.retrieveAppointmentBySearchForStaff(search, username);      
    }
    
    public List<Appointment> retrieveExistingAppointment() {
        return AppointmentDAO.retrieveExistingAppointment();      
    }
    
    public List<Appointment> retrieveExistingAppointment(String username) {
        return AppointmentDAO.retrieveExistingAppointment(username);      
    }
    
    public void updateAppointment(int apptId, String hairStylist,Date sqlDate,Time t, String remarks) {
        AppointmentDAO.updateAppointment(apptId, hairStylist,sqlDate,t, remarks);     
    }
    
    public void deleteAppt(int apptId) {
        AppointmentDAO.deleteAppt(apptId);     
    }
    
    public void updateApptById(int apptId) {
        AppointmentDAO.updateApptById(apptId);     
    }
}

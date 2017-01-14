/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.service;

import fyp.hms.dao.CustomerDAO;
import fyp.hms.model.Customer;
import java.sql.Date;
import java.text.ParseException;
import java.util.TreeMap;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HajaReethwan
 */
@Service("customerService")
public class CustomerService {
    @Autowired
    HttpSession session;

    public void insertCustomer(String name, int contact, Date regdate, Date dob, String email, String gender, String username, String remarks) throws ParseException {       
       CustomerDAO.insertCustomer(name,contact,regdate,dob,email,gender,username,remarks);
       
    }
    
    public Customer retrieveCustomer(int id){
        return CustomerDAO.retrieveCustomer(id);
    }
    
    public TreeMap<String,Customer> retrieveSearch(String search) throws ParseException{
        return CustomerDAO.retrieveSearch(search);
    }
    
    public TreeMap<String,Customer> retrieveMaleCustomers() throws ParseException{
        return CustomerDAO.retrieveMaleCustomers();
    }
    
    public TreeMap<String,Customer> retrieveFemaleCustomers() throws ParseException{
        return CustomerDAO.retrieveFemaleCustomers();
    }
    
    public TreeMap<String,Customer> retrieveAllCustomers() throws ParseException{
        return CustomerDAO.retrieveAllCustomers();
    }    

    public TreeMap<String,Customer> retrieveMaleCustomersByStaff(String name) throws ParseException{
        return CustomerDAO.retrieveMaleCustomersByStaff(name);
    }
    
    public TreeMap<String,Customer> retrieveFemaleCustomersByStaff(String name) throws ParseException{
        return CustomerDAO.retrieveFemaleCustomersByStaff(name);
    }
    
    public TreeMap<String,Customer> retrieveAllCustomersByStaff(String name) throws ParseException{
        return CustomerDAO.retrieveAllCustomersByStaff(name);
    }
    
    public TreeMap<String,Customer> retrieveSearchByStaff(String name, String search) throws ParseException{
        return CustomerDAO.retrieveSearchByStaff(name,search);
    }
    
    public void deleteCustomer(int id){
        CustomerDAO.deleteCustomer(id);
    }

    public void updateCustomer(String name, String contact, String regdate, String dob, String email, String gender, String username, String remarks) throws ParseException {
        CustomerDAO.updateCustomer(name,contact,regdate,dob,email,gender,username,remarks);
    }
}
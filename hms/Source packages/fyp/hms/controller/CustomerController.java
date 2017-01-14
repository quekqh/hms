/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.controller;

import fyp.hms.dao.UserDAO;
import fyp.hms.model.Customer;
import fyp.hms.service.CustomerService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author HajaReethwan
 */
@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;
    
    @RequestMapping(value = "/editCustomer", method = RequestMethod.GET)
    public String editCustomer(@RequestParam Map<String, String> parameters, HttpSession session) {        
        return "editCustomer";
    }    

    @RequestMapping(value = "/clientProfile", method = RequestMethod.GET)
    public String clientProfile(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        String id = parameters.get("id");
        int customerId = 0;
        
        if(id != null){
            try{
                customerId = Integer.parseInt(id.trim());
                Customer customer = customerService.retrieveCustomer(customerId);
                if(customer != null){
                    model.addAttribute("customer", customer);
                }
                
            }catch(NumberFormatException e){
                System.out.println(e);

            }
            
        }
     
        return "clientProfile";
    }    
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String signup(@RequestParam Map<String, String> parameters, ModelMap model,HttpSession session) throws ParseException {
        String name = parameters.get("name");
        String contact = parameters.get("contact");
        String dob = parameters.get("dob");
        String email = parameters.get("email");
        String gender = parameters.get("gender");
        String username = parameters.get("username");
        String remarks = parameters.get("remarks");
        
        if (name == null || contact == null || dob == null || email == null || gender == null || username == null) {
            model.addAttribute("name",name);
            model.addAttribute("contact",contact);
            model.addAttribute("dob",dob);
            model.addAttribute("email",email);
            model.addAttribute("gender",gender);
            model.addAttribute("username",username);
            model.addAttribute("remarks",remarks);
            
            return "client";
        }
        
        Pattern pat = Pattern.compile("^[ A-z]+$");
        Matcher m = pat.matcher(name);
        if (!m.matches()) {
            model.addAttribute("error","Invalid customer name.");
            model.addAttribute("name",name);
            model.addAttribute("contact",contact);
            model.addAttribute("dob",dob);
            model.addAttribute("email",email);
            model.addAttribute("gender",gender);
            model.addAttribute("username",username);
            model.addAttribute("remarks",remarks);
            
            return "client";
        }
        
        
        if(contact.length() < 8){
            model.addAttribute("error","Invalid phone number");
            model.addAttribute("name",name);
            model.addAttribute("contact",contact);
            model.addAttribute("dob",dob);
            model.addAttribute("email",email);
            model.addAttribute("gender",gender);
            model.addAttribute("username",username);
            model.addAttribute("remarks",remarks);
            return "client";
        }
        
        int customerId = 0;
        
        try{
            customerId = Integer.parseInt(contact.trim());
            Customer customer = customerService.retrieveCustomer(customerId);
            if(customer != null){
                model.addAttribute("error", "Customer already exists.");
                return "client";
            }

        }catch(NumberFormatException e){
            model.addAttribute("error", "Invalid phone number.");
            return "client";
        }
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dobDate;
        java.sql.Date sqlDate = null;
        String strSqlDate = "";
        try {
            dobDate = format.parse(dob);
            sqlDate = new java.sql.Date(dobDate.getTime());
            strSqlDate = sqlDate + "";
            
        } catch (ParseException ex) {
            model.addAttribute("error","Date of Birth is invalid.");
            return "client";
        }
        
        //check if the appointment date is before the current date
        Date currentDate = new Date();
        java.sql.Date sqlCurrentDate = new java.sql.Date(currentDate.getTime());
        String strSqlCurrentDate = sqlCurrentDate + "";
        if(!strSqlDate.equals(strSqlCurrentDate) && sqlCurrentDate.before(sqlDate)){
            model.addAttribute("error","Date of Birth needs to be before the Current Date.");
            return "client";
        }
        
        email = email.toLowerCase();
        Pattern patEmail = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
        Matcher matcherEmail = patEmail.matcher(email);
        if (!matcherEmail.matches()) {
            model.addAttribute("error","Invalid email.");
            model.addAttribute("name",name);
            model.addAttribute("contact",contact);
            model.addAttribute("dob",dob);
            model.addAttribute("email",email);
            model.addAttribute("gender",gender);
            model.addAttribute("username",username);
            model.addAttribute("remarks",remarks);
            return "client";
        }
        
        sqlCurrentDate = new java.sql.Date(dobDate.getTime());
        String user = UserDAO.staffUsername(username);
        customerService.insertCustomer(name,customerId,sqlCurrentDate,sqlDate,email,gender,user,remarks);
        String message = "Customer Id No: " + contact + " has been successfully created!";
        model.addAttribute("success",message);
        return "client";
    }
    
    @RequestMapping(value = "/customerSearch", method = RequestMethod.GET)
    public String search(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) throws ParseException {        
        if(parameters.get("search") == null || parameters.get("search").isEmpty()){
            model.addAttribute("error", "search is blank");
            return "client";
        } else {
            String search = parameters.get("search");
            String name = (String)session.getAttribute("username");
            if(name.equals("admin")){
                TreeMap<String,Customer> map = customerService.retrieveSearch(search);
                model.addAttribute("map",map);
                return "client";
            } else {
                TreeMap<String,Customer> map = customerService.retrieveSearchByStaff(name,search);
                model.addAttribute("map",map);
                return "client";                
            }    
        }
    }    
    
    @RequestMapping(value = "/customerAccounts", method = RequestMethod.GET)
    public String staffAccount(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) throws ParseException {
        String name = (String)session.getAttribute("username");
        if(name.equals("admin")){
            if(parameters.get("male") != null){
                TreeMap<String,Customer> map = customerService.retrieveMaleCustomers();
                model.addAttribute("map",map);
                return "client";
            } else if(parameters.get("female") != null){
                TreeMap<String,Customer> map = customerService.retrieveFemaleCustomers();
                model.addAttribute("map",map);
                return "client";
            } else {       
                TreeMap<String,Customer> map = customerService.retrieveAllCustomers();
                model.addAttribute("map",map);
                return "client";
            }
        } else {
            if(parameters.get("male") != null){
                TreeMap<String,Customer> map = customerService.retrieveMaleCustomersByStaff(name);
                model.addAttribute("map",map);
                return "client";
            } else if(parameters.get("female") != null){
                TreeMap<String,Customer> map = customerService.retrieveFemaleCustomersByStaff(name);
                model.addAttribute("map",map);
                return "client";
            } else {       
                TreeMap<String,Customer> map = customerService.retrieveAllCustomersByStaff(name);
                model.addAttribute("map",map);
                return "client";
            }            
        }
    }    
    
    @RequestMapping(value = "/clientTools", params="delete", method = RequestMethod.GET)
    public String deleteCustomer(@RequestParam Map<String, String> parameters, ModelMap map, HttpSession session) {
            int id = Integer.parseInt(parameters.get("delete"));
            String message = "Customer ID No: " + id + " has been successfully deleted";
            customerService.deleteCustomer(id);
            map.addAttribute("success", message);
            return "client";
    }
    
    @RequestMapping(value = "/clientTools", params="edit", method = RequestMethod.GET)
    public String editCustomer(@RequestParam Map<String, String> parameters, ModelMap map, HttpSession session) {        
        String id = parameters.get("edit");
        map.addAttribute("id", id);
        return "editCustomer";
    }    
    
    @RequestMapping(value = "/clientTools", params="cancel", method = RequestMethod.GET)
    public String cancelEdit(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) throws ParseException {
        return "client";
    }  
    
    @RequestMapping(value = "/customerUpdate", params="submit", method = RequestMethod.GET)
    public String customerUpdate(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) throws ParseException {
        
        if (parameters.get("name") == null || parameters.get("name").length() == 0 
            || parameters.get("gender") == null || parameters.get("gender").length() == 0 
            || parameters.get("number") == null || parameters.get("number").length() == 0 
            || parameters.get("email") == null || parameters.get("email").length() == 0 
            || parameters.get("id") == null || parameters.get("id").length() == 0 
            || parameters.get("dob") == null || parameters.get("dob").length() == 0)
        {
            String number = parameters.get("id");
            model.addAttribute("error","The following Field(s) cannot be empty:");
            if(parameters.get("name") == null || parameters.get("name").length() == 0){
                model.addAttribute("name","Name");
            }
            if(parameters.get("number") == null || parameters.get("number").length() == 0){
                model.addAttribute("number","Phone Number");
            }
            if(parameters.get("email") == null || parameters.get("email").length() == 0){
                model.addAttribute("email","Email");
            } 
            if(parameters.get("gender") == null || parameters.get("gender").length() == 0){
                model.addAttribute("gender","Gender");
            }
            if(parameters.get("regDate") == null || parameters.get("regDate").length() == 0){
                model.addAttribute("regDate","Registration Date");
            }
            if(parameters.get("dob") == null || parameters.get("dob").length() == 0){
                model.addAttribute("dob","Date of Birth");
            }
            model.addAttribute("id", number.trim());
            return "editCustomer";
        }
        
        String number = parameters.get("id");
        String name = parameters.get("name").trim();
        Pattern pat = Pattern.compile("[a-z]");
        Matcher m = pat.matcher(name.toLowerCase());
        if(!m.find()){
            model.addAttribute("error","Name should not be empty and must contain English letters only.");
            model.addAttribute("id", number.trim());
            return "editCustomer";
        }        
        
        String email = parameters.get("email").trim();
        pat = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
        m = pat.matcher(email.toLowerCase());
        if (!m.matches()) {
            model.addAttribute("error", "Email is not in the correct format");
            model.addAttribute("id", number.trim());
            return "editCustomer";
        } else if (!email.contains(".com")){
            model.addAttribute("error", "Email is not in the correct format");
            model.addAttribute("id", number.trim());
            return "editCustomer";
        }
        
        String phoneNum = parameters.get("number").trim();
        int phone = 0;
        
        try{
            phone = Integer.parseInt(phoneNum);
            if(phoneNum.length() != 8){
                model.addAttribute("error","Phone number must contain 8 digits.");
                model.addAttribute("id", number.trim());
                return "editCustomer";  
            }
        }catch(NumberFormatException e){
            model.addAttribute("error","Invalid phone number.");
            model.addAttribute("id", number.trim());
            return "editCustomer";  
        }
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dob = parameters.get("dob").trim();
        if(dob.length() != 10){
            model.addAttribute("error","Date of Birth must be in dd-mm-yyyy.");
            model.addAttribute("id", number.trim());
            return "editCustomer";             
        }
        
        Date dobDate;
        java.sql.Date sqlDate = null;
        String strSqlDate = "";
        try {
            dobDate = format.parse(dob);
            sqlDate = new java.sql.Date(dobDate.getTime());
            strSqlDate = sqlDate + "";
            
        } catch (ParseException ex) {
            model.addAttribute("error","Date of Birth is invalid.");
            model.addAttribute("id", number.trim());
            return "client";
        }
        
        String r = parameters.get("regDate").trim();
        String regDateStr = ""; 
        Date regDate;
        java.sql.Date sqlRegDate = null;        
        try{
            regDate = format.parse(r);
            sqlRegDate = new java.sql.Date(regDate.getTime());
            regDateStr = sqlRegDate + "";
        }catch(ParseException e){
            model.addAttribute("error","Registered Date is invalid.");
            model.addAttribute("id", number.trim());
            return "editCustomer";
        }
        
        if(!strSqlDate.equals(regDateStr) && sqlRegDate.before(sqlDate)){
            model.addAttribute("error","Date of Birth needs to be before the Registered Date.");
            model.addAttribute("id", number.trim());
            return "editCustomer";
        }        
        
        String gender = parameters.get("gender");
        String remarks = parameters.get("remarks");
        String username = (String)session.getAttribute("username");
        customerService.updateCustomer(name,phoneNum,regDateStr,strSqlDate,email,gender,username,remarks);
        String message = "Customer ID No: " + phone + " has been successfully updated";
        model.addAttribute("success", message);
        return "client"; 
    }
    
    @RequestMapping(value = "/customerUpdate", params="cancel", method = RequestMethod.GET)
    public String cancelUpdate(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) throws ParseException {
        return "client";
    }    
}    
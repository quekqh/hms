/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.controller;

import fyp.hms.model.Appointment;
import fyp.hms.model.Customer;
import fyp.hms.model.Payment;
import fyp.hms.model.Services;
import fyp.hms.model.User;
import fyp.hms.service.AppointmentService;
import fyp.hms.service.PaymentService;
import fyp.hms.service.ServiceService;
import fyp.hms.service.UserService;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Low Kang Li
 */
@Controller
public class PaymentController {
    @Autowired
    AppointmentService appointmentService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    ServiceService serviceService;
    
    @Autowired
    PaymentService paymentService;
    
    @RequestMapping(value = "/paymentForm", params="makePayment", method = RequestMethod.GET)
    public String makePayment(@RequestParam Map<String, String> parameters, HttpServletRequest request, ModelMap model,HttpSession session) {        
        String strApptId = (String)parameters.get("apptId");
        String staffUsername = (String)parameters.get("username");
        String strCustomerId = (String)parameters.get("customerId");
        String paymentAmount = (String)parameters.get("paymentAmount");
        String remarks = (String)parameters.get("remarks");
        String comment = (String)parameters.get("comment");
        String[] cutArray = (String[]) request.getParameterValues("cut");
        String[] styleArray = (String[]) request.getParameterValues("style");
        String[] colourArray = (String[]) request.getParameterValues("colour");
        String[] rpArray = (String[]) request.getParameterValues("rp");
        String[] treatmentArray = (String[]) request.getParameterValues("treatment");
        String services = "";
        
        String discount = (String)parameters.get("discount");
        String distAmt = (String)parameters.get("distAmt");
        if(remarks == null){
           remarks = "" ;
        }
        
        int apptId = 0;
        if(cutArray == null && styleArray == null && colourArray == null && rpArray == null && treatmentArray == null){
            apptId = Integer.parseInt(strApptId);
                Appointment appt = appointmentService.retrieveApptStatus(apptId);
                
                if(appt != null){
                    Customer cust = appointmentService.retrieveCust(appt.getCustomerId());
                    model.addAttribute("appt", appt);
                    
                    if(cust != null){
                        model.addAttribute("customer", cust);
                    }
                }
                
                TreeMap<String, User> users = userService.retrieveAll();
                if(users != null){
                    session.setAttribute("allStaff", users);
                }
                
                model.addAttribute("remarks", remarks);
                model.addAttribute("comment", comment);
                
                if(discount != null){
                    model.addAttribute("discount", discount);
                }
                
                if(distAmt != null){
                    model.addAttribute("distAmt", distAmt);
                }
                
                List<Services> colourServices = serviceService.retrieveColourServices();
                List<Services> rpServices = serviceService.retrieveRpServices();
                List<Services> treatmentServices = serviceService.retrieveTreatmentServices();
                List<Services> cutServices = serviceService.retrieveCutServices();
                List<Services> styleServices = serviceService.retrieveStyleServices();

                if(colourServices != null && rpServices != null && 
                        treatmentServices != null && cutServices != null && styleServices != null){    
                    model.addAttribute("colour",colourServices);
                    model.addAttribute("rp",rpServices);
                    model.addAttribute("treatment",treatmentServices);
                    model.addAttribute("cut",cutServices);
                    model.addAttribute("style",styleServices);
                }
            
            model.addAttribute("error", "Please select the service(s).");
            return "payment";
        }
        
        int custId = 0;
        double paymentAmtDouble = 0.0;
        try{
            apptId = Integer.parseInt(strApptId);
            custId = Integer.parseInt(strCustomerId);
            if(paymentAmount != null || paymentAmount.equals("0.0")){
                paymentAmtDouble = Double.parseDouble(paymentAmount);
                
            }
        }catch(NumberFormatException e){
            e.printStackTrace();
        }    
        
        
        if(styleArray != null){
            for(String s: styleArray){
                int indexValue = s.indexOf(":");
                String description = s.substring(0,indexValue);
                services += description + ",";
            }
        }
        
        if(colourArray != null){
            for(String s: colourArray){
                int indexValue = s.indexOf(":");
                String description = s.substring(0,indexValue);
                services += description + ",";
            }
        }
        
        if(rpArray != null){
            for(String s: rpArray){
                int indexValue = s.indexOf(":");
                String description = s.substring(0,indexValue);
                services += description + ",";
            }
        }
        
        if(cutArray != null){
            for(String s: cutArray){
                int indexValue = s.indexOf(":");
                String description = s.substring(0,indexValue);
                services += description + ",";
            }
        }
        
        if(treatmentArray != null){
            for(String s: treatmentArray){
                int indexValue = s.indexOf(":");
                String description = s.substring(0,indexValue);
                services += description + ",";
            }
        }
        
        
        services = services.substring(0,services.length()-1);
        
        
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        
        Payment payment = new Payment(apptId, staffUsername, custId , paymentAmtDouble, services, sqlDate, remarks, comment);
        paymentService.makePayment(payment);
        
        appointmentService.updateApptById(apptId);
        String username = (String)session.getAttribute("username");
        String role = (String)session.getAttribute("role");
        
        if(username != null && role != null){
            if(role.equals("staff")){
                List<Appointment> apptList = appointmentService.retrieveAppointment(username);
                model.addAttribute("apptList",apptList);
                return "appointment";
            }else{
                List<Appointment> apptList = appointmentService.retrieveAllAppointment();
                model.addAttribute("apptList",apptList);
                return "appointment";
            }
            
        }
        return "appointment";
    }
    
    @RequestMapping(value = "/paymentForm",params="cancelPayment", method = RequestMethod.GET)
    public String cancelAppt(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session){
        String strApptId = (String)parameters.get("apptId");
        if(strApptId != null && strApptId.length() > 0){
            int apptId = 0;
            
            try{
                apptId = Integer.parseInt(strApptId);
                Appointment appt = appointmentService.retrieveApptStatus(apptId);
                if(appt != null){
                    Customer cust = appointmentService.retrieveCust(appt.getCustomerId());
                    model.addAttribute("appt", appt);
                    //session.setAttribute("apptId",apptId);
                    if(cust != null){
                        model.addAttribute("customer", cust);
                    }
                }
            }catch(NumberFormatException e){
                e.printStackTrace();
            }    
        }
        
        
        return "updateAppointment";
        
    }
    
    @RequestMapping(value = "/paymentList", method = RequestMethod.GET)
    public String paymentList(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session){
        List<Payment> allPayments = paymentService.retrieveAllPayments();
        List<Payment> todayPayments = paymentService.retrieveTodayPayments();
        TreeMap<String, User> users = userService.retrieveAll();
        if(users != null){
            session.setAttribute("allStaff", users);
        }
        
        if(allPayments != null){
            model.addAttribute("allPayments", allPayments);
        }
        
        if(todayPayments != null){
            model.addAttribute("todayPayments", todayPayments);
        }else{
            model.addAttribute("error", "No payment has been made today.");
        }
        return "paymentTable";
        
    }
}

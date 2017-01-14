/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.service;

import fyp.hms.dao.PaymentDAO;
import fyp.hms.dao.ServiceDAO;
import fyp.hms.model.Payment;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Low Kang Li
 */
@Service("paymentService")
public class PaymentService {
    @Autowired
    HttpSession session;
    
    @Autowired
    PaymentDAO paymentDAO;
    
    public void makePayment(Payment payment){
        paymentDAO.makePayment(payment);
    }
    
    public List<Payment> retrieveAllPayments(){
        return paymentDAO.retrieveAllPayments();
    }
    
    public List<Payment> retrieveTodayPayments(){
        return paymentDAO.retrieveTodayPayments();
    }
}

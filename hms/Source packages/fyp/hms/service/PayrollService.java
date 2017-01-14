/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.service;

import fyp.hms.dao.PayrollDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HajaReethwan
 */
@Service("payrollService")
public class PayrollService {
    @Autowired
    HttpSession session;
    
    public void createPayroll(String userId, java.sql.Date date, int basic, int comm, int deduct, int gross, int cpf, int nett){
       PayrollDAO.createPayroll(userId, date, basic, comm, deduct, gross, cpf, nett);      
    }
    
    public void updatePayroll(String userId, java.sql.Date date, int basic, int comm, int deduct, int gross, int cpf, int nett){
       PayrollDAO.updatePayroll(userId, date, basic, comm, deduct, gross, cpf, nett);      
    }
    
    public void deletePayroll(String userId, java.sql.Date date){
       PayrollDAO.deletePayroll(userId, date);      
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.controller;

import fyp.hms.dao.PayrollDAO;
import fyp.hms.model.Payroll;
import fyp.hms.service.PayrollService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
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
 * @author HajaReethwan
 */
@Controller
public class PayrollController {
    @Autowired
    PayrollService payrollService;
    
    @RequestMapping(value = "/payroll", method = RequestMethod.GET)
    public String payroll(@RequestParam Map<String, String> parameters, HttpServletRequest request, ModelMap model,HttpSession session) {
        String name = parameters.get("user");
        String startDate = parameters.get("startDate");
        String endDate = parameters.get("endDate");        
        
        if(name == null || name.length() == 0 || startDate == null || startDate.length() == 0  || endDate == null || endDate.length() == 0){
            model.addAttribute("nameError","Name cannot be empty");
            model.addAttribute("startDateError","Start Date cannot be empty");
            model.addAttribute("endDateError","End Date cannot be empty");
            return "payslip";            
        }
       
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date start;
        Date end;
        java.sql.Date sqlDate = null;
        try {
            start = format.parse(startDate);
            sqlDate = new java.sql.Date(start.getTime());
        } catch (ParseException ex) {
            model.addAttribute("error","Start Date is invalid.");
            return "payslip";
        }
        
        try {
            end = format.parse(endDate); 
        } catch (ParseException ex) {
            model.addAttribute("error","End Date is invalid.");
            return "payslip";
        }
        
        if(start.equals(end) || start.after(end)){
            model.addAttribute("error","Start Date needs to be before the End Date.");
            return "payslip";
        }        

        Payroll payroll = PayrollDAO.retrieveUniquePayroll(name, sqlDate);
        if(payroll != null){
            model.addAttribute("error","Payroll already exists");
            return "payslip";            
        }
        
        model.put("name",name);
        model.put("startDate",startDate);
        model.put("endDate",endDate);
        return "payslipTable";
    }
    
    @RequestMapping(value = "/payslipFunction", params="submit", method = RequestMethod.GET)
    public String createPayroll(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) throws ParseException {
        
        String userId = parameters.get("id").trim();
        String date = parameters.get("date").trim();
        String basicPay = parameters.get("basicPay").trim();
        String commission = parameters.get("commission").trim();
        String deductions = parameters.get("deductions").trim();
        String grossPay = parameters.get("grossPay").trim();
        String cpfContribution = parameters.get("cpfContribution").trim();
        String nettPay = parameters.get("nettPay").trim();
        
        if (userId == null || date == null || basicPay == null || commission == null ||
            deductions == null || grossPay == null || cpfContribution == null || nettPay == null ||
            userId.length() == 0 || date.length() == 0 || basicPay.length() == 0 || commission.length() == 0 ||
            deductions.length() == 0 || grossPay.length() == 0 || cpfContribution.length() == 0 || nettPay.length() == 0) {
            model.addAttribute("userId","Description cannot be empty");
            model.addAttribute("deductions","Short Price cannot be empty");
            model.addAttribute("basicPay","Medium Price cannot be empty");
            model.addAttribute("commission","Long Price cannot be empty");
            model.addAttribute("deductions","Description cannot be empty");
            model.addAttribute("grossPay","Short Price cannot be empty");
            model.addAttribute("cpfContribution","Medium Price cannot be empty");
            model.addAttribute("nettPay","Long Price cannot be empty");
            return "payslipTable";
        }
        
        int basic = 0;
        int comm = 0;
        int deduct = 0;
        int gross = 0;
        int cpf = 0;
        int nett = 0;
        
        try{
            basic = Integer.parseInt(basicPay);
        }catch(NumberFormatException e){
            model.addAttribute("error", "Basic Pay must contain numbers only");
            return "payslipTable";
        }                        

        try{
            comm = Integer.parseInt(commission);
        }catch(NumberFormatException e){
            model.addAttribute("error", "Commission must contain numbers only");
            return "payslipTable";
        } 

        try{
            deduct = Integer.parseInt(deductions);
        }catch(NumberFormatException e){
            model.addAttribute("error", "Deductions Pay must contain numbers only");
            return "payslipTable";
        }
        
        try{
            gross = Integer.parseInt(grossPay);
        }catch(NumberFormatException e){
            model.addAttribute("error", "Gross Pay must contain numbers only");
            return "payslipTable";
        }         
        
        try{
            cpf = Integer.parseInt(cpfContribution);
        }catch(NumberFormatException e){
            model.addAttribute("error", "CPF must contain numbers only");
            return "payslipTable";
        } 

        try{
            nett = Integer.parseInt(nettPay);
        }catch(NumberFormatException e){
            model.addAttribute("error", "Basic Pay must contain numbers only");
            return "payslipTable";
        }
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        java.sql.Date sqlDate = null;
        try {
            startDate = format.parse(date);
            sqlDate = new java.sql.Date(startDate.getTime());
        } catch (ParseException ex) {
            model.addAttribute("error","Date is invalid.");
            return "payslipTable";
        }        
        
        payrollService.createPayroll(userId,sqlDate,basic,comm,deduct,gross,cpf,nett);
        String message = "Payslip has been successfully created!";
        model.addAttribute("success",message);
        return "payslip";
    }
    
    @RequestMapping(value = "/payslipUpdate", method = RequestMethod.GET)    
    public String payslipUpdate(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session){
        String userId = parameters.get("id");
        String date = parameters.get("date");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        java.sql.Date sqlDate = null;
        try {
            startDate = format.parse(date);
            sqlDate = new java.sql.Date(startDate.getTime());
        } catch (ParseException ex) {

        }
        Payroll payroll = PayrollDAO.retrieveUniquePayroll(userId, sqlDate);
        model.put("payroll",payroll);
        return "payslipUpdate";
    }
    
    @RequestMapping(value = "/viewPayslip", method = RequestMethod.GET)
    public String viewPayslip(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session){
        String userId = parameters.get("id");
        String date = parameters.get("date");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        java.sql.Date sqlDate = null;
        try {
            startDate = format.parse(date);
            sqlDate = new java.sql.Date(startDate.getTime());
        } catch (ParseException ex) {

        }
        Payroll payroll = PayrollDAO.retrieveUniquePayroll(userId, sqlDate);
        model.put("payroll",payroll);
        return "payslipTable";
    }

    @RequestMapping(value = "/payslipFunction", params="close", method = RequestMethod.GET)
    public String closePayslip(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session){
        return "payslip";
    }

    @RequestMapping(value = "/updatePayslip", params="submit", method = RequestMethod.GET)
    public String updatePayslip(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session){
        String userId = parameters.get("id").trim();
        String date = parameters.get("date").trim();
        String basicPay = parameters.get("basic").trim();
        String commission = parameters.get("comm").trim();
        String deductions = parameters.get("deduct").trim();
        String grossPay = parameters.get("gross").trim();
        String cpfContribution = parameters.get("cpf").trim();
        String nettPay = parameters.get("nett").trim();
        
        if (userId == null || date == null || basicPay == null || commission == null ||
            deductions == null || grossPay == null || cpfContribution == null || nettPay == null ||
            userId.length() == 0 || date.length() == 0 || basicPay.length() == 0 || commission.length() == 0 ||
            deductions.length() == 0 || grossPay.length() == 0 || cpfContribution.length() == 0 || nettPay.length() == 0) {
            model.addAttribute("userId","Description cannot be empty");
            model.addAttribute("deductions","Short Price cannot be empty");
            model.addAttribute("basicPay","Medium Price cannot be empty");
            model.addAttribute("commission","Long Price cannot be empty");
            model.addAttribute("deductions","Description cannot be empty");
            model.addAttribute("grossPay","Short Price cannot be empty");
            model.addAttribute("cpfContribution","Medium Price cannot be empty");
            model.addAttribute("nettPay","Long Price cannot be empty");
            return "payslipUpdate";
        }
        
        int basic = 0;
        int comm = 0;
        int deduct = 0;
        int gross = 0;
        int cpf = 0;
        int nett = 0;
        
        try{
            basic = Integer.parseInt(basicPay);
        }catch(NumberFormatException e){
            model.addAttribute("error", "Basic Pay must contain numbers only");
            return "payslipUpdate";
        }                        

        try{
            comm = Integer.parseInt(commission);
        }catch(NumberFormatException e){
            model.addAttribute("error", "Commission must contain numbers only");
            return "payslipUpdate";
        } 

        try{
            deduct = Integer.parseInt(deductions);
        }catch(NumberFormatException e){
            model.addAttribute("error", "Deductions Pay must contain numbers only");
            return "payslipUpdate";
        }
        
        try{
            gross = Integer.parseInt(grossPay);
        }catch(NumberFormatException e){
            model.addAttribute("error", "Gross Pay must contain numbers only");
            return "payslipUpdate";
        }         
        
        try{
            cpf = Integer.parseInt(cpfContribution);
        }catch(NumberFormatException e){
            model.addAttribute("error", "CPF must contain numbers only");
            return "payslipUpdate";
        } 

        try{
            nett = Integer.parseInt(nettPay);
        }catch(NumberFormatException e){
            model.addAttribute("error", "Basic Pay must contain numbers only");
            return "payslipUpdate";
        }
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        java.sql.Date sqlDate = null;
        try {
            startDate = format.parse(date);
            sqlDate = new java.sql.Date(startDate.getTime());
        } catch (ParseException ex) {
            model.addAttribute("error","Date is invalid.");
            return "payslipUpdate";
        }        
        payrollService.updatePayroll(userId,sqlDate,basic,comm,deduct,gross,cpf,nett);
        String message = "Payslip has been successfully updated!";
        model.addAttribute("success",message);                 
        return "payslip";
    }

    @RequestMapping(value = "/updatePayslip", params="cancel", method = RequestMethod.GET)
    public String cancelPayslip(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session){
        return "payslip";
    }

    @RequestMapping(value = "/deletePayslip", method = RequestMethod.GET)
    public String deletePayslip(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session){
        String userId = parameters.get("id");
        String date = parameters.get("date");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        java.sql.Date sqlDate = null;
        try {
            startDate = format.parse(date);
            sqlDate = new java.sql.Date(startDate.getTime());
        } catch (ParseException ex) {
            model.addAttribute("error","Date is invalid.");
            return "payslip";
        }        
        payrollService.deletePayroll(userId,sqlDate);
        String message = "Payslip has been successfully deleted!";
        model.addAttribute("success",message);                
        return "payslip";
    }    
    
}

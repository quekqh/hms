/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.controller;

import fyp.hms.model.Appointment;
import fyp.hms.model.Leave;
import fyp.hms.service.AppointmentService;
import fyp.hms.service.LeaveService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
public class LeaveController {
    @Autowired
    AppointmentService appointmentService;
    
    @Autowired
    LeaveService leaveService;
    
    @RequestMapping(value = "/createLeave", method = RequestMethod.GET)
    public String createLeave(@RequestParam Map<String, String> parameters, ModelMap model,HttpSession session) {        
        
        String uName = (String)parameters.get("username");
        String loginUserName = (String)parameters.get("loginUserName");
        String leaveDate = (String)parameters.get("leaveDate");
        String leaveType = (String)parameters.get("leaveType");
        String remarks = (String)parameters.get("remarks");
        
        String error = "";
        if(leaveDate == null || leaveDate.equals("")){
            error += " Leave Date,";
        }
        
        if(leaveType == null || leaveType.equals("")){
            error += " Leave Type,";
        }
        
        if(!error.equals("")){
            error = error.substring(0,error.length()-1);
            error += " cannot be blank.";
            model.addAttribute("error", error);
            model.addAttribute("username", uName);
            model.addAttribute("loginUserName", loginUserName);
            model.addAttribute("leaveDate", leaveDate);
            model.addAttribute("leaveType", leaveType);
            model.addAttribute("remarks", remarks);
            return "createLeave";
        }
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date leaveDateConvert;
        java.sql.Date sqlDate = null;
        
        try {
            leaveDateConvert = format.parse(leaveDate);
            sqlDate = new java.sql.Date(leaveDateConvert.getTime());
            
        } catch (ParseException ex) {
            model.addAttribute("error","Invalid leave date.");
            model.addAttribute("username", uName);
            model.addAttribute("loginUserName", loginUserName);
            model.addAttribute("leaveDate", leaveDate);
            model.addAttribute("leaveType", leaveType);
            model.addAttribute("remarks", remarks);
            return "createLeave";
        }
        
        Calendar cal = Calendar.getInstance();
        //SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        
        cal.setTime(leaveDateConvert);// all done
        
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
            model.addAttribute("error", "You are not allowed to apply leave on Monday.");
            model.addAttribute("username", uName);
            model.addAttribute("loginUserName", loginUserName);
            model.addAttribute("leaveDate", leaveDate);
            model.addAttribute("leaveType", leaveType);
            model.addAttribute("remarks", remarks);
            return "createLeave";
        }
        //int firstDayOfWeek = cal.getFirstDayOfWeek();

        boolean isCreate = leaveService.applyLeave(uName, loginUserName, sqlDate, leaveType, remarks, "Pending");
        if(isCreate){
            model.addAttribute("success", "The leave has been applied successfully. Please wait for the confirmation from administration.");
        }
        
        String username = (String)session.getAttribute("username");
        String role = (String)session.getAttribute("role");
        
        if(username != null && role != null){
            if(role.equals("staff")){
                List<Leave> leaveList = leaveService.retrieveLeaves(username);
                model.addAttribute("leaveList",leaveList);
                return "leave";
            }else{
                List<Leave> leaveList = leaveService.retrieveAllLeaves();
                model.addAttribute("leaveList",leaveList);
                
                return "leave";
            }
            
        }
        return "leave";
    }
    
    @RequestMapping(value = "/updateLeave", method = RequestMethod.GET)
    public String updateLeave(@RequestParam Map<String, String> parameters, ModelMap model,HttpSession session) {        
        String strLeaveId = (String)parameters.get("leaveId");
        
        int leaveId = 0;
        try{
            leaveId = Integer.parseInt(strLeaveId);
            
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        
        
        Leave leave = leaveService.retrieveLeavesById(leaveId);
        
        if(leave != null){
            model.addAttribute("leave",leave);
        }
        
        return "editLeave";
    }
    
    @RequestMapping(value = "/updateLeaveForm", params="updateLeave", method = RequestMethod.GET)
    public String updateLeaveForm(@RequestParam Map<String, String> parameters, ModelMap model,HttpSession session) {        
        String strLeaveId = (String)parameters.get("leaveId");
        String uName = (String)parameters.get("username");
        String loginUserName = (String)parameters.get("loginUserName");
        String leaveDate = (String)parameters.get("leaveDate");
        String leaveType = (String)parameters.get("leaveType");
        String remarks = (String)parameters.get("remarks");
        
        String error = "";
        if(leaveDate == null || leaveDate.equals("")){
            error += " Leave Date,";
        }
        
        if(leaveType == null || leaveType.equals("")){
            error += " Leave Type,";
        }
        
        if(!error.equals("")){
            error = error.substring(0,error.length()-1);
            error += " cannot be blank.";
            model.addAttribute("error", error);
            model.addAttribute("username", uName);
            model.addAttribute("loginUserName", loginUserName);
            model.addAttribute("leaveDate", leaveDate);
            model.addAttribute("leaveType", leaveType);
            model.addAttribute("remarks", remarks);
            return "createLeave";
        }
        
        int leaveId = 0;
        if(strLeaveId != null){
            try{
               leaveId = Integer.parseInt(strLeaveId);
            }catch(NumberFormatException e){
                e.printStackTrace();
            }
        }
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date leaveDateConvert;
        java.sql.Date sqlDate = null;
        
        try {
            leaveDateConvert = format.parse(leaveDate);
            sqlDate = new java.sql.Date(leaveDateConvert.getTime());
            
        } catch (ParseException ex) {
            model.addAttribute("error","Invalid leave date.");
            model.addAttribute("username", uName);
            model.addAttribute("loginUserName", loginUserName);
            model.addAttribute("leaveDate", leaveDate);
            model.addAttribute("leaveType", leaveType);
            model.addAttribute("remarks", remarks);
            return "createLeave";
        }
        
        Calendar cal = Calendar.getInstance();
        //SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        
        cal.setTime(leaveDateConvert);// all done
        
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
            model.addAttribute("error", "You are not allowed to apply leave on Monday.");
            model.addAttribute("username", uName);
            model.addAttribute("loginUserName", loginUserName);
            model.addAttribute("leaveDate", leaveDate);
            model.addAttribute("leaveType", leaveType);
            model.addAttribute("remarks", remarks);
            return "createLeave";
        }
        //int firstDayOfWeek = cal.getFirstDayOfWeek();

        boolean isUpdate = leaveService.updateLeave(leaveId, uName, loginUserName, sqlDate, leaveType, remarks, "Pending");
        if(isUpdate){
            model.addAttribute("success", "The leave has been updated successfully. Please wait for the confirmation from administration.");
        }
        
        String username = (String)session.getAttribute("username");
        String role = (String)session.getAttribute("role");
        
        if(username != null && role != null){
            if(role.equals("staff")){
                List<Leave> leaveList = leaveService.retrieveLeaves(username);
                model.addAttribute("leaveList",leaveList);
                return "leave";
            }else{
                List<Leave> leaveList = leaveService.retrieveAllLeaves();
                model.addAttribute("leaveList",leaveList);
                
                return "leave";
            }
            
        }
        return "leave";
    }
    
    @RequestMapping(value = "/updateLeaveForm",params="cancelLeave", method = RequestMethod.GET)
    public String cancelLeave(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session){
        String username = (String)session.getAttribute("username");
        String role = (String)session.getAttribute("role");
        
        if(username != null && role != null){
            if(role.equals("staff")){
                List<Leave> leaveList = leaveService.retrieveLeaves(username);
                model.addAttribute("leaveList",leaveList);
                return "leave";
            }else{
                List<Leave> leaveList = leaveService.retrieveAllLeaves();
                model.addAttribute("leaveList",leaveList);
                
                return "leave";
            }
            
        }
        return "leave";
    }
    
    @RequestMapping(value = "/updateLeaveForm",params="deleteLeave", method = RequestMethod.GET)
    public String deleteLeave(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session){
        String strLeaveId = (String)parameters.get("leaveId");
        if(strLeaveId != null && strLeaveId.length() > 0){
            int leaveId = 0;
            
            try{
                leaveId = Integer.parseInt(strLeaveId);
                  
                leaveService.deleteLeave(leaveId);
                  
            }catch(NumberFormatException e){
                e.printStackTrace();
            }    
        }
        
        model.addAttribute("success", "Leave has been deleted successfully.");
        
        String username = (String)session.getAttribute("username");
        String role = (String)session.getAttribute("role");
        
        if(username != null && role != null){
            if(role.equals("staff")){
                List<Leave> leaveList = leaveService.retrieveLeaves(username);
                model.addAttribute("leaveList",leaveList);
                return "leave";
            }else{
                List<Leave> leaveList = leaveService.retrieveAllLeaves();
                model.addAttribute("leaveList",leaveList);
                
                return "leave";
            }
            
        }
        return "leave";
    }
    
    @RequestMapping(value = "/updateLeaveForm",params="approveLeave", method = RequestMethod.GET)
    public String approveLeave(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session){
        String strLeaveId = (String)parameters.get("leaveId");
        int leaveId = 0;
        if(strLeaveId != null && strLeaveId.length() > 0){
            try{
                leaveId = Integer.parseInt(strLeaveId);
              
                leaveService.approveLeave(leaveId);
            }catch(NumberFormatException e){
                e.printStackTrace();
            }    
        }
        
        
        String username = (String)session.getAttribute("username");
        String role = (String)session.getAttribute("role");
        
        if(username != null && role != null){
            if(role.equals("staff")){
                List<Leave> leaveList = leaveService.retrieveLeaves(username);
                model.addAttribute("leaveList",leaveList);
                return "leave";
            }else{
                List<Leave> leaveList = leaveService.retrieveAllLeaves();
                model.addAttribute("leaveList",leaveList);
                
                return "leave";
            }
            
        }
        return "leave";
    }
    
    @RequestMapping(value = "/updateLeaveForm",params="cancelApprovedLeave", method = RequestMethod.GET)
    public String cancelApprovedLeave(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session){
        String strLeaveId = (String)parameters.get("leaveId");
        int leaveId = 0;
        if(strLeaveId != null && strLeaveId.length() > 0){
            try{
                leaveId = Integer.parseInt(strLeaveId);
              
                leaveService.cancelApproveLeave(leaveId);
            }catch(NumberFormatException e){
                e.printStackTrace();
            }    
        }
        
        
        String username = (String)session.getAttribute("username");
        String role = (String)session.getAttribute("role");
        
        if(username != null && role != null){
            if(role.equals("staff")){
                List<Leave> leaveList = leaveService.retrieveLeaves(username);
                model.addAttribute("leaveList",leaveList);
                return "leave";
            }else{
                List<Leave> leaveList = leaveService.retrieveAllLeaves();
                model.addAttribute("leaveList",leaveList);
                
                return "leave";
            }
            
        }
        return "leave";
    }
    
    @RequestMapping(value = "/leaveCalendarFilter", method = RequestMethod.GET)
    public String leaveCalendarFilter(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {        
        String username = (String)session.getAttribute("username");
        String role = (String)session.getAttribute("role");
        
        String hairStylist = parameters.get("hairStylist");
        
        if(hairStylist != null && hairStylist.length() > 0){
            if(hairStylist.equals("all")){
                List<Leave> leaveList = leaveService.retrieveAllLeaves();
                model.addAttribute("filter",hairStylist);
                model.addAttribute("leaveList",leaveList);
            }else{
                List<Leave> leaveList = leaveService.retrieveLeaves(hairStylist);
                model.addAttribute("filter",hairStylist);
                model.addAttribute("leaveList",leaveList);
            }
        }
        
        return "leave";
    }
}

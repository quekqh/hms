/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.controller;

import fyp.hms.model.Leave;
import fyp.hms.model.Services;
import fyp.hms.model.User;
import fyp.hms.service.LeaveService;
import fyp.hms.service.ServiceService;
import fyp.hms.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Asus
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;
    
    @Autowired
    LeaveService leaveService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(ModelMap model,HttpSession session) {
        String name = (String)session.getAttribute("username");
        if(name != null){
            if(name.equals("admin")){
                return "admin";
            }else{
                return "admin";
            }        
        }
        return "login";
    }
    
    @RequestMapping(value = "/forgotPwd", method = RequestMethod.GET)
    public String forgotPwd(ModelMap model) {
        return "forgotPassword";
    }
    
    
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String authenticate(@RequestParam Map<String, String> parameters,ModelMap model,HttpSession session) {
        
        if (parameters.get("username").length() == 0 ||parameters.get("password").length() == 0 ) {
                
            model.addAttribute("error","Field(s) cannot be empty.");
            return "login";
        }
        
        String username = parameters.get("username");
        String password = parameters.get("password");
        
        String verify = userService.authenticate(username, password);
        
        List<User> usersWithoutAdmin = userService.retrieveUsers();
        session.setAttribute("usersWithoutAdmin", usersWithoutAdmin);
        if(verify == null){
            model.addAttribute("error", "Invalid username/password. Please try again.");
        }else{
            User loginUser = userService.retrieveLoginUser(username);
            if(loginUser != null){
                session.setAttribute("loginUser",loginUser);
            }
            TreeMap<String, User> users = userService.retrieveAll();
            session.setAttribute("allStaff", users);
            if(verify.equals("admin")){

                return "admin";

            }else{
                return "admin";
            }
        }
        
        return "login";
    }
    
    @RequestMapping(value = "/getPassword", method = RequestMethod.POST)
    public String getPassword(@RequestParam Map<String, String> parameters,ModelMap model,HttpSession session) {
        String email = parameters.get("email");
        String confirmEmail = parameters.get("confirmEmail");
        if (parameters.get("email").length() == 0 ||parameters.get("confirmEmail").length() == 0 ) {
                
            model.addAttribute("error","Field(s) cannot be empty.");
            model.addAttribute("email", email);
            model.addAttribute("confirmEmail", confirmEmail);
            return "forgotPassword";
        }
        
        
        
        if(!email.equals(confirmEmail)){
            model.addAttribute("error","Your email and confirmation email do not match.");
            model.addAttribute("email", email);
            model.addAttribute("confirmEmail", confirmEmail);
            
            return "forgotPassword";
        }
        
        boolean isEmailExist = userService.isEmailExist(email);
        if(!isEmailExist){
            model.addAttribute("error", "Email does not exist. Please sign up.");
            model.addAttribute("email", email);
            model.addAttribute("confirmEmail", confirmEmail);
            return "forgotPassword";
        }
        
        boolean emailStatus = userService.sendPasswordEmail(email);  
        if(emailStatus){
            model.addAttribute("success", "Password has been sent to your email.");
            return "login";
        }
        return "login";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("username");
        session.removeAttribute("role");
        session.invalidate();
        return "login";
    }
    
    @RequestMapping(value = "/adminSetting", method = RequestMethod.GET)
    public String adminSetting(@RequestParam Map<String, String> parameters, HttpSession session) {        
        return "adminSetting";
    }
    
    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public String services(@RequestParam Map<String, String> parameters, HttpSession session) {        
        return "services";
    }
    
    @RequestMapping(value = "/payslip", method = RequestMethod.GET)
    public String payslip(@RequestParam Map<String, String> parameters, HttpSession session) {        
        return "payslip";
    }
     
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(@RequestParam Map<String, String> parameters, HttpSession session) {        
        return "admin";
    }    
    
    @RequestMapping(value = "/goBackToLogin", method = RequestMethod.GET)
    public String goBackToLogin(@RequestParam Map<String, String> parameters, HttpSession session) {        
        return "login";
    }
    
    @RequestMapping(value = "/calender", method = RequestMethod.GET)
    public String calender(@RequestParam Map<String, String> parameters, HttpSession session) {        
        return "schedule";
    }
    
    @RequestMapping(value = "/leaveCalendar", method = RequestMethod.GET)
    public String leaveCalendar(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {        
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
    
    @RequestMapping(value = "/applyLeave", method = RequestMethod.GET)
    public String applyLeave(@RequestParam Map<String, String> parameters, HttpSession session) {        
        return "createLeave";
    }
    
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String customer(@RequestParam Map<String, String> parameters, HttpSession session) {        
        return "client";
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(ModelMap model) {
        return "signup";
    }
    
    @RequestMapping(value = "/staffSearch", method = RequestMethod.GET)
    public String staffSearch(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        String search = (String)parameters.get("search");
        String username = (String)session.getAttribute("username");
        String role = (String)session.getAttribute("role");
        
        if(search != null || search.length() >0 ){
            if(username != null && role != null){
                if(role.equals("admin")){
                    TreeMap<String, User> map = userService.retrieveSearch(search);
                    model.addAttribute("map",map);
                    return "adminSetting";
                }

            }
        
            
        }
        
        model.addAttribute("error","search bar cannot be blank.");
        return "adminSetting";
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(@RequestParam Map<String, String> parameters, ModelMap model,HttpSession session) {
        //check for empty fields
            
        if (parameters.get("username").length() == 0 || parameters.get("name").length() == 0
                ||parameters.get("password").length() == 0 || parameters.get("passwordRetype").length() == 0 
                ||parameters.get("address").length() == 0 || parameters.get("phone").length() == 0 
                ||parameters.get("email").length() == 0 ||parameters.get("securityCode").length() == 0) {
            model.addAttribute("error","Field(s) cannot be empty.");
            model.addAttribute("username", parameters.get("username"));
            model.addAttribute("name", parameters.get("name"));
            model.addAttribute("password", parameters.get("password"));
            model.addAttribute("passwordRetype", parameters.get("passwordRetype"));
            model.addAttribute("address", parameters.get("address"));
            model.addAttribute("phone", parameters.get("phone"));
            model.addAttribute("email", parameters.get("email"));
            model.addAttribute("role", parameters.get("role"));
            model.addAttribute("securityCode", parameters.get("securityCode"));
            return "signup";
        }
        
        String username = parameters.get("username");
        String name = parameters.get("name");
        String password = parameters.get("password");
        String passwordRetype = parameters.get("passwordRetype");
        String address = parameters.get("address");
        String role = parameters.get("role");
        String email = parameters.get("email");
        String strPhoneNo = parameters.get("phone");
        String securityCode = parameters.get("securityCode");
        //check whether username exists 
        if(role == null){
            model.addAttribute("error","Please select role.");  
            model.addAttribute("username", username);
            model.addAttribute("name", name);
            model.addAttribute("password", password);
            model.addAttribute("passwordRetype", passwordRetype);
            model.addAttribute("address", address);
            model.addAttribute("phone", strPhoneNo);
            model.addAttribute("email", email);
            model.addAttribute("role", role);
            model.addAttribute("securityCode",securityCode);
            return "signup";
        }
        
        boolean securityStatus = userService.isSecurityCodeMatch(securityCode);
        
        if(!securityStatus){
            model.addAttribute("error","Your security code is wrong.");  
            model.addAttribute("username", username);
            model.addAttribute("name", name);
            model.addAttribute("password", password);
            model.addAttribute("passwordRetype", passwordRetype);
            model.addAttribute("address", address);
            model.addAttribute("phone", strPhoneNo);
            model.addAttribute("email", email);
            model.addAttribute("role", role);
            model.addAttribute("securityCode",securityCode);
            return "signup";
        }
        
        boolean status = userService.isUsernameExist(username);
        if(status){
            model.addAttribute("error","username exists.");  
            model.addAttribute("username", username);
            model.addAttribute("name", name);
            model.addAttribute("password", password);
            model.addAttribute("passwordRetype", passwordRetype);
            model.addAttribute("address", address);
            model.addAttribute("phone", strPhoneNo);
            model.addAttribute("email", email);
            model.addAttribute("role", role);
            model.addAttribute("securityCode",securityCode);
            return "signup";
        }
        
        //check whether both password and confirm password are the same
        if(!password.equals(passwordRetype)){
            model.addAttribute("error","Your password and confirmation password do not match.");
            model.addAttribute("username", username);
            model.addAttribute("name", name);
            model.addAttribute("password", password);
            model.addAttribute("passwordRetype", passwordRetype);
            model.addAttribute("address", address);
            model.addAttribute("phone", strPhoneNo);
            model.addAttribute("email", email);
            model.addAttribute("role", role);
            model.addAttribute("securityCode",securityCode);
            return "signup";
        }
        
        //check email format
        email = email.toLowerCase();
        Pattern pat = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
        Matcher m = pat.matcher(email);
        if (!m.matches()) {
            model.addAttribute("error", "Invalid email");
            model.addAttribute("username", username);
            model.addAttribute("name", name);
            model.addAttribute("password", password);
            model.addAttribute("passwordRetype", passwordRetype);
            model.addAttribute("address", address);
            model.addAttribute("phone", strPhoneNo);
            model.addAttribute("email", email);
            model.addAttribute("role", role);
            model.addAttribute("securityCode",securityCode);
            return "signup";
        }
        
        //check if email exists
        boolean isEmailExist = userService.isEmailExist(email);
        if(isEmailExist){
            model.addAttribute("error","Email exists.");  
            model.addAttribute("username", username);
            model.addAttribute("name", name);
            model.addAttribute("password", password);
            model.addAttribute("passwordRetype", passwordRetype);
            model.addAttribute("address", address);
            model.addAttribute("phone", strPhoneNo);
            model.addAttribute("email", email);
            model.addAttribute("role", role);
            model.addAttribute("securityCode",securityCode);
            return "signup";
        }
        
        //check whether password is at least 7 characters and contains at least one uppper case, one lower case,
        //one digit and one special character.
        Pattern passwordPattern = Pattern.compile("^.*(?=.{7,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[*@#$%^&+=]).*$");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if (!passwordMatcher.matches()) {
            model.addAttribute("error", "Password must at least 7 characters which contains at least <br>"
                    + "1. one lower case letter (a-z) <br> "
                    + "2. one upper case letter (A-Z) <br>"
                    + "3. one digit (0-9) <br> "
                    + "4. one special character.(e.g *@#$%^&+=\\)");
            
            model.addAttribute("username", username);
            model.addAttribute("name", name);
            model.addAttribute("password", password);
            model.addAttribute("passwordRetype", passwordRetype);
            model.addAttribute("address", address);
            model.addAttribute("phone", strPhoneNo);
            model.addAttribute("email", email);
            model.addAttribute("role", role);
            model.addAttribute("securityCode",securityCode);
            return "signup";
        }        
        
        //check whether it is a valid phone number (make sure numeric number is submitted)
        int phoneNo = 0;
        try{
            phoneNo = Integer.parseInt(strPhoneNo);
            if(strPhoneNo.length() != 8){
                model.addAttribute("error","Phone number must contain 8 digits.");
                model.addAttribute("username", username);
                model.addAttribute("name", name);
                model.addAttribute("password", password);
                model.addAttribute("passwordRetype", passwordRetype);
                model.addAttribute("address", address);
                model.addAttribute("phone", strPhoneNo);
                model.addAttribute("email", email);
                model.addAttribute("role", role);
                model.addAttribute("securityCode",securityCode);
                return "signup";  
            }
        }catch(NumberFormatException e){
            model.addAttribute("error","Invalid Phone Number.");
            model.addAttribute("username", username);
            model.addAttribute("name", name);
            model.addAttribute("password", password);
            model.addAttribute("passwordRetype", passwordRetype);
            model.addAttribute("address", address);
            model.addAttribute("phone", strPhoneNo);
            model.addAttribute("email", email);
            model.addAttribute("role", role);
            model.addAttribute("securityCode",securityCode);
            return "signup";  
        }
        
        
        userService.insertUser(username,name, password, role, phoneNo, email, address);   
        session.setAttribute("username", username);
        return "staff";  
        
    }
       
    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String changePassword(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        return "changePassword";       
    }
    
    @RequestMapping(value = "/changeSecurityCode", method = RequestMethod.GET)
    public String changeSecurityCode(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        return "changeSecurityCode"; 
    }
    
    @RequestMapping(value = "/authenticatepassword", method = RequestMethod.POST)
    public String authenticatePassword(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {

        if (parameters.get("currentpassword") == null || parameters.get("currentpassword").length() == 0 
            || parameters.get("newpassword") == null || parameters.get("newpassword").length() == 0 
            || parameters.get("password") == null || parameters.get("password").length() == 0) {
            model.addAttribute("error","The following Field(s) cannot be empty:");
            if(parameters.get("currentpassword") == null || parameters.get("currentpassword").length() == 0){
                model.addAttribute("current","Current Password");
            }
            if(parameters.get("newpassword") == null || parameters.get("newpassword").length() == 0){
                model.addAttribute("new","New Password");
            }
            if(parameters.get("password") == null || parameters.get("password").length() == 0){
                model.addAttribute("reenter","Re-Enter New Password");
            } 
            return "changePassword";
        }        
        
        String currentpassword = parameters.get("currentpassword");
        String newpassword = parameters.get("newpassword");
        String password = parameters.get("password");
        String username = (String)session.getAttribute("username");
        
        String verify = userService.authenticate(username, currentpassword);
        if(verify == null){
            model.addAttribute("error", "Wrong password. Please try again.");
            return "changePassword";
        }
        
        Pattern passwordPattern = Pattern.compile("^.*(?=.{7,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[*@#$%^&+=]).*$");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if (!passwordMatcher.find()) {
            model.addAttribute("error", "Password must at least 7 characters which contains at least <br>"
                    + "1. one lower case letter (a-z) <br> "
                    + "2. one upper case letter (A-Z) <br>"
                    + "3. one digit (0-9) <br> "
                    + "4. one special character.(e.g *@#$%^&+=\\)");
            return "changePassword";
        }    
        
        if(passwordMatcher.matches() && newpassword.equals(password)){
            userService.updatePassword(username, newpassword);
            model.addAttribute("success", "Password Updated!");
            return "changePassword";
        }else{
            model.addAttribute("error", "Your re-entered password does not match your new password. Please try again.");
            return "changePassword";
        }
    } 
    
    @RequestMapping(value = "/editProfile", method = RequestMethod.GET)
    public String editProfile(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        User user = (User)session.getAttribute("loginUser");
        
        if(user != null){
            User userRetrieve = userService.retrieveLoginUser(user.getUsername());
        
            if(userRetrieve != null){
                model.addAttribute("userRetrieve", userRetrieve);
            }
        }
        return "editProfile";
    }
    
    @RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
    public String deleteAccount(@RequestParam Map<String, String> parameters, HttpSession session) {
        String username = parameters.get("username");
        userService.deleteUser(username);
        return "adminSetting";
    }
    
    @RequestMapping(value = "/updateprofile", method = RequestMethod.POST)
    public String updateProfile(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        String name = parameters.get("name");
        String address = parameters.get("address");
        String number = parameters.get("number");
        String email = parameters.get("email");
        
        if (name == null || name.length() == 0 
            || address == null || address.length() == 0 
            || number == null || number.length() == 0 
            || email == null || email.length() == 0) {
            model.addAttribute("error","The following Field(s) cannot be empty:");
            if(parameters.get("name") == null || parameters.get("name").length() == 0){
                model.addAttribute("errorName","Name");
            }
            if(parameters.get("number") == null || parameters.get("number").length() == 0){
                model.addAttribute("errorNumber","Phone Number");
            }
            if(parameters.get("email") == null || parameters.get("email").length() == 0){
                model.addAttribute("errorEmail","Email");
            } 
            if(parameters.get("address") == null || parameters.get("address").length() == 0){
                model.addAttribute("errorAddress","Address");
            }
            model.addAttribute("name", name);
            model.addAttribute("email", email);
            model.addAttribute("address", address);
            model.addAttribute("number",number);
            return "editProfile";
        }
        
        //String name = parameters.get("name").trim();
        Pattern pat = Pattern.compile("[a-z]");
        Matcher m = pat.matcher(name.toLowerCase());
        if(!m.find()){
            model.addAttribute("error","Name should not be empty and must contain English letters only.");
            model.addAttribute("name", name.trim());
            model.addAttribute("email", email.trim());
            model.addAttribute("address", address.trim());
            model.addAttribute("number",number.trim());
            return "editProfile";
        }
        
        //String address = parameters.get("address").trim();
        //Pattern patAddress = Pattern.compile("[a-z0-9#]");
        //Matcher mAddress = patAddress.matcher(address.toLowerCase().trim());
        //if(!mAddress.find()){
            //model.addAttribute("error","Address should not be empty and must follow the right format.");
            //model.addAttribute("name", name.trim());
            //model.addAttribute("email", email.trim());
            //model.addAttribute("address", address.trim());
            //model.addAttribute("number",number.trim());
            //return "editProfile";
        //}
        
        //String email = parameters.get("email").trim();
        email = email.toLowerCase().trim();
        Pattern patEmail = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
        Matcher mEmail = patEmail.matcher(email);
        if (!mEmail.matches()) {
            model.addAttribute("error", "Email is not in the correct format");
            model.addAttribute("name", name.trim());
            model.addAttribute("email", email.trim());
            model.addAttribute("address", address.trim());
            model.addAttribute("number",number.trim());
            return "editProfile";
        } else if (!email.contains(".com")){
            model.addAttribute("error", "Email is not in the correct format");
            model.addAttribute("name", name.trim());
            model.addAttribute("email", email.trim());
            model.addAttribute("address", address.trim());
            model.addAttribute("number",number.trim());
            return "editProfile";
        }
        
        String phoneNum = parameters.get("number").trim();
        int phone = 0;
        
        try{
            phone = Integer.parseInt(phoneNum);
            if(phoneNum.length() != 8){
                model.addAttribute("error","Phone number must contain 8 digits.");
                model.addAttribute("name", name.trim());
                model.addAttribute("email", email.trim());
                model.addAttribute("address", address.trim());
                model.addAttribute("number",number.trim());
                return "editProfile";  
            }
        } catch(NumberFormatException e){
            model.addAttribute("error","Invalid Phone Number");
            model.addAttribute("name", name.trim());
            model.addAttribute("email", email.trim());
            model.addAttribute("address", address.trim());
            model.addAttribute("number",number.trim());
            return "editProfile";  
        }        
        
        String username = (String)session.getAttribute("username");
        userService.updateUser(username, name, phone, email, address.trim());
        model.addAttribute("success", "Profile Updated!");
        return "editProfile"; 
    }

    @RequestMapping(value = "/updatecode", method = RequestMethod.POST)
    public String updateSecurityCode(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        String currentcode = parameters.get("currentcode");
        String newcode = parameters.get("newcode");
        String code = parameters.get("code");
        String username = (String)session.getAttribute("username");
        
        if (currentcode.length() == 0 || currentcode == null || newcode.length() == 0 || newcode == null || code.length() == 0 || code == null) {    
            model.addAttribute("error","Field(s) cannot be empty.");
            return "changePassword";
        }   
        
        if(newcode.equals(code)){
            userService.updateSecurityCode(username, newcode);
            model.addAttribute("success", "Security Code Updated!");
            return "changePassword";
        }else{
            model.addAttribute("error", "Your re-entered security code does not match your new security code. Please try again.");
            return "changePassword";
        }
    } 
    
    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public String staffAccount(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        if(parameters.get("staff") != null){
            TreeMap<String,User> map = userService.retrieveStaff();
            model.addAttribute("map",map);
            return "adminSetting";
        } else if(parameters.get("freelance") != null){
            TreeMap<String,User> map = userService.retrieveFreelancer();
            model.addAttribute("map",map);
            return "adminSetting";
        } else {       
            TreeMap<String,User> map = userService.retrieveAll();
            model.addAttribute("map",map);
            return "adminSetting";
        }
    }       
}
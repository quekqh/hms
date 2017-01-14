/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.service;

import fyp.hms.dao.RoleDAO;
import fyp.hms.dao.ServiceDAO;
import fyp.hms.dao.UserDAO;
import fyp.hms.model.Role;
import fyp.hms.model.Services;
import fyp.hms.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */
@Service("userService")
public class UserService {
    
    @Autowired
    HttpSession session;
    
    public String authenticate(String username, String password) {
        String roleAuthenticate = null;
        if (username == null || password == null) {
            return roleAuthenticate;
        }

        User user = UserDAO.retrieveUser(username);
        
        
        if (user != null) {
            String userPassword = user.getPassword();
            int userRole = user.getRole();
            Role role = RoleDAO.retrieveRoleById(userRole);
            String roleDesc = role.getRoleDescription();
            
            if (password.equals(userPassword)) {         
                if(roleDesc.equals("admin")){
                    roleAuthenticate = "admin";
                    
                }else{
                    roleAuthenticate = "staff";
                    
                }
                
                session.setAttribute("username", username);
                session.setAttribute("role", roleAuthenticate);
            }
        }

        return roleAuthenticate;
    }
    
    public User retrieveUserByName(String name){
        
        if(name != null && name.length() > 0){
            User user = UserDAO.retrieveUserByName(name);
            if(user != null){
                return user;
            }
        }
        return null;
    }
    
    public void insertUser(String username, String name, String password, String role, int phoneNo, String email, String address) {
        
        Role r = RoleDAO.retrieveIdByRole(role);
        int roleId = r.getRoleID();
        
        User user = new User(username,name, password, roleId, phoneNo, email, address);
        UserDAO.insertUser(user);
    }
    
    public boolean isUsernameExist(String username) {
        
        User user = UserDAO.retrieveUser(username);
        if(user != null){
            return true;
        }
        
        return false;
    }    
    
    public boolean isSecurityCodeMatch(String securityCode) {
        
        String _securityCode = UserDAO.retrieveSecurityCode();
        if(securityCode.equals(_securityCode)){
            return true;
        }
        
        return false;
    }
    
    public boolean isEmailExist(String email) {
        
        User user = UserDAO.retrieveUserByEmail(email);
        if(user != null){
            return true;
        }
        
        return false;
    }  
    
    public boolean sendPasswordEmail(String email) {
        boolean status = false;
                
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication("harts.hairstylist@gmail.com", "harts2016");
              }
        });
        
        User user = UserDAO.retrieveUserByEmail(email);
        String username = user.getUsername();
        String password = user.getPassword();
        String name = user.getName();
        
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("harts.hairstylist@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Your request to retrieve your password");
            message.setText("Dear " + name + "," 
                    + "\n\nWe've received your request to retrieve your password."
                    + "\n\nUsername: " + username 
                    + "\nPassword: " + password 
                    + "\n"
                    + "\n\nBest Regards,"
                    + "\nHarts");

            Transport.send(message);
            status = true;
            
        } catch (MessagingException e) {
                throw new RuntimeException(e);
        }
    
        return status;
        
    }

    public boolean checkPassword(String username, String password) {
        User user = UserDAO.retrieveUser(username);
        if(user == null || username == null || username.equals("") || password == null || password.equals("")){
            return false;
        }
        String userPassword = user.getPassword();
        if(!userPassword.equals(user)){
            return false;
        }
        return true;
    }
    
    public void updatePassword(String username, String password){
        UserDAO.updateUserPassword(username, password);
    }
    
    public void updateUser(String username, String name, int number, String email, String address){
        UserDAO.updateProfile(username,name,number,email,address);
    }
    
    public void updateSecurityCode(String username, String code){
        UserDAO.updateSecurityCode(username, code);
    }
    
    public TreeMap<String,User> retrieveAllStaff(){
        return UserDAO.retrieveStaff();
    }
    
    public TreeMap<String,User> retrieveSearch(String search){
        return UserDAO.retrieveSearch(search);
    }
    
    public void deleteUser(String username){
        UserDAO.deleteUser(username);
    }
    
    public TreeMap<String,User> retrieveAll(){
        return UserDAO.retrieveAll();
    }
    
    public TreeMap<String,User> retrieveStaff(){
        return UserDAO.retrieveStaff();
    }
    
    public TreeMap<String,User> retrieveFreelancer(){
        return UserDAO.retrieveFreelancer();
    }       
    
    public List<User> retrieveUsers(){
        return UserDAO.retrieveUsers();
    }  
    
    public List<Services> retrieveColourServices() {
        return ServiceDAO.retrieveColourServices();
    }

    public User retrieveLoginUser(String username) {
        return UserDAO.retrieveUser(username);
    }
}

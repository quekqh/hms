/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.dao;

import fyp.hms.connection.ConnectionManager;
import fyp.hms.model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Asus
 */
@Repository("userDAO")
public class UserDAO {
    private static final String TBLNAME = "user";

    public static User retrieveUser(String username) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT username,name, password, role, phone_number, email, address, security_code FROM " + TBLNAME + " where username = '" + username + "'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
         
            //Retrieve by column name
            while (rs.next()){

                String usernameDb = rs.getString("username");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int role = rs.getInt("role");
                int phoneNo = rs.getInt("phone_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String securityCode = rs.getString("security_code");
                
                User userResult = new User(usernameDb,name, password, role, phoneNo, email, address, securityCode);
                return userResult;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        
        return null;
    }
    
    public static User retrieveUserByName(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT username,name, password, role, phone_number, email, address, security_code FROM " + TBLNAME + " where name = '" + name + "'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
         
            //Retrieve by column name
            while (rs.next()){

                String usernameDb = rs.getString("username");
                String _name = rs.getString("name");
                String password = rs.getString("password");
                int role = rs.getInt("role");
                int phoneNo = rs.getInt("phone_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String securityCode = rs.getString("security_code");
                
                User userResult = new User(usernameDb,_name, password, role, phoneNo, email, address, securityCode);
                return userResult;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        
        return null;
    }
    
    public static String retrieveSecurityCode() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT security_code FROM " + TBLNAME + " where role = 1";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
         
            //Retrieve by column name
            while (rs.next()){
                String securityCode = rs.getString("security_code");
                return securityCode;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        
        return null;
    }
    
    public static User retrieveUserByEmail(String email) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT username,name, password, role, phone_number, email, address FROM " + TBLNAME + " where email = '" + email + "'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
         
            //Retrieve by column name
            while (rs.next()){

                String usernameDb = rs.getString("username");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int role = rs.getInt("role");
                int phoneNo = rs.getInt("phone_number");
                String e = rs.getString("email");
                String address = rs.getString("address");
                
                User userResult = new User(usernameDb,name, password, role, phoneNo, e, address);
                return userResult;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        
        return null;
    }
    
    public static void insertUser(User user) {
        
        String username = user.getUsername();
        String name = user.getName();
        String password = user.getPassword();
        int role = user.getRole();
        int phoneNo = user.getPhoneNumber();
        String email = user.getEmail();
        String address = user.getAddress();
        
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps;
            String insertUser = "insert into user (username, name,password, role, phone_number, email, address, security_code) Values(?,?,?,?,?,?,?,?);";
            ps = conn.prepareStatement(insertUser);
            ps.setString(1, username);
            ps.setString(2, name);
            ps.setString(3, password);
            ps.setInt(4, role);
            ps.setInt(5, phoneNo);
            ps.setString(6, email);
            ps.setString(7, address);
            ps.setString(8, "");
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void updateUserPassword(String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        
        try {
            conn = ConnectionManager.getConnection();

            sql = "UPDATE " + TBLNAME + " SET password = '" + password + "' WHERE username = '" + username + "'";
            
            stmt = conn.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
    }

    public static void updateSecurityCode(String username, String code) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        
        try {
            conn = ConnectionManager.getConnection();

            sql = "UPDATE " + TBLNAME + " SET security_code = '" + code + "' WHERE username = '" + username + "'";
            
            stmt = conn.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
    }    
    
    public static void deleteUser(String username){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        
        try {
            conn = ConnectionManager.getConnection();

            sql = "DELETE from " + TBLNAME + " WHERE username = ?";
            
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
    }
    
    public static void updateProfile(String username, String name, int number, String email, String address) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        String num = number + "";
        
        try {
            conn = ConnectionManager.getConnection();

            sql = "UPDATE " + TBLNAME + " SET name = '" + name + "', phone_number = '" + num + "', email = '" + email + "', address = '" + address + "' WHERE username = '" + username + "'";
            
            stmt = conn.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
    }
    
    public static ArrayList<String> retrieveAllUsername() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        ArrayList<String> results = new ArrayList<String>();

        try {
            conn = ConnectionManager.getConnection();
            sql = "SELECT username FROM user order by name asc";
            stmt = conn.prepareStatement(sql);          
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String username = rs.getString("username");               
                results.add(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return results;
    }
    
    public static TreeMap<String, User> retrieveAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        TreeMap<String, User> results = new TreeMap<String, User>();

        try {
            conn = ConnectionManager.getConnection();
            sql = "SELECT username, name, password, role, phone_number, email, address, security_code FROM user where role <> 1 order by name asc";
            stmt = conn.prepareStatement(sql);          
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String username = rs.getString("username");               
                String name = rs.getString("name");               
                String password = rs.getString("password");               
                String role = rs.getString("role");              
                String phonenumber = rs.getString("phone_number");                
                String email = rs.getString("email");                
                String address = rs.getString("address");
                String code = rs.getString("security_code");
                User user = new User(username, name, password, Integer.parseInt(role), Integer.parseInt(phonenumber), address, email);
                results.put(name, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return results;
    }
    
    public static TreeMap<String, User> retrieveStaff() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        TreeMap<String, User> results = new TreeMap<String, User>();

        try {
            conn = ConnectionManager.getConnection();
            sql = "SELECT username, name, password, role, phone_number, email, address, security_code FROM user where role = 2 order by name asc";
            stmt = conn.prepareStatement(sql);          
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String username = rs.getString("username");               
                String name = rs.getString("name");               
                String password = rs.getString("password");               
                String role = rs.getString("role");              
                String phonenumber = rs.getString("phone_number");                
                String email = rs.getString("email");                
                String address = rs.getString("address");
                String code = rs.getString("security_code");
                User user = new User(username, name, password, Integer.parseInt(role), Integer.parseInt(phonenumber), address, email);
                results.put(name, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return results;
    }
    
    public static TreeMap<String, User> retrieveFreelancer() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        TreeMap<String, User> results = new TreeMap<String, User>();

        try {
            conn = ConnectionManager.getConnection();
            sql = "SELECT username, name, password, role, phone_number, email, address, security_code FROM user where role = 3 order by name asc";
            stmt = conn.prepareStatement(sql);          
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String username = rs.getString("username");               
                String name = rs.getString("name");               
                String password = rs.getString("password");               
                String role = rs.getString("role");              
                String phonenumber = rs.getString("phone_number");                
                String email = rs.getString("email");                
                String address = rs.getString("address");
                String code = rs.getString("security_code");
                User user = new User(username, name, password, Integer.parseInt(role), Integer.parseInt(phonenumber), address, email);
                results.put(name, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return results;
    }    

    public static TreeMap<String, User> retrieveSearch(String search) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        TreeMap<String, User> results = new TreeMap<String, User>();

        try {
            conn = ConnectionManager.getConnection();
            sql = "SELECT * FROM user where username like '%" + search + "%' or name like '%" + search + "%' order by name asc";
            stmt = conn.prepareStatement(sql);          
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String username = rs.getString("username");               
                String name = rs.getString("name");               
                String password = rs.getString("password");               
                String role = rs.getString("role");              
                String phonenumber = rs.getString("phone_number");                
                String email = rs.getString("email");                
                String address = rs.getString("address");
                String code = rs.getString("security_code");
                User user = new User(username, name, password, Integer.parseInt(role), Integer.parseInt(phonenumber), address, email);
                results.put(name, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return results;
    } 
    
    public static ArrayList<String> retrieveAllNames() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        ArrayList<String> results = new ArrayList<String>();

        try {
            conn = ConnectionManager.getConnection();
            sql = "SELECT name FROM user order by name asc";
            stmt = conn.prepareStatement(sql);          
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String name = rs.getString("name");               
                results.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return results;
    }
    
    public static String staffUsername(String name){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        String result = "";

        try {
            conn = ConnectionManager.getConnection();
            sql = "SELECT username FROM user where name = '" + name + "'";
            stmt = conn.prepareStatement(sql);          
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                result = rs.getString("username");               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return result;
    }    
    
    public static List<User> retrieveUsers() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<User> results = new ArrayList<User>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM " + TBLNAME + " where username <> 'admin'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                String username = rs.getString("username");               
                String name = rs.getString("name");               
                String password = rs.getString("password");               
                String role = rs.getString("role");              
                String phonenumber = rs.getString("phone_number");                
                String email = rs.getString("email");                
                String address = rs.getString("address");
                String code = rs.getString("security_code");
                User user = new User(username, name, password, Integer.parseInt(role), Integer.parseInt(phonenumber), address, email);
                
                results.add(user);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }

}

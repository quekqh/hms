/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.dao;

import fyp.hms.connection.ConnectionManager;
import fyp.hms.model.Customer;
import fyp.hms.model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TreeMap;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Low Kang Li
 */
@Repository("customerDAO")
public class CustomerDAO {
    private static final String TBLNAME = "customer";
    
    public static Customer retrieveCustomer(int customerId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        
        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM " + TBLNAME + " where customer_id = " + customerId;
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
         
            //Retrieve by column name
            while (rs.next()){
                int custId = rs.getInt("customer_id");
                String custName = rs.getString("customer_name");
                Date dob = rs.getDate("dob");
                String gender = rs.getString("gender");
                String remarks = rs.getString("remarks");
                Date regDate = rs.getDate("regdate");
                String email = rs.getString("email");
                String username = rs.getString("username");
                User user = UserDAO.retrieveUser(username);
                
                Customer customerResult = new Customer(custId,custName,dob,gender,remarks,regDate,email,user);
                return customerResult;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        
        return null;
    }
    
    public static Customer retrieveCustbyName(String customerName) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        
        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM " + TBLNAME + " where customer_name like '%" + customerName + "%';";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
         
            //Retrieve by column name
            while (rs.next()){
                int custId = rs.getInt("customer_id");
                String custName = rs.getString("customer_name");
                Date dob = rs.getDate("dob");
                String gender = rs.getString("gender");
                String remarks = rs.getString("remarks");
                Date regDate = rs.getDate("regdate");
                String email = rs.getString("email");
                String username = rs.getString("username");
                User user = UserDAO.retrieveUser(username);
                
                Customer customerResult = new Customer(custId,custName,dob,gender,remarks,regDate,email,user);
                //System.out.println("customerDAO got retrieve customer? " + customerResult);
                return customerResult;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        
        return null;
    }
    
    
    
    public static void insertCustomer(String name, int contact, Date regdate, Date dob, String email, String gender, String username, String remarks) throws ParseException {                
        //SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        //java.util.Date dobdate = format.parse(dob);
        //java.util.Date rdate = format.parse(regdate);
        //java.sql.Date sqlDob = new java.sql.Date(dobdate.getTime()); 
        //java.sql.Date sqlDate = new java.sql.Date(rdate.getTime());
        //int id = Integer.parseInt(contact);
        
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps;
            String insertCustomer = "insert into " + TBLNAME + " (customer_id,customer_name,dob,gender,remarks,regdate,email,username) Values(?,?,?,?,?,?,?,?);";
            ps = conn.prepareStatement(insertCustomer);
            ps.setInt(1, contact);
            ps.setString(2, name);
            ps.setDate(3, dob);
            ps.setString(4, gender);
            ps.setString(5, remarks);
            ps.setDate(6, regdate);
            ps.setString(7, email);
            ps.setString(8,username);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static TreeMap<String, Customer> retrieveSearch(String search) throws ParseException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        TreeMap<String, Customer> results = new TreeMap<String, Customer>();

        try {
            conn = ConnectionManager.getConnection();
            sql = "SELECT * FROM " + TBLNAME + " where customer_name like '%" + search + "%' order by customer_name asc";
            stmt = conn.prepareStatement(sql);          
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String id = rs.getString("customer_id");               
                String name = rs.getString("customer_name");               
                String dob = rs.getString("dob");               
                String gender = rs.getString("gender");              
                String remarks = rs.getString("remarks");                
                String regdate = rs.getString("regdate");                
                String email = rs.getString("email");
                String username = rs.getString("username");
                User user = UserDAO.retrieveUser(username);
                java.util.Date dobdate = format.parse(dob);
                java.util.Date rdate = format.parse(regdate);
                java.sql.Date sqlDob = new java.sql.Date(dobdate.getTime()); 
                java.sql.Date sqlDate = new java.sql.Date(rdate.getTime());
                Customer customer = new Customer(Integer.parseInt(id), name, sqlDob, gender, remarks, sqlDate, email, user);
                results.put(name, customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return results;
    }
    
    
    public static TreeMap<String, Customer> retrieveSearchByStaff(String staffName, String search) throws ParseException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        TreeMap<String, Customer> results = new TreeMap<String, Customer>();

        try {
            conn = ConnectionManager.getConnection();
            sql = "SELECT * FROM " + TBLNAME + " where customer_name like '%" + search + "%' and username = '" + staffName + "' order by customer_name asc";
            stmt = conn.prepareStatement(sql);          
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String id = rs.getString("customer_id");               
                String name = rs.getString("customer_name");               
                String dob = rs.getString("dob");               
                String gender = rs.getString("gender");              
                String remarks = rs.getString("remarks");                
                String regdate = rs.getString("regdate");                
                String email = rs.getString("email");
                String username = rs.getString("username");
                User user = UserDAO.retrieveUser(username);
                java.util.Date dobdate = format.parse(dob);
                java.util.Date rdate = format.parse(regdate);
                java.sql.Date sqlDob = new java.sql.Date(dobdate.getTime()); 
                java.sql.Date sqlDate = new java.sql.Date(rdate.getTime());
                Customer customer = new Customer(Integer.parseInt(id), name, sqlDob, gender, remarks, sqlDate, email, user);
                results.put(name, customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return results;
    }    
    
    public static TreeMap<String, Customer> retrieveMaleCustomers() throws ParseException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        TreeMap<String, Customer> results = new TreeMap<String, Customer>();

        try {
            conn = ConnectionManager.getConnection();
            sql = "SELECT * FROM " + TBLNAME + " where gender = 'male' order by customer_name asc";
            stmt = conn.prepareStatement(sql);          
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String id = rs.getString("customer_id");               
                String name = rs.getString("customer_name");               
                String dob = rs.getString("dob");               
                String gender = rs.getString("gender");              
                String remarks = rs.getString("remarks");                
                String regdate = rs.getString("regdate");                
                String email = rs.getString("email");
                String username = rs.getString("username");
                User user = UserDAO.retrieveUser(username);
                java.util.Date dobdate = format.parse(dob);
                java.util.Date rdate = format.parse(regdate);
                java.sql.Date sqlDob = new java.sql.Date(dobdate.getTime()); 
                java.sql.Date sqlDate = new java.sql.Date(rdate.getTime());
                Customer customer = new Customer(Integer.parseInt(id), name, sqlDob, gender, remarks, sqlDate, email, user);
                results.put(name, customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return results;
    }
    
    public static TreeMap<String, Customer> retrieveFemaleCustomers() throws ParseException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        TreeMap<String, Customer> results = new TreeMap<String, Customer>();

        try {
            conn = ConnectionManager.getConnection();
            sql = "SELECT * FROM " + TBLNAME + " where gender = 'female' order by customer_name asc";
            stmt = conn.prepareStatement(sql);          
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String id = rs.getString("customer_id");               
                String name = rs.getString("customer_name");               
                String dob = rs.getString("dob");               
                String gender = rs.getString("gender");              
                String remarks = rs.getString("remarks");                
                String regdate = rs.getString("regdate");                
                String email = rs.getString("email");
                String username = rs.getString("username");
                User user = UserDAO.retrieveUser(username);
                java.util.Date dobdate = format.parse(dob);
                java.util.Date rdate = format.parse(regdate);
                java.sql.Date sqlDob = new java.sql.Date(dobdate.getTime()); 
                java.sql.Date sqlDate = new java.sql.Date(rdate.getTime());
                Customer customer = new Customer(Integer.parseInt(id), name, sqlDob, gender, remarks, sqlDate, email, user);
                results.put(name, customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return results;
    }
    
    public static TreeMap<String, Customer> retrieveAllCustomers() throws ParseException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        TreeMap<String, Customer> results = new TreeMap<String, Customer>();

        try {
            conn = ConnectionManager.getConnection();
            sql = "SELECT * FROM " + TBLNAME + " order by customer_name asc";
            stmt = conn.prepareStatement(sql);          
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String id = rs.getString("customer_id");               
                String name = rs.getString("customer_name");               
                String dob = rs.getString("dob");               
                String gender = rs.getString("gender");              
                String remarks = rs.getString("remarks");                
                String regdate = rs.getString("regdate");                
                String email = rs.getString("email");
                String username = rs.getString("username");
                User user = UserDAO.retrieveUser(username);
                java.util.Date dobdate = format.parse(dob);
                java.util.Date rdate = format.parse(regdate);
                java.sql.Date sqlDob = new java.sql.Date(dobdate.getTime()); 
                java.sql.Date sqlDate = new java.sql.Date(rdate.getTime());
                Customer customer = new Customer(Integer.parseInt(id), name, sqlDob, gender, remarks, sqlDate, email, user);
                results.put(name, customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return results;
    }

    public static TreeMap<String, Customer> retrieveMaleCustomersByStaff(String staffName) throws ParseException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        TreeMap<String, Customer> results = new TreeMap<String, Customer>();

        try {
            conn = ConnectionManager.getConnection();
            sql = "SELECT * FROM " + TBLNAME + " where gender = 'male' and username = '" + staffName + "' order by customer_name asc";
            stmt = conn.prepareStatement(sql);          
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String id = rs.getString("customer_id");               
                String name = rs.getString("customer_name");               
                String dob = rs.getString("dob");               
                String gender = rs.getString("gender");              
                String remarks = rs.getString("remarks");                
                String regdate = rs.getString("regdate");                
                String email = rs.getString("email");
                String username = rs.getString("username");
                User user = UserDAO.retrieveUser(username);
                java.util.Date dobdate = format.parse(dob);
                java.util.Date rdate = format.parse(regdate);
                java.sql.Date sqlDob = new java.sql.Date(dobdate.getTime()); 
                java.sql.Date sqlDate = new java.sql.Date(rdate.getTime());
                Customer customer = new Customer(Integer.parseInt(id), name, sqlDob, gender, remarks, sqlDate, email, user);
                results.put(name, customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return results;
    }
    
    public static TreeMap<String, Customer> retrieveFemaleCustomersByStaff(String staffName) throws ParseException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        TreeMap<String, Customer> results = new TreeMap<String, Customer>();

        try {
            conn = ConnectionManager.getConnection();
            sql = "SELECT * FROM " + TBLNAME + " where gender = 'female' and username = '" + staffName + "' order by customer_name asc";
            stmt = conn.prepareStatement(sql);          
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String id = rs.getString("customer_id");               
                String name = rs.getString("customer_name");               
                String dob = rs.getString("dob");               
                String gender = rs.getString("gender");              
                String remarks = rs.getString("remarks");                
                String regdate = rs.getString("regdate");                
                String email = rs.getString("email");
                String username = rs.getString("username");
                User user = UserDAO.retrieveUser(username);
                java.util.Date dobdate = format.parse(dob);
                java.util.Date rdate = format.parse(regdate);
                java.sql.Date sqlDob = new java.sql.Date(dobdate.getTime()); 
                java.sql.Date sqlDate = new java.sql.Date(rdate.getTime());
                Customer customer = new Customer(Integer.parseInt(id), name, sqlDob, gender, remarks, sqlDate, email, user);
                results.put(name, customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return results;
    }
    
    public static TreeMap<String, Customer> retrieveAllCustomersByStaff(String staffName) throws ParseException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        TreeMap<String, Customer> results = new TreeMap<String, Customer>();

        try {
            conn = ConnectionManager.getConnection();
            sql = "SELECT * FROM " + TBLNAME + " where username = '" + staffName + "' order by customer_name asc";
            stmt = conn.prepareStatement(sql);          
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String id = rs.getString("customer_id");               
                String name = rs.getString("customer_name");               
                String dob = rs.getString("dob");               
                String gender = rs.getString("gender");              
                String remarks = rs.getString("remarks");                
                String regdate = rs.getString("regdate");                
                String email = rs.getString("email");
                String username = rs.getString("username");
                User user = UserDAO.retrieveUser(username);
                java.util.Date dobdate = format.parse(dob);
                java.util.Date rdate = format.parse(regdate);
                java.sql.Date sqlDob = new java.sql.Date(dobdate.getTime()); 
                java.sql.Date sqlDate = new java.sql.Date(rdate.getTime());
                Customer customer = new Customer(Integer.parseInt(id), name, sqlDob, gender, remarks, sqlDate, email, user);
                results.put(name, customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return results;
    }
    
    public static void deleteCustomer(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        
        try {
            conn = ConnectionManager.getConnection();

            sql = "DELETE from " + TBLNAME + " WHERE customer_id = ?";
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
    }
    
    public static void updateCustomer(String name, String contact, String regdate, String dob, String email, String gender, String username, String remarks) throws ParseException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dobdate = format.parse(dob);
        java.util.Date rdate = format.parse(regdate);
        java.sql.Date sqlDob = new java.sql.Date(dobdate.getTime()); 
        java.sql.Date sqlDate = new java.sql.Date(rdate.getTime());
        int id = Integer.parseInt(contact);
        
        try {
            conn = ConnectionManager.getConnection();
            
            sql = "UPDATE " + TBLNAME + " SET customer_id = '" + contact + "', customer_name = '" + name + "', gender = '" + gender + "', dob = '" 
                + sqlDob + "', regdate = '" + sqlDate + "', email = '" + email + "', remarks = '" + remarks + "' WHERE customer_id = '" + contact + "'";
            
            stmt = conn.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.dao;

import fyp.hms.connection.ConnectionManager;
import fyp.hms.model.Payment;
import fyp.hms.model.Services;
import fyp.hms.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Low Kang Li
 */
@Repository("serviceDAO")
public class ServiceDAO {
    
    public static List<Services> retrieveColourServices() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Services> results = new ArrayList<Services>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM service where category = 'colour'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int serviceId = rs.getInt("serviceId");               
                String serviceDescription = rs.getString("serviceDesc");               
                double shortPrice = rs.getDouble("short");               
                double mediumPrice = rs.getDouble("medium");  
                double longPrice = rs.getDouble("long_price"); 
                String category = rs.getString("category");                
                
                Services services = new Services(serviceId, serviceDescription, shortPrice,mediumPrice,longPrice, category);
                
                results.add(services);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static List<Services> retrieveRpServices() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Services> results = new ArrayList<Services>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM service where category = 'rebonding/perm'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int serviceId = rs.getInt("serviceId");               
                String serviceDescription = rs.getString("serviceDesc");               
                double shortPrice = rs.getDouble("short");               
                double mediumPrice = rs.getDouble("medium");  
                double longPrice = rs.getDouble("long_price"); 
                String category = rs.getString("category");                
                
                Services services = new Services(serviceId, serviceDescription, shortPrice,mediumPrice,longPrice, category);
                
                results.add(services);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static List<Services> retrieveTreatmentServices() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Services> results = new ArrayList<Services>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM service where category = 'treatment'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int serviceId = rs.getInt("serviceId");               
                String serviceDescription = rs.getString("serviceDesc");               
                double shortPrice = rs.getDouble("short");               
                double mediumPrice = rs.getDouble("medium");  
                double longPrice = rs.getDouble("long_price"); 
                String category = rs.getString("category");                
                
                Services services = new Services(serviceId, serviceDescription, shortPrice,mediumPrice,longPrice, category);
                
                results.add(services);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static List<Services> retrieveCutServices() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Services> results = new ArrayList<Services>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM service where category = 'cut'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int serviceId = rs.getInt("serviceId");               
                String serviceDescription = rs.getString("serviceDesc");               
                double shortPrice = rs.getDouble("short");               
                double mediumPrice = rs.getDouble("medium");  
                double longPrice = rs.getDouble("long_price"); 
                String category = rs.getString("category");                
                
                Services services = new Services(serviceId, serviceDescription, shortPrice,mediumPrice,longPrice, category);
                
                results.add(services);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static List<Services> retrieveStyleServices() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Services> results = new ArrayList<Services>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM service where category = 'style'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int serviceId = rs.getInt("serviceId");               
                String serviceDescription = rs.getString("serviceDesc");               
                double shortPrice = rs.getDouble("short");               
                double mediumPrice = rs.getDouble("medium");  
                double longPrice = rs.getDouble("long_price"); 
                String category = rs.getString("category");                
                
                Services services = new Services(serviceId, serviceDescription, shortPrice,mediumPrice,longPrice, category);
                
                results.add(services);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static List<Services> retrieveAllServices() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Services> results = new ArrayList<Services>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM service ";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int serviceId = rs.getInt("serviceId");               
                String serviceDescription = rs.getString("serviceDesc");               
                double shortPrice = rs.getDouble("short");               
                double mediumPrice = rs.getDouble("medium");  
                double longPrice = rs.getDouble("long_price"); 
                String category = rs.getString("category");                
                
                Services services = new Services(serviceId, serviceDescription, shortPrice,mediumPrice,longPrice, category);
                
                results.add(services);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }

    public static List<String> retrieveCategory() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<String> results = new ArrayList<String>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT category FROM service group by category";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                
                results.add(rs.getString("category"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    } 
    public static TreeMap<Integer,Services> colourServices() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        TreeMap<Integer,Services> results = new TreeMap<Integer,Services>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM service where category = 'colour'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int serviceId = rs.getInt("serviceId");               
                String serviceDescription = rs.getString("serviceDesc");               
                double shortPrice = rs.getDouble("short");               
                double mediumPrice = rs.getDouble("medium");  
                double longPrice = rs.getDouble("long_price"); 
                String category = rs.getString("category");                
                
                Services services = new Services(serviceId, serviceDescription, shortPrice,mediumPrice,longPrice, category);
                
                results.put(serviceId,services);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static TreeMap<Integer,Services> rpServices() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        TreeMap<Integer,Services> results = new TreeMap<Integer,Services>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM service where category = 'rebonding/perm'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int serviceId = rs.getInt("serviceId");               
                String serviceDescription = rs.getString("serviceDesc");               
                double shortPrice = rs.getDouble("short");               
                double mediumPrice = rs.getDouble("medium");  
                double longPrice = rs.getDouble("long_price"); 
                String category = rs.getString("category");                
                
                Services services = new Services(serviceId, serviceDescription, shortPrice,mediumPrice,longPrice, category);
                
                results.put(serviceId,services);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static TreeMap<Integer,Services> treatmentServices() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        TreeMap<Integer,Services> results = new TreeMap<Integer,Services>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM service where category = 'treatment'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int serviceId = rs.getInt("serviceId");               
                String serviceDescription = rs.getString("serviceDesc");               
                double shortPrice = rs.getDouble("short");               
                double mediumPrice = rs.getDouble("medium");  
                double longPrice = rs.getDouble("long_price"); 
                String category = rs.getString("category");                
                
                Services services = new Services(serviceId, serviceDescription, shortPrice,mediumPrice,longPrice, category);
                
                results.put(serviceId,services);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static TreeMap<Integer,Services> cutServices() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        TreeMap<Integer,Services> results = new TreeMap<Integer,Services>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM service where category = 'cut'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int serviceId = rs.getInt("serviceId");               
                String serviceDescription = rs.getString("serviceDesc");               
                double shortPrice = rs.getDouble("short");               
                double mediumPrice = rs.getDouble("medium");  
                double longPrice = rs.getDouble("long_price"); 
                String category = rs.getString("category");                
                
                Services services = new Services(serviceId, serviceDescription, shortPrice,mediumPrice,longPrice, category);
                
                results.put(serviceId,services);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static TreeMap<Integer,Services> styleServices() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        TreeMap<Integer,Services> results = new TreeMap<Integer,Services>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM service where category = 'style'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int serviceId = rs.getInt("serviceId");               
                String serviceDescription = rs.getString("serviceDesc");               
                double shortPrice = rs.getDouble("short");               
                double mediumPrice = rs.getDouble("medium");  
                double longPrice = rs.getDouble("long_price"); 
                String category = rs.getString("category");                
                
                Services services = new Services(serviceId, serviceDescription, shortPrice,mediumPrice,longPrice, category);
                
                results.put(serviceId,services);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static TreeMap<Integer,Services> allServices() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        TreeMap<Integer,Services> results = new TreeMap<Integer,Services>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM service ";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int serviceId = rs.getInt("serviceId");               
                String serviceDescription = rs.getString("serviceDesc");               
                double shortPrice = rs.getDouble("short");               
                double mediumPrice = rs.getDouble("medium");  
                double longPrice = rs.getDouble("long_price"); 
                String category = rs.getString("category");                
                
                Services services = new Services(serviceId, serviceDescription, shortPrice,mediumPrice,longPrice, category);
                
                results.put(serviceId,services);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }

    public static TreeMap<Integer,Services> retrieveSearch(String search) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        TreeMap<Integer,Services> results = new TreeMap<Integer,Services>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM service where serviceDesc like '%" + search + "%' order by serviceId asc";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int serviceId = rs.getInt("serviceId");               
                String serviceDescription = rs.getString("serviceDesc");               
                double shortPrice = rs.getDouble("short");               
                double mediumPrice = rs.getDouble("medium");  
                double longPrice = rs.getDouble("long_price"); 
                String category = rs.getString("category");                
                
                Services services = new Services(serviceId, serviceDescription, shortPrice,mediumPrice,longPrice, category);
                
                results.put(serviceId,services);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }    

    public static void deleteService(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        
        try {
            conn = ConnectionManager.getConnection();

            sql = "DELETE from service WHERE serviceId = ?";
            
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
    }   

    public static void insertService(int newId, String desc, double shortFee, double mediumFee, double longFee, String category) {        
       try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps;           
            String sql = "insert into service (serviceId, serviceDesc, short, medium, long_price, category) Values (?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, newId);
            ps.setString(2, desc);
            ps.setDouble(3, shortFee);
            ps.setDouble(4, mediumFee);
            ps.setDouble(5, longFee);
            ps.setString(6, category);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static Services retrieveService(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        
        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM service where serviceId = " + id;
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
         
            //Retrieve by column name
            while (rs.next()){
                int serviceId = rs.getInt("serviceId");               
                String serviceDescription = rs.getString("serviceDesc");               
                double shortPrice = rs.getDouble("short");               
                double mediumPrice = rs.getDouble("medium");  
                double longPrice = rs.getDouble("long_price"); 
                String category = rs.getString("category");                
                
                Services service = new Services(serviceId, serviceDescription, shortPrice,mediumPrice,longPrice, category);
                return service;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return null;
    }

    public static void updateService(int id, String desc, double shortFee, double mediumFee, double longFee, String category){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        
        try {
            conn = ConnectionManager.getConnection();
            
            sql = "UPDATE service SET serviceId = '" + id + "', serviceDesc = '" + desc + "', short = '" + shortFee + "', medium = '" 
                + mediumFee + "', long_price = '" + longFee + "', category = '" + category + "' WHERE serviceId = '" + id + "'";
            
            stmt = conn.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
    }   
}

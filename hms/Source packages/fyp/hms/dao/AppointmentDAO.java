/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.dao;

import fyp.hms.connection.ConnectionManager;
import fyp.hms.model.Appointment;
import fyp.hms.model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Asus
 */
@Repository("appointmentDAO")
public class AppointmentDAO {
    private static final String TBLNAME = "appointment";
    
    public static boolean insertAppointment(int customerId, String hairStylistUsername, String customerName, Date appointmentDate,Time time, Time endTime, String remarks, String status) {
        
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps;
            String insertUser = "insert into appointment (customer_id, username,customer_name, appointment_date, appointment_time, end_time, remarks, status) Values(?,?,?,?,?,?,?,?);";
            ps = conn.prepareStatement(insertUser);
            ps.setInt(1, customerId);
            ps.setString(2, hairStylistUsername);
            ps.setString(3, customerName);
            ps.setDate(4, appointmentDate);
            ps.setTime(5, time);
            ps.setTime(6, endTime);
            ps.setString(7, remarks);
            ps.setString(8, status);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        return true;
    }
    
    public static List<Appointment> retrieveAppointmentByHairStylist(String hairStylistUsername) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Appointment> results = new ArrayList<Appointment>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM " 
                    + TBLNAME + " where username = '" + hairStylistUsername + "' order by appointment_date, appointment_time";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int apptId = rs.getInt("appointment_id");
                int customerId = rs.getInt("customer_id");
                String username = rs.getString("username");
                String customerName = rs.getString("customer_name");
                Date apptDate = rs.getDate("appointment_date");
                Time apptTime = rs.getTime("appointment_time");
                Time endTime = rs.getTime("end_time");
                String remarks = rs.getString("remarks");
                String status = rs.getString("status");

                Appointment appt = new Appointment(apptId, customerId, username, customerName, apptDate, apptTime, endTime, remarks, status);
                results.add(appt);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static List<Appointment> retrieveAllAppts() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Appointment> results = new ArrayList<Appointment>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM " + TBLNAME + " order by appointment_date, appointment_time";
                    
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int apptId = rs.getInt("appointment_id");
                int customerId = rs.getInt("customer_id");
                String username = rs.getString("username");
                String customerName = rs.getString("customer_name");
                Date apptDate = rs.getDate("appointment_date");
                Time apptTime = rs.getTime("appointment_time");
                Time endTime = rs.getTime("end_time");
                String remarks = rs.getString("remarks");
                String status = rs.getString("status");

                Appointment appt = new Appointment(apptId, customerId, username, customerName, apptDate, apptTime,endTime, remarks, status);
                results.add(appt);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }

    public static void changeApptStatus(String status, int apptId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            conn = ConnectionManager.getConnection();

            sql = "UPDATE " + TBLNAME + " set status=? where appointment_id = " + apptId;
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt);
        }
    }
    
    public static Appointment retrieveApptStatus(int apptId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        Appointment result = null;
        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM "  
                    + TBLNAME + " where appointment_id =" + apptId;
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("appointment_id");
                int customerId = rs.getInt("customer_id");
                String username = rs.getString("username");
                String customerName = rs.getString("customer_name");
                Date apptDate = rs.getDate("appointment_date");
                Time apptTime = rs.getTime("appointment_time");
                Time endTime = rs.getTime("end_time");
                String remarks = rs.getString("remarks");
                String status = rs.getString("status");

                Appointment appt = new Appointment(id, customerId, username, customerName, apptDate, apptTime, endTime, remarks, status);
                return appt;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return result;
    }
    
    public static List<Appointment> retrieveApptDateInOder() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Appointment> results = new ArrayList<Appointment>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM " 
                    + TBLNAME + " ORDER BY appointment_date asc";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int apptId = rs.getInt("appointment_id");
                int customerId = rs.getInt("customer_id");
                String username = rs.getString("username");
                String customerName = rs.getString("customer_name");
                Date apptDate = rs.getDate("appointment_date");
                Time apptTime = rs.getTime("appointment_time");
                Time endTime = rs.getTime("end_time");
                String remarks = rs.getString("remarks");
                String status = rs.getString("status");

                Appointment appt = new Appointment(apptId, customerId, username, customerName, apptDate, apptTime, endTime, remarks, status);
                results.add(appt);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static List<Appointment> retrieveApptDateInOder(String username) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Appointment> results = new ArrayList<Appointment>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM " 
                    + TBLNAME + " WHERE username = '" + username + "' ORDER BY appointment_date asc";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int apptId = rs.getInt("appointment_id");
                int customerId = rs.getInt("customer_id");
                String uName = rs.getString("username");
                String customerName = rs.getString("customer_name");
                Date apptDate = rs.getDate("appointment_date");
                Time apptTime = rs.getTime("appointment_time");
                Time endTime = rs.getTime("end_time");
                String remarks = rs.getString("remarks");
                String status = rs.getString("status");

                Appointment appt = new Appointment(apptId, customerId, uName, customerName, apptDate, apptTime, endTime, remarks, status);
                results.add(appt);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }

    public static List<Appointment> retrieveCustomerInOder() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Appointment> results = new ArrayList<Appointment>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM " 
                    + TBLNAME + " WHERE appointment_date >= CURDATE() ORDER BY customer_name asc";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int apptId = rs.getInt("appointment_id");
                int customerId = rs.getInt("customer_id");
                String username = rs.getString("username");
                String customerName = rs.getString("customer_name");
                Date apptDate = rs.getDate("appointment_date");
                Time apptTime = rs.getTime("appointment_time");
                Time endTime = rs.getTime("end_time");
                String remarks = rs.getString("remarks");
                String status = rs.getString("status");

                Appointment appt = new Appointment(apptId, customerId, username, customerName, apptDate, apptTime, endTime, remarks, status);
                results.add(appt);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static List<Appointment> retrieveCustomerInOder(String username) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Appointment> results = new ArrayList<Appointment>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM " 
                    + TBLNAME + " WHERE username = '" + username + "' and appointment_date >= CURDATE() ORDER BY customer_name asc";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int apptId = rs.getInt("appointment_id");
                int customerId = rs.getInt("customer_id");
                String uName = rs.getString("username");
                String customerName = rs.getString("customer_name");
                Date apptDate = rs.getDate("appointment_date");
                Time apptTime = rs.getTime("appointment_time");
                Time endTime = rs.getTime("end_time");
                String remarks = rs.getString("remarks");
                String status = rs.getString("status");

                Appointment appt = new Appointment(apptId, customerId, uName, customerName, apptDate, apptTime,endTime, remarks, status);
                results.add(appt);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static List<Appointment> retrieveStaffInOder() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Appointment> results = new ArrayList<Appointment>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT a.appointment_id, a.customer_id, a.username, a.customer_name, a.appointment_date, a.appointment_time, a.remarks, a.status FROM appointment a " +
                    " INNER JOIN user u On a.username = u.username WHERE appointment_date >= CURDATE() ORDER BY u.username asc";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int apptId = rs.getInt("appointment_id");
                int customerId = rs.getInt("customer_id");
                String username = rs.getString("username");
                String customerName = rs.getString("customer_name");
                Date apptDate = rs.getDate("appointment_date");
                Time apptTime = rs.getTime("appointment_time");
                Time endTime = rs.getTime("end_time");
                String remarks = rs.getString("remarks");
                String status = rs.getString("status");

                Appointment appt = new Appointment(apptId, customerId, username, customerName, apptDate, apptTime,endTime, remarks, status);
                results.add(appt);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static List<Appointment> retrieveAppointmentBySearch(String search) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Appointment> results = new ArrayList<Appointment>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM " + TBLNAME + " WHERE customer_name like '%" + search + "%' or customer_id like '%" + search + "%' or username like '%" + search + "%' and appointment_date >= CURDATE()";
                    
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int apptId = rs.getInt("appointment_id");
                int customerId = rs.getInt("customer_id");
                String username = rs.getString("username");
                String customerName = rs.getString("customer_name");
                Date apptDate = rs.getDate("appointment_date");
                Time apptTime = rs.getTime("appointment_time");
                Time endTime = rs.getTime("end_time");
                String remarks = rs.getString("remarks");
                String status = rs.getString("status");

                Appointment appt = new Appointment(apptId, customerId, username, customerName, apptDate, apptTime, endTime, remarks, status);
                results.add(appt);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static List<Appointment> retrieveAppointmentBySearchForStaff(String search, String username) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Appointment> results = new ArrayList<Appointment>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM " + TBLNAME + " WHERE username = '" + username + "' and customer_name like '%" + search + "%' or customer_id like '%" + search + "%' and appointment_date >= CURDATE()";
                    
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int apptId = rs.getInt("appointment_id");
                int customerId = rs.getInt("customer_id");
                String uName = rs.getString("username");
                String customerName = rs.getString("customer_name");
                Date apptDate = rs.getDate("appointment_date");
                Time apptTime = rs.getTime("appointment_time");
                Time endTime = rs.getTime("end_time");
                String remarks = rs.getString("remarks");
                String status = rs.getString("status");

                Appointment appt = new Appointment(apptId, customerId, uName, customerName, apptDate, apptTime, endTime,remarks, status);
                results.add(appt);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static List<Appointment> retrieveExistingAppointment() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Appointment> results = new ArrayList<Appointment>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM " 
                    + TBLNAME + " WHERE status = 'Done' ORDER BY appointment_date, appointment_time";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int apptId = rs.getInt("appointment_id");
                int customerId = rs.getInt("customer_id");
                String username = rs.getString("username");
                String customerName = rs.getString("customer_name");
                Date apptDate = rs.getDate("appointment_date");
                Time apptTime = rs.getTime("appointment_time");
                Time endTime = rs.getTime("end_time");
                String remarks = rs.getString("remarks");
                String status = rs.getString("status");

                Appointment appt = new Appointment(apptId, customerId, username, customerName, apptDate, apptTime, endTime,remarks, status);
                results.add(appt);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static List<Appointment> retrieveExistingAppointment(String username) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Appointment> results = new ArrayList<Appointment>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM " 
                    + TBLNAME + " WHERE username = '" + username + "' and status = 'Done' ORDER BY appointment_date, appointment_time";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int apptId = rs.getInt("appointment_id");
                int customerId = rs.getInt("customer_id");
                String uName = rs.getString("username");
                String customerName = rs.getString("customer_name");
                Date apptDate = rs.getDate("appointment_date");
                Time apptTime = rs.getTime("appointment_time");
                Time endTime = rs.getTime("end_time");
                String remarks = rs.getString("remarks");
                String status = rs.getString("status");

                Appointment appt = new Appointment(apptId, customerId, uName, customerName, apptDate, apptTime, endTime, remarks, status);
                results.add(appt);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static void updateAppointment(int apptId, String hairStylist,Date sqlDate,Time t, String remarks) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            conn = ConnectionManager.getConnection();

            sql = "UPDATE " + TBLNAME + " set username = ?, appointment_date = ?, appointment_time = ?, remarks = ? where appointment_id = " + apptId;
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, hairStylist);
            stmt.setDate(2, sqlDate);
            stmt.setTime(3, t);
            stmt.setString(4, remarks);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt);
        }
    }
    
    public static void deleteAppt(int apptId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            conn = ConnectionManager.getConnection();

            sql = "DELETE FROM " + TBLNAME + " where appointment_id = ? ";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, apptId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt);
        }
    }
    
    public static void updateApptById(int apptId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            conn = ConnectionManager.getConnection();

            sql = "UPDATE " + TBLNAME + " set status=? where appointment_id = " + apptId;
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Done");
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt);
        }
    }
}

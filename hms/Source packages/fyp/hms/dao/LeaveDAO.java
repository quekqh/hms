/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.dao;

import fyp.hms.connection.ConnectionManager;
import fyp.hms.model.Appointment;
import fyp.hms.model.Leave;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Low Kang Li
 */
@Repository("leaveDAO")
public class LeaveDAO {

    public static boolean applyLeave(String username, String loginUserName, Date sqlDate, String leaveType, String remarks, String status) {
        
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps;
            String insertLeave = "insert into leavedb(username, name, date, leave_types, remarks, leave_status) Values(?,?,?,?,?,?);";
            ps = conn.prepareStatement(insertLeave);
            ps.setString(1, username);
            ps.setString(2, loginUserName);
            ps.setDate(3, sqlDate);
            ps.setString(4, leaveType);
            ps.setString(5, remarks);
            ps.setString(6, status);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        return true;
    
    }

    public static List<Leave> retrieveLeaves(String username) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Leave> results = new ArrayList<Leave>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM leavedb where username = '" + username + "' order by date";
                    
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int leaveId = rs.getInt("leaveId");
                String uName = rs.getString("username");
                String name = rs.getString("name");
                Date apptDate = rs.getDate("date");
                String leaveType = rs.getString("leave_types");
                String remarks = rs.getString("remarks");
                String status = rs.getString("leave_status");

                Leave leave = new Leave(leaveId, uName, name, apptDate, leaveType, remarks, status);
                results.add(leave);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    
    }
    
    public static List<Leave> retrieveWeeklyLeaves(String username, String startDate, String endDate) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Leave> results = new ArrayList<Leave>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date start = null;
        java.util.Date end = null;
        
        try {
            start = format.parse(startDate); 
        } catch (ParseException ex) {
            
        }
        
        try {
            end = format.parse(endDate); 
        } catch (ParseException ex) {
            
        }        

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM leavedb where username = '" + username + "'and leave_types = 'weekly leave' and leave_status = 'approved' and date between '" + start + "' and '" + end + "' order by date asc";
                    
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int leaveId = rs.getInt("leaveId");
                String uName = rs.getString("username");
                String name = rs.getString("name");
                Date apptDate = rs.getDate("date");
                String leaveType = rs.getString("leave_types");
                String remarks = rs.getString("remarks");
                String status = rs.getString("leave_status");

                Leave leave = new Leave(leaveId, uName, name, apptDate, leaveType, remarks, status);
                results.add(leave);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    
    }    
    
    public static List<Leave> retrieveMedicalLeaves(String username) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Leave> results = new ArrayList<Leave>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM leavedb where username = '" + username + "'and leave_types = 'medical leave' and leave_status = 'approved' order by date asc";
                    
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int leaveId = rs.getInt("leaveId");
                String uName = rs.getString("username");
                String name = rs.getString("name");
                Date apptDate = rs.getDate("date");
                String leaveType = rs.getString("leave_types");
                String remarks = rs.getString("remarks");
                String status = rs.getString("leave_status");

                Leave leave = new Leave(leaveId, uName, name, apptDate, leaveType, remarks, status);
                results.add(leave);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    
    }    
    
    public static List<Leave> retrieveAllLeaves() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Leave> results = new ArrayList<Leave>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM leavedb";
                    
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int leaveId = rs.getInt("leaveId");
                String uName = rs.getString("username");
                String name = rs.getString("name");
                Date apptDate = rs.getDate("date");
                String leaveType = rs.getString("leave_types");
                String remarks = rs.getString("remarks");
                String status = rs.getString("leave_status");

                Leave leave = new Leave(leaveId, uName, name, apptDate, leaveType, remarks, status);
                results.add(leave);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }

    public static Leave retrieveLeavesById(int leaveId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        Leave result = null;
        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM leavedb where leaveId = " + leaveId;
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int leaveIdFromDb = rs.getInt("leaveId");
                String uName = rs.getString("username");
                String name = rs.getString("name");
                Date apptDate = rs.getDate("date");
                String leaveType = rs.getString("leave_types");
                String remarks = rs.getString("remarks");
                String status = rs.getString("leave_status");

                
                result = new Leave(leaveIdFromDb, uName, name, apptDate, leaveType, remarks, status);
                return result;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return result;
    }

    public static boolean updateLeave(int leaveId, String uName, String loginUserName, Date sqlDate, String leaveType, String remarks, String status) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            conn = ConnectionManager.getConnection();

            sql = "UPDATE leavedb set username = ?, name = ?, date = ?, leave_types = ?, remarks = ?, leave_status = ? where leaveId = " + leaveId;
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, uName);
            stmt.setString(2, loginUserName);
            stmt.setDate(3, sqlDate);
            stmt.setString(4, leaveType);
            stmt.setString(5, remarks);
            stmt.setString(6, status);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConnectionManager.close(conn, stmt);
        }
        
        return true;
    }

    public static void deleteLeave(int leaveId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            conn = ConnectionManager.getConnection();

            sql = "DELETE FROM leavedb where leaveId = ? ";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, leaveId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt);
        }
    }

    public static void approveLeave(int leaveId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            conn = ConnectionManager.getConnection();

            sql = "UPDATE leavedb set leave_status = ? where leaveId = " + leaveId;
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Approved");
      
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        } finally {
            ConnectionManager.close(conn, stmt);
        }
   
    }

    public static void cancelApproveLeave(int leaveId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            conn = ConnectionManager.getConnection();

            sql = "UPDATE leavedb set leave_status = ? where leaveId = " + leaveId;
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Pending");
      
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
        } finally {
            ConnectionManager.close(conn, stmt);
        }
    }
    
    
}

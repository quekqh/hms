/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.dao;

import fyp.hms.connection.ConnectionManager;
import fyp.hms.model.Payroll;
import fyp.hms.model.Services;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HajaReethwan
 */
@Repository("payrollDAO")
public class PayrollDAO {
    
    private static final String TBLNAME = "payroll";

    public static void createPayroll(String userId, java.sql.Date date, int basic, int comm, int deduct, int gross, int cpf, int nett){                

        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps;
            String createPayroll = "insert into " + TBLNAME + " (userId,date,basic,comm,deduct,gross,cpf,nett) Values(?,?,?,?,?,?,?,?);";
            ps = conn.prepareStatement(createPayroll);
            ps.setString(1, userId);
            ps.setDate(2, date);
            ps.setInt(3, basic);
            ps.setInt(4, comm);
            ps.setInt(5, deduct);
            ps.setInt(6, gross);
            ps.setInt(7, cpf);
            ps.setInt(8,nett);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static List<Payroll> retrieveUserPayrolls(String username) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Payroll> results = new ArrayList<>();
        
        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM " + TBLNAME + " where userId = '" + username + "' order by date asc";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
         
            //Retrieve by column name
            while (rs.next()){
                String userId = rs.getString("userId");
                Date date = rs.getDate("date");
                int basic = rs.getInt("basic");
                int comm = rs.getInt("comm");
                int deduct = rs.getInt("deduct");
                int gross = rs.getInt("gross");
                int cpf = rs.getInt("cpf");
                int nett = rs.getInt("nett");
                
                Payroll payroll = new Payroll(userId,date,basic,comm,deduct,gross,cpf,nett);
                results.add(payroll);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        
        return results;
    } 
    
    public static Payroll retrieveUniquePayroll(String username, java.sql.Date date) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        
        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM " + TBLNAME + " where userId = '" + username + "' and date = '" + date + "'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
         
            //Retrieve by column name
            while (rs.next()){
                String userId = rs.getString("userId");
                Date newdate = rs.getDate("date");
                int basic = rs.getInt("basic");
                int comm = rs.getInt("comm");
                int deduct = rs.getInt("deduct");
                int gross = rs.getInt("gross");
                int cpf = rs.getInt("cpf");
                int nett = rs.getInt("nett");
                
                Payroll payroll = new Payroll(userId,newdate,basic,comm,deduct,gross,cpf,nett);
                return payroll;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        
        return null;
    } 
    
    public static List<Payroll> retrieveAllPayrolls() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Payroll> results = new ArrayList<>();
        
        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM " + TBLNAME + " order by date asc";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
         
            //Retrieve by column name
            while (rs.next()){
                String userId = rs.getString("userId");
                Date newdate = rs.getDate("date");
                int basic = rs.getInt("basic");
                int comm = rs.getInt("comm");
                int deduct = rs.getInt("deduct");
                int gross = rs.getInt("gross");
                int cpf = rs.getInt("cpf");
                int nett = rs.getInt("nett");
                
                Payroll payroll = new Payroll(userId,newdate,basic,comm,deduct,gross,cpf,nett);
                results.add(payroll);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        
        return results;
    }      

    public static void updatePayroll(String userId, java.sql.Date date, int basic, int comm, int deduct, int gross, int cpf, int nett){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        
        try {
            conn = ConnectionManager.getConnection();
            
            sql = "UPDATE " + TBLNAME + " SET userId = '" + userId + "', date = '" + date + "', basic = '" + basic + "', comm = '" 
                + comm + "', deduct = '" + deduct + "', gross = '" + gross + "', cpf = '" + cpf + "', nett = '" + nett + 
                "' WHERE userId = '" + userId + "' and date = '" + date + "'";
            
            stmt = conn.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
    }
    
    public static void deletePayroll(String id, java.sql.Date date){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        
        try {
            conn = ConnectionManager.getConnection();

            sql = "DELETE from " + TBLNAME + " WHERE userId = '" + id + "' and date = '" + date + "'";
            
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
    }        
}

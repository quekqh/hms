/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.dao;

import fyp.hms.connection.ConnectionManager;
import fyp.hms.model.Appointment;
import fyp.hms.model.Payment;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Low Kang Li
 */
@Repository("paymentDAO")
public class PaymentDAO {
    public static void makePayment(Payment payment) {
        
        int apptId = payment.getApptId();
        String username = payment.getUsername();
        int customerId = payment.getCustomerId();
        double paymentAmt = payment.getPayment();
        String services = payment.getServices();
        Date paymentDate = payment.getPaymentDate();
        String remarks = payment.getRemarks();
        String comment = payment.getComment();
        
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps;
            String insertPayment = "insert into payment (apptId, hairstylist,customerId, payment, services, paymentDate, remarks, comment) Values(?,?,?,?,?,?,?,?);";
            ps = conn.prepareStatement(insertPayment);
            ps.setInt(1, apptId);
            ps.setString(2, username);
            ps.setInt(3, customerId);
            ps.setDouble(4, paymentAmt);
            ps.setString(5, services);
            ps.setDate(6, paymentDate);
            ps.setString(7, remarks);
            ps.setString(8, comment);
            
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static List<Payment> retrieveAllPayments() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Payment> results = new ArrayList<Payment>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM payment order by paymentDate";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int apptId = rs.getInt("apptId");
                String username = rs.getString("hairstylist");
                int customerId = rs.getInt("customerId");
                double payments = rs.getDouble("payment");
                String services = rs.getString("services");
                Date date = rs.getDate("paymentDate");
                String remarks = rs.getString("remarks");
                String comment = rs.getString("comment");
                
                Payment payment = new Payment(apptId,username, customerId,payments, services, date, remarks, comment);
                results.add(payment);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
    
    public static List<Payment> retrieveTodayPayments() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        List<Payment> results = new ArrayList<Payment>();

        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT * FROM payment where paymentDate = CURDATE()";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int apptId = rs.getInt("apptId");
                String username = rs.getString("hairstylist");
                int customerId = rs.getInt("customerId");
                double payments = rs.getDouble("payment");
                String services = rs.getString("services");
                Date date = rs.getDate("paymentDate");
                String remarks = rs.getString("remarks");
                String comment = rs.getString("comment");
                
                Payment payment = new Payment(apptId,username, customerId,payments, services, date, remarks, comment);
                results.add(payment);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        return results;
    }
}

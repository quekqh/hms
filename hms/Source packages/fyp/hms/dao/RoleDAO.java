/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.dao;

import fyp.hms.connection.ConnectionManager;
import fyp.hms.model.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Asus
 */
@Repository("roleDAO")
public class RoleDAO {
    private static final String TBLNAME = "role";
    
    public static Role retrieveRoleById(int roleId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        
        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT role_id, role_description FROM " + TBLNAME + " where role_id = " + roleId;
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
         
            //Retrieve by column name
            
            while (rs.next()){
                int roleID = rs.getInt("role_id");
                String roleDescription = rs.getString("role_description");

                Role roleResult = new Role(roleID, roleDescription);
                return roleResult;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        
        return null;
    }
    
    public static Role retrieveIdByRole(String roleDesc) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        
        try {
            conn = ConnectionManager.getConnection();

            sql = "SELECT role_id, role_description FROM " + TBLNAME + " where role_description = '" + roleDesc + "'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
         
            //Retrieve by column name
            
            while (rs.next()){
                int roleID = rs.getInt("role_id");
                String roleDescription = rs.getString("role_description");

                Role roleResult = new Role(roleID, roleDescription);
                return roleResult;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.close(conn, stmt, rs);

        }
        
        return null;
    }
}

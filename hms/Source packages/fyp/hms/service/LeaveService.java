/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.service;

import fyp.hms.dao.LeaveDAO;
import fyp.hms.model.Appointment;
import fyp.hms.model.Leave;
import java.sql.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Low Kang Li
 */
@Service("leaveService")
public class LeaveService {
    @Autowired
    HttpSession session;
    
    public boolean applyLeave(String username, String loginUserName, Date sqlDate, String leaveType, String remarks, String status){
       return LeaveDAO.applyLeave(username, loginUserName, sqlDate, leaveType, remarks, status);
       
    }

    public List<Leave> retrieveLeaves(String username) {
        return LeaveDAO.retrieveLeaves(username);
    }

    public List<Leave> retrieveAllLeaves() {
        return LeaveDAO.retrieveAllLeaves();
    }

    public Leave retrieveLeavesById(int leaveId) {
        return LeaveDAO.retrieveLeavesById(leaveId);
    }

    public boolean updateLeave(int leaveId, String uName, String loginUserName, Date sqlDate, String leaveType, String remarks, String status) {
       return LeaveDAO.updateLeave(leaveId, uName, loginUserName, sqlDate, leaveType, remarks, status);
    }

    public void deleteLeave(int leaveId) {
        LeaveDAO.deleteLeave(leaveId);
    }

    public void approveLeave(int leaveId) {
        LeaveDAO.approveLeave(leaveId);
    }

    public void cancelApproveLeave(int leaveId) {
        LeaveDAO.cancelApproveLeave(leaveId);
    }

}

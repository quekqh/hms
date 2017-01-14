/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.model;

import java.sql.Date;

/**
 *
 * @author Low Kang Li
 */
public class Leave {
    private int leaveId;
    private String username;
    private String name;
    private Date leaveDate;
    private String leaveType;
    private String remarks;
    private String leaveStatus;

    public Leave(int leaveId, String username, String name, Date leaveDate, String leaveType, String remarks, String leaveStatus) {
        this.leaveId = leaveId;
        this.username = username;
        this.name = name;
        this.leaveDate = leaveDate;
        this.leaveType = leaveType;
        this.remarks = remarks;
        this.leaveStatus = leaveStatus;
    }

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    
}

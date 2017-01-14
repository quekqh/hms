<%-- 
    Document   : editLeave
    Created on : 28 Dec, 2016, 1:55:06 AM
    Author     : Low Kang Li
--%>

<%@page import="fyp.hms.model.Leave"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.TreeMap"%>
<%@page import="fyp.hms.model.User"%>
<%@page import="java.sql.Date"%>
<%@page import="fyp.hms.model.Customer"%>
<%@page import="org.springframework.ui.ModelMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html>
    <head>
        <%@ include file="template_head.jsp" %>
    </head>
    <body>
        <div id="wrapper">

            <!-- Navigation -->

            <%@include file="template_navbar.jsp" %>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-xs-1 col-md-3"></div>
                            <div class="col-xs-10 col-md-6">
                                <h1>APPLY LEAVE</h1>
            <%
            String error = (String) request.getAttribute("error");
            
            String role = (String)session.getAttribute("role");
            String username = "";
            String uName = "";
            String leaveDate = "";
            String leaveType = "";
            String remarks = "";
            
            
            if (error != null) {

                out.println("<div class='alert alert-danger' role='alert'>");
                out.println("<strong>Error:</strong>");
                out.println(error);
                out.println("</div>");

             
            }
            
            String loginUserName = (String) request.getAttribute("loginUserName");
            if(loginUserName != null){
                uName = loginUserName;
            }
            
            String getUsername = (String) request.getAttribute("username");
            if(getUsername != null){
                username = getUsername;
            }
            
            String getRemarks = (String) request.getAttribute("remarks");
            if(getRemarks != null){
                remarks = getRemarks;
            }
            
            String getLeaveDate = (String) request.getAttribute("leaveDate");
            if(getLeaveDate != null){
                leaveDate = getLeaveDate;
            }
            
            String getLeaveType = (String) request.getAttribute("leaveType");
            if(getLeaveType != null){
                leaveType = getLeaveType;
            }
            
            User user = (User)session.getAttribute("loginUser");
            if(user != null){
                uName = user.getName();
                username = user.getUsername();
            }
            
            Leave leave = (Leave)request.getAttribute("leave");
            int leaveId = 0;
            String status = "";
            if(leave != null){
                leaveId = leave.getLeaveId();
                username = leave.getUsername();
                uName = leave.getName();
                leaveDate = leave.getLeaveDate().toString();
                leaveType = leave.getLeaveType();
                remarks = leave.getRemarks();
                status = leave.getLeaveStatus();
            }
        %>         
    
    <form action="${contextPath}/updateLeaveForm" data-toggle="validator" role="form" style="width:100%">
        <input type="hidden" name="username" value="<%=username%>"/>    
        <input type="hidden" name="leaveId" value="<%=leaveId%>"/> 
        <div class="form-group">
            <label for="inputUsername" class="control-label">Name</label>
            <input type="text" class="form-control" id="loginUserName" name="loginUserName" placeholder="Enter your name" value="<%=uName%>" readonly>
        </div>
        
        
        <div class="form-group">
            <label for="inputAppointmentDate">Leave Date:</label>
            <%if(status.equals("Approved") || role.equals("admin")){%>
            <input class="form-control" name="leaveDate" type="date" value="<%=leaveDate%>" readonly/>
            <%}else{%>
            <input class="form-control" name="leaveDate" type="date" value="<%=leaveDate%>"/>
            <%}%>
        </div>
                
        
        <div class="form-group">
            <label for="inputHairStylists" class="control-label">Leave Types</label>
            <%if(status.equals("Approved") || role.equals("admin")){%>
                <select class="form-control" id="leaveType" name="leaveType" disabled="true">
                <option value="">Select the type of your leave</option>
                <%
                    if(leaveType.equals("Weekly Leave")){
                %>
                    <option value="Weekly Leave" selected>Weekly Leave</option>
                    <option value="Medical Leave">Medical Leave</option>
                    <%
                        }else if(leaveType.equals("Medical Leave")){
                    %>
                    <option value="Weekly Leave">Weekly Leave</option>
                    <option value="Medical Leave" selected>Medical Leave</option>
                    <%}else{%>
                    <option value="Weekly Leave">Weekly Leave</option>
                    <option value="Medical Leave">Medical Leave</option>
                    <%}%>
            <%}else{%>
                    
                    <select class="form-control" id="leaveType" name="leaveType" >
                    <option value="">Select the type of your leave</option>
                    <%
                        if(leaveType.equals("Weekly Leave")){
                    %>
                    <option value="Weekly Leave" selected>Weekly Leave</option>
                    <option value="Medical Leave">Medical Leave</option>
                    <%
                        }else if(leaveType.equals("Medical Leave")){
                    %>
                    <option value="Weekly Leave">Weekly Leave</option>
                    <option value="Medical Leave" selected>Medical Leave</option>
                    <%}else{%>
                    <option value="Weekly Leave">Weekly Leave</option>
                    <option value="Medical Leave">Medical Leave</option>
                    <%}%>
            <%}%>
                </select>
        </div>
        
        <div class="form-group">
            <label for="inputRemarks">Remarks:</label>
            <%if(status.equals("Approved") || role.equals("admin")){%>
            <textarea class="form-control" rows="5" id="remarks" name="remarks" value="<%=remarks%>" readonly><%=remarks%></textarea>
            <%}else{%>
            <textarea class="form-control" rows="5" id="remarks" name="remarks" value="<%=remarks%>"><%=remarks%></textarea>
            <%}%>
        </div>
         
         <div class="form-group text-center">
            <%if(role.equals("admin")){%>
                <%if(status.equals("Approved")){%>
                    <button type="submit" name="cancelApprovedLeave" value="Cancel Approved Leave" class="btn btn-warning">Cancel Approved Leave</button>  
                    <button type="submit" name="deleteLeave" value="Delete Leave" class="btn btn-danger">Delete</button>
                    <button type="submit" name="cancelLeave" value="Cancel Leave" class="btn btn-primary">Cancel</button> 
                <%}else{%>
                    <button type="submit" name="approveLeave" value="Approve Leave" class="btn btn-warning">Approve</button>  
                    <button type="submit" name="deleteLeave" value="Delete Leave" class="btn btn-danger">Delete</button>
                    <button type="submit" name="cancelLeave" value="Cancel Leave" class="btn btn-primary">Cancel</button> 
                <%}%>
            <%}else{%>
                <%if(status.equals("Approved")){%>
                    <button type="submit" name="deleteLeave" value="Delete Leave" class="btn btn-danger">Delete</button>
                    <button type="submit" name="cancelLeave" value="Cancel Leave" class="btn btn-primary">Cancel</button> 
                <%}else{%>
                    <button type="submit" name="updateLeave" value="Update Leave" class="btn btn-warning">Update</button>
                    <button type="submit" name="deleteLeave" value="Delete Leave" class="btn btn-danger">Delete</button>
                    <button type="submit" name="cancelLeave" value="Cancel Leave" class="btn btn-primary">Cancel</button> 
                <%}%>
            <%}%>
         </div>
        
    </form>
                
    </div>  
           
        

    <div class="col-xs-1 col-md-4"></div>  
    </div>
          </div>
                </div>

            </div>
        </div>

    </body>

   

</html>


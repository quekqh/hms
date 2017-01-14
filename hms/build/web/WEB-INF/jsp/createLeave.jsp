<%-- 
    Document   : admin
    Created on : 29 Sep, 2016, 8:17:29 PM
    Author     : Asus
--%>
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
        %>         
    
    <form action="${contextPath}/createLeave" data-toggle="validator" role="form" style="width:100%">
        <input type="hidden" name="username" value="<%=username%>"/>      
        <div class="form-group">
            <label for="inputUsername" class="control-label">Name</label>
            <input type="text" class="form-control" id="loginUserName" name="loginUserName" placeholder="Enter your name" value="<%=uName%>" readonly>
        </div>
        
        
        <div class="form-group">
            <label for="inputAppointmentDate">Leave Date:</label>
            <input class="form-control" name="leaveDate" type="date" value="<%=leaveDate%>"/>
        </div>
                
        
        <div class="form-group">
            <label for="inputHairStylists" class="control-label">Leave Types</label>
            <select class="form-control" id="leaveType" name="leaveType">
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
            </select>
        </div>
        
        <div class="form-group">
            <label for="inputRemarks">Remarks:</label>
            <textarea class="form-control" rows="5" id="remarks" name="remarks" value="<%=remarks%>"><%=remarks%></textarea>
         </div>

        <div class="form-group">
            <button style= "background-color: #000000;border-color: #000000;" type="submit" name="createApp" value="Apply Leave" class="btn btn-primary btn-lg btn-block">Apply</button>
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


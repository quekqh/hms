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
        <h1>Create Appointment</h1>
        <%
        String error = (String) request.getAttribute("error");
        String error1 = (String) request.getAttribute("error1");
        
        String hairStylistName = "";
        String custId = "";
        String customerName = "";
        String dob = "";
        String email = "";
        String apptDate = "";
        String time = "";
        String duration = "";
        String remarks = "";
        
        if (error1 != null) {

            out.println("<div class='alert alert-danger' role='alert'>");
            out.println("<strong>Error:</strong>");
            out.println(error1);
            out.println("</div>");
        }
        
        if (error != null) {

            out.println("<div class='alert alert-danger' role='alert'>");
            out.println("<strong>Error:</strong>");
            out.println(error);
            out.println("</div>");
            
            custId = (String)request.getAttribute("customerId");
            hairStylistName = (String)request.getAttribute("hairStylistName");
            customerName = (String)request.getAttribute("customerName");
            dob = (String)request.getAttribute("dob");
            email = (String)request.getAttribute("email");
            apptDate = (String)request.getAttribute("apptDate");
            time = (String)request.getAttribute("time");
            duration = (String)request.getAttribute("duration");
            remarks = (String)request.getAttribute("remarks");
        }
        
        String success = (String) request.getAttribute("success");
        if (success != null) {

            out.println("<div class='alert alert-success' role='alert'>");
            out.println("<strong>success:</strong>");
            out.println(success);
            out.println("</div>");
            
        }
        
        Customer customer = (Customer)request.getAttribute("customer");
        if(customer != null){
            custId = customer.getCustomerId() + "";
            customerName = customer.getCustomerName();
            dob = customer.getDob() + "";
            email = customer.getEmail();
            
            hairStylistName = (String)request.getAttribute("hairStylistName");
            apptDate = (String)request.getAttribute("apptDate");
            time = (String)request.getAttribute("time");
            remarks = (String)request.getAttribute("remarks");
        }
    %>         
    
    <form action="${contextPath}/createApptment" data-toggle="validator" role="form" style="width:100%">
                 
        <div class="form-group">     
           <div class="input-group add-on">
                <input type="tel" class="form-control" name="customerId" id="customerId" placeholder="Enter customer name or contact no." value="<%=custId%>">
                <div class="input-group-btn">
                    <button class="btn btn-default" type="submit" name ="retrieveInfo" value="Search"><i class="glyphicon glyphicon-search"></i></button>
                </div>
           </div>
        </div>
                
                
        <div class="form-group">
            <label for="inputCustomerName" class="control-label">Customer Name</label>
            <input type="text" class="form-control" id="customerName" name="customerName" placeholder="-NIL-" value="<%=customerName%>" readonly>
        </div>

        <div class="form-group">
            <label for="inputdob" class="control-label">Date of birth</label>
            <%if(dob != null){%>
                <input type="text" class="form-control" id="dob" name="dob" placeholder="-NIL-" value="<%=dob%>" readonly>
            <%}else{%>
                <input type="text" class="form-control" id="dob" name="dob" placeholder="-NIL-" readonly>
            <%}%>
        </div>

        <div class="form-group">
            <label for="inputEmail" class="control-label">Email</label>
            <input type="email" class="form-control" id="email" name ="email" placeholder="-NIL-" data-error="Bro, that email address is invalid" value="<%=email%>" readonly>
            <div class="help-block with-errors"></div>
        </div>
            
        <%
            TreeMap<String, User> users = (TreeMap<String, User>) session.getAttribute("allStaff");
        
        %>
        <div class="form-group">
            <label for="inputHairStylists" class="control-label">Assign Hair Stylist</label>
            <select class="form-control" id="hairStylist" name="hairStylist">
                <option value="">Select hairStylist</option>
                <%
                    if(users != null){ 
                        
                        Set set = users.entrySet();
                        Iterator iterator = set.iterator();
                        while(iterator.hasNext()) {
                            Map.Entry mentry = (Map.Entry)iterator.next();
                            User u = (User)mentry.getValue();
                            String username = u.getUsername();
                            String n = u.getName();
                            if(hairStylistName != null){
                                if(hairStylistName.equals(username)){
                                    out.println("<option value='" + username + "'selected>" + n + "</option>");
                                }else{

                                    out.println("<option value='" + username + "'>" + n + "</option>");
                                }
                            }else{
                                out.println("<option value='" + username + "'>" + n + "</option>");
                            }
                            
                        }      
                    }
                %>
                    
                

            </select>
        </div>
        
        <div class="form-group">
            <label for="inputAppointmentDate">Appointment Date:</label>
            <input class="form-control" name="appointmentDate" type="date" value="<%=apptDate%>"/>
        </div>
                
        <div class="form-group">
            <label for="inputAppointmentTime">Appointment Time:</label>
            <input class="form-control" name="appointmentTime" type="time" value="<%=time%>"/>
        </div>       
                
        <div class="form-group">
            <label for="inputDuration" class="control-label">Appointment Duration</label>
            <select class="form-control" id="duration" name="duration">
                <option value="">Select Appointment Duration (minutes)</option>
                <%
                    if(duration.equals("30")){
                %>
                <option value="30" selected>30</option>
                <option value="60">60</option>
                <option value="90">90</option>
                <option value="120">120</option>
                <option value="150">150</option>
                <option value="180">180</option>
                <%
                    }else if(duration.equals("60")){
                    
                    %>
                    
                <option value="30">30</option>
                <option value="60" selected>60</option>
                <option value="90">90</option>
                <option value="120">120</option>
                <option value="150">150</option>
                <option value="180">180</option>
                
                <%
                    }else if(duration.equals("90")){
                    
                    %>
                    
                <option value="30">30</option>
                <option value="60">60</option>
                <option value="90" selected>90</option>
                <option value="120">120</option>
                <option value="150">150</option>
                <option value="180">180</option>
                
                <%
                    }else if(duration.equals("120")){
                    
                    %>
                    
                <option value="30">30</option>
                <option value="60">60</option>
                <option value="90">90</option>
                <option value="120" selected>120</option>
                <option value="150">150</option>
                <option value="180">180</option>
                
                <%
                    }else if(duration.equals("150")){
                    
                    %>
                    
                <option value="30">30</option>
                <option value="60">60</option>
                <option value="90">90</option>
                <option value="120">120</option>
                <option value="150" selected>150</option>
                <option value="180">180</option>
                
                <%
                    }else if(duration.equals("180")){
                    
                    %>
                    
                <option value="30">30</option>
                <option value="60">60</option>
                <option value="90">90</option>
                <option value="120">120</option>
                <option value="150">150</option>
                <option value="180" selected>180</option>
                
                <%}else{%>
                <option value="30">30</option>
                <option value="60">60</option>
                <option value="90">90</option>
                <option value="120">120</option>
                <option value="150">150</option>
                <option value="180">180</option>
                <%}%>
            </select>
        </div>
        
        <div class="form-group">
            <label for="inputRemarks">Remarks:</label>
            <textarea class="form-control" rows="5" id="remarks" name="remarks" value="<%=remarks%>"><%=remarks%></textarea>
         </div>

        <div class="form-group">
            <button style= "background-color: #000000;border-color: #000000;" type="submit" name="createApp" value="Create Appointment" class="btn btn-primary btn-lg btn-block">Submit</button>
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


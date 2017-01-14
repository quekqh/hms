<%-- 
    Document   : admin
    Created on : 29 Sep, 2016, 8:17:29 PM
    Author     : Asus
--%>

<%@page import="fyp.hms.model.Appointment"%>
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

    <div class="col-xs-10 col-md-6"> <%
        String error = (String) request.getAttribute("error");
        String hairStylistName = "";
        String custId = "";
        String customerName = "";
        String dob = "";
        String email = "";
        String apptDate = "";
        String time = "";
        String remarks = "";
        String status = "";
        if (error != null) {

            out.println("<div class='alert alert-danger' role='alert'>");
            out.println("<strong>Error:</strong>");
            out.println(error);
            out.println("</div>");
            
            hairStylistName = (String)request.getAttribute("hairStylist");
            custId = (String)request.getAttribute("customerId");
            customerName = (String)request.getAttribute("customerName");
            dob = (String)request.getAttribute("dob");
            email = (String)request.getAttribute("email");
            time = (String)request.getAttribute("time");
            apptDate = (String)request.getAttribute("apptDate");
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
            
        }
        
        Appointment appt = (Appointment)request.getAttribute("appt");
        int apptId = 0;
        //User assignHS = (User)request.getAttribute("appointedHairStylist");
        if(appt != null){
            apptId = appt.getAppointmentId();
            hairStylistName = appt.getHairStylistUsername();
            apptDate = appt.getAppointmentDate() + "";
            time = appt.getAppointmentTime() + "";
            remarks = appt.getRemarks();
            status = appt.getStatus();
        }

    %>    
    <%if(status.equals("Done")){%>
    <h1>View Appointment</h1>
    <%}else{%>
    <h1>Update Appointment</h1>
    <%}%>
    <form action="${contextPath}/updateApptment" data-toggle="validator" role="form" style="width:100%">
        <input type="hidden" name="apptId" value="<%=apptId%>"/>
        <div class="form-group">
            <label for="inputCustomerId" class="control-label">Customer Id</label>
            <input type="tel" class="form-control" id="customerId" minlength="8" maxlength="8" name="customerId" placeholder="Enter Customer Id (customer contact number)" value="<%=custId%>" readonly>
        </div>

        <div class="form-group">
            <label for="inputCustomerName" class="control-label">Customer Name</label>
            <input type="text" class="form-control" id="customerName" name="customerName" placeholder="Enter customer Name" value="<%=customerName%>" readonly>
        </div>

        <div class="form-group">
            <label for="inputdob" class="control-label">Date of birth</label>
            <%if(dob != null){%>
                <input type="text" class="form-control" id="dob" name="dob" placeholder="yyyy/mm/dd" value="<%=dob%>" readonly>
            <%}else{%>
                <input type="text" class="form-control" id="dob" name="dob" placeholder="yyyy/mm/dd" readonly>
            <%}%>
        </div>

        <div class="form-group">
            <label for="inputEmail" class="control-label">Email</label>
            <input type="email" class="form-control" id="email"name ="email" placeholder="Enter Email" data-error="Bro, that email address is invalid" value="<%=email%>" readonly>
            <div class="help-block with-errors"></div>
        </div>
            
        <%
            TreeMap<String, User> users = (TreeMap<String, User>) session.getAttribute("allStaff");
            String appointedHairStylistName = "";
        %>
        <div class="form-group">
            <label for="inputHairStylists" class="control-label">Assign Hair Stylist</label>
            <%if(!status.equals("Done")){%>
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
                                    out.println("<option value='" + username + "' selected>" + n + "</option>");
                                    appointedHairStylistName = n;
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
            <%}else{%>
            <select class="form-control" id="hairStylist" name="hairStylist" disabled="true">
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
                                    out.println("<option value='" + username + "' selected>" + n + "</option>");
                                    appointedHairStylistName = n;
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
            <%}%>
        </div>
        
        <div class="form-group">
            <label for="inputAppointmentDate">Appointment Date:</label>
            
            <%if(status.equals("Done")){%>
            <input class="form-control" name="appointmentDate" type="date" value="<%=apptDate%>" readonly/>
            <%}else{%>
            <input class="form-control" name="appointmentDate" type="date" value="<%=apptDate%>"/>
            <%}%>
        </div>
                
        <div class="form-group">
            <label for="inputAppointmentDate">Appointment Time:</label>
            <%if(status.equals("Done")){%>
            <input class="form-control" name="appointmentTime" type="time" value="<%=time%>" readonly/>
            <%}else{%>
            <input class="form-control" name="appointmentTime" type="time" value="<%=time%>"/>
            <%}%>
        </div>       
                
                
        <div class="form-group">
            <label for="inputRemarks">Remarks:</label>
            <%if(status.equals("Done")){%>
            <textarea class="form-control" rows="5" id="remarks" name="remarks" value="<%=remarks%>" readonly><%=remarks%></textarea>
            <%}else{%>
            <textarea class="form-control" rows="5" id="remarks" name="remarks" value="<%=remarks%>"><%=remarks%></textarea>
            <%}%>
        </div>
<%
        if(!status.equals("Done")){
%>
        <div class="form-group text-center">
            <button type="submit" name="updateAppt" value="Update Appointment" class="btn btn-warning">Update</button>
       
         

            <button type="submit" name="delete" value="Delete Appointment" class="btn btn-danger">Delete</button>
        
        
      
            <button type="submit" name="proceedPayment" value="Proceed to Payment" class="btn btn-success">Proceed to Payment</button>
    
         

            <button type="submit" name="cancelAppt" value="Cancel Appointment" class="btn btn-primary">Cancel</button>
        </div>
        
        <% }else{%>
        
        <div class="form-group">
            <button type="submit" name="cancelAppt" value="Go Back" class="btn btn-primary btn-lg btn-block">Go Back</button>
        </div>
        <% }%>
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


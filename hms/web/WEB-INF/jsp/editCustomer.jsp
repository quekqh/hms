<%-- 
    Document   : editCustomer
    Created on : Oct 15, 2016, 4:22:40 PM
    Author     : HajaReethwan
--%>
<%@page import="fyp.hms.dao.CustomerDAO"%>
<%@page import="java.sql.Date"%>
<%@page import="fyp.hms.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>

<html>
    <head>
        <%@include file="template_head.jsp"%>
               <%
            String num = (String)request.getAttribute("id");
            System.out.println("num");
            if(num != null){
                int id = Integer.parseInt(num); 
                Customer customer = CustomerDAO.retrieveCustomer(id);
                String n = customer.getCustomerName();
                int number = customer.getCustomerId();
                String gender = customer.getGender();
                String email = customer.getEmail();
                String remarks = customer.getRemarks();
                Date dob = customer.getDob();
                Date regDate = customer.getRegDate();
    %>
    
    <style>
        .form-horizontal .form-group {
           margin-right: 0px;
margin-left: 0px;
        }

    </style>
    </head>

    <body>
        <div id="wrapper">
            <%@include file="template_navbar.jsp"%>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="col-lg-12">
<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
        out.println("<div class='alert alert-danger' role='alert'>");
        out.println("<strong>Error:</strong>");
        out.println(error + "<br>");
        if((String)request.getAttribute("name") != null){
            out.println((String)request.getAttribute("name") + "<br>");
        }
        if((String)request.getAttribute("number") != null){
            out.println((String)request.getAttribute("number") + "<br>");            
        }
        if((String)request.getAttribute("email") != null){
            out.println((String)request.getAttribute("email") + "<br>");            
        }
        if((String)request.getAttribute("gender") != null){
            out.println((String)request.getAttribute("gender") + "<br>");           
        }
        if((String)request.getAttribute("dob") != null){
            out.println((String)request.getAttribute("dob") + "<br>");           
        }
        out.println("</div>");
    }         
%>
                    </div>
                </div>
                <div class="container-fluid">
                    <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">EDIT CUSTOMER</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Customer Information
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                 <form action="${contextPath}/customerUpdate" class="form-horizontal" method="GET">
                                <div class="col-lg-6">
                                    <input type="hidden" name="id" class="form-control" id="id" value="<%= number %>">
                
                
                  <div class="form-group">
                    Name:
                    <input type="text" name="name" class="form-control" id="name" value=" <%= n %> " placeholder="Enter Name"  style="width:100%"><br>
                </div>

                <div class="form-group">
                    Contact No.:
                    <input type="text" name="number" class="form-control" id="number" value=" <%= number %>  " placeholder="Enter Contact Number"  style="width:100%"><br>
                </div>
                
                <div class="form-group">
                    Registration Date:
                    <input type="text" name="regDate" class="form-control" id="regDate"  value=" <%= regDate %> " placeholder="Enter date in DD/MM/YYYY format" style="width:100%" readonly><br>
                </div>
                
                <div class="form-group">
                    Remarks:
                    <input type="text" name="remarks" class="form-control" id="remarks"  value=" <%= remarks %> " placeholder="Enter Remarks"  style="width:100%"><br>
                </div>                  
                                      
                </div>
                <!-- /.col-lg-6 (nested) -->
                <div class="col-lg-6">
                                    
                <div class="form-group">
                    Date of Birth:
                    <input type="text" class="form-control" name="dob" value="<%= dob %>" style="width:100%"><br>
                </div>
                
                <div class="form-group">
                    Email:
                    <input type="text" name="email" class="form-control" id="email"  value=" <%= email %> " placeholder="Enter Email"  style="width:100%"><br>
                </div>
                
                <div class="form-group">
                    Gender:
                    <select class="form-control" id="gender" name="gender">
                        <option>Male</option>
                        <option>Female</option>      
                    </select>
                </div>
               
                 
                                    
                                </div>
        <div class="form-group text-center">
            <button type="submit" name="submit" value="Submit" class="btn btn-warning">Submit</button>  
            <button type="submit" name="cancel" value="Cancel" class="btn btn-primary">Cancel</button> 
        </div>
                               </form>
                <%}%>
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->
                    
                   
                </div>
            </div>
        </div>
    </body> 

</html>

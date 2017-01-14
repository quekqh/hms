<%-- 
    Document   : client
    Created on : 15 Oct, 2016, 7:30:01 PM
    Author     : Low Kang Li
--%>
<%@page import="fyp.hms.dao.CustomerDAO"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.List"%>
<%@page import="fyp.hms.model.Customer"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fyp.hms.dao.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@include file="staffProtect.jsp"%>
<html>        

    <head>
        <%@include file="template_head.jsp"%>
    </head>
    <body>

        <div id="wrapper">
            <%@include file="template_navbar.jsp"%>
            <div id="page-wrapper">
                <%
                    TreeMap<String, Customer> customerMap = new TreeMap<String, Customer>();
                    if (verify != null && verify.equals("admin")) {
                        customerMap = CustomerDAO.retrieveAllCustomers();
                        System.out.println("admin customermap size" + customerMap.size());
                    } else {
                        customerMap = CustomerDAO.retrieveAllCustomersByStaff(verify);
                    }
                    System.out.println("overall customermap size" + customerMap.size());
                    List<Customer> customList = new ArrayList<>(customerMap.values());
                    ArrayList<String> list = UserDAO.retrieveAllNames();
                    String error = (String) request.getAttribute("error");
                    String success = (String) request.getAttribute("success");
                    if (error != null) {
                        out.println("<div class='alert alert-danger' role='alert'>");
                        out.println("<strong>Error:</strong>");
                        out.println(error + "<br>");
                        out.println("</div>");
                    }

                    if (success != null) {
                        out.println("<div class='alert alert-success' role='alert'>");
                        out.println("<strong>Success:</strong>");
                        out.println(success + "<br>");
                        out.println("</div>");
                    }
                %>

                <div class="container-fluid">
                    <div class="col-lg-12">
                        <div class="row">
                            <h1 class="page-header">Customer List</h1>
                        </div>
                    </div>

                    <style>form{ display: inline-block; }</style>
                    <form action="${contextPath}/customerSearch">
                        <div id="custom-search-input">
                            <div class="input-group col-md-12">
                                <input type="text" name="search" class="form-control input-lg"  placeholder="Search customer name" />
                                <span class="input-group-btn">
                                    <button class="btn btn-info btn-lg" style= "background-color: #000000;border-color: #000000;" type="submit">
                                        <i class="fa fa-fw fa-search"></i>
                                    </button>
                                </span>
                            </div>
                        </div>
                    </form>
                </div>  

                <div class="container-fluid">       
                    <div style="float: right">
                        <button class="btn btn-default" data-toggle="modal" data-target="#myModalHorizontal">+</button> 
                        <form action="${contextPath}/customerAccounts"><button type="submit" name="male" class="btn btn-default" style="background-color: #000000;border-color: #000000; color: #ffffff"><b>Male</b></button></form>
                        <form action="${contextPath}/customerAccounts"><button type="submit" name="female" class="btn btn-default" style="background-color: #000000;border-color: #000000; color: #ffffff"><b>Female</b></button></form>
                        <form action="${contextPath}/customerAccounts"><button type="submit" name="all" class="btn btn-default" style="background-color: #000000;border-color: #000000; color: #ffffff"><b>All</b></button></form>
                    </div>   
                </div> 

                <div class="container-fluid">
                    <!-- /.row -->

                    <div class="row">
                        <div class="col-md-12">
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>Phone No.</th>
                                            <th>Name</th>
                                            <th>D.O.B</th>
                                            <th>Gender</th>
                                            <th>Email</th>
                                            <th>Remarks</th>
                                        </tr>
                                    </thead>
                                    <%
                                        TreeMap<String, Customer> map = (TreeMap<String, Customer>) request.getAttribute("map");
                                        if (map != null) {
                                            List<Customer> customerList = new ArrayList<Customer>(map.values());
                                            for (int i = 0; i < customerList.size(); i++) {
                                                Customer customer = customerList.get(i);
                                                int id = customer.getCustomerId();
                                                String customerName = customer.getCustomerName();
                                                Date dob = customer.getDob();
                                                String gender = customer.getGender();
                                                String email = customer.getEmail();
                                                String remarks = customer.getRemarks();
                                    %>     
                                    <tbody>
                                        <tr>
                                            <td><form action="${contextPath}/clientProfile">
                                                    <input type="submit" name="id" value="<%=id%>">
                                                </form></td>
                                            <td><%= customerName%></td>
                                            <td><%= dob%></td>
                                            <td><%= gender%></td> 
                                            <td><%= email%></td>
                                            <td><%= remarks%></td>	
                                        </tr>

                                        <%}
                                        } else {
                                            for (int i = 0; i < customList.size(); i++) {
                                                Customer customer = customList.get(i);
                                                int id = customer.getCustomerId();
                                                String customerName = customer.getCustomerName();
                                                Date dob = customer.getDob();
                                                String gender = customer.getGender();
                                                String email = customer.getEmail();
                                                String remarks = customer.getRemarks();
                                        %>     
                                    <tbody>    
                                        <tr>
                                            <td><form action="${contextPath}/clientProfile">
                                                    <input type="submit" name="id" value="<%=id%>">
                                                </form></td>
                                            <td><%= customerName%></td>
                                            <td><%= dob%></td>
                                            <td><%= gender%></td> 
                                            <td><%= email%></td>
                                            <td><%= remarks%></td>	
                                        </tr>                           
                                        <%}
                                            }%>
                                    </tbody>
                                </table>

                            </div>
                        </div> 

                    </div> 

                    <div class="modal fade" id="myModalHorizontal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">
                                        <span aria-hidden="true">&times;</span>
                                        <span class="sr-only">Close</span>
                                    </button>
                                    <h4 class="modal-title" id="myModalLabel">
                                        Create New Customer
                                    </h4>
                                </div>

                                <!-- Modal Body -->
                                <div class="modal-body">

                                    <form action="${contextPath}/register" data-toggle="validator" role="form" style="width:100%">
                                        <div class="form-group">
                                            <label for="inputName" class="control-label">Name</label>
                                            <input type="text" class="form-control" id="name" name="name" placeholder="Enter Customer Name" required>
                                        </div>

                                        <div class="form-group">
                                            <label for="inputcontactno" class="control-label">Contact No.</label>
                                            <input type="tel" class="form-control" id="contact" name="contact" placeholder="Enter contact number (e.g 89203829)" required>
                                        </div>

                                        <div class="form-group">
                                            <label for="inputdob">Date of Birth:</label>
                                            <input class="form-control" name="dob" type="date" required/>
                                        </div>

                                        <div class="form-group">
                                            <label for="inputEmail" class="control-label">Email</label>
                                            <input type="email" class="form-control" id="email" name ="email" placeholder="Enter Email" data-error="That email address is invalid" required>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="form-group">
                                            <label for="inputgender" class="control-label">Gender</label>
                                            <select class="form-control" id="gender" name="gender">
                                                <option>Male</option>
                                                <option>Female</option>      
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <label for="inputusername" class="control-label">Hair Stylist</label>
                                            <select class="form-control" name="username">
                                                <option value="">Select hairStylist</option>
                                                <%
                                                    for (int i = 0; i < list.size(); i++) {
                                                        String user = list.get(i);
                                                %>
                                                <option><%= user%></option>
                                                <% }%>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <label for="comment">Remarks:</label>
                                            <textarea class="form-control" rows="5" id="comment" name="remarks"></textarea>
                                        </div>

                                        <div class="form-group">
                                            <button type="submit" class="btn btn-primary btn-lg btn-block" style="background-color: #000000;border-color: #000000; color: #ffffff">Submit</button>
                                        </div>
                                    </form>

                                </div>   
                                <!-- Modal Footer -->
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default"
                                            data-dismiss="modal">
                                        Close
                                    </button>               
                                </div>
                            </div>
                        </div>
                    </div>        
                    
                </div>
            </div>

        </div>
        <!-- /.row -->

    </div>
    
</body>   
</html>

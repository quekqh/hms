<%-- 
    Document   : clientProfile
    Created on : Nov 1, 2016, 6:59:46 PM
    Author     : HajaReethwan
--%>

<%@page import="java.sql.Date"%>
<%@page import="fyp.hms.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@include file="staffProtect.jsp"%>
 <%@include file="template_head.jsp"%>
<html>
    <div id="wrapper">
        <%@include file="template_navbar.jsp"%>
        <div id="page-wrapper">
    <% 
        Customer customer = (Customer)request.getAttribute("customer");
        if(customer != null){
            String n = customer.getCustomerName();
            int number = customer.getCustomerId();
            Date regDate = customer.getRegDate();
            Date dob = customer.getDob();
            String gender = customer.getGender();
            String email = customer.getEmail();
            String remarks = customer.getRemarks();
        
    %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><%=n%></h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           registered on <%=regDate%>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                               
                                <tbody>
                                    <tbody>
        <tr>
        
        <td>Customer ID</td>
        <td><%=number%></td>
      </tr>
     
        
      <tr>
        <td>Date of birth</td>
        <td><%=dob%></td>
        
      </tr>
      <tr>
        <td>Email</td>
        <td><a href="<%=email%>"><%=email%></a></td>
      </tr>
      <tr>
        
        <td>Gender</td>
        <td><%=gender%></td>
      </tr>
      <tr>
        <td>Remark</td>
        <td><%=remarks%></td>
      </tr>
    </tbody>
                                     
                                </tbody>
                            </table>

                <form action="${contextPath}/clientTools">
                <button  class="btn btn-warning" data-original-title="Edit this user" data-toggle="tooltip" type="submit" class="btn btn-sm btn-warning" name="edit" value="<%= number%>">Edit Customer profile</button>
                <button class="btn btn-danger" data-original-title="Remove this user" data-toggle="tooltip" type="submit" class="btn btn-sm btn-danger" name="delete" value="<%= number%>">Delete Customer profile</button>
                <button class="btn btn-primary" data-original-title="Cancel" data-toggle="tooltip" type="submit" class="btn btn-sm btn-primary" name="cancel" value="Cancel" >Cancel</button> 
                
                </form> 

<%}%>
</div>
</div>
</html>

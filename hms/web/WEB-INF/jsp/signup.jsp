<%-- 
    Document   : signup
    Created on : 7 Oct, 2016, 8:37:25 PM
    Author     : Asus
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
      
    </head>
    <%@ include file="templateSignUp.jsp" %>
    <body>
        
   <div class="container-fluid">
   <div class="row">
  <div class="col-xs-2 col-md-4"></div>



  <div class="col-xs-8 col-md-4">

    
        
        <form action="${contextPath}/signup" width="40%">
            <%
                String error = (String) request.getAttribute("error");
                
                if (error != null) {
                    
                    out.println("<div class='alert alert-danger' role='alert'>");
                    out.println("<strong>Error:</strong>");
                    out.println(error);
                    out.println("</div>");
                    
                }
                
                String username = (String) request.getAttribute("username");
                if(username == null){
                    username= "";
                }
                
                String name = (String) request.getAttribute("name");
                if(name == null){
                    name= "";
                }
                
                String password = (String) request.getAttribute("password");
                if(password == null){
                    password= "";
                }
                
                String passwordRetype = (String) request.getAttribute("passwordRetype");
                if(passwordRetype == null){
                    passwordRetype= "";
                }
                
                String role = (String) request.getAttribute("role");
                if(role == null){
                    role= "";
                }
                
                String strPhoneNo = (String) request.getAttribute("phone");
                if(strPhoneNo == null){
                    strPhoneNo= "";
                }
                
                String email = (String) request.getAttribute("email");
                if(email == null){
                    email= "";
                }
                
                String address = (String) request.getAttribute("address");
                if(address == null){
                    address= "";
                }
                
                String securityCode = (String) request.getAttribute("securityCode");
                if(securityCode == null){
                    securityCode= "";
                }
                
            %>
            
      
                <h2>Registration</h2>
                <br>
     
	
		<div class="form-group" >
                    <label for="username">Username:</label>

                    <input type="text" name="username" class="form-control" id="username" placeholder="Enter username" value="<%=username%>">
                </div>

		<div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" name="name" class="form-control" id="name" placeholder="Enter name" value="<%=name%>">
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" name="password" class="form-control" id="pwd" placeholder="Enter password" value="<%=password%>">
                </div>

		<div class="form-group">
                    <label for="passwordRetype">Password(retype):</label>
                    <input type="password" name="passwordRetype" class="form-control" id="pwdRetype" placeholder="Enter password"  value="<%=passwordRetype%>">
                </div>

		<div class="form-group">
                    <label for="phone">Phone:</label>
                    <input type="text" name="phone" class="form-control" id="phone" placeholder="Enter phone number" value="<%=strPhoneNo%>">
                </div>

		<div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" name="address" class="form-control" id="address" placeholder="Enter address" value="<%=address%>">
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" name="email" class="form-control" id="email" placeholder="Enter email" value="<%=email%>">
                </div>
                
                <div class="form-group">
                    <%if(role.equals("staff")){%>
                        <label class="radio-inline">
                            <input type="radio" name="role" value="staff" checked="check"> Full-time staff
                        </label>

                        <label class="radio-inline">
                            <input type="radio" name="role" value="freelancer"> Freelancer
                        </label>
                    <%}else if(role.equals("freelancer")){%>
                        <label class="radio-inline">
                            <input type="radio" name="role" value="staff"> Full-time staff
                        </label>

                        <label class="radio-inline">
                            <input type="radio" name="role" value="freelancer" checked="check"> Freelancer
                        </label>
                    <%}else{%>
                        <label class="radio-inline">
                            <input type="radio" name="role" value="staff"> Full-time staff
                        </label>

                        <label class="radio-inline">
                            <input type="radio" name="role" value="freelancer"> Freelancer
                        </label>
                    <%}%>
                </div>
                
                <div class="form-group">
                    <label for="securityCode">Security Code</label>
                    <input type="password" name="securityCode" class="form-control" id="securityCode" placeholder="Enter security code" value="<%=securityCode%>">
                </div>
                
                <div class="form-group">   
                   <center>
                    <button type="submit" class="btn btn-secondary btn-lg btn-block" >Submit</button> </center> 
                </div>
                </br>
		    
                <center>  <a href="${contextPath}/goBackToLogin"> go back to login page </a></center> 
        </form>
    </div>
        

    <div class="col-xs-6 col-md-4"></div></div>   
    </div>
    </body>
    <br>
    <br>
</html>

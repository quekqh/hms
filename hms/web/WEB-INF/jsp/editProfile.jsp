<%-- 
    Document   : editProfile
    Created on : 15 Oct, 2016, 7:36:00 PM
    Author     : Low Kang Li
--%>
<%@page import="fyp.hms.dao.UserDAO"%>
<%@page import="fyp.hms.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="template_head.jsp"%>

    </head>

    <body>
        <div id="wrapper">
            <%@include file="template_navbar.jsp"%>
            
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="col-lg-12">
                        <div class="row">
                            
                        </div>
                    </div>
                </div>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-xs-2 col-md-4"></div>

                        <div class="col-xs-8 col-md-4">
                             <%
                //User user = UserDAO.retrieveUser(verify);
                User user = (User)request.getAttribute("userRetrieve");
                String userName = "";
                String strPhoneNum = "";
                String email = "";
                String address = "";
                
                if(user != null){
                    userName = user.getName();
                    strPhoneNum = user.getPhoneNumber() + "";
                    email = user.getEmail();
                    address = user.getAddress();
                }
                
                String nameDb = (String )request.getAttribute("name");
                if(nameDb != null){
                    userName = nameDb;
                }
                
                String addressDb = (String )request.getAttribute("address");
                if(addressDb != null){
                    address = addressDb;
                }
                
                String emailDb = (String )request.getAttribute("email");
                if(emailDb != null){
                    email = emailDb;
                }
                
                String phoneDb = (String )request.getAttribute("number");
                if(phoneDb != null){
                    strPhoneNum = phoneDb;
                }
                
                String error = (String) request.getAttribute("error");
                String success = (String) request.getAttribute("success");
                if (error != null) {
                    out.println("<div class='alert alert-danger' role='alert'>");
                    out.println("<strong>Error:</strong>");
                    out.println(error + "<br>");
                    if ((String) request.getAttribute("errorName") != null) {
                        out.println((String) request.getAttribute("errorName") + "<br>");
                    }
                    if ((String) request.getAttribute("errorNumber") != null) {
                        out.println((String) request.getAttribute("errorNumber") + "<br>");
                    }
                    if ((String) request.getAttribute("errorEmail") != null) {
                        out.println((String) request.getAttribute("errorEmail") + "<br>");
                    }
                    if ((String) request.getAttribute("errorAddress") != null) {
                        out.println((String) request.getAttribute("errorAddress") + "<br>");
                    }
                    out.println("</div>");
                }

                if (success != null) {
                    out.println("<div class='alert alert-success' role='alert'>");
                    out.println("<strong>Success:</strong>");
                    out.println(success);
                    out.println("</div>");
                }
            %>

                            <form action="${contextPath}/updateprofile" class="form-horizontal" method="POST">
                                <center> <h2>Edit profile</h2> </center>
                                <br>

                                <div class="form-group">
                                    Name:
                                    <input type="text" name="name" class="form-control" id="name" value="<%=userName%>" style="width:100%"><br>
                                </div>

                                <div class="form-group">
                                    Phone number:
                                    <input type="text" name="number" class="form-control" id="number" value="<%=strPhoneNum%>" style="width:100%"><br>
                                </div>

                                <div class="form-group">
                                    Email:
                                    <input type="text" name="email" class="form-control" id="email"  value="<%=email%>" style="width:100%"><br>
                                </div>

                                <div class="form-group">
                                    Address:
                                    <input type="text" name="address" class="form-control" id="address"  value="<%=address%>" style="width:100%"><br>
                                </div>

                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary btn-lg btn-block" style="background-color: #000000;border-color: #000000">Submit</button><br>
                                </div>
                            </form>
                        </div>

                        <div class="col-xs-2 col-md-4"></div></div>   
                </div>
            </div>
        </div>
    </body>
</html>


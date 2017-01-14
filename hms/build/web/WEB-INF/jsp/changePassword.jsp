<%-- 
    Document   : updatepassword
    Created on : Oct 15, 2016, 4:22:40 PM
    Author     : HajaReethwan
--%>

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
                            <center><h2>Change Password</h2></center>
                        </div>
                    </div>
                </div>
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-xs-2 col-md-4"></div>

                        <div class="col-xs-8 col-md-4">
                            <%
                                String error = (String) request.getAttribute("error");
                                String success = (String) request.getAttribute("success");
                                if (error != null) {
                                    out.println("<div class='alert alert-danger' role='alert'>");
                                    out.println("<strong>Error:</strong>");
                                    out.println(error + "<br>");
                                    if ((String) request.getAttribute("current") != null) {
                                        out.println((String) request.getAttribute("current") + "<br>");
                                    }
                                    if ((String) request.getAttribute("new") != null) {
                                        out.println((String) request.getAttribute("new") + "<br>");
                                    }
                                    if ((String) request.getAttribute("reenter") != null) {
                                        out.println((String) request.getAttribute("reenter") + "<br>");
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
                            <form action="${contextPath}/authenticatepassword" class="form-horizontal" method="POST">
                                <br>
                                <div class="form-group">
                                    <input type="text" name="currentpassword" class="form-control" id="currentpassword" placeholder="Enter Current Password">

                                </div>

                                <div class="form-group">
                                    <input type="text" name="newpassword" class="form-control" id="newpassword" placeholder="Enter New Password" style="width:100%">
                                </div>

                                <div class="form-group">
                                    <input type="text" name="password" class="form-control" id="password"  placeholder="Re-Enter New Password" style="width:100%"><br>
                                </div>

                                <div class="form-group">
                                    <center><button type="submit" class="btn btn-primary btn-lg btn-block" style="background-color: #000000;border-color: #000000; max-width:100%">Submit</button></center>
                                </div>
                                <br/>   



                            </form>
                        </div>


                        <div class="col-xs-2 col-md-4"></div></div>   
                </div>
            </div>
        </div>
    </body> 

</html>

<%@page import="org.springframework.ui.ModelMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title> 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
    </head> 
    <%@ include file="template_login.jsp" %>
    
    
    <body>
        
        <div class="container-fluid">
        <div class="row">
        <div class="col-xs-2 col-md-4"></div>

        <div class="col-xs-8 col-md-4">
            <form action="${contextPath}/getPassword" class="form-horizontal" method="POST">
                <%
                    String error = (String) request.getAttribute("error");
                    String success = (String) request.getAttribute("success");
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

                    String email = (String) request.getAttribute("email");
                    if(email == null){
                        email= "";
                    }

                    String confirmEmail = (String) request.getAttribute("confirmEmail");
                    if(confirmEmail == null){
                        confirmEmail= "";
                    }

                    if (success != null) {

                        out.println("<div class='alert alert-success' role='alert'>");
                        out.println("<strong>Success: </strong>");
                        out.println(success);
                        out.println("</div>");

                        username = "";
                        email= "";
                        confirmEmail= "";
                    }
                %>

                <br><br>               
                <center><img src="${contextPath}/img/harts.jpg" class="img-responsive" alt="Cinque Terre" width="309"/></center>
                <br/><br/>

                <center> <h2>Forgot Username/Password</h2> </center>

                <div class="form-group">
                    <input type="text" name="email" class="form-control" id="email" placeholder="Enter Email" value="<%=email%>">
                </div>

                <div class="form-group">
                  <input type="text" name="confirmEmail" class="form-control" id="confirmEmail" placeholder="Confirm Email" value="<%=confirmEmail%>" style="width:100%">
                </div>

                <br/>   

                <center><button type="submit" class="">submit</button></center>

                <br/>   

                <center>  <a href="${contextPath}/goBackToLogin"> go back to login page </a></center>  			

            </form>
        </div>
        

        <div class="col-xs-2 col-md-4"></div></div>   
        </div>
    </body> 
</html>
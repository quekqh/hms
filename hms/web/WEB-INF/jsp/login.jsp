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

    
        
        <form action="${contextPath}/authenticate" class="form-horizontal" method="POST">
            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {

                    out.println("<div class='alert alert-danger' role='alert'>");
                    out.println("<strong>Error:</strong>");
                    out.println(error);
                    out.println("</div>");

                }
                
                String success = (String) request.getAttribute("success");
                if (success != null) {

                    out.println("<div class='alert alert-success' role='alert'>");
                    out.println("<strong>Success:</strong>");
                    out.println(success);
                    out.println("</div>");

                }
            %>
            <br>
            <br>

           
            <center><img src="${contextPath}/img/harts.jpg" class="img-responsive" alt="Cinque Terre" width="309"/></center>
            <br/>
                  <br/>
            <center> <h2>Sign In</h2> </center>
                  <div class="form-group">
                      <input type="text" name="username" class="form-control" id="username" placeholder="Enter Username" >
                  </div>
                  <div class="form-group">
                    <input type="password" name="password" class="form-control" id="pwd" placeholder="Enter Password">
                  </div>
                <div class="form-group">
            <center><button type="submit" class="btn btn-primary btn-lg btn-block" style="background-color: #000000;border-color: #000000">Sign In</button></center>
                </div> <br/>
           
                  <center> <p>
                      <a href="${contextPath}/forgotPwd">Forgot Username/Password?</a>
                      </p> </center>


            
            
    </form>
     
 
</div>
        

 <div class="col-xs-2 col-md-4"></div></div>   
   </div>
    </body>
    
    
   
    
</html>
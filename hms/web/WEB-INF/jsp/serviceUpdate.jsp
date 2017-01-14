<%-- 
    Document   : services
    Created on : Jan 2, 2017, 1:46:23 PM
    Author     : HajaReethwan
--%>

<%@page import="fyp.hms.model.Services"%>
<%@page import="fyp.hms.dao.ServiceDAO"%>
<%@page import="java.util.List"%>
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
        <%
            List<String> categories = ServiceDAO.retrieveCategory();
            String num = (String)request.getAttribute("id");
            if(num != null){
                int id = Integer.parseInt(num); 
                Services service = ServiceDAO.retrieveService(id);
                String desc = service.getServiceDescription();
                double shortPrice = service.getShortPrice();
                double mediumPrice = service.getMediumPrice();
                double longPrice = service.getLongPrice();
                String category = service.getCategory();
        %>
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
                                <h1>EDIT SERVICE</h1>
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
            out.println("<div class='alert alert-danger' role='alert'>");
            out.println("<strong>Error:</strong>");
            out.println(error + "<br>");
            if((String)request.getAttribute("desc") != null){
                out.println((String)request.getAttribute("desc") + "<br>");
            }
            if((String)request.getAttribute("short") != null){
                out.println((String)request.getAttribute("short") + "<br>");            
            }
            if((String)request.getAttribute("medium") != null){
                out.println((String)request.getAttribute("medium") + "<br>");            
            }
            if((String)request.getAttribute("long") != null){
                out.println((String)request.getAttribute("long") + "<br>");           
            }
            out.println("</div>");
        }         
    %>        
    
    <form action="${contextPath}/updateService" class="form" method="GET">
        <input type="hidden" name="id" class="form-control" id="id" value="<%= id %>">      
                <div class="form-group">
                    Service ID:
                    <input type="text" name="id" class="form-control" id="id" value=" <%= id %> "  style="width:100%" readonly><br>
                </div>

                <div class="form-group">
                    Service Name:
                    <input type="text" name="desc" class="form-control" id="desc" value=" <%= desc %>  " placeholder="Enter Description"  style="width:100%"><br>
                </div>
                
                <div class="form-group">
                    Short Price:
                    <input type="price" name="short" class="form-control" id="short"  value=" <%= shortPrice %> " placeholder="Enter price for short hair" style="width:100%"><br>
                </div>
                
                <div class="form-group">
                    Medium Price:
                    <input type="price" name="medium" class="form-control" id="medium"  value=" <%= mediumPrice %> " placeholder="Enter price for medium hair" style="width:100%"><br>
                </div>                 
                                      
                <div class="form-group">
                    Long Price:
                    <input type="price" name="long" class="form-control" id="long"  value=" <%= longPrice %> " placeholder="Enter price for long hair" style="width:100%"><br>
                </div>
                
                <div class="form-group">
                    Category:
                    <select class="form-control" name="category">
                        <%
                            for (int i = 0; i < categories.size(); i++) {
                                String s = categories.get(i);
                        %>
                            <option><%= s %></option>
                        <%}%>
                    </select>
                </div>        

        <div class="form-group text-center">
            <button type="submit" name="submit" value="Submit" class="btn btn-warning">Submit</button>  
            <button type="submit" name="cancel" value="Cancel" class="btn btn-primary">Cancel</button> 
        </div>
    </form>
                
    </div>  
           
        
    <% } %>
    <div class="col-xs-1 col-md-4"></div>  
    </div>
          </div>
                </div>

            </div>
        </div>

    </body>

   

</html>
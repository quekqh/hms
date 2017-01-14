<%-- 
    Document   : payslipUpdate
    Created on : Jan 11, 2017, 1:24:23 PM
    Author     : HajaReethwan
--%>
<%@page import="fyp.hms.model.Payroll"%>
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
            Payroll payroll = (Payroll)request.getAttribute("payroll");
            String userId = payroll.getUserId();
            Date date = payroll.getDate();
            int basic = payroll.getBasicPay();
            int comm = payroll.getCommission();
            int deduct = payroll.getDeductions();
            int gross = payroll.getGrossPay();
            int cpf = payroll.getCpf();
            int nett = payroll.getNettPay();
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
            out.println("</div>");
        }         
    %>        
    
    <form action="${contextPath}/updatePayslip" class="form" method="GET"> 
            
                <div class="form-group">
                    User Id:
                    <input type="text" name="id" class="form-control" id="id" value=" <%= userId %> "  style="width:100%" readonly><br>
                </div>
                
                <div class="form-group">
                    Date:
                    <input type="text" name="date" class="form-control" id="date" value=" <%= date %> "  style="width:100%" readonly><br>
                </div>                
        
                <div class="form-group">
                    Basic Pay:
                    <input type="text" name="basic" class="form-control" id="basic" value=" <%= basic %> "  style="width:100%" placeholder="Enter Basic Pay"><br>
                </div>

                <div class="form-group">
                    Commission:
                    <input type="text" name="comm" class="form-control" id="comm" value=" <%= comm %>  " placeholder="Enter Commission Amount"  style="width:100%"><br>
                </div>
                
                <div class="form-group">
                    Deductions:
                    <input type="text" name="deduct" class="form-control" id="deduct"  value=" <%= deduct %> " placeholder="Enter Deduction Amount" style="width:100%"><br>
                </div>
                
                <div class="form-group">
                    Gross Pay:
                    <input type="text" name="gross" class="form-control" id="gross"  value=" <%= basic + comm - deduct %> " placeholder="Enter price for medium hair" style="width:100%"><br>
                </div>                 
                                      
                <div class="form-group">
                    CPF Contribution:
                    <input type="text" name="cpf" class="form-control" id="cpf"  value=" <%= cpf %> " placeholder="Enter CPF Contribution" style="width:100%"><br>
                </div>
                
                <div class="form-group">
                    Nett Pay:
                    <input type="text" name="nett" class="form-control" id="nett"  value=" <%= nett %> " placeholder="Enter Nett Pay" style="width:100%"><br>
                </div>        

        <div class="form-group text-center">
            <button type="submit" name="submit" value="Submit" class="btn btn-warning">Submit</button>  
            <button type="submit" name="cancel" value="Cancel" class="btn btn-primary">Cancel</button> 
        </div>
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

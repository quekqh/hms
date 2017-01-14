<%-- 
    Document   : payslipTable
    Created on : Jan 2, 2017, 10:34:58 PM
    Author     : HajaReethwan
--%>

<%@page import="java.util.Date"%>
<%@page import="fyp.hms.model.Payroll"%>
<%@page import="fyp.hms.model.Role"%>
<%@page import="fyp.hms.dao.UserDAO"%>
<%@page import="java.util.List"%>
<%@page import="fyp.hms.model.Leave"%>
<%@page import="fyp.hms.model.User"%>
<%@page import="fyp.hms.dao.LeaveDAO"%>
<%@page import="fyp.hms.dao.RoleDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>

<html>
    <head>
        <%@ include file="template_head.jsp" %>
    </head>
    <body>
        <div id="wrapper">
            <!-- Navigation -->
            <form action="${contextPath}/payslipFunction" class="form" method="GET">
            <%@include file="template_navbar.jsp" %>
    <% 
        String username = (String) session.getAttribute("username");
        if(username.equals("admin")){
            String userName = (String)request.getAttribute("name");
            String startDate = (String)request.getAttribute("startDate");
            String endDate = (String)request.getAttribute("endDate");
            User user = UserDAO.retrieveUserByName(userName);
            Role role = RoleDAO.retrieveRoleById(user.getRole());
            String userId = user.getUsername();
            List <Leave> medLeaves = LeaveDAO.retrieveMedicalLeaves(userId);
            int numMedLeaves = medLeaves.size();
            List <Leave> weeklyLeaves = LeaveDAO.retrieveWeeklyLeaves(userId, startDate, endDate);
            int numWeeklyLeaves = weeklyLeaves.size();
            int grossPay = 0;
            int nettPay = 0;
            final int COMMISSION = 3000;
            int deductions = 0;
            int cpfContribution = 0;
            final int BASIC_PAY = 2500;
            if(numMedLeaves > 14){
                deductions = deductions + ((numMedLeaves - 14) * 200);
            }
            if(numWeeklyLeaves > 5){
                deductions = deductions + ((numWeeklyLeaves - 5) * 200);
            }
            grossPay = BASIC_PAY + COMMISSION - deductions;
            cpfContribution = grossPay * 17/100;
            nettPay = grossPay - cpfContribution;
    %>
        <div id="page-wrapper">
            
                <input type="hidden" name="id" value="<%= userId %>">
                <input type="hidden" name="date" value="<%= startDate %>">
                <input type="hidden" name="basicPay" value="<%= BASIC_PAY %>">
                <input type="hidden" name="commission" value="<%= COMMISSION %>">
                <input type="hidden" name="deductions" value="<%= deductions %>">
                <input type="hidden" name="grossPay" value="<%= grossPay %>">
                <input type="hidden" name="cpfContribution" value="<%= cpfContribution %>">
                <input type="hidden" name="nettPay" value="<%= nettPay %>"
        <div class="container">
              
              <style>
                  .invoice-title h2, .invoice-title h3 {
    display: inline-block;
}

.table > tbody > tr > .no-line {
    border-top: none;
}

.table > thead > tr > .no-line {
    border-bottom: none;
}

.table > tbody > tr > .thick-line {
    border-top: 2px solid;
}
               </style>         
    <div class="row">
        <div class="col-md-10">
    		<div class="invoice-title">
    			<h2>Payslip</h2>
    		</div>
    		<hr>
    		<div class="row">
    			<div class="col-md-7">
    				<address>
                                <strong>Employee Name:</strong><br>
    					<%= userName %><br>
                                <strong>Employee Status:</strong><br>
    					<%= role.getRoleDescription() %><br>    					
    				</address>
    			</div>
    			<div class="col-md-5 text-right">
    				<address>
        			<strong>Date:</strong><br>
    					<%= startDate %><br>
    				</address>
    			</div>
    		</div>
    		
    	</div>
    </div>
    
    <div class="row">
    	<div class="col-md-10">
    		<div class="panel panel-default">
    			<div class="panel-heading">
    				<h3 class="panel-title"><strong>Payslip Breakdown</strong></h3>
    			</div>
    			<div class="panel-body">
    				<div class="table-responsive">
    					<table class="table table-condensed">
    						<thead>
                                <tr>
        							<td><strong>Item</strong></td>
                                                                <td class="text-center"><strong>Amount($)</strong></td>
        							<td class="text-right"><strong>Total($)</strong></td>
                                </tr>
    						</thead>
    						<tbody>
    							<!-- foreach ($order->lineItems as $line) or some such thing here -->
    							<tr>
    								<td>Basic Pay</td>
                                                                <td class="text-center"><%= BASIC_PAY %></td>
    								<td class="text-right"><%= BASIC_PAY %></td>
    							</tr>
                                <tr>
        							<td>Commission Pay</td>
    								<td class="text-center"><%= COMMISSION %></td>
    								<td class="text-right"><%= COMMISSION %></td>
    							</tr>
                                <tr>
                                                                <td>Leave Deduction</td>
                                                                <td class="text-center"><%= deductions %></td>
    								<td class="text-right"><%= deductions %></td>
    							</tr>
    							<tr>
    								<td class="thick-line"></td>
    								<td class="thick-line text-center"><strong>Gross Pay($)</strong></td>
    								<td class="thick-line text-right"><%= grossPay %></td>
    							</tr>
    							<tr>
    								<td class="no-line"></td>
    								<td class="no-line text-center"><strong>CPF Contribution($)</strong></td>
    								<td class="no-line text-right"><%= cpfContribution %></td>
    							</tr>
    							<tr>
    								<td class="no-line"></td>
    								<td class="no-line text-center"><strong>Nett Pay($)</strong></td>
    								<td class="no-line text-right"><%= nettPay %></td>
    							</tr>
    						</tbody>
    					</table>
    				</div>
    			</div>
    		</div>
    	</div>
    </div>
                        <div class="form-group text-center">
            <button type="submit" name="submit" value="Submit" class="btn btn-warning">Submit</button>  
            <button type="submit" name="close" value="close" class="btn btn-primary">Close</button> 
                        </div>
                                                        </form>
</div>
                 </div>                                   
        <%
            } else {
                Payroll payroll = (Payroll)request.getAttribute("payroll");
                String userId = payroll.getUserId();
                Date date = payroll.getDate();
                int basic = payroll.getBasicPay();
                int comm = payroll.getCommission();
                int deduct = payroll.getDeductions();
                int gross = payroll.getGrossPay();
                int cpf = payroll.getCpf();
                int nett = payroll.getNettPay();
                User user = UserDAO.retrieveUser(userId);
                Role role = RoleDAO.retrieveRoleById(user.getRole());
                String userName = user.getName();
        %>
        <div id="page-wrapper">
        <div class="container">
              
              <style>
                  .invoice-title h2, .invoice-title h3 {
    display: inline-block;
}

.table > tbody > tr > .no-line {
    border-top: none;
}

.table > thead > tr > .no-line {
    border-bottom: none;
}

.table > tbody > tr > .thick-line {
    border-top: 2px solid;
}
               </style>         
    <div class="row">
        <div class="col-md-10">
    		<div class="invoice-title">
    			<h2>Payslip</h2>
    		</div>
    		<hr>
    		<div class="row">
    			<div class="col-md-7">
    				<address>
                                <strong>Employee Name:</strong><br>
    					<%= userName %><br>
                                <strong>Employee Status:</strong><br>
    					<%= role.getRoleDescription() %><br>    					
    				</address>
    			</div>
    			<div class="col-md-5 text-right">
    				<address>
        			<strong>Date:</strong><br>
    					<%= date %><br>
    				</address>
    			</div>
    		</div>
    		
    	</div>
    </div>
    
    <div class="row">
    	<div class="col-md-10">
    		<div class="panel panel-default">
    			<div class="panel-heading">
    				<h3 class="panel-title"><strong>Payslip Breakdown</strong></h3>
    			</div>
    			<div class="panel-body">
    				<div class="table-responsive">
    					<table class="table table-condensed">
    						<thead>
                                <tr>
        							<td><strong>Item</strong></td>
                                                                <td class="text-center"><strong>Amount($)</strong></td>
        							<td class="text-right"><strong>Total($)</strong></td>
                                </tr>
    						</thead>
    						<tbody>
    							<!-- foreach ($order->lineItems as $line) or some such thing here -->
    							<tr>
    								<td>Basic Pay</td>
                                                                <td class="text-center"><%= basic %></td>
    								<td class="text-right"><%= basic %></td>
    							</tr>
                                <tr>
        							<td>Commission Pay</td>
    								<td class="text-center"><%= comm %></td>
    								<td class="text-right"><%= comm %></td>
    							</tr>
                                <tr>
                                                                <td>Leave Deduction</td>
                                                                <td class="text-center"><%= deduct %></td>
    								<td class="text-right"><%= deduct %></td>
    							</tr>
    							<tr>
    								<td class="thick-line"></td>
    								<td class="thick-line text-center"><strong>Gross Pay($)</strong></td>
    								<td class="thick-line text-right"><%= gross %></td>
    							</tr>
    							<tr>
    								<td class="no-line"></td>
    								<td class="no-line text-center"><strong>CPF Contribution($)</strong></td>
    								<td class="no-line text-right"><%= cpf %></td>
    							</tr>
    							<tr>
    								<td class="no-line"></td>
    								<td class="no-line text-center"><strong>Nett Pay($)</strong></td>
    								<td class="no-line text-right"><%= nett %></td>
    							</tr>
    						</tbody>
    					</table>
    				</div>
    			</div>
    		</div>
    	</div>
    </div>
            <form action="${contextPath}/closePayslip" class="form" method="GET">                                            
                        <div class="form-group text-center">
            <button type="submit" name="close" class="btn btn-warning">Close</button>             
                        </div>
            </form>
</div>
                 </div>
            <% } %>            
    </body>

    

</html>


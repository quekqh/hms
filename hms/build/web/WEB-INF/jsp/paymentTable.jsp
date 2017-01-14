<%-- 
    Document   : paymentTable
    Created on : 28 Dec, 2016, 12:24:49 AM
    Author     : Low Kang Li
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="fyp.hms.model.Payment"%>
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
        <style>

            /* ==========================
            Template name: Outlined Flat Buttons
            Version: 1.0
            Author: RowBootstrap
            Author website: http://www.rowbootstrap.com/
            ========================== */ 


            /* =================================
            Basic Styling
            ================================= */ 
            .table-responsive {height:450px;}
            body,
            html {
                width: 100%;
                    height: 100%;
                    margin: 0;
                    padding: 0;
                    font-family: 'Open Sans', sans-serif;
                    color: #000000;
                    background: #FFFFFF;
            }

            /* Text colors */

            .text-color-white {
                    color: #FFFFFF;
            }

            .text-color-black {
                    color: #000000;
            }

            .text-color-theme {
                    color: #6f5499;
            }

            /* Background colors */
            .theme-bg{
                    background: #6f5499;
            }

            .bg-black{
                    background: #000000;
            }

            .secondary-bg {
                    background: #f0ecf6;
            }


            /* Margin */

            .margin-bottom-0 {
                    margin-bottom: 0 !important;
            }

            .margin-bottom-10 {
                    margin-bottom: 10px !important;
            }

            .margin-bottom-15 {
                    margin-bottom: 15px !important;
            }

            .margin-bottom-20 {
                    margin-bottom: 20px !important;
            }

            .margin-bottom-30 {
                    margin-bottom: 30px !important;
            }

            .margin-bottom-40 {
                    margin-bottom: 40px !important;
            }

            .margin-bottom-50 {
                    margin-bottom: 50px !important;
            }

            .margin-bottom-60 {
                    margin-bottom: 60px !important;
            }

            .margin-bottom-70 {
                    margin-bottom: 70px !important;
            }

            .margin-bottom-80 {
                    margin-bottom: 80px !important;
            }

            .margin-bottom-90 {
                    margin-bottom: 90px !important;
            }

            .margin-bottom-100 {
                    margin-bottom: 100px !important;
            }

            .margin-bottom-120 {
                    margin-bottom: 120px !important;
            }

            .margin-right-0 {
                    margin-right: 0 !important;
            }

            .margin-right-5 {
                    margin-right: 5px !important;
            }

            .margin-right-10 {
                    margin-right: 10px !important;
            }

            /* Padding surround */

            .padding-0 {
                    padding: 0px !important;
            }

            .padding-30 {
                    padding: 30px !important;
            }

            .padding-50 {
                    padding: 40px !important;
            }


            /* Buttons */

            .btn {
                    letter-spacing: 1px;
                    text-decoration: none;
                    background: none;
                -moz-user-select: none;
                background-image: none;
                border: 1px solid transparent;
                border-radius: 0;
                cursor: pointer;
                display: inline-block;
                margin-bottom: 0;
                vertical-align: middle;
                white-space: nowrap;
                    font-size:14px;
                    line-height:20px;
                    font-weight:700;
                    text-transform:uppercase;
                    border: 3px solid;
                    padding:8px 20px;
            }

            .btn-outlined {
                border-radius: 0;
                -webkit-transition: all 0.3s;
                   -moz-transition: all 0.3s;
                        transition: all 0.3s;
            }

            .btn-outlined.btn-theme {
                background: none;
                color: #6f5499;
                    border-color: #6f5499;
            }

            .btn-outlined.btn-theme:hover,
            .btn-outlined.btn-theme:active {
                color: #FFF;
                background: #6f5499;
                border-color: #6f5499;
            }

            .btn-outlined.btn-black {
                background: none;
                color: #000000;
                    border-color: #000000;
            }

            .btn-outlined.btn-black:hover,
            .btn-outlined.btn-black:active {
                color: #FFF;
                background: #000000;
                border-color: #000000;
            }

            .btn-outlined.btn-white {
                background: none;
                color: #FFFFFF;
                    border-color: #FFFFFF;
            }

            .btn-outlined.btn-white:hover,
            .btn-outlined.btn-white:active {
                color: #6f5499;
                background: #FFFFFF;
                border-color: #FFFFFF;
            }

            .btn-xs{
                    font-size:11px;
                    line-height:14px;
                    border: 1px solid;
                    padding:5px 10px;
            }

            .btn-sm{
                    font-size:12px;
                    line-height:16px;
                    border: 2px solid;
                    padding:8px 15px;
            }

            .btn-lg{
                    font-size:18px;
                    line-height:22px;
                    border: 4px solid;
                    padding:13px 40px;
            }
        </style>
    </head>
    <body>

        <div id="wrapper">
            <%@include file="template_navbar.jsp"%>
            <div id="page-wrapper">
            
                <%
                    List<Payment> allPayments = (List<Payment>)request.getAttribute("allPayments");
                    List<Payment> todayPayments = (List<Payment>)request.getAttribute("todayPayments");
                    TreeMap<String, User> users = (TreeMap<String, User>) session.getAttribute("allStaff");
                    double totalPayment = 0;
                %>
                <div class="container-fluid">
                </br>
                </br>
                </br>
                
                <h1 class="page-header">Payment List</h1>
                 
                
                
                
   <div class="container">
    <div class='col-md-5'>
        <div class="form-group">
                from:
                 <input class="form-control" id="paymentDateFrom" name="paymentDate" type="date" onchange="javascript:selectDate();"/>
            
        </div>
    </div>
    <div class='col-md-5'>
        <div class="form-group">
             to:
             <input class="form-control" id="paymentDateTo" name="paymentDate" type="date" onchange="javascript:selectDate();"/>
        </div>
    </div>
</div>
                
                
                <div class="container-fluid">       
                    <div style="float: right">
                        <button type="submit" name="all" class="btn btn-default" onclick="javascript:displayAll();" style="background-color: #000000;border-color: #000000; color: #ffffff"><b>All</b></button>
                    </div>   
                </div> 
                <%
                  if(todayPayments == null){  
                %>
                <h3><i><b>No payment has been made today.</b></i></h3>
                <%
                  }else{  
                %>
                <div id="displayTable">
                <div class="row">
                    <div class="col-md-12">

                        <div class="table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Payment Date</th>
                                        <th>Customer Id</th>
                                        <th>Assigned Hair Stylist</th>
                                        <th>Services Provided</th>
                                        <th>Payment</th>
                                    </tr>
                                </thead>
                                <%
                                    if(todayPayments != null){
                                        for(Payment p: todayPayments){
                                            int apptId = p.getApptId();
                                            String hairStylist = p.getUsername();
                                            int customerId = p.getCustomerId();
                                            double paymentAmt = p.getPayment();
                                            String services = p.getServices();
                                            Date date = p.getPaymentDate();
                                            totalPayment += paymentAmt;
                                %>
                                <tbody>
                                    <tr>
                                        <td><%=date%></td>
                                        <td><%=customerId%></td>
                                            <%            
                                            if(users != null){
                                                Set set = users.entrySet();
                                                Iterator iterator = set.iterator();
                                                while(iterator.hasNext()) {
                                                    Map.Entry mentry = (Map.Entry)iterator.next();
                                                    User u = (User)mentry.getValue();
                                                    String uName = u.getUsername();
                                                    String n = u.getName();

                                                    if(hairStylist.equals(uName)){


                                %>     

                                        <td><%=n%></td> 

                                        <%  break;              }

                                                    }

                                                }  
                                    %>

                                    <td><%=services%></td>
                                    <td>$<%=paymentAmt%>0</td>	
                                    </tr>

                                    <%
                                            }                                            

                                        }
                                    %>
                                    <tr>
                                        <td><b>Total</b></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td><b>$<%=totalPayment%>0</b></td>
                                    </tr>
                                </tbody>
                            </table>

                        </div>
                    </div> 
                     
                </div> 
                
            </div>
            <%}%>
        </div>
    </body>

    <script>
    $.fn.pageMe = function(opts){
        var $this = this,
            defaults = {
                perPage: 7,
                showPrevNext: false,
                hidePageNumbers: false
            },
            settings = $.extend(defaults, opts);

            var listElement = $this;
            var perPage = settings.perPage; 
            var children = listElement.children();
            var pager = $('.pager');

            if (typeof settings.childSelector!="undefined") {
                children = listElement.find(settings.childSelector);
            }

            if (typeof settings.pagerSelector!="undefined") {
                pager = $(settings.pagerSelector);
            }

            var numItems = children.size();
            var numPages = Math.ceil(numItems/perPage);

            pager.data("curr",0);

            if (settings.showPrevNext){
                $('<li><a href="#" class="prev_link">«</a></li>').appendTo(pager);
            }

            var curr = 0;
            while(numPages > curr && (settings.hidePageNumbers==false)){
                $('<li><a href="#" class="page_link">'+(curr+1)+'</a></li>').appendTo(pager);
                curr++;
            }

            if (settings.showPrevNext){
                $('<li><a href="#" class="next_link">»</a></li>').appendTo(pager);
            }

            pager.find('.page_link:first').addClass('active');
            pager.find('.prev_link').hide();
            if (numPages<=1) {
                pager.find('.next_link').hide();
            }
            pager.children().eq(1).addClass("active");

            children.hide();
            children.slice(0, perPage).show();

            pager.find('li .page_link').click(function(){
                var clickedPage = $(this).html().valueOf()-1;
                goTo(clickedPage,perPage);
                return false;
            });
            pager.find('li .prev_link').click(function(){
                previous();
                return false;
            });
            pager.find('li .next_link').click(function(){
                next();
                return false;
            });

            function previous(){
                var goToPage = parseInt(pager.data("curr")) - 1;
                goTo(goToPage);
            }

            function next(){
                goToPage = parseInt(pager.data("curr")) + 1;
                goTo(goToPage);
            }

            function goTo(page){
                var startAt = page * perPage,
                    endOn = startAt + perPage;

                children.css('display','none').slice(startAt, endOn).show();

                if (page>=1) {
                    pager.find('.prev_link').show();
                }
                else {
                    pager.find('.prev_link').hide();
                }

                if (page<(numPages-1)) {
                    pager.find('.next_link').show();
                }
                else {
                    pager.find('.next_link').hide();
                }

                pager.data("curr",page);
                pager.children().removeClass("active");
                pager.children().eq(page+1).addClass("active");

            }
    };

    $(document).ready(function(){
        $('#myTable').pageMe({pagerSelector:'#myPager',showPrevNext:true,hidePageNumbers:false,perPage:4});
    });
    
    $(function () {
        $('#datetimepicker1').datetimepicker();
    });
            
             
    $(function () {
        $('#datetimepicker3').datetimepicker({
            format: 'LT'
        });
    });
    
    
    function selectDate(){
        var inputDate = document.getElementById('paymentDateFrom').value;
        var inputDateTo = document.getElementById('paymentDateTo').value;
        //document.write(inputDateTo);
        //document.write(inputDate);
        var table = "<div class='row'>";
        table += "<div class='col-md-12'>";
        table += "<div class='table-responsive'>";
        table += "<table class='table table-bordered table-hover'>";
        table += "<thead>";
        table += "<tr>";
        table += "<th>Payment Date</th>";
        table += "<th>Customer Id</th>";
        table += "<th>Assigned Hair Stylist</th>";
        table += "<th>Services Provided</th>";
        table += "<th>Payment</th>";
        table += "</tr>";
        table += "</thead>";
        table += "<tbody>";
        var total = 0.0;
        var cannotFind = true;
        <%   
  
            if(allPayments != null){
                for(Payment p: allPayments){

                    int apptId = p.getApptId();
                    String hairStylist = p.getUsername();
                    int customerId = p.getCustomerId();
                    double paymentAmt = p.getPayment();
                    String services = p.getServices();
                    Date date = p.getPaymentDate();
                    String strDate = date.toString();
                    
                      
        %>                    
                    var paymentDate = '<%=strDate%>';
                    var paymentAmtDouble = <%=paymentAmt%>;
                    var realPaymentDate = new Date(paymentDate);
                    var realInputDate  = new Date(inputDate);
                    var realInputDateTo  = new Date(inputDateTo);
                    if(inputDate !== null && inputDateTo !== null && inputDateTo != ''){
                        if(realPaymentDate >= realInputDate && realPaymentDate <= realInputDateTo){
                            cannotFind = false;   
                            total = total + paymentAmtDouble;
                            table += "<tr>";
                            table += "<td><%=date%></td>";
                            table += "<td><%=customerId%></td>";   
                            
        <%  
                        //total = total + paymentAmt;
                            if(users != null){
                                Set set = users.entrySet();
                                Iterator iterator = set.iterator();
                                while(iterator.hasNext()) {
                                    Map.Entry mentry = (Map.Entry)iterator.next();
                                    User u = (User)mentry.getValue();
                                    String uName = u.getUsername();
                                    String n = u.getName();

                                    if(hairStylist.equals(uName)){

        %>
                                        table += "<td><%=n%></td>";     
                                        
        <%
                                        break;
                                    }
                                }
                            }
        %>    
                            table += "<td><%=services%></td>";
                            table += "<td>$<%=paymentAmt%>0</td>";                    
                            table += "</tr>";
                              
                        }   
                    }else if(inputDate === paymentDate){
                        cannotFind = false;   
                        total = total + paymentAmtDouble;
                        table += "<tr>";
                        table += "<td><%=date%></td>";
                        table += "<td><%=customerId%></td>";              
        <%  
                        //total = total + paymentAmt;
                        if(users != null){
                            Set set = users.entrySet();
                            Iterator iterator = set.iterator();
                            while(iterator.hasNext()) {
                                Map.Entry mentry = (Map.Entry)iterator.next();
                                User u = (User)mentry.getValue();
                                String uName = u.getUsername();
                                String n = u.getName();

                                if(hairStylist.equals(uName)){

        %>
                                    table += "<td><%=n%></td>"; 
        <%
                                    break;
                                }
                            }
                        }
        %>

                        table += "<td><%=services%></td>";
                        table += "<td>$<%=paymentAmt%>0</td>";                    
                        table += "</tr>";
                    }
        <%
                }
            }        
        %>
                
        if(cannotFind){
            if(inputDate !== null && inputDateTo !== null && inputDateTo !== ''){
                alert("No payment has been made between " + inputDate + " and " + inputDateTo);
            }else{
                if(inputDate !== null && inputDate !== ''){
                    alert("No payment has been made on " + inputDate);
                }
            }
        }else{
            table += "<tr><td><b>Total</b></td><td></td><td></td><td></td><td><input type='hidden' name='paymentAmount' value='" + total + "' /><b>$" + total + ".00</b></td></tr>";
            table += "</tbody></table>";

            document.getElementById('displayTable').innerHTML = table;
        }
    };
    
    function displayAll(){
        
        var table = "<div class='row'>";
        table += "<div class='col-md-12'>";
        table += "<div class='table-responsive'>";
        table += "<table class='table table-bordered table-hover'>";
        table += "<thead>";
        table += "<tr>";
        table += "<th>Payment Date</th>";
        table += "<th>Customer Id</th>";
        table += "<th>Assigned Hair Stylist</th>";
        table += "<th>Services Provided</th>";
        table += "<th>Payment</th>";
        table += "</tr>";
        table += "</thead>";
        table += "<tbody>";
        var total = 0.0;
        var cannotFind = true;
        <%   
            if(allPayments != null){
                for(Payment p: allPayments){

                    int apptId = p.getApptId();
                    String hairStylist = p.getUsername();
                    int customerId = p.getCustomerId();
                    double paymentAmt = p.getPayment();
                    String services = p.getServices();
                    Date date = p.getPaymentDate();
                    String strDate = date.toString();
                    
                      
        %>                    
                    
                    var paymentAmtDouble = <%=paymentAmt%>; 
                    total = total + paymentAmtDouble;
                    table += "<tr>";
                    table += "<td><%=date%></td>";
                    table += "<td><%=customerId%></td>";              
        <%  
                        //total = total + paymentAmt;
                    if(users != null){
                        Set set = users.entrySet();
                        Iterator iterator = set.iterator();
                        while(iterator.hasNext()) {
                            Map.Entry mentry = (Map.Entry)iterator.next();
                            User u = (User)mentry.getValue();
                            String uName = u.getUsername();
                            String n = u.getName();

                            if(hairStylist.equals(uName)){

        %>
                                table += "<td><%=n%></td>"; 
        <%
                                break;
                            }
                        }
                    }
        %>

                    table += "<td><%=services%></td>";
                    table += "<td>$<%=paymentAmt%>0</td>";                    
                    table += "</tr>";
                    
        <%
                }
            }        
        %>
        
        table += "<tr><td><b>Total</b></td><td></td><td></td><td></td><td><input type='hidden' name='paymentAmount' value='" + total + "' /><b>$" + total + ".00</b></td></tr>";
        table += "</tbody></table>";

        document.getElementById('displayTable').innerHTML = table;
        
    };
    </script>
                   
   
</html>

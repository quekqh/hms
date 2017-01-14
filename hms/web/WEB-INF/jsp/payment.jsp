<!DOCTYPE html>
<%@page import="fyp.hms.model.Services"%>
<%@page import="java.util.List"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="fyp.hms.model.Appointment"%>
<%@page import="fyp.hms.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@include file="adminProtect.jsp"%>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

        <%@ include file="template_head.jsp" %>
         <style>
            .package-table tbody tr:nth-child(odd){background:#FAFAFA;}
            .package-table td{border-top:0px !important; border-right:1px solid #fff !important;}
            .package-table tbody tr:nth-child(even){background:#DFDFDF; }
        </style>
  

</head>

<body>

    <div id="wrapper">
  <%@include file="template_navbar.jsp" %>
      

        <div id="page-wrapper">

            <div class="container-fluid">
<%
    String error = (String) request.getAttribute("error");
    if (error != null) {

        out.println("<div class='alert alert-danger' role='alert'>");
        out.println("<strong>Error:</strong>");
        out.println(error);
        out.println("</div>");

    }
%>
   <form action="${contextPath}/paymentForm" data-toggle="validator" role="form" style="width:100%">
                <div class="row">
                    <div class="col-lg-6">
                        
                        <%
        String hairStylistName = "";
        String custId = "";
        String customerName = "";
        String dob = "";
        String email = "";
        String apptDate = "";
        String time = "";
        String remarks = "";
        String comment = "";
        String discount = "";
        String distAmt = "";
                
        Customer customer = (Customer)request.getAttribute("customer");
        if(customer != null){
            custId = customer.getCustomerId() + "";
            customerName = customer.getCustomerName();
            dob = customer.getDob() + "";
            email = customer.getEmail();
            
        }
        
        String strRemark = (String)request.getAttribute("remarks");
        String strComment = (String)request.getAttribute("comment");
        
        if(strRemark != null){
            remarks = strRemark;
        }
        
        if(strComment != null){
            comment = strComment;
        }
        
        String strDiscount = (String)request.getAttribute("discount");
        String strDistAmt = (String)request.getAttribute("distAmt");
        
        if(strDiscount != null){
            discount = strDiscount;
        }
        
        if(strDistAmt != null){
            distAmt = strDistAmt;
        }
        
        Appointment appt = (Appointment)request.getAttribute("appt");
        int apptId = 0;
        //User assignHS = (User)request.getAttribute("appointedHairStylist");
        if(appt != null){
            apptId = appt.getAppointmentId();
            hairStylistName = appt.getHairStylistUsername();
            apptDate = appt.getAppointmentDate() + "";
            time = appt.getAppointmentTime() + "";
            //remarks = appt.getRemarks();
        }

    %>
                                                        
          
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            Appointment Information
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <!-- Nav tabs -->
                            <input type="hidden" name="apptId" value="<%=apptId%>"/>
        <input type="hidden" name="username" value="<%=hairStylistName%>"/>
        <div class="form-group">
            <label for="inputCustomerId" class="control-label">Customer Id</label>
            <input type="tel" class="form-control" id="customerId" minlength="8" maxlength="8" name="customerId" placeholder="Enter Customer Id (customer contact number)" value="<%=custId%>" readonly>
        </div>

        <div class="form-group">
            <label for="inputCustomerName" class="control-label">Customer Name</label>
            <input type="text" class="form-control" id="customerName" name="customerName" placeholder="Enter customer Name" value="<%=customerName%>" readonly>
        </div>

        <%
            TreeMap<String, User> users = (TreeMap<String, User>) session.getAttribute("allStaff");
        
        %>
        <div class="form-group">
            <label for="inputHairStylists" class="control-label">Assign Hair Stylist</label>
            
            <%
                if(users != null){ 

                    Set set = users.entrySet();
                    Iterator iterator = set.iterator();
                    while(iterator.hasNext()) {
                        Map.Entry mentry = (Map.Entry)iterator.next();
                        User u = (User)mentry.getValue();
                        String uName = u.getUsername();
                        String n = u.getName();
                        if(hairStylistName != null){
                            if(hairStylistName.equals(uName)){
                                out.println("<input type='text' class='form-control' id='hairStylistName' name='hairStylistName' value='" + n +  "' readonly>");

                            }
                        }
                    }      
                }
            %>
        </div>
        
        <div class="form-group">
            <label for="inputAppointmentDate">Appointment Date:</label>
            <input class="form-control" name="appointmentDate" type="date" value="<%=apptDate%>" readonly/>
        </div>
                
        <div class="form-group">
            <label for="inputAppointmentDate">Appointment Time:</label>
            <input class="form-control" name="appointmentTime" type="time" value="<%=time%>" readonly/>
        </div>             

                                
                            </div>
                        </div>
                        <!-- /.panel-body -->
                
         

                       
                    </div>
                  <div class="col-lg-6">
                        <div class="panel panel-green">
                        <div class="panel-heading">
                            Discount
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            
                            
<%
    if(discount.equals("20")){
%>                            
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="20" onclick="displayPrice();" checked="checked">First Visit Discount (20%)</label>
</div>
                            
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="25" onclick="displayPrice();">Student Discount (25%)</label>
</div>
                            
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="10" onclick="displayPrice();">Loyal Customer Discount (10%)</label>
</div>
                            
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="50" onclick="displayPrice();">Hair Model Discount  (50%)</label>
</div>

<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="0" onclick="displayPrice();">No Discount</label>
</div>
<%}else if(discount.equals("25")){%>  
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="20" onclick="displayPrice();">First Visit Discount (20%)</label>
</div>
                            
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="25" onclick="displayPrice();" checked="checked">Student Discount (25%)</label>
</div>
                            
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="10" onclick="displayPrice();">Loyal Customer Discount (10%)</label>
</div>
                            
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="50" onclick="displayPrice();">Hair Model Discount  (50%)</label>
</div>

<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="0" onclick="displayPrice();">No Discount</label>
</div>
<%}else if(discount.equals("10")){%>  
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="20" onclick="displayPrice();">First Visit Discount (20%)</label>
</div>
                            
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="25" onclick="displayPrice();">Student Discount (25%)</label>
</div>
                            
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="10" onclick="displayPrice();" checked="checked">Loyal Customer Discount (10%)</label>
</div>
                            
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="50" onclick="displayPrice();">Hair Model Discount  (50%)</label>
</div>

<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="0" onclick="displayPrice();">No Discount</label>
</div>
<%}else if(discount.equals("50")){%>  
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="20" onclick="displayPrice();">First Visit Discount (20%)</label>
</div>
                            
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="25" onclick="displayPrice();">Student Discount (25%)</label>
</div>
                            
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="10" onclick="displayPrice();">Loyal Customer Discount (10%)</label>
</div>
                            
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="50" onclick="displayPrice();" checked="checked">Hair Model Discount  (50%)</label>
</div>

<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="0" onclick="displayPrice();">No Discount</label>
</div>
<%}else{%>
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="20" onclick="displayPrice();">First Visit Discount (20%)</label>
</div>
                            
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="25" onclick="displayPrice();">Student Discount (25%)</label>
</div>
                            
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="10" onclick="displayPrice();">Loyal Customer Discount (10%)</label>
</div>
                            
<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="50" onclick="displayPrice();">Hair Model Discount  (50%)</label>
</div>

<div class="radio">
    <label><input type="radio" id="discount" name="discount" value="0" onclick="displayPrice();">No Discount</label>
</div>
<%}%>
                     
                            </div>
                        </div>
                  
                         <div class="panel panel-red">
                        <div class="panel-heading">
                          Remarks
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
    <%
        if(remarks.equals("Very Satisfied")){
    %>
                            
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Very Satisfied" checked="checked">Very Satisfied
    </label>
                            
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Satisfied">Satisfied
    </label>
                            
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Room for Improvement">Room for improvement
    </label>
                            
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Bad">Bad
    </label>
<%}else if(remarks.equals("Satisfied")){%>
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Very Satisfied">Very Satisfied
    </label>
                            
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Satisfied" checked="checked">Satisfied
    </label>
                            
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Room for Improvement">Room for improvement
    </label>
                            
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Bad">Bad
    </label>
<%}else if(remarks.equals("Room for Improvement")){%>
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Very Satisfied">Very Satisfied
    </label>
                            
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Satisfied">Satisfied
    </label>
                            
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Room for Improvement" checked="checked">Room for improvement
    </label>
                            
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Bad">Bad
    </label>
<%}else if(remarks.equals("Bad")){%>
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Very Satisfied">Very Satisfied
    </label>
                            
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Satisfied">Satisfied
    </label>
                            
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Room for Improvement">Room for improvement
    </label>
                            
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Bad" checked="checked">Bad
    </label>
<%}else{%>
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Very Satisfied">Very Satisfied
    </label>
                            
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Satisfied">Satisfied
    </label>
                            
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Room for Improvement">Room for improvement
    </label>
                            
    <label class="radio-inline">
        <input type="radio" name="remarks" value="Bad">Bad
    </label>
<%}%>
<div class="form-group">
   Comment:
  <input type="text" class="form-control" id="comment" name="comment" value="<%=comment%>">
</div>   
                            </div>
                        </div>
            
            
            
            
                        </div>
                </div>
                <!-- /.row -->

                        <div class="row">
                 
                    
            <div class="col-lg-6">
                           <h2>cut</h2>
                         <%
            List<Services> cutServices = (List<Services>)request.getAttribute("cut");
        
        %>
                        
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover table-striped">
                                  <tbody>
              

                <tr>
                    <th>Description</th>
                    <th>Short</th>
                    <th>Medium</th>
                    <th>Long</th>
                   
                </tr>
                <%
                   
                    if(cutServices != null){
                        for(Services s: cutServices){
                            int serviceId = s.getServiceID();
                            String desc = s.getServiceDescription();
                            double shortPrice = s.getShortPrice();
                            double mediumPrice = s.getMediumPrice();
                            double longPrice = s.getLongPrice();
                           
                            
                
                %>
                <tr> 
                    <td><input type="hidden" name="serviceId" value="<%=serviceId%>"/><%=desc%></td>
                    <td><input type="checkbox" class="checkthis" name="cut" value="<%=desc%> (Short):<%=shortPrice%>\<%=serviceId%>" onclick="displayPrice();"/></td>     
                    <td><input type="checkbox" class="checkthis" name="cut" value="<%=desc%> (Medium):<%=mediumPrice%>\<%=serviceId%>" onclick="displayPrice();"/></td>
                    <td><input type="checkbox" class="checkthis" name="cut" value="<%=desc%> (Long):<%=longPrice%>\<%=serviceId%>" onclick="displayPrice();"/></td>
                    
                </tr>
                <%
                        }
                    }
                    
                %>
                
            </tbody>
                            </table>
                        </div>
                 
                    <h2>Treatment</h2>
                        <%
                        List<Services> treatmentServices = (List<Services>)request.getAttribute("treatment");
        
                        %>
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover table-striped">   
                        <tbody>
               

                <tr>
                    <th>Description</th>
                    <th>Short</th>
                    <th>Medium</th>
                    <th>Long</th>
                   
                </tr>

                <%
                    
                    if(treatmentServices != null){
                        for(Services s: treatmentServices){
                            int serviceId = s.getServiceID();
                            String desc = s.getServiceDescription();
                            double shortPrice = s.getShortPrice();
                            double mediumPrice = s.getMediumPrice();
                            double longPrice = s.getLongPrice();
                           
                            
                
                %>
                <tr> 
                    <td><input type="hidden" name="serviceId" value="<%=serviceId%>"/><%=desc%></td>
                    <td><input type="checkbox" class="checkthis" name="treatment" value="<%=desc%> (Short):<%=shortPrice%>\<%=serviceId%>" onclick="displayPrice();"/></td>     
                    <td><input type="checkbox" class="checkthis" name="treatment" value="<%=desc%> (Medium):<%=mediumPrice%>\<%=serviceId%>" onclick="displayPrice();"/></td>
                    <td><input type="checkbox" class="checkthis" name="treatment" value="<%=desc%> (Long):<%=longPrice%>\<%=serviceId%>" onclick="displayPrice();"/></td>
                    
                </tr>
                <%
                        }
                    }
                    
                %>
            </tbody>
        </table>
                        </div>
                </div>
                         
                         <div class="col-lg-6">
                        <h2>Colour</h2>
                        <%
            List<Services> colourServices = (List<Services>)request.getAttribute("colour");
        
        %>
                          <div class="table-responsive">
                            <table class="table table-bordered table-hover table-striped">  
                                <tbody>
            

                <tr>
                    <th>Description</th>
                    <th>Short</th>
                    <th>Medium</th>
                    <th>Long</th>
                   
                </tr>

                <%
                    
                    if(colourServices != null){
                        for(Services s: colourServices){
                            int serviceId = s.getServiceID();
                            String desc = s.getServiceDescription();
                            double shortPrice = s.getShortPrice();
                            double mediumPrice = s.getMediumPrice();
                            double longPrice = s.getLongPrice();
                           
                            
                
                %>
                <tr> 
                    <td><input type="hidden" name="serviceId" value="<%=serviceId%>"/><%=desc%></td>
                    <td><input type="checkbox" class="checkthis" name="colour" value="<%=desc%> (Short):<%=shortPrice%>\<%=serviceId%>" onclick="displayPrice();"/></td>     
                    <td><input type="checkbox" class="checkthis" name="colour" value="<%=desc%> (Medium):<%=mediumPrice%>\<%=serviceId%>" onclick="displayPrice();"/></td>
                    <td><input type="checkbox" class="checkthis" name="colour" value="<%=desc%> (Long):<%=longPrice%>\<%=serviceId%>" onclick="displayPrice();"/></td>
                    
                </tr>
                <%
                        }
                    }
                    
                %>
            </tbody>
        </table>
                        </div>
            
                <h2>Services</h2>
                           <%
            List<Services> styleServices = (List<Services>)request.getAttribute("style");
        
        %>
       <div class="table-responsive">
                            <table class="table table-bordered table-hover table-striped">     
            <tbody>
                

                <tr>
                    <th>Description</th>
                    <th>Short</th>
                    <th>Medium</th>
                    <th>Long</th>
                   
                </tr>
                <%
                    
                    if(styleServices != null){
                        for(Services s: styleServices){
                            int serviceId = s.getServiceID();
                            String desc = s.getServiceDescription();
                            double shortPrice = s.getShortPrice();
                            double mediumPrice = s.getMediumPrice();
                            double longPrice = s.getLongPrice();
                           
                            
                
                %>
                <tr> 
                    <td><input type="hidden" name="serviceId" value="<%=serviceId%>"/><%=desc%></td>
                    <td><input type="checkbox" class="checkthis" name="style" value="<%=desc%> (Short):<%=shortPrice%>\<%=serviceId%>" onclick="displayPrice();"/></td>     
                    <td><input type="checkbox" class="checkthis" name="style" value="<%=desc%> (Medium):<%=mediumPrice%>\<%=serviceId%>" onclick="displayPrice();"/></td>
                    <td><input type="checkbox" class="checkthis" name="style" value="<%=desc%> (Long):<%=longPrice%>\<%=serviceId%>" onclick="displayPrice();"/></td>
                    
                </tr>
                <%
                        }
                    }
                    
                %>
                
            </tbody>
        </table>
                    </div>
                    </div>
                    </div>
                
                
                <div class="row">
                    
                       <div class="col-lg-6">
                         <h2>Rebonding/Perm</h2>
                        <%
            List<Services> rpServices = (List<Services>)request.getAttribute("rp");
        
        %>
       <div class="table-responsive">
                            <table class="table table-bordered table-hover table-striped">      
            <tbody>
              

                <tr>
                    <th>Description</th>
                    <th>Short</th>
                    <th>Medium</th>
                    <th>Long</th>
                   
                </tr>
<%
                    
                    if(rpServices != null){
                        for(Services s: rpServices){
                            int serviceId = s.getServiceID();
                            String desc = s.getServiceDescription();
                            double shortPrice = s.getShortPrice();
                            double mediumPrice = s.getMediumPrice();
                            double longPrice = s.getLongPrice();
                           
                            
                
                %>
                <tr> 
                    <td><input type="hidden" name="serviceId" value="<%=serviceId%>"/><%=desc%></td>
                    <td><input type="checkbox" class="checkthis" name="rp" value="<%=desc%> (Short):<%=shortPrice%>\<%=serviceId%>" onclick="displayPrice();"/></td>     
                    <td><input type="checkbox" class="checkthis" name="rp" value="<%=desc%> (Medium):<%=mediumPrice%>\<%=serviceId%>" onclick="displayPrice();"/></td>
                    <td><input type="checkbox" class="checkthis" name="rp" value="<%=desc%> (Long):<%=longPrice%>\<%=serviceId%>" onclick="displayPrice();"/></td>
                    
                </tr>
                <%
                        }
                    }
                    
                %>
            </tbody>
        </table>
        </div>
          
        
                          
                   
     </div>
                   
              
                <!-- /.row -->

        
                   
   
                      <div class="col-lg-6">
                          
                          <div id="displayArea"></div>
                   
       
                         <div class="form-group">
                <button type="submit" name="makePayment" value="Make Payment" class="btn btn-primary btn-lg btn-block">Make Payment</button>
            </div>

            <div class="form-group">
                <button type="submit" name="cancelPayment" value="Cancel Payment" class="btn btn-primary btn-lg btn-block">Cancel</button>
            </div>
                        
                        </div>
                    </div>
         </form>
            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

   <script>
        function displayPrice(disc){
            
            var checkedValue = null; 
            var inputElements = document.getElementsByClassName('checkthis');
            
            var total = 0;
            var table = "<h2>Total</h2>";
      
            table += "<div class='col-lg-13'>";
            table += "<div class='table-responsive'>";
            table += "<table class='table table-inverse'>";
            table += "<thead><tr><th>Service(s)</th><th>Quantity</th><th>Price</th></tr></thead>";
            table += "<tbody>";
            for(var i=0; inputElements[i]; ++i){
                  if(inputElements[i].checked){
                    checkedValue = inputElements[i].value;
                    var indexValue = checkedValue.indexOf(":");
                    var desc = checkedValue.substring(0,indexValue);
                    var indexValueForDash = checkedValue.indexOf("\\");
                    var price = checkedValue.substring(indexValue + 1, indexValueForDash);
                    total = parseFloat(total) + parseFloat(price);
                    table += "<tr><td> " + desc + " </td>";
                    table += "<td>1</td>";
                    table += "<td> $" + price + "0</td></tr>";
                    
                  }
            }
            
            var radios = document.getElementsByName('discount');
            var isDiscount = false;
            var discount = null;
            var discountAmt = null;
            
           
            for (var i = 0, length = radios.length; i < length; i++) {
                if (radios[i].checked) {
                    discount = radios[i].value;
                    if(discount != '0'){
                        table += "<tr><td>Discount</td><td>" + discount + "%</td>";
                        isDiscount = true;
                        
                    }else{
                        isDiscount = false;
                    }
                    
                    break;
                    
                }
            }
           
            if(isDiscount){
                discountAmt = parseFloat(total) * parseFloat(discount)/100;
                table += "<td>($" + discountAmt.toFixed(2) + ")</td></tr>";
                total = parseFloat(total) - parseFloat(discountAmt);
                
            }
            
            table += "<tr><td>Total</td><td></td><td><input type='hidden' name='paymentAmount' value='" + total + "' />$" + total.toFixed(2) + "</td></tr>";
            table += "</tbody></table>"; 
            
            document.getElementById('displayArea').innerHTML = table;
        };
        
        
    </script>
</html>

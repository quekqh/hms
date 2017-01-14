<%-- 
    Document   : adminsetting
    Created on : Oct 18, 2016, 1:46:41 AM
    Author     : HajaReethwan
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.String"%>
<%@page import="fyp.hms.dao.UserDAO"%>
<%@page import="fyp.hms.model.User"%>
<%@page import="java.util.TreeMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@include file="adminProtect.jsp"%>
<script src="js/boostrap.min.js"></script>
 <script src="js/boostrap.js"></script>
<html>
    <%        TreeMap<String, User> staffMap = UserDAO.retrieveAll();
        List<User> staffList = new ArrayList<User>(staffMap.values());
    %>
    <head>
        <%@include file="template_head.jsp"%>
    </head>
    <body>
        

        <div id="wrapper">
              <div class="modal fade" id="confirmDelete" role="dialog" aria-labelledby="confirmDeleteLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Delete Parmanently</h4>
      </div>
      <div class="modal-body">
        <p>Are you sure about this ?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <button type="button" class="btn btn-danger" id="confirm">Delete</button>
      </div>
    </div>
  </div>
</div>
            <%@include file="template_navbar.jsp"%>
            <div id="page-wrapper" >
        
                <div class="container-fluid">
                    <div class="col-lg-12">
                        <div class="row">
                            <h1 class="page-header">Staff List</h1>
                        </div>
                    </div>

                <style>form{ display: inline-block; }</style>


                <form action="${contextPath}/staffSearch">
                    <div id="custom-search-input">
                        <div class="input-group col-md-12">
                            <input type="text" name="search" class="form-control input-lg" placeholder="Search username / name" />
                            <span class="input-group-btn">
                                <button class="btn btn-info btn-lg" style="background-color: #000000;border-color: #000000" type="submit">
                                    <i class="fa fa-fw fa-search"></i>
                                </button>
                            </span>
                        </div>
                    </div>
                </form>  
                </div>

                <div class="container-fluid">
                    <div style="float: right"> 
                        <form action="${contextPath}/accounts"><button type="submit" name="staff" class="btn btn-default" style="background-color: #000000;border-color: #000000;color: #ffffff"><b>Full-time</b></button></form>
                        <form action="${contextPath}/accounts"><button type="submit" name="freelance" value ="freelance" class="btn btn-default" style="background-color: #000000;border-color: #000000; color: #ffffff"><b>Freelancer</b></button></form>
                        <form action="${contextPath}/accounts"><button type="submit" name="all" value ="all" class="btn btn-default" style="background-color: #000000;border-color: #000000; color: #ffffff"><b>All</b></button></form> 
                    </div>
                </div>

                <div class="container-fluid">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Username</th>
                                    <th>Name</th>
                                    <th>Role</th>
                                    <th>Phone</th>
                                    <th>Address</th>
                                    <th>E-mail</th>
                                </tr>
                            </thead>
                            <%
                                TreeMap<String, User> map = (TreeMap<String, User>) request.getAttribute("map");
                                if (map != null) {
                                    List<User> list = new ArrayList<User>(map.values());
                                    for (int i = 0; i < list.size(); i++) {
                                        User user = list.get(i);
                                        String u = user.getUsername();
                                        String n = user.getName();
                                        int role = user.getRole();
                                        String r = "";
                                        if (role == 2) {
                                            r = "Staff";
                                        } else {
                                            r = "Freelancer";
                                        }
                                        int number = user.getPhoneNumber();
                                        String address = user.getAddress();
                                        String email = user.getEmail();
                            %>
                            <tbody>      
                                <tr>
                                    <td><%= u%></td>
                                    <td><%= n%></td>
                                    <td><%= r%></td>
                                    <td><%= number%></td> 
                                    <td><%= email%></td>
                                    <td><%= address%></td>
                                    <td>
                                        
                                        
                                        <form action="${contextPath}/deleteAccount">
                                            <input type="hidden" name="username" value="<%=u%>">
                                            <input type="submit" value="Delete"/>
                                        </form>
                                    </td>
                                </tr>    
                                <%}
                                } else {
                                    for (int i = 0; i < staffList.size(); i++) {
                                        User user = staffList.get(i);
                                        String u = user.getUsername();
                                        String n = user.getName();
                                        int role = user.getRole();
                                        String r = "";
                                        if (role == 2) {
                                            r = "Staff";
                                        } else {
                                            r = "Freelancer";
                                        }
                                        int number = user.getPhoneNumber();
                                        String address = user.getAddress();
                                        String email = user.getEmail();
                                %>
                            <tbody>      
                                <tr>
                                    <td><%= u%></td>
                                    <td><%= n%></td>
                                    <td><%= r%></td>
                                    <td><%= number%></td> 
                                    <td><%= email%></td>
                                    <td><%= address%></td>
                                    <td>
                                     <form action="${contextPath}/deleteAccount" accept-charset="UTF-8" style="display:inline">
                                     <input type="hidden" name="username" value="<%=u%>">
                                                   
                                     <button class="btn btn-xs btn-danger" type="button" data-toggle="modal" data-target="#confirmDelete" data-title="Delete Account" data-message="Are you sure you want to delete this staff ?">
                                        <i class="glyphicon glyphicon-trash"></i> Delete
                                    </button>
                                    </form>     
                                            
                                            
                                    </td>
                                </tr>                
                                <%}
                            }%>
                            </tbody>
                        </table>
                       
                    </div>
                </div> 
            </div>
        </div>
                                            
            <div class="modal fade" id="myModal" role="dialog">
                            <div class="modal-dialog">

                                <!-- Modal content-->
                                <div class="modal-content">

                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Warning</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>You sure you wanna delete?</p>
                                    </div>


                                    <div class="modal-footer">         
                                        <form action="${contextPath}/deleteAccount" class="form-horizontal" method="GET" id="form1"> 
                                            <button type="submit" class="btn btn-default" form="form1" name="username" id="username" value= "">Yes</button>
                                            <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                                        </form>
                                    </div>
                                </div>

                            </div>
                        </div>                                <!-- Dialog show event handler -->
<script type="text/javascript">
  $('#confirmDelete').on('show.bs.modal', function (e) {
      $message = $(e.relatedTarget).attr('data-message');
      $(this).find('.modal-body p').text($message);
      $title = $(e.relatedTarget).attr('data-title');
      $(this).find('.modal-title').text($title);

      // Pass form reference to modal for submission on yes/ok
      var form = $(e.relatedTarget).closest('form');
      $(this).find('.modal-footer #confirm').data('form', form);
  });

  <!-- Form confirm (yes/ok) handler, submits form -->
  $('#confirmDelete').find('.modal-footer #confirm').on('click', function(){
      $(this).data('form').submit();
  });
</script>
    </body>
</html>

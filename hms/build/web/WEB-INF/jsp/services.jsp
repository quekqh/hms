<%-- 
    Document   : services
    Created on : Jan 2, 2017, 1:46:23 PM
    Author     : HajaReethwan
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="fyp.hms.dao.ServiceDAO"%>
<%@page import="java.util.TreeMap"%>
<%@page import="fyp.hms.model.Services"%>
<%@page import="fyp.hms.dao.UserDAO"%>
<%@page import="fyp.hms.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script src="js/boostrap.min.js"></script>
 <script src="js/boostrap.js"></script>
<!DOCTYPE html>
<html>        

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
            <div id="page-wrapper">
               <% 
                    List<Services> servicesList = ServiceDAO.retrieveAllServices();
                    List<String> categories = ServiceDAO.retrieveCategory();
                    String error = (String) request.getAttribute("error");
                    String success = (String) request.getAttribute("success");
                    if (error != null) {
                        out.println("<div class='alert alert-danger' role='alert'>");
                        out.println("<strong>Error:</strong>");
                        out.println(error + "<br>");
                        out.println("</div>");
                    }

                    if (success != null) {
                        out.println("<div class='alert alert-success' role='alert'>");
                        out.println("<strong>Success:</strong>");
                        out.println(success + "<br>");
                        out.println("</div>");
                    }
                %>

                
                
                
                
                
                <div class="container-fluid">
                    <div class="col-lg-12">
                        <div class="row">
                            <h1 class="page-header">Service List</h1>
                        </div>
                    </div>

                    <style>form{ display: inline-block; }</style>
                    <form action="${contextPath}/serviceSearch">
                        <div id="custom-search-input">
                            <div class="input-group col-md-12">
                                <input type="text" name="search" class="form-control input-lg"  placeholder="Search Service Name" />
                                <span class="input-group-btn">
                                    <button class="btn btn-info btn-lg" style= "background-color: #000000;border-color: #000000;" type="submit">
                                        <i class="fa fa-fw fa-search"></i>
                                    </button>
                                </span>
                            </div>
                        </div>
                    </form>
                </div>  

                <div class="container-fluid">       
                    <div style="float: right">
                        <button class="btn btn-default" data-toggle="modal" data-target="#myModalHorizontal">+</button> 
                        <form action="${contextPath}/serviceTypes"><button type="submit" name="cut" class="btn btn-default" style="background-color: #000000;border-color: #000000; color: #ffffff"><b>Cut</b></button></form>
                        <form action="${contextPath}/serviceTypes"><button type="submit" name="style" class="btn btn-default" style="background-color: #000000;border-color: #000000; color: #ffffff"><b>Style</b></button></form>
                        <form action="${contextPath}/serviceTypes"><button type="submit" name="colour" class="btn btn-default" style="background-color: #000000;border-color: #000000; color: #ffffff"><b>Colour</b></button></form>
                        <form action="${contextPath}/serviceTypes"><button type="submit" name="perm" class="btn btn-default" style="background-color: #000000;border-color: #000000; color: #ffffff"><b>Perm</b></button></form>
                        <form action="${contextPath}/serviceTypes"><button type="submit" name="treatment" class="btn btn-default" style="background-color: #000000;border-color: #000000; color: #ffffff"><b>Treatment</b></button></form>
                        <form action="${contextPath}/serviceTypes"><button type="submit" name="all" class="btn btn-default" style="background-color: #000000;border-color: #000000; color: #ffffff"><b>All</b></button></form>
                    </div>   
                </div> 

                <div class="container-fluid">
                    <!-- /.row -->

                    <div class="row">
                        <div class="col-md-12">
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>Service ID</th>
                                            <th>Service Name</th>
                                            <th>Short($)</th>
                                            <th>Medium($)</th>
                                            <th>Long($)</th>
                                            <th>Category</th>
                                             <th>Edit/Delete</th>
                                        </tr>
                                    </thead>
                                    <%
                                        TreeMap<String, Services> map = (TreeMap<String, Services>) request.getAttribute("map");
                                        if (map != null) {
                                            List<Services> serviceList = new ArrayList<>(map.values());
                                            System.out.println(serviceList);
                                            for (int i = 0; i < serviceList.size(); i++) {
                                                Services service = serviceList.get(i);
                                                int id = service.getServiceID();
                                                String serviceName = service.getServiceDescription();
                                                double shortPrice = service.getShortPrice();
                                                double mediumPrice = service.getMediumPrice();
                                                double longPrice = service.getLongPrice();
                                                String category = service.getCategory();
                                    %>     
                                    <tbody>
                                        <tr>
                                            <td><%= id %></td>
                                            <td><%= serviceName %></td>
                                            <td><%= shortPrice %></td>
                                            <td><%= mediumPrice %></td> 
                                            <td><%= longPrice %></td>
                                            <td><%= category %></td>
                                            <td><form action="${contextPath}/serviceEdit">
                                                   
                                                    <input type="hidden" name="edit" value="<%= id %>">
                                                    <input type="submit" value="Update"/>
                                                </form>
                                            </td>
                       
                                                
                                                
                                                
                                             
                                            </td>
                                    </tr>
                                    <%}
                                        } else {
                                            for (int i = 0; i < servicesList.size(); i++) {
                                                Services service = servicesList.get(i);
                                                int id = service.getServiceID();
                                                String serviceName = service.getServiceDescription();
                                                double shortPrice = service.getShortPrice();
                                                double mediumPrice = service.getMediumPrice();
                                                double longPrice = service.getLongPrice();
                                                String category = service.getCategory();
                                        %>     
                                    <tbody>    
                                        <tr>
                                            <td><%= id %></td>
                                            <td><%= serviceName %></td>
                                            <td><%= shortPrice %></td>
                                            <td><%= mediumPrice %></td> 
                                            <td><%= longPrice %></td>
                                            <td><%= category %></td>
                                            <td><form action="${contextPath}/serviceEdit">
                                                    <input type="hidden" name="edit" value="<%= id %>">
    
                                                    <input class="btn btn-xs btn-warning" type="submit" value="Update"/>
                                                </form>
                                             
                                                
                                                    
                                                   <form action="${contextPath}/serviceDelete" accept-charset="UTF-8" style="display:inline">
                                    <input type="hidden" name="delete" value="<%= id %>">
                                                   
                                                       <button class="btn btn-xs btn-danger" type="button" data-toggle="modal" data-target="#confirmDelete" data-title="Delete Service" data-message="Are you sure you want to delete this service ?">
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

                    <div class="modal fade" id="myModalHorizontal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">
                                        <span aria-hidden="true">&times;</span>
                                        <span class="sr-only">Close</span>
                                    </button>
                                    <h4 class="modal-title" id="myModalLabel">
                                        Create New Service
                                    </h4>
                                </div>

                                <!-- Modal Body -->
                                <div class="modal-body">

                                    <form action="${contextPath}/createService" data-toggle="validator" role="form" style="width:100%">
                                        <div class="form-group">
                                            <label for="inputName" class="control-label">Service Description</label>
                                            <input type="text" class="form-control" id="desc" name="desc" placeholder="Enter Service Description" required>
                                        </div>

                                        <div class="form-group">
                                            <label for="inputShortPrice" class="control-label">Short Price</label>
                                            <input type="price" class="form-control" id="short" name="short" placeholder="Enter price for short hair" required>
                                        </div>

                                        <div class="form-group">
                                            <label for="inputShortPrice" class="control-label">Medium Price</label>
                                            <input type="price" class="form-control" id="medium" name="medium" placeholder="Enter price for short hair" required>
                                        </div>

                                        <div class="form-group">
                                            <label for="inputLongPrice" class="control-label">Long Price</label>
                                            <input type="price" class="form-control" id="long" name ="long" placeholder="Enter price for long hair" required>
                                        </div>

                                        <div class="form-group">
                                            <label for="inputCategory" class="control-label">Category</label>
                                            <select class="form-control" name="category">
                                                <%
                                                    for (int i = 0; i < categories.size(); i++) {
                                                        String s = categories.get(i);
                                                %>
                                                <option><%= s %></option>
                                                <%}%>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <button type="submit" class="btn btn-primary btn-lg btn-block" style="background-color: #000000;border-color: #000000; color: #ffffff">Submit</button>
                                        </div>
                                    </form>

                                </div>   
                                <!-- Modal Footer -->
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default"
                                            data-dismiss="modal">
                                        Close
                                    </button>               
                                </div>
                            </div>
                        </div>
                    </div>        
                    
                      
                                            <!-- Dialog show event handler -->
<!-- Dialog show event handler -->
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
                </div>
            </div>

        </div>
        <!-- /.row -->

    </div>
    
    
</body>   
</html>


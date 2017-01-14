<%-- 
    Document   : admin
    Created on : 29 Sep, 2016, 8:17:29 PM
    Author     : Asus
--%>

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

            <%@include file="template_navbar.jsp" %>
            <div id="page-wrapper">

                <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">This month's performance</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-comments fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">26</div>
                                    <div>Appointments!</div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-tasks fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">12</div>
                                    <div>New Customers!</div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                               
                                <div class="col-xs-12 text-right">
                                    <div class="huge"> $ 124,000</div>
                                    <div>Sales!</div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-support fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">13</div>
                                    <div>Late comer fine!</div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
         <!-- /.row -->
          <!-- /.row -->
            <div class="row">
               <div class="col-lg-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bell fa-fw"></i> Noel Ng's sales
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        progress to 3k:
                        <div class="progress">
                        <div class="progress-bar progress-bar-warning progress-bar-striped active" role="progressbar"
                        aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
                          100%
                        </div>
                      </div>
                        progress to 5k:
                        <div class="progress">
                        <div class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar"
                        aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
                          100%
                        </div>
                      </div>
                       progress to 7k:
                        <div class="progress">
                        <div class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar"
                        aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%">
                          50%
                        </div>
                      </div>
                       
                       progress to 10k:
                        <div class="progress">
                        <div class="progress-bar progress-bar-striped active" role="progressbar"
                        aria-valuenow="30" aria-valuemin="0" aria-valuemax="100" style="width:30%">
                          30%
                        </div>
                      </div>
                       
                       Current sales:
                       $5000
                       
                        </div>
                        <!-- /.panel-body -->
                    </div>
         
         </div>
                 <div class="col-lg-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bell fa-fw"></i> Noel Ng's sales
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        progress to 3k:
                        <div class="progress">
                        <div class="progress-bar progress-bar-warning progress-bar-striped active" role="progressbar"
                        aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
                          100%
                        </div>
                      </div>
                        progress to 5k:
                        <div class="progress">
                        <div class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar"
                        aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
                          100%
                        </div>
                      </div>
                       progress to 7k:
                        <div class="progress">
                        <div class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar"
                        aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%">
                          50%
                        </div>
                      </div>
                       
                       progress to 10k:
                        <div class="progress">
                        <div class="progress-bar progress-bar-striped active" role="progressbar"
                        aria-valuenow="30" aria-valuemin="0" aria-valuemax="100" style="width:30%">
                          30%
                        </div>
                      </div>
                    
                        </div>
                        <!-- /.panel-body -->
                    </div>
         
         </div>
                 <div class="col-lg-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bell fa-fw"></i> Noel Ng's sales
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        progress to 3k:
                        <div class="progress">
                        <div class="progress-bar progress-bar-warning progress-bar-striped active" role="progressbar"
                        aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
                          100%
                        </div>
                      </div>
                        progress to 5k:
                        <div class="progress">
                        <div class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar"
                        aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
                          100%
                        </div>
                      </div>
                       progress to 7k:
                        <div class="progress">
                        <div class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar"
                        aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%">
                          50%
                        </div>
                      </div>
                       
                       progress to 10k:
                        <div class="progress">
                        <div class="progress-bar progress-bar-striped active" role="progressbar"
                        aria-valuenow="30" aria-valuemin="0" aria-valuemax="100" style="width:30%">
                          30%
                        </div>
                      </div>
                    
                        </div>
                        <!-- /.panel-body -->
                    </div>
         
         </div>
         
         </div>
         </div> <!-- /row -->
         
         
         
         
                    </div>
                </div>
    </body>

    

</html>


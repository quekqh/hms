<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${contextPath}/">HARTS</a>
            </div>
            <!-- Top Menu Items -->
            <%
                String name = (String) session.getAttribute("username");
                String name2 = "";
                if (name != null) {
                    name2 = name;
                }

            %>
            <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                    <a class="dropdown-toggle" href="#" data-toggle="dropdown"><i class="fa fa-fw fa-user"></i> <%=name2.toLowerCase()%> <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="${contextPath}/changePassword"><i class="fa fa-fw fa-user"></i> Change Password</a>
                        </li>
                        <%
                            String verify = (String) session.getAttribute("role");

                            if (verify != null && verify.equals("admin")) {
                        %>
                        <li>
                            <a href="${contextPath}/changeSecurityCode"><i class="fa fa-fw fa-envelope"></i> Change Security Code</a>
                        </li>
                        <%
                            }
                        %>
                        <li>
                            <a href="${contextPath}/editProfile"><i class="fa fa-fw fa-gear"></i> Edit Profile</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="${contextPath}/logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="${contextPath}/admin"><i class="fa fa-fw fa-dashboard"></i> HOME</a>
                    </li>


                    <li>
                        <a href="${contextPath}/customer"><i class="fa fa-fw fa-table"></i> CUSTOMERS</a>
                    </li>

                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo" class="" aria-expanded="false">
                            <i class="fa fa-fw fa-heart"></i> APPOINTMENT<i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="demo" class="collapse" aria-expanded="false">
                            <li>
                                <a href="${contextPath}/apptment"><i class="fa fa-fw fa-calendar"></i> ALL APPOINTMENTS</a>
                            </li>
                            <li>
                                <a href="${contextPath}/createAppt"><i class="fa fa-fw fa-plus-square"></i> CREATE</a>
                            </li>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>


                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo2" class="" aria-expanded="false">
                            <i class="fa fa-wrench fa-fw"></i> LEAVE<i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="demo2" class="collapse" aria-expanded="false">

                            <li>
                                <a href="${contextPath}/leaveCalendar"><i class="fa fa-fw fa-calendar"></i> ALL LEAVE</a>
                            </li>
                            <li>
                                <a href="${contextPath}/applyLeave"><i class="fa fa-fw fa-edit"></i> APPLY LEAVE</a>
                            </li>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                    <li>
                        <a href="${contextPath}/payslip"><i class="fa fa-fw fa-dollar"></i> PAYROLL</a>
                    </li>
                    <%
                        if (verify != null && verify.equals("admin")) {

                    %>
                    <li>
                        <a href="${contextPath}/paymentList"><i class="fa fa-fw fa-dollar"></i> PAYMENT</a>
                    </li>
                    <li>
                        <a href="${contextPath}/services"><i class="fa fa-fw fa-wrench"></i> SERVICES</a>
                    </li>
                    <li>
                        <a href="${contextPath}/adminSetting"><i class="fa fa-fw fa-group"></i> STAFF LIST</a>
                    </li>              
                    <%}%>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>
    </body>

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.dropdown-toggle').dropdown();
        });
    </script>
</html>
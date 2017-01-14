<%@page import="fyp.hms.model.Leave"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.sql.Time"%>
<%@page import="java.util.List"%>
<%@page import="fyp.hms.model.Appointment"%>
<%@page import="java.sql.Date"%>
<%@page import="fyp.hms.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@include file="staffProtect.jsp"%>

<html>
    <head>
        <%@ include file="template_head.jsp" %>
        <style>
           form {

max-width: 955px;
}
 

        </style>
        
    </head>
    <body>
        <div id="wrapper">

            <!-- Navigation -->
            <%TreeMap<String, User> users = (TreeMap<String, User>) session.getAttribute("allStaff");%>
            <%@include file="template_navbar.jsp" %>
            <div id="page-wrapper">
                
                <%
                    String role = (String)session.getAttribute("role");
                    String filter = (String)request.getAttribute("filter");
                    if(role.equals("admin")){
                %>
                <div class="container-fluid">
         
                        <form  class="form-inline text-right" action="${contextPath}/leaveCalendarFilter">
                       
        <div class="form-group">
                        <select class="form-control" id="hairStylist" name="hairStylist">
                            <option value="all">Show All Leaves</option>
                            <%
                                if(users != null){ 
                                    if(filter == null){

                                        Set set = users.entrySet();
                                        Iterator iterator = set.iterator();
                                        while(iterator.hasNext()) {
                                            Map.Entry mentry = (Map.Entry)iterator.next();
                                            User u = (User)mentry.getValue();
                                            String uName = u.getUsername();
                                            String n = u.getName();

                                            out.println("<option value='" + uName + "'>" + n + "</option>");


                                        } 

                                    }else{
                                        Set set = users.entrySet();
                                        Iterator iterator = set.iterator();
                                        while(iterator.hasNext()) {
                                            Map.Entry mentry = (Map.Entry)iterator.next();
                                            User u = (User)mentry.getValue();
                                            String uName = u.getUsername();
                                            String n = u.getName();
                                            if(filter.equals(uName)){
                                                out.println("<option value='" + uName + "' selected>" + n + "</option>");
                                            }else{
                                                out.println("<option value='" + uName + "'>" + n + "</option>");
                                            }

                                        } 
                                    } 
                                }
                            %>
                            
                        </select>
                            </div>
                        <div class="form-group">
                            <button type="submit"  class="btn btn-info pull-right">Go</button>
                        </div>
                    </form>
                            
               
                    <%}%>
                    
                    </br>
                    <div id='calendar'></div>
                    </br>
                                        </br>

                    <!-- /.row -->
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->

        </div>
    </body>


    <script src="js/jquery.min.js"></script>
    <script src="js/moment.min.js"></script>
    <script src="js/fullcalendar.min.js"></script>

    <script>

        $(document).ready(function () {
            $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay,listWeek'
                },
                defaultDate: new Date(),
                navLinks: true, // can click day/week names to navigate views
                editable: false,
                eventLimit: true, // allow "more" link when too many events
                events: [
        <%                                    //TreeMap<String, User> users = (TreeMap<String, User>) session.getAttribute("allStaff");
            List<Leave> leaveList = (List<Leave>) request.getAttribute("leaveList");
            List<User> userList = (List<User>) session.getAttribute("usersWithoutAdmin");
            int apptId = 0;
            if (leaveList != null) {
                for (Leave a : leaveList) {
                    int leaveId = a.getLeaveId();
                    String uName = a.getUsername();
                    String applicantName = a.getName();
                    Date leaveDate = a.getLeaveDate();
                    String remarks = a.getRemarks();
                    String status = a.getLeaveStatus();
                    
                    if(status.equals("Pending")){
        %>
                        {
                        id: '<%=uName%>',
                        title: '<%=applicantName%>',
                        start: '<%=leaveDate%>',
                        color: '#FF4500',
                     <%}else{%>   
                         {
                        id: '<%=uName%>',
                        title: '<%=applicantName%>',
                        start: '<%=leaveDate%>',
                    <%}%>
                        
                        url:'${contextPath}/updateLeave?leaveId=' + <%=leaveId%>
                        
                       
                    },
        <%
                }
            }
        %>
                        ]
                    });

                });
                
      
          

    </script>

    
</html>
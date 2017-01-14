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
                                     <form  class="form-inline text-right" action="${contextPath}/apptCalendarFilter">
                       
        <div class="form-group">
                        <select class="form-control" id="hairStylist" name="hairStylist">
                            <option value="all">Show All Hair Stylist</option>
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
            List<Appointment> apptList = (List<Appointment>) request.getAttribute("apptList");
            List<User> userList = (List<User>) session.getAttribute("usersWithoutAdmin");
            int apptId = 0;
            if (apptList != null) {
                for (Appointment appt : apptList) {

                    apptId = appt.getAppointmentId();
                    int custId = appt.getCustomerId();
                    String custName = appt.getCustomerName();
                    String hairStylist = appt.getHairStylistUsername();
                    Date apptDate = appt.getAppointmentDate();
                    Time apptTime = appt.getAppointmentTime();
                    String remarks = appt.getRemarks();
                    String status = appt.getStatus();
                    Time endTime = appt.getApptEndTime();
                    if(status.equals("Done")){
        %>
                        
                        {
                        id: <%=custId%>,
                        title: '<%=custName%> (<%=custId%>)',
                        start: '<%=apptDate%>T<%=apptTime%>',
                        end: '<%=apptDate%>T<%=endTime%>',
                        color: '#808080',
                     <%}else{%>   
                         {
                        id: <%=custId%>,
                        title: '<%=custName%> (<%=custId%>)',
                        start: '<%=apptDate%>T<%=apptTime%>',
                        end: '<%=apptDate%>T<%=endTime%>',
                        
                        <% 
                            
                            for(int i = 0; i < userList.size(); i++){
                                User u = userList.get(i);
                                String uName = u.getUsername();
                                if(hairStylist.equals(uName)){
                                    switch(i){
                                        case 0:
                            %>
                                            color: '#337FFF',                   
                            <%
                                            break;
                                        case 1:    
                            %>
                                            color: '#FFC300', 

                            <%
                                            break;
                                        case 2:    
                            %>
                                            color: '#FF33B8', 

                            <%
                                            break;
                                        case 3:    
                            %>
                                            color: '#FFA833',
                                                    
                            <%
                                            break;
                                        case 4:    
                            %>
                                            color: '#A569BD',
                            
                            <%
                                            break;
                                        case 5:    
                            %>
                                            color: '#FFC300',
                            <%
                                            break;
                                        case 6:    
                            %>
                                            color: '#b51039',
            
                            <%    
                                            break;
                                        }
                                    
                                    }
                                }   
                            }
                        %>          
                            url:'${contextPath}/updateLink?apptId=' + <%=apptId%>
                        
                       
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
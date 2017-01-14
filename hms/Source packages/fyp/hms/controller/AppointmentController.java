/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.controller;

import fyp.hms.model.Appointment;
import fyp.hms.model.Customer;
import fyp.hms.model.Services;
import fyp.hms.service.AppointmentService;
import fyp.hms.service.PaymentService;
import fyp.hms.service.ServiceService;
import fyp.hms.service.UserService;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Asus
 */
@Controller
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    UserService userService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    ServiceService serviceService;

    @RequestMapping(value = "/apptment", method = RequestMethod.GET)
    public String apptment(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {

        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username != null && role != null) {
            if (role.equals("staff")) {
                List<Appointment> apptList = appointmentService.retrieveAppointment(username);
                model.addAttribute("apptList", apptList);
                return "appointment";
            } else {
                List<Appointment> apptList = appointmentService.retrieveAllAppointment();
                model.addAttribute("apptList", apptList);

                return "appointment";
            }

        }
        return "appointment";
    }

    @RequestMapping(value = "/createAppt", method = RequestMethod.GET)
    public String createAppt(@RequestParam Map<String, String> parameters, HttpSession session) {
        return "addAppointment";
    }

    @RequestMapping(value = "/createApptment", params = "retrieveInfo", method = RequestMethod.GET)
    public String retrieveInfo(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        String customerId = (String) parameters.get("customerId");
        //customerId supposedly contains both customer name and customer Id now
        String time = parameters.get("appointmentTime");
        String apptDate = parameters.get("appointmentDate");
        String hairStylistName = parameters.get("hairStylist");
        String remarks = parameters.get("remarks");
        String customerName = parameters.get("customerName");
        String dob = parameters.get("dob");
        String email = parameters.get("email");
        String hairStylist = parameters.get("hairStylist");
        String duration = parameters.get("duration");

        int phoneNo = 0;

        if (customerId == null || customerId.length() == 0) {
            model.addAttribute("error1", "Search field cannot be empty.");
            model.addAttribute("customerId", customerId);
            model.addAttribute("time", time);
            model.addAttribute("apptDate", apptDate);
            model.addAttribute("hairStylistName", hairStylistName);
            model.addAttribute("remarks", remarks);
            model.addAttribute("customerName", customerName);
            model.addAttribute("dob", dob);
            model.addAttribute("email", email);
            model.addAttribute("hairStylist", hairStylist);
            model.addAttribute("duration", duration);
            return "addAppointment";
        }

        if (!customerId.isEmpty()) {
            Customer cust = appointmentService.retrieveCustbyName(customerId);
            //System.out.println("appointmentcontroller searchForCustomer" + searchForCustomer);
            if (cust == null) {

                try {
                    phoneNo = Integer.parseInt(customerId);
                    if (customerId.length() < 8) {
                        model.addAttribute("error1", "Customer Id must contain at least 8 digits.");
                        model.addAttribute("customerId", customerId);
                        model.addAttribute("time", time);
                        model.addAttribute("apptDate", apptDate);
                        model.addAttribute("hairStylistName", hairStylistName);
                        model.addAttribute("remarks", remarks);
                        model.addAttribute("customerName", customerName);
                        model.addAttribute("dob", dob);
                        model.addAttribute("email", email);
                        model.addAttribute("hairStylist", hairStylist);
                        model.addAttribute("duration", duration);
                        return "addAppointment";
                    }
                } catch (NumberFormatException e) {
                    model.addAttribute("error1", "Customer doesn't exist!");
                    model.addAttribute("customerId", customerId);
                    model.addAttribute("time", time);
                    model.addAttribute("apptDate", apptDate);
                    model.addAttribute("hairStylistName", hairStylistName);
                    model.addAttribute("remarks", remarks);
                    model.addAttribute("customerName", customerName);
                    model.addAttribute("dob", dob);
                    model.addAttribute("email", email);
                    model.addAttribute("hairStylist", hairStylist);
                    model.addAttribute("duration", duration);
                    return "addAppointment";

                }

                cust = appointmentService.retrieveCust(phoneNo);
                if (cust == null) {
                    model.addAttribute("error1", "Customer Id doesn't exist.");
                    model.addAttribute("customerId", customerId);
                    model.addAttribute("time", time);
                    model.addAttribute("apptDate", apptDate);
                    model.addAttribute("hairStylistName", hairStylistName);
                    model.addAttribute("remarks", remarks);
                    model.addAttribute("customerName", customerName);
                    model.addAttribute("dob", dob);
                    model.addAttribute("email", email);
                    model.addAttribute("hairStylist", hairStylist);
                    model.addAttribute("duration", duration);
                    return "addAppointment";
                }
            }
                model.addAttribute("customer", cust);
                model.addAttribute("customerId", customerId);
                model.addAttribute("time", time);
                model.addAttribute("apptDate", apptDate);
                model.addAttribute("hairStylistName", hairStylistName);
                model.addAttribute("remarks", remarks);
                model.addAttribute("customerName", customerName);
                model.addAttribute("dob", dob);
                model.addAttribute("email", email);
                model.addAttribute("hairStylist", hairStylist);
                model.addAttribute("duration", duration);
                return "addAppointment";
        }

        model.addAttribute("error1", "Customer Id cannot be empty.");
        model.addAttribute("customerId", customerId);
        model.addAttribute("time", time);
        model.addAttribute("apptDate", apptDate);
        model.addAttribute("hairStylistName", hairStylistName);
        model.addAttribute("remarks", remarks);
        model.addAttribute("customerName", customerName);
        model.addAttribute("dob", dob);
        model.addAttribute("email", email);
        model.addAttribute("hairStylist", hairStylist);
        model.addAttribute("duration", duration);
        return "addAppointment";
    }

    @RequestMapping(value = "/createApptment", params = "createApp", method = RequestMethod.GET)
    public String createApp(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) throws ParseException {
        String customerId = (String) parameters.get("customerId");
        String time = parameters.get("appointmentTime");
        String apptDate = parameters.get("appointmentDate");
        String hairStylistName = parameters.get("hairStylist");
        String remarks = parameters.get("remarks");
        String customerName = parameters.get("customerName");
        String dob = parameters.get("dob");
        String email = parameters.get("email");
        String hairStylist = parameters.get("hairStylist");
        String duration = parameters.get("duration");

        //check empty fields (ps: remarks is allowed to be empty)
        if (parameters.get("customerId").length() == 0 || parameters.get("hairStylist").length() == 0
                || parameters.get("appointmentDate").length() == 0 || parameters.get("customerName").length() == 0
                || parameters.get("appointmentDate") == null
                || parameters.get("appointmentTime").length() == 0 || parameters.get("appointmentTime") == null
                || parameters.get("appointmentTime").equals("") || parameters.get("duration").length() == 0) {

            model.addAttribute("error", "Field(s) cannot be empty (except for remarks).");
            model.addAttribute("customerId", customerId);
            model.addAttribute("time", time);
            model.addAttribute("apptDate", apptDate);
            model.addAttribute("hairStylistName", hairStylistName);
            model.addAttribute("remarks", remarks);
            model.addAttribute("customerName", customerName);
            model.addAttribute("dob", dob);
            model.addAttribute("email", email);
            model.addAttribute("hairStylist", hairStylist);
            model.addAttribute("duration", duration);
            return "addAppointment";
        }

        //check the format of customer id
        int phoneNo = 0;
        try {
            phoneNo = Integer.parseInt(customerId);
            if (customerId.length() < 8) {
                model.addAttribute("error", "Phone number must contain at least 8 digits.");
                model.addAttribute("customerId", customerId);
                model.addAttribute("time", time);
                model.addAttribute("apptDate", apptDate);
                model.addAttribute("hairStylistName", hairStylistName);
                model.addAttribute("remarks", remarks);
                model.addAttribute("customerName", customerName);
                model.addAttribute("dob", dob);
                model.addAttribute("email", email);
                model.addAttribute("hairStylist", hairStylist);
                model.addAttribute("duration", duration);
                return "addAppointment";
            }
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid Customer Id");
            model.addAttribute("customerId", customerId);
            model.addAttribute("time", time);
            model.addAttribute("apptDate", apptDate);
            model.addAttribute("hairStylistName", hairStylistName);
            model.addAttribute("remarks", remarks);
            model.addAttribute("customerName", customerName);
            model.addAttribute("dob", dob);
            model.addAttribute("email", email);
            model.addAttribute("hairStylist", hairStylist);
            model.addAttribute("duration", duration);
            return "addAppointment";

        }

        if (apptDate.length() != 10) {
            model.addAttribute("error", "Invalid appointment date.");
            model.addAttribute("customerId", customerId);
            model.addAttribute("time", time);
            model.addAttribute("apptDate", apptDate);
            model.addAttribute("hairStylistName", hairStylistName);
            model.addAttribute("remarks", remarks);
            model.addAttribute("customerName", customerName);
            model.addAttribute("dob", dob);
            model.addAttribute("email", email);
            model.addAttribute("hairStylist", hairStylist);
            model.addAttribute("duration", duration);
            return "addAppointment";
        }
        //retrieve customer based on customer id
        Customer cust = appointmentService.retrieveCust(phoneNo);
        if (cust == null) {
            model.addAttribute("error", "Customer Id doesn't exist.");
            model.addAttribute("customerId", customerId);
            model.addAttribute("time", time);
            model.addAttribute("apptDate", apptDate);
            model.addAttribute("hairStylistName", hairStylistName);
            model.addAttribute("remarks", remarks);
            model.addAttribute("customerName", customerName);
            model.addAttribute("dob", dob);
            model.addAttribute("email", email);
            model.addAttribute("hairStylist", hairStylist);
            model.addAttribute("duration", duration);
            return "addAppointment";
        }

        //convert util.date format to sql.date format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date appointmentDate;
        java.sql.Date sqlDate = null;
        String strSqlDate = "";
        try {
            appointmentDate = format.parse(apptDate);
            sqlDate = new java.sql.Date(appointmentDate.getTime());
            strSqlDate = sqlDate + "";

        } catch (ParseException ex) {
            model.addAttribute("error", "Invalid appointment date.");
            model.addAttribute("customerId", customerId);
            model.addAttribute("time", time);
            model.addAttribute("apptDate", apptDate);
            model.addAttribute("hairStylistName", hairStylistName);
            model.addAttribute("remarks", remarks);
            model.addAttribute("customerName", customerName);
            model.addAttribute("dob", dob);
            model.addAttribute("email", email);
            model.addAttribute("hairStylist", hairStylist);
            model.addAttribute("duration", duration);
            return "addAppointment";
        }

        //check if the appointment date is before the current date
        Date currentDate = new Date();
        java.sql.Date sqlCurrentDate = new java.sql.Date(currentDate.getTime());
        String strSqlCurrentDate = sqlCurrentDate + "";
        if (!strSqlDate.equals(strSqlCurrentDate) && sqlDate.before(sqlCurrentDate)) {
            model.addAttribute("error", "Appointment Date needs to be  after current date.");
            model.addAttribute("customerId", customerId);
            model.addAttribute("time", time);
            model.addAttribute("apptDate", apptDate);
            model.addAttribute("hairStylistName", hairStylistName);
            model.addAttribute("remarks", remarks);
            model.addAttribute("customerName", customerName);
            model.addAttribute("dob", dob);
            model.addAttribute("email", email);
            model.addAttribute("hairStylist", hairStylist);
            model.addAttribute("duration", duration);
            return "addAppointment";
        }

        //appointment time
        SimpleDateFormat timeDateFormat = new SimpleDateFormat("hh:mm");
        long ms = timeDateFormat.parse(time).getTime();
        int intDuration = Integer.parseInt(duration);
        long endMs = ms + (intDuration * 60000);
        Time t = new Time(ms);
        Time endTime = new Time(endMs);

        //get current time
        Calendar cal = Calendar.getInstance();

        LocalDateTime localDateTime = LocalDateTime.now();
        String localTime = localDateTime.toLocalTime() + "";
        String strLocalTime = localTime.substring(0, 8);
        //String strCurrentTime = timeDateFormat.format(cal.getTime());
        long currentMS = timeDateFormat.parse(strLocalTime).getTime();
        Time currentTime = new Time(currentMS);

        //if appointment date is same as the current date
        if (strSqlDate.equals(strSqlCurrentDate)) {
            ////check if the appointment time is before the current time
            if (t.before(currentTime)) {
                model.addAttribute("error", "Appointment Time needs to be  after current time.");
                model.addAttribute("customerId", customerId);
                model.addAttribute("time", time);
                model.addAttribute("apptDate", apptDate);
                model.addAttribute("hairStylistName", hairStylistName);
                model.addAttribute("remarks", remarks);
                model.addAttribute("customerName", customerName);
                model.addAttribute("dob", dob);
                model.addAttribute("email", email);
                model.addAttribute("hairStylist", hairStylist);
                model.addAttribute("duration", duration);
                return "addAppointment";
            }

        }

        List<Appointment> apptList = appointmentService.retrieveAppointment(hairStylistName);
        /*
        if(apptList != null){
            for(Appointment appt: apptList){
                String strDate = appt.getAppointmentDate() + "";
                if(strSqlDate.equals(strDate)){
                    Time apptTime = appt.getAppointmentTime();
                    Time apptEndTime = appt.getApptEndTime();
                    if(t.after(apptTime) && t.before(apptEndTime)){
                        model.addAttribute("error","The hairStylist has an appointment at this timing. Plese select another time.");
                        model.addAttribute("customerId",customerId);
                        model.addAttribute("time",time);
                        model.addAttribute("apptDate",apptDate);
                        model.addAttribute("hairStylistName",hairStylistName);
                        model.addAttribute("remarks",remarks);
                        model.addAttribute("customerName",customerName);
                        model.addAttribute("dob",dob);
                        model.addAttribute("email",email);
                        model.addAttribute("hairStylist",hairStylist);
                        model.addAttribute("duration",duration);
                        return "addAppointment";
                    }
                    
                    String strAfterTime = t + "";
                    SimpleDateFormat dfAfter = new SimpleDateFormat("HH:mm");
                    Date afterDate = dfAfter.parse(strAfterTime); 
                    Calendar afterCal = Calendar.getInstance();
                    afterCal.setTime(afterDate);
                    afterCal.add(Calendar.MINUTE, 60);
                    String newAfterTime = dfAfter.format(afterCal.getTime());
                    long afterMS = dfAfter.parse(newAfterTime).getTime();
                    Time afterTime = new Time(afterMS);
                    
                    String strBeforeTime = t + "";
                    SimpleDateFormat dfBefore = new SimpleDateFormat("HH:mm");
                    Date beforeDate = dfBefore.parse(strBeforeTime); 
                    Calendar beforeCal = Calendar.getInstance();
                    beforeCal.setTime(beforeDate);
                    beforeCal.add(Calendar.MINUTE, -60);
                    String newBeforeTime = dfBefore.format(beforeCal.getTime());
                    long beforeMS = dfBefore.parse(newBeforeTime).getTime();
                    Time beforeTime = new Time(beforeMS);
                    //Date beforeTime1 = beforeCal.getTime();

                    if(apptTime.after(beforeTime) && apptTime.before(afterTime)){
                        model.addAttribute("error","The hairStylist has an another appointment within 1 hour of this timing. Plese select another time.");
                        model.addAttribute("customerId",customerId);
                        model.addAttribute("time",time);
                        model.addAttribute("apptDate",apptDate);
                        model.addAttribute("hairStylistName",hairStylistName);
                        model.addAttribute("remarks",remarks);
                        model.addAttribute("customerName",customerName);
                        model.addAttribute("dob",dob);
                        model.addAttribute("email",email);
                        model.addAttribute("hairStylist",hairStylist);
                        model.addAttribute("duration",duration);
                        return "addAppointment";
                    }
                }
            }
        }*/

        boolean isAdded = appointmentService.addAppointment(phoneNo, hairStylistName, customerName, sqlDate, t, endTime, remarks, "Ongoing");
        if (!isAdded) {
            model.addAttribute("error", "Appointment cannot be added.");
        } else {
            model.addAttribute("success", "Appointment has been added successfully.");
        }

        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username != null && role != null) {
            if (role.equals("staff")) {
                apptList = appointmentService.retrieveAppointment(username);
                model.addAttribute("apptList", apptList);

            } else {
                apptList = appointmentService.retrieveAllAppointment();
                model.addAttribute("apptList", apptList);

            }

        }
        return "appointment";
    }

    @RequestMapping(value = "/table", params = "changeStatus", method = RequestMethod.GET)
    public String changeStatus(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) throws ParseException {
        String statusFromForm = (String) parameters.get("changeStatus");
        if (statusFromForm != null && statusFromForm.length() > 0) {
            int apptId = 0;

            try {
                apptId = Integer.parseInt(statusFromForm);
                Appointment appt = appointmentService.retrieveApptStatus(apptId);
                if (appt != null) {
                    String status = appt.getStatus();
                    if (status.equals("Ongoing")) {
                        appointmentService.changeApptStatus("Done", apptId);
                    } else {
                        appointmentService.changeApptStatus("Ongoing", apptId);
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username != null && role != null) {
            if (role.equals("staff")) {
                List<Appointment> apptList = appointmentService.retrieveAppointment(username);
                model.addAttribute("apptList", apptList);
                return "appointment";
            } else {
                List<Appointment> apptList = appointmentService.retrieveAllAppointment();
                model.addAttribute("apptList", apptList);
                return "appointment";
            }

        }
        return "appointment";
    }

    @RequestMapping(value = "/updateLink", method = RequestMethod.GET)
    public String updateLink(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) throws ParseException {
        String update = (String) parameters.get("apptId");
        if (update != null && update.length() > 0) {
            int apptId = 0;

            try {
                apptId = Integer.parseInt(update);
                Appointment appt = appointmentService.retrieveApptStatus(apptId);
                if (appt != null) {
                    Customer cust = appointmentService.retrieveCust(appt.getCustomerId());
                    model.addAttribute("appt", appt);
                    //session.setAttribute("apptId",apptId);
                    if (cust != null) {
                        model.addAttribute("customer", cust);
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return "updateAppointment";
    }

    @RequestMapping(value = "/updateApptment", params = "delete", method = RequestMethod.GET)
    public String delete(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) throws ParseException {
        String statusFromForm = (String) parameters.get("apptId");
        if (statusFromForm != null && statusFromForm.length() > 0) {
            int apptId = 0;

            try {
                apptId = Integer.parseInt(statusFromForm);

                appointmentService.deleteAppt(apptId);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("success", "Appointment deleted successfully.");
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username != null && role != null) {
            if (role.equals("staff")) {
                List<Appointment> apptList = appointmentService.retrieveAppointment(username);
                model.addAttribute("apptList", apptList);
                return "appointment";
            } else {
                List<Appointment> apptList = appointmentService.retrieveAllAppointment();
                model.addAttribute("apptList", apptList);
                return "appointment";
            }

        }
        return "appointment";
    }

    @RequestMapping(value = "/updateApptment", params = "proceedPayment", method = RequestMethod.GET)
    public String proceedPayment(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        String update = (String) parameters.get("apptId");
        if (update != null && update.length() > 0) {
            int apptId = 0;

            try {
                apptId = Integer.parseInt(update);
                Appointment appt = appointmentService.retrieveApptStatus(apptId);

                if (appt != null) {
                    Customer cust = appointmentService.retrieveCust(appt.getCustomerId());
                    model.addAttribute("appt", appt);

                    if (cust != null) {
                        model.addAttribute("customer", cust);
                    }
                }

                List<Services> colourServices = serviceService.retrieveColourServices();
                List<Services> rpServices = serviceService.retrieveRpServices();
                List<Services> treatmentServices = serviceService.retrieveTreatmentServices();
                List<Services> cutServices = serviceService.retrieveCutServices();
                List<Services> styleServices = serviceService.retrieveStyleServices();

                if (colourServices != null && rpServices != null
                        && treatmentServices != null && cutServices != null && styleServices != null) {
                    model.addAttribute("colour", colourServices);
                    model.addAttribute("rp", rpServices);
                    model.addAttribute("treatment", treatmentServices);
                    model.addAttribute("cut", cutServices);
                    model.addAttribute("style", styleServices);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        //List<Services> test = userService.retrieveColourServices();
        return "payment";
    }

    @RequestMapping(value = "/filter", params = "filterDate", method = RequestMethod.GET)
    public String filterDate(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username != null && role != null) {
            if (role.equals("staff")) {
                List<Appointment> apptList = appointmentService.retrieveApptDateInOder(username);
                model.addAttribute("apptList", apptList);
            } else {
                List<Appointment> apptList = appointmentService.retrieveApptDateInOder();
                model.addAttribute("apptList", apptList);
            }
        }
        return "appointment";
    }

    @RequestMapping(value = "/filter", params = "filterCustomer", method = RequestMethod.GET)
    public String filterCustomer(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username != null && role != null) {
            if (role.equals("staff")) {
                List<Appointment> apptList = appointmentService.retrieveCustomerInOder(username);
                model.addAttribute("apptList", apptList);
            } else {
                List<Appointment> apptList = appointmentService.retrieveCustomerInOder();
                model.addAttribute("apptList", apptList);
            }
        }
        return "appointment";

    }

    @RequestMapping(value = "/filter", params = "filterStaff", method = RequestMethod.GET)
    public String filterStaff(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        List<Appointment> apptList = appointmentService.retrieveStaffInOder();
        model.addAttribute("apptList", apptList);
        return "appointment";
    }

    @RequestMapping(value = "/filter", params = "filterAll", method = RequestMethod.GET)
    public String filterAll(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username != null && role != null) {
            if (role.equals("staff")) {
                List<Appointment> apptList = appointmentService.retrieveAppointment(username);
                model.addAttribute("apptList", apptList);
                return "appointment";
            } else {
                List<Appointment> apptList = appointmentService.retrieveAllAppointment();
                model.addAttribute("apptList", apptList);
                return "appointment";
            }

        }
        return "appointment";

    }

    @RequestMapping(value = "/filter", params = "filterExistingApp", method = RequestMethod.GET)
    public String filterExistingApp(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username != null && role != null) {
            if (role.equals("staff")) {
                List<Appointment> apptList = appointmentService.retrieveExistingAppointment(username);
                model.addAttribute("apptList", apptList);
                return "appointment";
            } else {
                List<Appointment> apptList = appointmentService.retrieveExistingAppointment();
                model.addAttribute("apptList", apptList);
                return "appointment";
            }

        }
        return "appointment";

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        String search = (String) parameters.get("search");
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (search == null || search.isEmpty()) {
            if (username != null && role != null) {
                if (role.equals("staff")) {
                    List<Appointment> apptList = appointmentService.retrieveAppointment(username);
                    model.addAttribute("apptList", apptList);
                } else {
                    List<Appointment> apptList = appointmentService.retrieveAllAppointment();
                    model.addAttribute("apptList", apptList);
                }

            }

            model.addAttribute("error", "search field is blank.");
            return "appointment";
        }

        if (username != null && role != null) {
            if (role.equals("staff")) {
                List<Appointment> apptList = appointmentService.retrieveAppointmentBySearchForStaff(search, username);
                if (apptList == null || apptList.size() <= 0) {
                    model.addAttribute("error", "Search result cannot be found.");
                    apptList = appointmentService.retrieveAppointment(username);
                    model.addAttribute("apptList", apptList);
                } else {
                    model.addAttribute("apptList", apptList);
                }

            } else {
                List<Appointment> apptList = appointmentService.retrieveAppointmentBySearch(search);
                if (apptList == null || apptList.size() <= 0) {
                    model.addAttribute("error", "Search result cannot be found.");
                    apptList = appointmentService.retrieveAllAppointment();
                    model.addAttribute("apptList", apptList);
                } else {
                    model.addAttribute("apptList", apptList);
                }

            }

        }

        return "appointment";
    }

    @RequestMapping(value = "/updateApptment", params = "updateAppt", method = RequestMethod.GET)
    public String updateAppt(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) throws ParseException {
        String strApptId = parameters.get("apptId");
        String time = parameters.get("appointmentTime");
        String customerId = parameters.get("customerId");
        String apptDate = parameters.get("appointmentDate");
        String remarks = parameters.get("remarks");
        String customerName = parameters.get("customerName");
        String dob = parameters.get("dob");
        String email = parameters.get("email");
        String hairStylist = parameters.get("hairStylist");

        //check empty fields (ps: remarks is allowed to be empty)
        if (parameters.get("customerId").length() == 0
                || parameters.get("appointmentDate").length() == 0 || parameters.get("customerName").length() == 0
                || parameters.get("appointmentDate") == null
                || parameters.get("appointmentTime").length() == 0 || parameters.get("appointmentTime") == null
                || parameters.get("appointmentTime").equals("")) {

            model.addAttribute("error", "Field(s) cannot be empty (except for remarks).");
            model.addAttribute("customerId", customerId);
            model.addAttribute("time", time);
            model.addAttribute("apptDate", apptDate);
            model.addAttribute("remarks", remarks);
            model.addAttribute("customerName", customerName);
            model.addAttribute("dob", dob);
            model.addAttribute("email", email);
            model.addAttribute("hairStylist", hairStylist);
            return "updateAppointment";
        }

        if (apptDate.length() != 10) {
            model.addAttribute("error", "Invalid appointment date.");
            model.addAttribute("customerId", customerId);
            model.addAttribute("time", time);
            model.addAttribute("apptDate", apptDate);
            model.addAttribute("hairStylistName", hairStylist);
            model.addAttribute("remarks", remarks);
            model.addAttribute("customerName", customerName);
            model.addAttribute("dob", dob);
            model.addAttribute("email", email);
            model.addAttribute("hairStylist", hairStylist);
            return "updateAppointment";
        }

        //convert util.date format to sql.date format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date appointmentDate;
        java.sql.Date sqlDate = null;
        String strSqlDate = "";
        try {
            appointmentDate = format.parse(apptDate);
            sqlDate = new java.sql.Date(appointmentDate.getTime());
            strSqlDate = sqlDate + "";

        } catch (ParseException ex) {
            model.addAttribute("error", "Invalid appointment date.");
            model.addAttribute("customerId", customerId);
            model.addAttribute("time", time);
            model.addAttribute("apptDate", apptDate);
            model.addAttribute("remarks", remarks);
            model.addAttribute("customerName", customerName);
            model.addAttribute("dob", dob);
            model.addAttribute("email", email);
            model.addAttribute("hairStylist", hairStylist);
            return "updateAppointment";
        }

        //check if the appointment date is before the current date
        Date currentDate = new Date();
        java.sql.Date sqlCurrentDate = new java.sql.Date(currentDate.getTime());
        String strSqlCurrentDate = sqlCurrentDate + "";
        if (!strSqlDate.equals(strSqlCurrentDate) && sqlDate.before(sqlCurrentDate)) {
            model.addAttribute("error", "Appointment Date needs to be  after current date.");
            model.addAttribute("customerId", customerId);
            model.addAttribute("time", time);
            model.addAttribute("apptDate", apptDate);
            model.addAttribute("remarks", remarks);
            model.addAttribute("customerName", customerName);
            model.addAttribute("dob", dob);
            model.addAttribute("email", email);
            model.addAttribute("hairStylist", hairStylist);
            return "updateAppointment";
        }

        //appointment time
        SimpleDateFormat timeDateFormat = new SimpleDateFormat("hh:mm");
        long ms = timeDateFormat.parse(time).getTime();
        Time t = new Time(ms);

        //get current time
        Calendar cal = Calendar.getInstance();
        String strCurrentTime = timeDateFormat.format(cal.getTime());
        long currentMS = timeDateFormat.parse(strCurrentTime).getTime();
        Time currentTime = new Time(currentMS);

        int apptId = 0;

        try {
            apptId = Integer.parseInt(strApptId);

        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        //if appointment date is same as the current date
        if (strSqlDate.equals(strSqlCurrentDate)) {
            ////check if the appointment time is before the current time
            if (t.before(currentTime)) {
                model.addAttribute("error", "Appointment Time needs to be  after current time.");
                model.addAttribute("customerId", customerId);
                model.addAttribute("time", time);
                model.addAttribute("apptDate", apptDate);
                model.addAttribute("remarks", remarks);
                model.addAttribute("customerName", customerName);
                model.addAttribute("dob", dob);
                model.addAttribute("email", email);
                model.addAttribute("hairStylist", hairStylist);
                return "updateAppointment";
            }

        }

        String strD = "";
        String strApptTime = "";
        Appointment a = appointmentService.retrieveApptStatus(apptId);
        if (a != null) {
            strD = a.getAppointmentDate() + "";
            strApptTime = a.getAppointmentTime() + "";
        }

        if (!time.equals(strApptTime) && strD.equals(apptDate)) {
            List<Appointment> apptList = appointmentService.retrieveAppointment(hairStylist);
            if (apptList != null) {
                for (Appointment appt : apptList) {
                    String strDate = appt.getAppointmentDate() + "";
                    if (strSqlDate.equals(strDate)) {
                        Time apptTime = appt.getAppointmentTime();
                        if (apptTime.equals(t)) {
                            model.addAttribute("error", "The hairStylist has an appointment at this timing. Plese select another time.");
                            model.addAttribute("customerId", customerId);
                            model.addAttribute("time", time);
                            model.addAttribute("apptDate", apptDate);
                            model.addAttribute("remarks", remarks);
                            model.addAttribute("customerName", customerName);
                            model.addAttribute("dob", dob);
                            model.addAttribute("email", email);
                            model.addAttribute("hairStylist", hairStylist);
                            return "updateAppointment";
                        }

                        String strAfterTime = t + "";
                        SimpleDateFormat dfAfter = new SimpleDateFormat("HH:mm");
                        Date afterDate = dfAfter.parse(strAfterTime);
                        Calendar afterCal = Calendar.getInstance();
                        afterCal.setTime(afterDate);
                        afterCal.add(Calendar.MINUTE, 60);
                        String newAfterTime = dfAfter.format(afterCal.getTime());
                        long afterMS = dfAfter.parse(newAfterTime).getTime();
                        Time afterTime = new Time(afterMS);

                        String strBeforeTime = t + "";
                        SimpleDateFormat dfBefore = new SimpleDateFormat("HH:mm");
                        Date beforeDate = dfBefore.parse(strBeforeTime);
                        Calendar beforeCal = Calendar.getInstance();
                        beforeCal.setTime(beforeDate);
                        beforeCal.add(Calendar.MINUTE, -60);
                        String newBeforeTime = dfBefore.format(beforeCal.getTime());
                        long beforeMS = dfBefore.parse(newBeforeTime).getTime();
                        Time beforeTime = new Time(beforeMS);
                        //Date beforeTime1 = beforeCal.getTime();

                        if (apptTime.after(beforeTime) && apptTime.before(afterTime)) {
                            model.addAttribute("error", "The hairStylist has an another appointment within 1 hour of this timing. Plese select another time.");
                            model.addAttribute("customerId", customerId);
                            model.addAttribute("time", time);
                            model.addAttribute("apptDate", apptDate);
                            model.addAttribute("remarks", remarks);
                            model.addAttribute("customerName", customerName);
                            model.addAttribute("dob", dob);
                            model.addAttribute("email", email);
                            model.addAttribute("hairStylist", hairStylist);
                            return "updateAppointment";
                        }
                    }
                }
            }
        }

        appointmentService.updateAppointment(apptId, hairStylist, sqlDate, t, remarks);

        model.addAttribute("success", "Appointment has been updated successfully.");

        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username != null && role != null) {
            if (role.equals("staff")) {
                List<Appointment> apptList = appointmentService.retrieveAppointment(username);
                model.addAttribute("apptList", apptList);
                return "appointment";
            } else {
                List<Appointment> apptList = appointmentService.retrieveAllAppointment();
                model.addAttribute("apptList", apptList);
                return "appointment";
            }

        }

        return "appointment";
    }

    @RequestMapping(value = "/updateApptment", params = "cancelAppt", method = RequestMethod.GET)
    public String cancelAppt(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username != null && role != null) {
            if (role.equals("staff")) {
                List<Appointment> apptList = appointmentService.retrieveAppointment(username);
                model.addAttribute("apptList", apptList);
                return "appointment";
            } else {
                List<Appointment> apptList = appointmentService.retrieveAllAppointment();
                model.addAttribute("apptList", apptList);
                return "appointment";
            }

        }
        return "appointment";
    }

    @RequestMapping(value = "/apptCalendarFilter", method = RequestMethod.GET)
    public String apptCalendarFilter(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        String hairStylist = parameters.get("hairStylist");

        if (hairStylist != null || hairStylist.length() > 0) {
            if (hairStylist.equals("all")) {
                List<Appointment> apptList = appointmentService.retrieveApptDateInOder();
                model.addAttribute("filter", hairStylist);
                model.addAttribute("apptList", apptList);
            } else {
                List<Appointment> apptList = appointmentService.retrieveApptDateInOder(hairStylist);
                model.addAttribute("filter", hairStylist);
                model.addAttribute("apptList", apptList);
            }
        }

        return "appointment";
    }
}

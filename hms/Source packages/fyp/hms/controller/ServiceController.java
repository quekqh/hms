/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.controller;

import fyp.hms.dao.ServiceDAO;
import fyp.hms.model.Services;
import fyp.hms.service.ServiceService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author HajaReethwan
 */
@Controller
public class ServiceController {
    @Autowired
    ServiceService serviceService;
    
    @RequestMapping(value = "/createService", method = RequestMethod.GET)
    public String createService(@RequestParam Map<String, String> parameters, ModelMap model,HttpSession session) throws ParseException {
        String desc = parameters.get("desc").trim();
        String shortPrice = parameters.get("short").trim();
        String mediumPrice = parameters.get("medium").trim();
        String longPrice = parameters.get("long").trim();
        String category = parameters.get("category").trim();
        
        if (desc == null || shortPrice == null || mediumPrice == null || longPrice == null || 
            desc.length() == 0 || shortPrice.length() == 0 || mediumPrice.length() == 0 || longPrice.length() == 0) {
            model.addAttribute("desc","Description cannot be empty");
            model.addAttribute("short","Short Price cannot be empty");
            model.addAttribute("medium","Medium Price cannot be empty");
            model.addAttribute("long","Long Price cannot be empty");
            return "services";
        }
        
        Pattern pat = Pattern.compile("^[ A-z()0-9]+$");
        Matcher m = pat.matcher(desc);
        if (!m.matches()) {
            model.addAttribute("error","Description cannot contain foreign characters");           
            return "services";
        }
        
        double shortFee = 0;
        double mediumFee = 0;
        double longFee = 0;
        
        try{
            shortFee = Double.parseDouble(shortPrice);
        }catch(NumberFormatException e){
            model.addAttribute("error", "Short Price must contain numbers only");
            return "services";
        }                        

        try{
            mediumFee = Double.parseDouble(mediumPrice);
        }catch(NumberFormatException e){
            model.addAttribute("error", "Medium Price must contain numbers only");
            return "services";
        } 

        try{
            longFee = Double.parseDouble(longPrice);
        }catch(NumberFormatException e){
            model.addAttribute("error", "Long Price must contain numbers only");
            return "services";
        } 
        
        List<Services> list = ServiceDAO.retrieveAllServices();
        int newId = list.size() + 1;
        System.out.println(newId);
        serviceService.insertService(newId,desc,shortFee,mediumFee,longFee,category);
        String message = "Service Id No: " + newId + " has been successfully created!";
        model.addAttribute("success",message);
        return "services";
    }
    
    @RequestMapping(value = "/serviceSearch", method = RequestMethod.GET)
    public String search(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) throws ParseException {        
        if(parameters.get("search") == null){
            return "services";
        } else {
            String search = parameters.get("search");
            TreeMap<Integer,Services> map = serviceService.retrieveSearch(search);
            model.addAttribute("map",map);
            return "services";
        }
    }    
    
    @RequestMapping(value = "/serviceTypes", method = RequestMethod.GET)
    public String serviceTypes(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) throws ParseException {
        if(parameters.get("cut") != null){
            TreeMap<Integer, Services> list = serviceService.cutServices();
            model.addAttribute("map",list);
            return "services";
        } else if(parameters.get("style") != null){
            TreeMap<Integer, Services> list = serviceService.styleServices();
            model.addAttribute("map",list);
            return "services";
        } else if(parameters.get("colour") != null){
            TreeMap<Integer, Services> list = serviceService.colourServices();
            model.addAttribute("map",list);
            return "services";
        } else if(parameters.get("perm") != null){
            TreeMap<Integer, Services> list = serviceService.rpServices();
            model.addAttribute("map",list);
            return "services";
        } else if(parameters.get("treatment") != null) {       
            TreeMap<Integer, Services> list = serviceService.treatmentServices();
            model.addAttribute("map",list);
            return "services";
        } else {
            TreeMap<Integer, Services> list = serviceService.allServices();
            model.addAttribute("map",list);            
            return "services";
        }
    }    
    
    @RequestMapping(value = "/serviceDelete", method = RequestMethod.GET)
    public String deleteService(@RequestParam Map<String, String> parameters, ModelMap map, HttpSession session) {
            int id = Integer.parseInt(parameters.get("delete"));
            String message = "Customer ID No: " + id + " has been successfully deleted";
            serviceService.deleteService(id);
            map.addAttribute("success", message);
            return "services";
    }
    
    @RequestMapping(value = "/serviceEdit", method = RequestMethod.GET)
    public String serviceUpdate(@RequestParam Map<String, String> parameters, ModelMap map, HttpSession session) {        
        String id = parameters.get("edit");
        map.addAttribute("id", id);
        return "serviceUpdate";
    }    
    
    @RequestMapping(value = "/updateService", params="submit", method = RequestMethod.GET)
    public String updateService(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) throws ParseException {
        
        String serviceId = parameters.get("id").trim();
        String desc = parameters.get("desc").trim();
        String shortPrice = parameters.get("short").trim();
        String mediumPrice = parameters.get("medium").trim();
        String longPrice = parameters.get("long").trim();
        String category = parameters.get("category").trim();
        
        if (desc == null || shortPrice == null || mediumPrice == null || longPrice == null || 
            desc.length() == 0 || shortPrice.length() == 0 || mediumPrice.length() == 0 || longPrice.length() == 0) {
            model.addAttribute("desc","Description cannot be empty");
            model.addAttribute("short","Short Price cannot be empty");
            model.addAttribute("medium","Medium Price cannot be empty");
            model.addAttribute("long","Long Price cannot be empty");
            return "services";
        }
        
        Pattern pat = Pattern.compile("^[ A-z()0-9]+$");
        Matcher m = pat.matcher(desc);
        if (!m.matches()) {
            model.addAttribute("error","Description cannot contain foreign characters");           
            return "services";
        }
        
        double shortFee = 0;
        double mediumFee = 0;
        double longFee = 0;
        
        try{
            shortFee = Double.parseDouble(shortPrice);
        }catch(NumberFormatException e){
            model.addAttribute("error", "Short Price must contain numbers only");
            return "services";
        }                        

        try{
            mediumFee = Double.parseDouble(mediumPrice);
        }catch(NumberFormatException e){
            model.addAttribute("error", "Medium Price must contain numbers only");
            return "services";
        } 

        try{
            longFee = Double.parseDouble(longPrice);
        }catch(NumberFormatException e){
            model.addAttribute("error", "Long Price must contain numbers only");
            return "services";
        }
        
        int id = Integer.parseInt(serviceId);
        serviceService.updateService(id,desc,shortFee,mediumFee,longFee,category);
        String message = "Service Id No: " + id + " has been successfully updated!";
        model.addAttribute("success",message);
        return "services";
    }
    
    @RequestMapping(value = "/updateService", params="cancel", method = RequestMethod.GET)
    public String cancelUpdate(@RequestParam Map<String, String> parameters, ModelMap model, HttpSession session) throws ParseException {
        return "services";
    }    
}    

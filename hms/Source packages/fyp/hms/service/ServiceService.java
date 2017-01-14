/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.service;

import fyp.hms.dao.ServiceDAO;
import fyp.hms.model.Payment;
import fyp.hms.model.Services;
import java.util.List;
import java.util.TreeMap;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Low Kang Li
 */
@Service("serviceService")
public class ServiceService {
    @Autowired
    HttpSession session;
    
    public List<Services> retrieveColourServices() {
        List<Services> colourServices = ServiceDAO.retrieveColourServices();
        
        if (colourServices != null) {
            return colourServices;  
        }
        
        return null;
    }
    
    public List<Services> retrieveRpServices() {
        List<Services> rpServices = ServiceDAO.retrieveRpServices();
        
        if (rpServices != null) {
            return rpServices;  
        }
        
        return null;
    }
    
    public List<Services> retrieveTreatmentServices() {
        List<Services> treatmentServices = ServiceDAO.retrieveTreatmentServices();
        
        if (treatmentServices != null) {
            return treatmentServices;  
        }
        
        return null;
    }
    
    public List<Services> retrieveCutServices() {
        List<Services> cutServices = ServiceDAO.retrieveCutServices();
        
        if (cutServices != null) {
            return cutServices;  
        }
        
        return null;
    }
    
    public List<Services> retrieveStyleServices() {
        List<Services> styleServices = ServiceDAO.retrieveStyleServices();
        
        if (styleServices != null) {
            return styleServices;  
        }
        
        return null;
    }
    
    public List<Services> retrieveAllServices() {
        List<Services> allServices = ServiceDAO.retrieveAllServices();
        
        if (allServices != null) {
            return allServices;  
        }
        
        return null;
    }

    public TreeMap<Integer, Services> retrieveSearch(String search) {
        return ServiceDAO.retrieveSearch(search);
    }

    public void deleteService(int id) {
        ServiceDAO.deleteService(id);
    }

    public TreeMap<Integer, Services> cutServices() {
        return ServiceDAO.cutServices();
    }

    public TreeMap<Integer, Services> styleServices() {
        return ServiceDAO.styleServices();
    }

    public TreeMap<Integer, Services> colourServices() {
        return ServiceDAO.colourServices();
    }

    public TreeMap<Integer, Services> rpServices() {
        return ServiceDAO.rpServices();
    }

    public TreeMap<Integer, Services> treatmentServices() {
        return ServiceDAO.treatmentServices();
    }

    public TreeMap<Integer, Services> allServices() {
        return ServiceDAO.allServices();
    }

    public void insertService(int newId, String desc, double shortFee, double mediumFee, double longFee, String category) {
        ServiceDAO.insertService(newId,desc,shortFee,mediumFee,longFee,category);
    }

    public void updateService(int id, String desc, double shortFee, double mediumFee, double longFee, String category) {
        ServiceDAO.updateService(id, desc, shortFee, mediumFee, longFee, category);
    }
}

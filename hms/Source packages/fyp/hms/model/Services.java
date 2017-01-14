/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.model;

/**
 *
 * @author Low Kang Li
 */
public class Services {
    private int serviceID;
    private String serviceDescription;
    private double shortPrice;
    private double mediumPrice;
    private double longPrice;
    private String category;
    
    public Services(int serviceID, String serviceDescription, double shortPrice, double mediumPrice, double longPrice, String category) {
        this.serviceID = serviceID;
        this.serviceDescription = serviceDescription;
        this.shortPrice = shortPrice;
        this.mediumPrice = mediumPrice;
        this.longPrice = longPrice;
        this.category = category;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public double getShortPrice() {
        return shortPrice;
    }

    public void setShortPrice(double shortPrice) {
        this.shortPrice = shortPrice;
    }

    public double getMediumPrice() {
        return mediumPrice;
    }

    public void setMediumPrice(double mediumPrice) {
        this.mediumPrice = mediumPrice;
    }

    public double getLongPrice() {
        return longPrice;
    }

    public void setLongPrice(double longPrice) {
        this.longPrice = longPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    
}

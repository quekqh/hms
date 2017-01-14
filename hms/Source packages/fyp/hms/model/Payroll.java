/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp.hms.model;

import java.sql.Date;

/**
 *
 * @author HajaReethwan
 */
public class Payroll {
    private String userId;
    private Date date;
    private int basicPay;
    private int commission;
    private int deductions;
    private int grossPay;
    private int cpf;
    private int nettPay;
    
    public Payroll(String userId, Date date, int basicPay, int commission, int deductions, int grossPay, int cpf, int nettPay){
       this.userId = userId;
       this.date = date;
       this.basicPay = basicPay;
       this.commission = commission;
       this.deductions = deductions;
       this.grossPay = grossPay;
       this.cpf = cpf;
       this.nettPay = nettPay;
    }

    public String getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }

    public int getBasicPay() {
        return basicPay;
    }

    public int getCommission() {
        return commission;
    }

    public int getDeductions() {
        return deductions;
    }

    public int getGrossPay() {
        return grossPay;
    }

    public int getCpf() {
        return cpf;
    }

    public int getNettPay() {
        return nettPay;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setBasicPay(int basicPay) {
        this.basicPay = basicPay;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public void setDeductions(int deductions) {
        this.deductions = deductions;
    }

    public void setGrossPay(int grossPay) {
        this.grossPay = grossPay;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public void setNettPay(int nettPay) {
        this.nettPay = nettPay;
    }
}

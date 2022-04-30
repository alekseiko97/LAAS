package com.alekseiko.laas.model;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import java.util.HashMap;

public class Contract {

    public Contract(String customerID, Double loanAmount, HashMap<String, Boolean> approvers) {
        this.customerID = customerID;
        this.loanAmount = loanAmount;
        this.approvers = approvers;
        this.pending = true;
    }

    private @Id @GeneratedValue Long id;
    private String customerID;
    private Double loanAmount;
    private Boolean pending;
    private HashMap<String, Boolean> approvers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public HashMap<String, Boolean> getApprovers() {
        return approvers;
    }

}

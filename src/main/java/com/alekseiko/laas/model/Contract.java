package com.alekseiko.laas.model;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import java.util.List;

public class Contract {

    public Contract(String customerID, Double loanAmount, List<String> approvers) {
        // TODO: text, must be in a pattern XX-XXXX-XXX where X is either number or a letter
        this.customerID = customerID;
        this.loanAmount = loanAmount;
        this.approvers = approvers;
        this.pending = true;
    }

    private @Id
    @GeneratedValue Long id;
    private String customerID;
    private Double loanAmount;
    private Boolean pending;
    //private HashMap<String, Boolean> approvers;
    private List<String> approvers;

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

    public List<String> getApprovers() {
        return approvers;
    }
}

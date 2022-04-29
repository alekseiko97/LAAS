package com.alekseiko.laas.model;

import java.util.List;

public class Contract {

    public Contract() {

    }

    public Contract(Integer id, String customerID, Double loanAmount, List<String> approvers) {
        this.id = id;
        this.customerID = customerID;
        this.loanAmount = loanAmount;
        this.pending = true;
        this.approvers = approvers;
    }

    private Integer id;
    private String customerID;
    private Double loanAmount;
    private Boolean pending;
    private List<String> approvers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getPending() {
        return pending;
    }

    public void setPending() {
        this.pending = pending;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount() {
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

    public void setApprovers(List<String> approvers) {
        this.approvers = approvers;
    }
}

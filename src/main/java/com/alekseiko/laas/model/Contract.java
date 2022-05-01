package com.alekseiko.laas.model;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contract {

    public Contract(String customerID, Double loanAmount, List<String> approvers) {

        ValidateCustomerID(customerID);
        ValidateApproversList(approvers);

        this.id = UUID.randomUUID();
        this.customerID = customerID;
        this.loanAmount = loanAmount;
        this.approvers = approvers;
        this.pending = true; // default
    }

    @Id
    private UUID id;
    private String customerID;
    private Double loanAmount;
    private Boolean pending;
    private final List<String> approvers;

    private void ValidateCustomerID(String customerID) {
        String expression = "^(\\w)(\\w)-(\\w)(\\w)(\\w)(\\w)-(\\w)(\\w)(\\w)$";
        Pattern pattern = Pattern.compile(expression);

        Matcher matcher = pattern.matcher(customerID);

        if (!matcher.matches()) throw new IllegalArgumentException(String.format("Invalid format for customerID. Must be in a pattern XX-XXXX-XXX where X is either number or a letter, whereas provided %s", customerID));
    }

    private void ValidateApproversList(List<String> approvers) {
        // list of usernames (text), up to 3
        if (approvers.size() > 3) {
            throw new IllegalArgumentException(String.format("Number of loan managers to approve loan request cannot exceed 3, provided %s", approvers.size()));
        }
    }

    public UUID getId() {
        return id;
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

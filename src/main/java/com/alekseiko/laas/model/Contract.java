package com.alekseiko.laas.model;

import java.util.List;

public class Contract {

    public Contract() {

    }

    public Contract(Integer id, String customerID, Double loanAmount, Boolean pending) {
        this.id = id;
        this.customerID = customerID;
        this.loanAmount = loanAmount;
        this.pending = pending;
    }

    private Integer id;
    private String customerID;
    private Double loanAmount;
    private Boolean pending;
}

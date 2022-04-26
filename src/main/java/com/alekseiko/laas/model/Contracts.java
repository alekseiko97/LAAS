package com.alekseiko.laas.model;

import java.util.ArrayList;
import java.util.List;

public class Contracts {
    private List<Contract> listOfContracts;

    public List<Contract> GetListOfContracts() {
        if (listOfContracts == null) {
            listOfContracts = new ArrayList<>();
        }

        return listOfContracts;
    }

    public void SetListOfContracts(List<Contract> listOfContracts) {
        this.listOfContracts = listOfContracts;
    }
}

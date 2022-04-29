package com.alekseiko.laas.service;

import com.alekseiko.laas.model.Contract;
import com.alekseiko.laas.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ContractService {

    ContractRepository contractRepository;

    private List<Contract> contractList = new ArrayList<>(Arrays.asList(
       new Contract(1, "XX-XXXX-XXX", 20.00, new ArrayList<>(Arrays.asList("alekseiko", "alekseiko", "alekseiko")))
    ));

    public List<Contract> getAllContractsList() {
        return this.contractList;
    }
}

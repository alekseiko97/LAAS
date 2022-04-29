package com.alekseiko.laas.controller;

import com.alekseiko.laas.model.Contract;
import com.alekseiko.laas.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class ContractController {
    @Autowired
    private ContractService contractService;

    @RequestMapping("/contracts")
    public List<Contract> GetAllContracts() {
        return contractService.getAllContractsList();
    }

/*    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contract CreateLoanAmountApprovalRequest() {

    }*/

    @RequestMapping(value = "/statistics", method = RequestMethod.GET, produces = "application/json")
    public void GetContractStatistics()
    {

    }

}

package com.alekseiko.laas.controller;

import com.alekseiko.laas.OperationNotAllowedException;
import com.alekseiko.laas.model.Contract;
import com.alekseiko.laas.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;

@RestController
class ContractController {
    @Autowired
    private ContractService contractService;

    @RequestMapping("/contracts")
    public List<Contract> GetAllContracts() {
        return contractService.getAllContractsList();
    }

    @PostMapping("/contracts")
    @ResponseStatus(HttpStatus.CREATED)
    @ExceptionHandler
    public void CreateLoanAmountRequest(@RequestBody Contract contract) {
        try
        {
            contractService.AddLoanAmountRequest(contract);
        }
        catch (OperationNotAllowedException ex)
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Operation not allowed", ex);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Illegal argument provided", ex);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/contracts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ApproveLoanAmountRequest(@RequestParam(name = "customerID") String customerID, @RequestParam(name = "loanManager") String loanManager) {
        contractService.ApproveContract(customerID, loanManager);
    }

    @GetMapping("/contracts/stats")
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String, Double> GetContractsStatistics() {
        return contractService.GetContractsStatistics();
    }

}

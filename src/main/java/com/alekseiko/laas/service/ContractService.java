package com.alekseiko.laas.service;

import com.alekseiko.laas.OperationNotAllowedException;
import com.alekseiko.laas.model.Contract;
import com.alekseiko.laas.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

// import org.springframework.data.jpa.repository.JpaRepository;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;
    // dummy list

    private final HashMap<String, Boolean> loanManagers = new HashMap<>();

    private final List<Contract> contractList = new ArrayList<>(List.of(
            new Contract("XX-XXXX-XXX", 20.00, loanManagers) // dummy entry
    ));

    public ContractService() {
        loanManagers.put("manager1", false); // false = not given approval yet (default value)
        loanManagers.put("manager2", false);
        loanManagers.put("manager3", false);
    }

    public void AddLoanAmountRequest(Contract c) throws OperationNotAllowedException {
        // TODO: There can be only one pending loan request for one customer
        for (var contract: contractList) {
            if (Objects.equals(contract.getCustomerID(), c.getCustomerID())) {
                if (contract.getPending()) throw new OperationNotAllowedException(String.format("Pending loan request for customer %s already exists", c.getCustomerID()));
            }
        }

        contractList.add(c);
    }

    public List<Contract> getAllContractsList() {
        return this.contractList;
    }

    public void ApproveContract(String customerID, String loanManager) {

        // try to find loan contract by customerID, loan manager userName that needs to approve it, and pending status being true
        getAllContractsList().stream().filter(x -> Objects.equals(x.getCustomerID(), customerID)
                        && x.getPending() && x.getApprovers().containsKey(loanManager))
                .findFirst().ifPresent(contract -> contract.getApprovers().put(loanManager, true));

        // TODO: if contract is not found, return proper HTTP code with an error message

    }
}

package com.alekseiko.laas.service;

import com.alekseiko.laas.OperationNotAllowedException;
import com.alekseiko.laas.model.Contract;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class ContractService {

    private final List<String> loanManagers = new ArrayList<>();

    // dummy list
    private final List<Contract> contractList = new ArrayList<>(List.of(
            new Contract("XX-XXXX-XXX", 20.00, loanManagers) // dummy entry
    ));

    public ContractService() {
        loanManagers.add("manager1");
        loanManagers.add("manager2");
        loanManagers.add("manager3");
    }

    public void AddLoanAmountRequest(Contract c) throws OperationNotAllowedException {
        // There can be only one pending loan request for one customer
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

        // first, try to find a pending loan contract for a customer
        var contractStream = getAllContractsList().stream().filter(x -> Objects.equals(x.getCustomerID(), customerID) && x.getPending());

        if (contractStream.findAny().isEmpty()) {
            throw new NullPointerException(String.format("Couldn't find a pending loan contract for customer %s", customerID));
        }

        // then, try to find a loan manager that needs to approve this contract
        contractStream = getAllContractsList().stream().filter(x -> Objects.equals(x.getCustomerID(), customerID) && x.getPending() && x.getApprovers().contains(loanManager));
        var contract = contractStream.findFirst().orElse(null);

        if (contract == null) {
            throw new NullPointerException(String.format("%s is not assigned to approve a loan contract for %s", loanManager, customerID));
        }

        // TODO: reconsider
        contract.getApprovers().remove(loanManager);

        if (contract.getApprovers().size() == 0) {
           // After the specified managers have added their approvals the contract will be automatically sent to the customer.
           contract.setPending(false); // approved
        }
    }

    /*
    * Return statistics of contracts that were sent to the customers during a period that is configured in the application (default is 60 seconds). Endpoint must return the following:
     * count -  count of sent contracts
     * sum - sum of all the loan amounts
     * avg - average loan amount
     * max - biggest loan amount
     * min - smallest loan amount
     * */
    public HashMap<String, Double> GetContractsStatistics() {
        // TODO: period that is configured in the application (default is 60 seconds)

        HashMap<String, Double> map = new HashMap<>();

        double count = contractList.size();

        Double sum = contractList.stream().mapToDouble(Contract::getLoanAmount).sum();
        Double avg = contractList.stream().mapToDouble(Contract::getLoanAmount).average().orElse(0);
        Double min = contractList.stream().mapToDouble(Contract::getLoanAmount).min().orElse(0);
        Double max = contractList.stream().mapToDouble(Contract::getLoanAmount).max().orElse(0);

        map.put("count", count);
        map.put("sum", sum);
        map.put("avg", avg);
        map.put("min", min);
        map.put("max", max);

        return map;
    }

}

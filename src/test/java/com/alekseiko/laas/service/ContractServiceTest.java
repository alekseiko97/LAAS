package com.alekseiko.laas.service;

import com.alekseiko.laas.OperationNotAllowedException;
import com.alekseiko.laas.model.Contract;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContractServiceTest {

    ContractService contractService = new ContractService();
    List<String> validListOfApprovers = new ArrayList<>(List.of("1","2","3"));
    String acceptedCustomerID = "aa-aaaa-aaa";

    @Test
    void shouldAddLoanAmountRequestToArrayList() throws OperationNotAllowedException {
        Contract contract = new Contract(acceptedCustomerID, 20.0, validListOfApprovers);
        contractService.AddLoanAmountRequest(contract);

        assertEquals(contractService.getAllContractsList().size(), 2);
    }

    @Test
    void shouldReturnListOfAllContracts() {
        var contracts = contractService.getAllContractsList();
        assertNotEquals(0, contracts.size()); // since 1 is created during service initialization (dummy entry)
    }

    @Test
    void contractWithInvalidCustomerIDPatternShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            String invalidCustomerIDFormat = "aa-aaaa-aa";
            new Contract(invalidCustomerIDFormat, 20.0, validListOfApprovers);
        });
    }

    @Test
    void contractWithMoreThanThreeApproversShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            String invalidCustomerIDFormat = "aa-aaaa-aa";
            new Contract(invalidCustomerIDFormat, 20.0, new ArrayList<>(List.of("1","2","3","4")));
        });
    }

    @Test
    void addingTwoPendingLoanRequestsForOneCustomerShouldThrowException() {
        assertThrows(OperationNotAllowedException.class, () ->
        {
            Contract c1 = new Contract(acceptedCustomerID, 20.0, validListOfApprovers);
            Contract c2 = new Contract(acceptedCustomerID, 35.0, validListOfApprovers);

            contractService.AddLoanAmountRequest(c1);
            contractService.AddLoanAmountRequest(c2); // expected to be thrown here
        });
    }

    @Test
    void contractShouldGetApprovalBySpecifiedManager() throws OperationNotAllowedException {
        String loanManager1 = "manager1";
        String loanManager2 = "manager2";

        Contract c1 = new Contract(acceptedCustomerID, 20.0, new ArrayList<>(List.of(loanManager1, loanManager2)));

        contractService.AddLoanAmountRequest(c1);
        contractService.ApproveContract(acceptedCustomerID, loanManager1);

        assertEquals(c1.getApprovers().size(), 1); // decreased by 1 as one manager had just given his/her approval
    }

    @Test
    void contractShouldNotGetApprovalByUnexpectedManager() {
        assertThrows(NullPointerException.class, () -> {
            String loanManager1 = "manager1";
            String loanManager2 = "manager2";
            String loanManager3 = "manager3";

            Contract c1 = new Contract(acceptedCustomerID, 20.0, new ArrayList<>(List.of(loanManager1, loanManager2))); // NOTE: loanManager3 is not added to the list

            contractService.AddLoanAmountRequest(c1);
            contractService.ApproveContract(acceptedCustomerID, loanManager3);
        });

    }

    @Test
    void contractShouldGetFullApproval() throws OperationNotAllowedException {
        String loanManager1 = "manager1";
        String loanManager2 = "manager2";

        Contract c1 = new Contract(acceptedCustomerID, 20.0, new ArrayList<>(List.of(loanManager1, loanManager2)));

        contractService.AddLoanAmountRequest(c1);

        // every approver (a.k.a. loan manager) mentioned by the loan preparator gives his/her approval
        contractService.ApproveContract(acceptedCustomerID, loanManager1);
        contractService.ApproveContract(acceptedCustomerID, loanManager2);

        assertEquals(c1.getPending(), false); // i.e. contract is automatically sent to the customer
    }
}
package com.ledger.dao.impl;

import com.ledger.dao.LedgerDao;
import com.ledger.exception.LoanNotFoundException;
import com.ledger.model.Loan;

import java.util.HashMap;
import java.util.Map;

public class LedgerDaoImpl implements LedgerDao {

    private final Map<String, Map<String, Loan>> customerLoanMap;

    public LedgerDaoImpl(final Map<String, Map<String, Loan>> customerLoanMap) {
        this.customerLoanMap = customerLoanMap;
    }

    @Override
    public void saveLoan(final Loan loan) {
        if (customerLoanMap.containsKey(loan.getBorrowerName())) {
            Map<String, Loan> bankToLoanMap = customerLoanMap.get(loan.getBorrowerName());
            bankToLoanMap.put(loan.getBankName(), loan);
        } else {
            Map<String, Loan> bankToLoanMap = new HashMap<>();
            bankToLoanMap.put(loan.getBankName(), loan);
            customerLoanMap.put(loan.getBorrowerName(), bankToLoanMap);
        }
    }

    @Override
    public Loan getLoan(final String bankName, final String borrowerName) {
        if(!customerLoanMap.containsKey(borrowerName) || !customerLoanMap.get(borrowerName).containsKey(bankName)){
            throw new LoanNotFoundException(String.format("No loan found for borrower- %s, bank - %s",borrowerName, bankName));
        }
        return customerLoanMap.get(borrowerName).get(bankName);
    }
}

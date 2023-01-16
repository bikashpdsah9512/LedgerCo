package com.ledger.manager;

import com.ledger.dao.LedgerDao;
import com.ledger.model.Loan;
import com.ledger.model.input.LoanInput;
import com.ledger.strategies.EMICalculator;
import com.ledger.strategies.InterestCalculator;
import com.ledger.util.DateUtil;

import java.util.ArrayList;

public class LoanManager {

    private LedgerDao ledgerDao;
    private InterestCalculator interestCalculator;
    private EMICalculator emiCalculator;

    public LoanManager(final LedgerDao ledgerDao,
                       final InterestCalculator interestCalculator,
                       final EMICalculator emiCalculator) {
        this.ledgerDao = ledgerDao;
        this.interestCalculator = interestCalculator;
        this.emiCalculator = emiCalculator;

    }

    public Loan createLoan(LoanInput loanInput) {
        double interest = interestCalculator.calculate(loanInput.getPrincipal(), loanInput.getInterestRate(), loanInput.getLoanPeriodInYears());
        double amount = loanInput.getPrincipal()+ interest;
        int emiAmount = emiCalculator.calculateEMI(amount, loanInput.getLoanPeriodInYears());
        Loan loan = Loan.builder()
                .totalAmount(amount)
                .borrowerName(loanInput.getBorrowerName())
                .bankName(loanInput.getBankName())
                .emiAmount(emiAmount)
                .remainingEMI(DateUtil.getNoOfMonthFromYear(loanInput.getLoanPeriodInYears()))
                .payments(new ArrayList<>())
                .build();
        ledgerDao.saveLoan(loan);
        return loan;
    }



}

package com.ledger.command;

import com.ledger.manager.LoanManager;
import com.ledger.model.Loan;
import com.ledger.model.input.LoanInput;
import com.ledger.strategies.EMICalculator;
import com.ledger.strategies.InterestCalculator;
import com.ledger.util.InputConverter;

/**
 * Class to handle Loan command.
 */
public class LoanCommand implements LedgerCommand{

    private InterestCalculator interestCalculator;
    private EMICalculator emiCalculator;

    LoanManager loanManager;

    public LoanCommand(LoanManager loanManager){
        this.loanManager = loanManager;
    }
    @Override
    public void execute(String[] input) {
        LoanInput loanInput = InputConverter.getLoanInput(input);
        loanManager.createLoan(loanInput);
    }
}

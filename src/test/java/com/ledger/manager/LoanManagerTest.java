package com.ledger.manager;

import com.ledger.dao.LedgerDao;
import com.ledger.model.Loan;
import com.ledger.strategies.EMICalculator;
import com.ledger.strategies.InterestCalculator;
import com.ledger.strategies.impl.SimpleEMICalculator;
import com.ledger.strategies.impl.SimpleInterestCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ledger.MockGenerator.getLoan;
import static com.ledger.MockGenerator.getLoanInput;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoanManagerTest {

    private LedgerDao ledgerDao;
    private EMICalculator emiCalculator;
    private InterestCalculator interestCalculator;

    private LoanManager loanManager;

    @BeforeEach
    public void setup(){
        ledgerDao = mock(LedgerDao.class);
        emiCalculator = new SimpleEMICalculator();
        interestCalculator = new SimpleInterestCalculator();
        loanManager = new LoanManager(ledgerDao, interestCalculator, emiCalculator);
    }

    @Test
    public void test_LoanCreatedSuccessfully(){
        doNothing().when(ledgerDao).saveLoan(any());
        Loan loan = loanManager.createLoan(getLoanInput());
        assertEquals(getLoan(), loan);
    }
}

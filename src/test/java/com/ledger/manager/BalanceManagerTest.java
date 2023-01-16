package com.ledger.manager;

import com.ledger.dao.LedgerDao;
import com.ledger.model.Balance;
import com.ledger.model.Loan;
import com.ledger.model.Payment;
import com.ledger.model.input.BalanceInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.ledger.MockGenerator.getBalanceInput;
import static com.ledger.MockGenerator.getLoan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class BalanceManagerTest {

    private LedgerDao ledgerDao;
    private BalanceManager balanceManager;

    @BeforeEach
    public void setup() {
        ledgerDao = Mockito.mock(LedgerDao.class);
        balanceManager = new BalanceManager(ledgerDao);
    }

    @Test
    public void test_BalanceCalculatedForNoLumpsumPaymentCorrectly() {
        Loan loan = getLoan();
        when(ledgerDao.getLoan(anyString(), anyString())).thenReturn(loan);
        Balance balance = balanceManager.getBalance(getBalanceInput());
        assertEquals(1045, balance.getAmountPaid());
        assertEquals(55, balance.getRemainingEMICount());
    }

    @Test
    public void test_BalanceCalculatedFor1PaymentCorrectly() {
        Loan loan = getLoan();
        loan.getPayments().add(getNewPayment());
        when(ledgerDao.getLoan(anyString(), anyString())).thenReturn(loan);
        Balance balance = balanceManager.getBalance(getBalanceInput());
        assertEquals(7545, balance.getAmountPaid());
        assertEquals(24, balance.getRemainingEMICount());
    }

    @Test
    public void test_BalanceCalculatedForLastEMICorrectly() {
        Loan loan = getLoan();
        loan.getPayments().add(getNewPayment());
        when(ledgerDao.getLoan(anyString(), anyString())).thenReturn(loan);
        BalanceInput balanceInput = getBalanceInput();
        balanceInput.setAfterEMI(29);
        Balance balance = balanceManager.getBalance(balanceInput);
        assertEquals(12500, balance.getAmountPaid());
        assertEquals(0, balance.getRemainingEMICount());
    }

    private Payment getNewPayment() {
        return Payment.builder().paymentDoneAfterEMI(4)
                .amount(6500)
                .build();
    }
}

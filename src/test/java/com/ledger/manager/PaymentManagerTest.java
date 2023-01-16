package com.ledger.manager;

import com.ledger.dao.LedgerDao;
import com.ledger.model.Loan;
import com.ledger.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ledger.MockGenerator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaymentManagerTest {

    private LedgerDao ledgerDao;
    private PaymentManager paymentManager;

    @BeforeEach
    public void setup(){
        ledgerDao = mock(LedgerDao.class);
        paymentManager = new PaymentManager(ledgerDao);
    }

    @Test
    public void test_paymentCreatedSuccessfully(){
        Loan loan = getLoan();
        when(ledgerDao.getLoan(anyString(), anyString())).thenReturn(loan);
        Payment payment = paymentManager.makePayment(getPaymentInput());
        assertEquals(getPayment(), payment);
        assertEquals(1, loan.getPayments().size());
    }
}

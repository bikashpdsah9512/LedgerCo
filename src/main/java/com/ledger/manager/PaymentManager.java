package com.ledger.manager;

import com.ledger.dao.LedgerDao;
import com.ledger.model.Loan;
import com.ledger.model.Payment;
import com.ledger.model.input.PaymentInput;

public class PaymentManager {

    private LedgerDao ledgerDao;

    public PaymentManager(LedgerDao ledgerDao){
        this.ledgerDao =  ledgerDao;
    }
    public Payment makePayment(PaymentInput input){
        Loan loan = ledgerDao.getLoan(input.getBankName(), input.getBorrowerName());
        Payment payment = Payment.builder()
                .amount(input.getAmount())
                .paymentDoneAfterEMI(input.getPaymentAfterEMI())
                .build();
        loan.getPayments().add(payment);
        return payment;
    }
}

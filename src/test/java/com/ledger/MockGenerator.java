package com.ledger;

import com.ledger.model.Balance;
import com.ledger.model.Loan;
import com.ledger.model.Payment;
import com.ledger.model.input.BalanceInput;
import com.ledger.model.input.LoanInput;
import com.ledger.model.input.PaymentInput;

import java.util.ArrayList;

public final class MockGenerator {

    public static BalanceInput getBalanceInput() {
        return BalanceInput.builder()
                .borrowerName("Harry")
                .bankName("MBI")
                .afterEMI(5).build();
    }

    public static Balance getBalance() {
        return Balance.builder()
                .borrowerName("Harry")
                .bankName("ABC")
                .amountPaid(2000)
                .remainingEMICount(5).build();
    }

    public static LoanInput getLoanInput() {
        return LoanInput.builder()
                .interestRate(5)
                .loanPeriodInYears(5)
                .principal(10000)
                .borrowerName("Tom")
                .bankName("IDI")
                .build();
    }

    public static Loan getLoan() {
        return Loan.builder()
                .borrowerName("Tom")
                .bankName("IDI")
                .payments(new ArrayList<>())
                .emiAmount(209)
                .totalAmount(12500)
                .remainingEMI(60)
                .build();
    }

    public static PaymentInput getPaymentInput() {
        return PaymentInput.builder()
                .borrowerName("Harry")
                .bankName("MBI")
                .paymentAfterEMI(5)
                .amount(5000)
                .build();
    }

    public static Payment getPayment() {
        return Payment.builder()
                .amount(5000)
                .paymentDoneAfterEMI(5).build();
    }
}

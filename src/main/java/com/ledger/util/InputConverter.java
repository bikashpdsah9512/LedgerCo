package com.ledger.util;

import com.ledger.model.input.BalanceInput;
import com.ledger.model.input.LoanInput;
import com.ledger.model.input.PaymentInput;

public final class InputConverter {

    private InputConverter() {
        //to restrict instantiation.
    }

    public static LoanInput getLoanInput(final String[] splitInput) {
        return LoanInput.builder()
                .bankName(splitInput[1])
                .borrowerName(splitInput[2])
                .principal(Double.parseDouble(splitInput[3]))
                .loanPeriodInYears(Integer.parseInt(splitInput[4]))
                .interestRate(Double.parseDouble(splitInput[5]))
                .build();
    }

    public static PaymentInput getPaymentInput(final String[] splitInput) {
        return PaymentInput.builder()
                .bankName(splitInput[1])
                .borrowerName(splitInput[2])
                .amount(Double.parseDouble(splitInput[3]))
                .paymentAfterEMI(Integer.parseInt(splitInput[4]))
                .build();
    }

    public static BalanceInput getBalanceInput(final String[] splitInput) {
        return BalanceInput.builder()
                .bankName(splitInput[1])
                .borrowerName(splitInput[2])
                .afterEMI(Integer.parseInt(splitInput[3]))
                .build();
    }

}

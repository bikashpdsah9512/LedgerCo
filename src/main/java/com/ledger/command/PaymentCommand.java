package com.ledger.command;

import com.ledger.manager.PaymentManager;
import com.ledger.model.Payment;
import com.ledger.model.input.PaymentInput;
import com.ledger.util.InputConverter;

/**
 * Class to handle Payment command.
 */
public class PaymentCommand implements LedgerCommand{

    private final PaymentManager paymentManager;

    public PaymentCommand(PaymentManager paymentManager){
        this.paymentManager = paymentManager;
    }

    @Override
    public void execute(String[] input) {
        PaymentInput paymentInput = InputConverter.getPaymentInput(input);
        paymentManager.makePayment(paymentInput);
    }
}

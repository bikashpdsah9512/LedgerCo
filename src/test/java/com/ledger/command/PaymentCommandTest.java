package com.ledger.command;

import com.ledger.manager.PaymentManager;
import com.ledger.model.input.PaymentInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static com.ledger.MockGenerator.getPayment;
import static com.ledger.MockGenerator.getPaymentInput;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentCommandTest {
    private PaymentManager paymentManager;

    private PaymentCommand paymentCommand;

    @BeforeEach
    public void setup() {
        this.paymentManager = Mockito.mock(PaymentManager.class);
        paymentCommand = new PaymentCommand(paymentManager);
    }

    @Test
    public void test_CommandExecutedSuccessfully() {
        ArgumentCaptor<PaymentInput> argumentCaptor = ArgumentCaptor.forClass(PaymentInput.class);
        Mockito.when(paymentManager.makePayment(Mockito.any())).thenReturn(getPayment());
        paymentCommand.execute(getInput());
        Mockito.verify(paymentManager, Mockito.times(1)).makePayment(argumentCaptor.capture());
        assertEquals(getPaymentInput(), argumentCaptor.getValue());

    }

    private String[] getInput() {
        return new String[]{"PAYMENT", "MBI", "Harry", "5000", "5"};
    }
}

package com.ledger.model.input;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Input class for Payment.
 */
@Data
@Builder
@EqualsAndHashCode
public class PaymentInput {
    private double amount;
    private int paymentAfterEMI;
    private String bankName;
    private String borrowerName;

}

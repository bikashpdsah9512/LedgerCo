package com.ledger.model;

import lombok.Builder;
import lombok.Data;


/**
 * Entity class for Payment.
 */
@Data
@Builder
public class Payment {
    private double amount;
    private int paymentDoneAfterEMI;

}

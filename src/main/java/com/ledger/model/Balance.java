package com.ledger.model;


import lombok.Builder;
import lombok.Data;


/**
 * Entity class for Balance.
 */
@Data
@Builder
public class Balance {
    private String bankName;
    private String borrowerName;
    private int amountPaid;

    private int remainingEMICount;
}

package com.ledger.model.input;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Input class for Balance.
 */
@Data
@Builder
@EqualsAndHashCode
public class BalanceInput {

    private String bankName;
    private String borrowerName;
    private int afterEMI;
}

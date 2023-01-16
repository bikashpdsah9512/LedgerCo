package com.ledger.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Entity class for Loan.
 */
@Data
@Builder
public class Loan {

    private String bankName;
    private String borrowerName;
    private double totalAmount;
    private int remainingEMI;
    private int emiAmount;
    private List<Payment> payments;
}

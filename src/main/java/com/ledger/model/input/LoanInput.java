package com.ledger.model.input;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * Input class for Loan.
 */
@Data
@Builder
@EqualsAndHashCode
public class LoanInput {
    private String bankName;
    private String borrowerName;
    private double principal;
    private int loanPeriodInYears;
    private double interestRate;
}

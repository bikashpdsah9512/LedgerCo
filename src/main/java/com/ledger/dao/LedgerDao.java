package com.ledger.dao;

import com.ledger.model.Loan;

/**
 * Dao for Loan management.
 */
public interface LedgerDao {

    /**
     * Stores the loan in the data store.
     *
     * @param loan {@link Loan}
     */
    void saveLoan(Loan loan);

    /**
     * Get the loan entity for given input.
     *
     * @param bankName     Name of the bank
     * @param borrowerName Name of the loan taker.
     * @return {@link Loan}
     */
    Loan getLoan(String bankName, String borrowerName);
}

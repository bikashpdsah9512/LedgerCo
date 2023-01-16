package com.ledger.exception;

public class LoanNotFoundException extends RuntimeException {

    public LoanNotFoundException(final String msg) {
        super(msg);
    }
}

package com.ledger.command;

/**
 * Interface for the commands used in the Ledger.
 */
public interface LedgerCommand {

    /**
     * This method takes the ledger commands in the space separated string format and executes
     * then appropriately.
     *
     * @param input an array of string.
     */
    void execute(String[] input);
}

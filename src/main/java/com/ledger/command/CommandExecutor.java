package com.ledger.command;

import com.ledger.constant.CommandType;
import com.ledger.factory.LedgerCommandFactory;


/**
 * Executor takes the input and invokes the appropriate command.
 */
public class CommandExecutor {
    private final LedgerCommandFactory ledgerCommandFactory;

    public CommandExecutor(final LedgerCommandFactory ledgerCommandFactory){
        this.ledgerCommandFactory = ledgerCommandFactory;
    }
    public void execute(final String input){
        String[] splitInput = input.split(" ");
        CommandType commandType = CommandType.valueOf(splitInput[0]);
        ledgerCommandFactory.getCommand(commandType).execute(splitInput);
    }
}

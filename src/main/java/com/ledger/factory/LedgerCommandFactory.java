package com.ledger.factory;

import com.ledger.command.LedgerCommand;
import com.ledger.constant.CommandType;

import java.util.Map;

/**
 * Factory class for the Ledger commands.
 */
public class LedgerCommandFactory {

    private final Map<CommandType, LedgerCommand> commandMap;

    public LedgerCommandFactory(final Map<CommandType, LedgerCommand> commandMap) {
        this.commandMap = commandMap;
    }

    public LedgerCommand getCommand(final CommandType commandType) {
        if (commandMap.containsKey(commandType)) {
            return commandMap.get(commandType);
        }
        throw new IllegalArgumentException("commandType not supported yet.");

    }
}

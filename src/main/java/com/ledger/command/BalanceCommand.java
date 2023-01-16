package com.ledger.command;

import com.ledger.manager.BalanceManager;
import com.ledger.model.Balance;
import com.ledger.model.input.BalanceInput;
import com.ledger.util.InputConverter;

/**
 * Class to handle Balance command
 */
public class BalanceCommand implements LedgerCommand {

    private final BalanceManager balanceManager;

    public BalanceCommand(final BalanceManager balanceManager) {
        this.balanceManager = balanceManager;
    }

    @Override
    public void execute(final String[] input) {
        BalanceInput balanceInput = InputConverter.getBalanceInput(input);
        Balance balance = balanceManager.getBalance(balanceInput);
        System.out.println(String.format("%s %s %d %d", balance.getBankName(), balance.getBorrowerName(), balance.getAmountPaid(), balance.getRemainingEMICount()));
    }
}

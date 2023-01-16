package com.ledger.command;

import com.ledger.manager.BalanceManager;
import com.ledger.model.input.BalanceInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static com.ledger.MockGenerator.getBalance;
import static com.ledger.MockGenerator.getBalanceInput;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BalanceCommandTest {

    private BalanceManager balanceManager;

    private BalanceCommand balanceCommand;

    @BeforeEach
    public void setup() {
        this.balanceManager = Mockito.mock(BalanceManager.class);
        balanceCommand = new BalanceCommand(balanceManager);
    }

    @Test
    public void test_BalanceExecuted() {
        ArgumentCaptor<BalanceInput> argumentCaptor = ArgumentCaptor.forClass(BalanceInput.class);
        Mockito.when(balanceManager.getBalance(Mockito.any())).thenReturn(getBalance());
        balanceCommand.execute(getInput());
        Mockito.verify(balanceManager, Mockito.times(1)).getBalance(argumentCaptor.capture());
        assertEquals(getBalanceInput(), argumentCaptor.getValue());

    }

    private String[] getInput() {
        return new String[]{"BALANCE", "MBI", "Harry", "5"};
    }


}

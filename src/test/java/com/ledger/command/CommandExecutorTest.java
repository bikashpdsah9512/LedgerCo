package com.ledger.command;

import com.ledger.constant.CommandType;
import com.ledger.factory.LedgerCommandFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandExecutorTest {
    private LedgerCommandFactory mockLedgerCommandFactory;
    private LoanCommand mockLoanCommand;

    private CommandExecutor commandExecutor;

    @BeforeEach
    public void setup(){
        this.mockLedgerCommandFactory = Mockito.mock(LedgerCommandFactory.class);
        this.mockLoanCommand = Mockito.mock(LoanCommand.class);
        commandExecutor = new CommandExecutor(mockLedgerCommandFactory);
    }

    @Test
    public void test_CommandExecuterExecutesSuccessfully(){
        ArgumentCaptor<CommandType> argumentCaptor = ArgumentCaptor.forClass(CommandType.class);
        Mockito.when(mockLedgerCommandFactory.getCommand(Mockito.any())).thenReturn(mockLoanCommand);
        commandExecutor.execute(getInput());
        Mockito.verify(mockLedgerCommandFactory, Mockito.times(1)).getCommand(argumentCaptor.capture());
        assertEquals(CommandType.LOAN, argumentCaptor.getValue());

    }

    private String getInput(){
        return "LOAN IDIDI Dale 5000 1 6";
    }
}

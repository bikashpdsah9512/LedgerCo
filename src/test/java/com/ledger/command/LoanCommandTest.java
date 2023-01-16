package com.ledger.command;

import com.ledger.manager.LoanManager;
import com.ledger.model.input.LoanInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static com.ledger.MockGenerator.getLoan;
import static com.ledger.MockGenerator.getLoanInput;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoanCommandTest {

    private LoanManager loanManager;

    private LoanCommand loanCommand;

    @BeforeEach
    public void setup() {
        this.loanManager = Mockito.mock(LoanManager.class);
        loanCommand = new LoanCommand(loanManager);
    }

    @Test
    public void test_CommandExecutedSuccessfully() {
        ArgumentCaptor<LoanInput> argumentCaptor = ArgumentCaptor.forClass(LoanInput.class);
        Mockito.when(loanManager.createLoan(Mockito.any())).thenReturn(getLoan());
        loanCommand.execute(getInput());
        Mockito.verify(loanManager, Mockito.times(1)).createLoan(argumentCaptor.capture());
        assertEquals(getLoanInput(), argumentCaptor.getValue());
    }

    private String[] getInput() {
        return new String[]{"LOAN", "IDI", "Tom", "10000", "5", "5"};
    }
}


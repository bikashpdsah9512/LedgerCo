package com.ledger.factory;

import com.ledger.command.LedgerCommand;
import com.ledger.command.LoanCommand;
import com.ledger.constant.CommandType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LedgerCommandFactoryTest {

    private Map<CommandType, LedgerCommand> map;
    private LedgerCommandFactory factory;

    @BeforeEach
    public void setup() {
        LoanCommand mockLoanCommand = Mockito.mock(LoanCommand.class);
        map = new HashMap<>();
        map.put(CommandType.LOAN, mockLoanCommand);
        factory = new LedgerCommandFactory(map);
    }

    @Test
    public void test_FactoryReturnActualCommand() {
        LedgerCommand command = factory.getCommand(CommandType.LOAN);
        assertTrue(command instanceof LoanCommand);
    }

    @Test
    public void testThrowsErrorForCommandNotFound() {
        assertThrows(IllegalArgumentException.class, () -> factory.getCommand(CommandType.BALANCE));
    }
}

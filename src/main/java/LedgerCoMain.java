import com.ledger.command.*;
import com.ledger.constant.CommandType;
import com.ledger.dao.LedgerDao;
import com.ledger.dao.impl.LedgerDaoImpl;
import com.ledger.factory.LedgerCommandFactory;
import com.ledger.manager.BalanceManager;
import com.ledger.manager.LoanManager;
import com.ledger.manager.PaymentManager;
import com.ledger.strategies.EMICalculator;
import com.ledger.strategies.InterestCalculator;
import com.ledger.strategies.impl.SimpleEMICalculator;
import com.ledger.strategies.impl.SimpleInterestCalculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class LedgerCoMain {
    public static void main(String[] args) {
        // dependency injection
        InterestCalculator interestCalculator = new SimpleInterestCalculator();
        EMICalculator emiCalculator = new SimpleEMICalculator();
        LedgerDao ledgerDao = new LedgerDaoImpl(new HashMap<>());
        LoanManager loanManager = new LoanManager(ledgerDao, interestCalculator, emiCalculator);
        PaymentManager paymentManager = new PaymentManager(ledgerDao);
        BalanceManager balanceManager = new BalanceManager(ledgerDao);
        Map<CommandType, LedgerCommand> commandMap = new HashMap<>();
        commandMap.put(CommandType.LOAN, new LoanCommand(loanManager));
        commandMap.put(CommandType.PAYMENT, new PaymentCommand(paymentManager));
        commandMap.put(CommandType.BALANCE, new BalanceCommand(balanceManager));

        LedgerCommandFactory commandFactory = new LedgerCommandFactory(commandMap);
        CommandExecutor commandManager = new CommandExecutor(commandFactory);


        //process input
        String inputFilePath = args[0];
        try (Stream<String> stream = Files.lines(Paths.get(inputFilePath))) {
            stream.forEach(commandManager::execute);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
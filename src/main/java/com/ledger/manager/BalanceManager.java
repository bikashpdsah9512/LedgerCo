package com.ledger.manager;

import com.ledger.dao.LedgerDao;
import com.ledger.model.Balance;
import com.ledger.model.Loan;
import com.ledger.model.Payment;
import com.ledger.model.input.BalanceInput;

import java.util.List;

/**
 * Manager class for managing loan balances.
 */
public class BalanceManager {

    private final LedgerDao ledgerDao;

    public BalanceManager(final LedgerDao ledgerDao) {
        this.ledgerDao = ledgerDao;
    }

    public Balance getBalance(final BalanceInput balanceInput) {
        Loan loan = ledgerDao.getLoan(balanceInput.getBankName(), balanceInput.getBorrowerName());
        double totalAmountPaid = getTotalAmountPaid(loan, balanceInput);
        double remainingAmount =  loan.getTotalAmount() - totalAmountPaid;
        if(remainingAmount <0){
            totalAmountPaid = loan.getTotalAmount();
            remainingAmount=0;
        }
        int remainingEMIs = (int) Math.ceil(remainingAmount / loan.getEmiAmount());
        return Balance.builder()
                .amountPaid((int)totalAmountPaid)
                .bankName(balanceInput.getBankName())
                .borrowerName(balanceInput.getBorrowerName())
                .remainingEMICount(remainingEMIs)
                .build();
    }

    private double getTotalAmountPaid(final Loan loan, final BalanceInput balanceInput){
        List<Payment> payments = loan.getPayments();
        double totalAmountPaid = 0;
        for (Payment payment : payments) {
            if (payment.getPaymentDoneAfterEMI() <= balanceInput.getAfterEMI()) {
                totalAmountPaid += payment.getAmount();
            }
        }
        totalAmountPaid += loan.getEmiAmount() * balanceInput.getAfterEMI();
        return totalAmountPaid;
    }
}

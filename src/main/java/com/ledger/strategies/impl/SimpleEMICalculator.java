package com.ledger.strategies.impl;

import com.ledger.strategies.EMICalculator;
import com.ledger.util.DateUtil;

public class SimpleEMICalculator implements EMICalculator {
    @Override
    public int calculateEMI(double amount, int periodInYears) {
        int periodInMonths = DateUtil.getNoOfMonthFromYear(periodInYears);
        return (int) Math.ceil(amount / periodInMonths);
    }
}

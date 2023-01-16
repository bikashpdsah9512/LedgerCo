package com.ledger.strategies.impl;

import com.ledger.strategies.InterestCalculator;

public class SimpleInterestCalculator implements InterestCalculator {
    @Override
    public double calculate(double principal, double rate, int durationInYears) {
        return (principal * rate * durationInYears)/100;
    }
}

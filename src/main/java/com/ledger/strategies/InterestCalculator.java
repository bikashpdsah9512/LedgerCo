package com.ledger.strategies;

public interface InterestCalculator {

    double calculate(double principal, double rate, int durationInYears);
}

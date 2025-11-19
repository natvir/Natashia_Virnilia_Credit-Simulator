package com.natashiavirnilia.creditsimulator.service.impl;

import com.natashiavirnilia.creditsimulator.model.CreditRequest;
import com.natashiavirnilia.creditsimulator.model.CreditResponse;
import com.natashiavirnilia.creditsimulator.service.strategy.CreditSimulatorStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CreditMotorImpl implements CreditSimulatorStrategy {
    private final double baseRate = 9.0;

    @Override
    public List<CreditResponse> calculate(CreditRequest req) {

        List<CreditResponse> results = new ArrayList<>();

        double principal = req.getTotalCredit() - req.getDp();

        for (int year = 1; year <= req.getTenure(); year++) {

            double rate = computeRate(year);

            double interest = principal * (rate / 100.0);
            double totalPayment = principal + interest;

            int remainingTenor = req.getTenure() - year + 1;

            double yearlyInstallment = totalPayment / remainingTenor;
            double monthlyInstallment = yearlyInstallment / 12.0;

            results.add(new CreditResponse(year, monthlyInstallment, rate));

            principal = totalPayment - yearlyInstallment;
        }

        return results;
    }

    /**
     * RULE RATE:
     *  Year 1 → base
     *  Even year → +0.1
     *  Odd year (>1) → +0.5
     */
    private double computeRate(int year) {
        double rate = baseRate;

        if (year == 1) return rate;

        for (int y = 2; y <= year; y++) {
            if (y % 2 == 0) {
                rate += 0.1;  // even
            } else {
                rate += 0.5;  // odd > 1
            }
        }
        return rate;
    }
}

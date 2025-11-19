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
public class CreditMobilImpl implements CreditSimulatorStrategy {
    private final double baseRate = 8.0;

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
     * Interest Year On Year:
     *  +0.1% every 1 year
     *  +0.5% every 2 years
     *
     * Formula:
     *  rate = base + (0.1 * (year - 1)) + (0.5 * floor((year - 1) / 2))
     */
    private double computeRate(int year) {
        double rate = baseRate;

        if (year == 1) return rate;

        for (int y = 2; y <= year; y++) {
            if (y % 2 == 0) {
                // Tahun genap → +0.1%
                rate += 0.1;
            } else {
                // Tahun ganjil > 1 → +0.5%
                rate += 0.5;
            }
        }
        return rate;
    }
}

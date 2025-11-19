package com.natashiavirnilia.creditsimulator.service;

import com.natashiavirnilia.creditsimulator.model.CreditRequest;
import org.springframework.stereotype.Service;

@Service
public class CreditValidationService {
    private final int currentYear = java.time.Year.now().getValue();

    public void validate(CreditRequest request) {

        if (request.getCondition().equalsIgnoreCase("baru")) {
            if (request.getYear() < currentYear - 1)
                throw new IllegalArgumentException("Tahun kendaraan BARU minimal " + (currentYear - 1));
        }

        if (request.getTenure() < 1 || request.getTenure() > 6)
            throw new IllegalArgumentException("Tenor harus 1-6 tahun.");

        if (request.getTotalCredit() > 1_000_000_000)
            throw new IllegalArgumentException("Maksimal pinjaman adalah 1 miliar.");

        double minDP = request.getCondition().equalsIgnoreCase("baru") ? 0.35 : 0.25;

        if (request.getDp() < request.getTotalCredit() * minDP)
            throw new IllegalArgumentException("DP minimal " + (minDP * 100) + "% dari pinjaman.");
    }
}

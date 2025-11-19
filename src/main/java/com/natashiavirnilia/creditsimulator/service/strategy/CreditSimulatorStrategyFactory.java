package com.natashiavirnilia.creditsimulator.service.strategy;

import com.natashiavirnilia.creditsimulator.service.impl.CreditMobilImpl;
import com.natashiavirnilia.creditsimulator.service.impl.CreditMotorImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreditSimulatorStrategyFactory {
    public CreditSimulatorStrategy create(String type) {
        if (type.equalsIgnoreCase("mobil")) return new CreditMobilImpl();
        if (type.equalsIgnoreCase("motor")) return new CreditMotorImpl();
        throw new IllegalArgumentException("Jenis kendaraan tidak valid.");
    }
}

package com.natashiavirnilia.creditsimulator.service.strategy;

import com.natashiavirnilia.creditsimulator.model.CreditRequest;
import com.natashiavirnilia.creditsimulator.model.CreditResponse;
import java.util.List;

public interface CreditSimulatorStrategy {
    List<CreditResponse> calculate(CreditRequest request);
}

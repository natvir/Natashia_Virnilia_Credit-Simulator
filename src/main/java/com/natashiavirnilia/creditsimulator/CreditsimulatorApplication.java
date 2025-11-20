package com.natashiavirnilia.creditsimulator;

import com.natashiavirnilia.creditsimulator.controller.CreditSimulatorController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreditsimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditsimulatorApplication.class, args);
		CreditSimulatorController controller = new CreditSimulatorController();

		if (args.length > 0) {
			String fileName = args[0];
			controller.loadFromFile(fileName);
		} else {
			controller.start();
		}
	}

}


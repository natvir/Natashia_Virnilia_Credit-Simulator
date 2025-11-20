package com.natashiavirnilia.creditsimulator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.natashiavirnilia.creditsimulator.CreditsimulatorApplication;
import com.natashiavirnilia.creditsimulator.model.CreditRequest;
import com.natashiavirnilia.creditsimulator.model.CreditResponse;
import com.natashiavirnilia.creditsimulator.service.strategy.CreditSimulatorStrategy;
import com.natashiavirnilia.creditsimulator.service.strategy.CreditSimulatorStrategyFactory;
import com.natashiavirnilia.creditsimulator.view.CommandMenu;
import com.natashiavirnilia.creditsimulator.view.SheetManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping(value = "/v1.0/credit-simulator/calculate")
public class CreditSimulatorController {
    private final Scanner scanner;
    private final SheetManager sheetManager;
    private final CreditSimulatorStrategyFactory factory;

    public CreditSimulatorController() {
        this.scanner = new Scanner(System.in);
        this.sheetManager = new SheetManager();
        this.factory = new CreditSimulatorStrategyFactory();
    }

    public void start() {
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) break;

            switch (input.toLowerCase()) {
                case "show":
                    CommandMenu.showMenu();
                    break;

                case "save sheet":
                    System.out.print("Nama sheet: ");
                    String name = scanner.nextLine().trim();
                    System.out.println("Simulasi sheet tersimpan: " + name);
                    break;

                case "switch sheet":
                    System.out.print("Nama sheet: ");
                    String switchName = scanner.nextLine().trim();
                    sheetManager.switchSheet(switchName);
                    break;

                case "load":
                    loadFromFile("file_inputs.txt");
                    break;

                case "calculate":
                    calculateInteractive();
                    break;

                default:
                    System.out.println("Perintah tidak dikenal, ketik 'show' untuk list perintah.");
            }
        }
        System.out.println("Terima kasih, aplikasi selesai.");
    }

    public void loadFromFile(String fileName) {
        try (InputStream inputStream = CreditsimulatorApplication.class
                .getClassLoader()
                .getResourceAsStream(fileName)) {

            if (inputStream == null) {
                System.out.println("File tidak ditemukan di resources: " + fileName);
                return;
            }

            String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            CreditRequest request = mapper.readValue(content, CreditRequest.class);

            CreditSimulatorStrategy calculator = factory.create(request.getVehicleType());
            List<CreditResponse> results = calculator.calculate(request);

            System.out.println("=== Hasil Perhitungan (Load File) ===");
            for (CreditResponse response : results) {
                System.out.printf("Tahun %d : Rp. %, .2f/bln, Suku Bunga: %.1f%%\n",
                        response.getYear(), response.getMonthlyInstallment(), response.getInterestRate());
            }
            sheetManager.saveSheet("Sheet_" + System.currentTimeMillis(), results);

        } catch (IOException e) {
            System.out.println("Error membaca file: " + e.getMessage());
        }
    }

    private void calculateInteractive() {
        CreditRequest request = new CreditRequest();

        System.out.print("Jenis Kendaraan (Motor/Mobil): ");
        request.setVehicleType(scanner.nextLine());

        System.out.print("Kondisi (Baru/Bekas): ");
        request.setCondition(scanner.nextLine());

        System.out.print("Tahun Kendaraan: ");
        request.setYear(Integer.parseInt(scanner.nextLine()));

        System.out.print("Total Pinjaman: ");
        request.setTotalCredit(Long.parseLong(scanner.nextLine()));

        System.out.print("Tenor (1-6 tahun): ");
        request.setTenure(Integer.parseInt(scanner.nextLine()));

        System.out.print("DP: ");
        request.setDp(Long.parseLong(scanner.nextLine()));

        CreditSimulatorStrategy calculator = factory.create(request.getVehicleType());
        List<CreditResponse> results = calculator.calculate(request);

        for (CreditResponse response : results) {
            System.out.printf("Tahun %d : Rp. %, .2f/bln, Suku Bunga: %.1f%%\n",
                    response.getYear(), response.getMonthlyInstallment(), response.getInterestRate());
        }
        sheetManager.saveSheet("Sheet_" + System.currentTimeMillis(), results);
    }
}

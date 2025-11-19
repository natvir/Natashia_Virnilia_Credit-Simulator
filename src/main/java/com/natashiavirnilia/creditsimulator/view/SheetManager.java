package com.natashiavirnilia.creditsimulator.view;
import com.natashiavirnilia.creditsimulator.model.CreditResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SheetManager {
    private Map<String, List<CreditResponse>> sheets = new HashMap<>();
    private String currentSheet = null;

    public void saveSheet(String sheetName, List<CreditResponse> results) {
        sheets.put(sheetName, results);
        currentSheet = sheetName;
        System.out.println("Sheet '" + sheetName + "' tersimpan dan aktif.");
    }

    public void switchSheet(String sheetName) {
        if (sheets.containsKey(sheetName)) {
            currentSheet = sheetName;
            System.out.println("Beralih ke sheet '" + sheetName + "'.");
            displayCurrentSheet();
        } else {
            System.out.println("Sheet '" + sheetName + "' tidak ditemukan.");
        }
    }

    public void displayCurrentSheet() {
        if (currentSheet == null) {
            System.out.println("Belum ada sheet aktif.");
            return;
        }
        System.out.println("=== SHEET: " + currentSheet + " ===");
        List<CreditResponse> results = sheets.get(currentSheet);
        for (CreditResponse r : results) {
            System.out.printf("Tahun %d : Rp. %, .2f/bln, Suku Bunga: %.1f%%\n",
                    r.getYear(), r.getMonthlyInstallment(), r.getInterestRate());
        }
    }
}

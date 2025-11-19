package com.natashiavirnilia.creditsimulator.model;

import lombok.Builder;

@Builder
public class CreditResponse {
    private int year;
    private double monthlyInstallment;
    private double interestRate;

    public CreditResponse(int year, double monthlyInstallment, double interestRate) {
        this.year = year;
        this.monthlyInstallment = monthlyInstallment;
        this.interestRate = interestRate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getMonthlyInstallment() {
        return monthlyInstallment;
    }

    public void setMonthlyInstallment(double monthlyInstallment) {
        this.monthlyInstallment = monthlyInstallment;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}

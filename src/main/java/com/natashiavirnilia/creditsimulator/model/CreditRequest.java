package com.natashiavirnilia.creditsimulator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditRequest {
    private String vehicleType;
    private String condition;
    private int year;
    private long totalCredit;
    private int tenure;
    private long dp;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(long totalCredit) {
        this.totalCredit = totalCredit;
    }

    public int getTenure() {
        return tenure;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }

    public long getDp() {
        return dp;
    }

    public void setDp(long dp) {
        this.dp = dp;
    }
}

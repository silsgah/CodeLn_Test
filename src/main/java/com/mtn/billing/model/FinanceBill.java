package com.mtn.billing.model;

public class FinanceBill {
    private Integer employeeId;

    public FinanceBill() {
    }

    public FinanceBill(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}

package com.mtn.billing.model;

public class CustomerResult {
    public Integer customerValue;
    public Integer valueValidity;

    public Integer getCustomerValue() {
        return customerValue;
    }

    public void setCustomerValue(Integer customerValue) {
        this.customerValue = customerValue;
    }

    public Integer getValueValidity() {
        return valueValidity;
    }

    public void setValueValidity(Integer valueValidity) {
        this.valueValidity = valueValidity;
    }

    public CustomerResult() {
    }

    public CustomerResult(Integer customerValue, Integer valueValidity) {
        this.customerValue = customerValue;
        this.valueValidity = valueValidity;
    }
}

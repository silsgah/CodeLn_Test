package com.mtn.billing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="customer")
@JsonIgnoreProperties({ "voucherValue", "redeemed" })
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Customer Id is mandatory")
    @Column(name="customer_id")
    private String customerId;

    @NotNull(message = "Customer Name is mandatory")
    @Column(name="customer_first_name")
    private String customerFirstName;

    @NotNull(message = "Customer order value is mandatory")
    @Column(name="customer_order_value")
    private Integer customerOrderValue;

    @Column(name="voucher_value")
    private Integer voucherValue;

    @Column(name="redeemed")
    private Boolean redeemed;

    public Customer() {
    }

    public Customer(long id, @NotNull(message = "Customer Id is mandatory") String customerId, @NotNull(message = "Customer Name is mandatory") String customerFirstName, @NotNull(message = "Customer order value is mandatory") Integer customerOrderValue) {
        this.id = id;
        this.customerId = customerId;
        this.customerFirstName = customerFirstName;
        this.customerOrderValue = customerOrderValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public Integer getCustomerOrderValue() {
        return customerOrderValue;
    }

    public void setCustomerOrderValue(Integer customerOrderValue) {
        this.customerOrderValue = customerOrderValue;
    }

    public Integer getVoucherValue() {
        return voucherValue;
    }

    public void setVoucherValue(Integer voucherValue) {
        this.voucherValue = voucherValue;
    }

    public Boolean getRedeemed() {
        return redeemed;
    }

    public void setRedeemed(Boolean redeemed) {
        this.redeemed = redeemed;
    }
}

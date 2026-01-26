package com.williamsilva.algashop.billing.application.creditcard.query;

import java.util.UUID;

public class CreditCardOutput {

    private UUID id;
    private String lastNumbers;
    private Integer expMonth;
    private Integer expYear;
    private String brand;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLastNumbers() {
        return lastNumbers;
    }

    public void setLastNumbers(String lastNumbers) {
        this.lastNumbers = lastNumbers;
    }

    public Integer getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    public Integer getExpYear() {
        return expYear;
    }

    public void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

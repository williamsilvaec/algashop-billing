package com.williamsilva.algashop.billing.domain.model.creditcard;

public class LimitedCreditCard {

    private String gatewayCode;
    private String lastNumbers;
    private String brand;
    private Integer expMonth;
    private Integer expYear;

    public String getGatewayCode() {
        return gatewayCode;
    }

    public void setGatewayCode(String gatewayCode) {
        this.gatewayCode = gatewayCode;
    }

    public String getLastNumbers() {
        return lastNumbers;
    }

    public void setLastNumbers(String lastNumbers) {
        this.lastNumbers = lastNumbers;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public static LimitedCreditCard builder() {
        return new LimitedCreditCard();
    }

    public LimitedCreditCard gatewayCode(String gatewayCode) {
        this.gatewayCode = gatewayCode;
        return this;
    }

    public LimitedCreditCard lastNumbers(String lastNumbers) {
        this.lastNumbers = lastNumbers;
        return this;
    }

    public LimitedCreditCard brand(String brand) {
        this.brand = brand;
        return this;
    }

    public LimitedCreditCard expMonth(Integer expMonth) {
        this.expMonth = expMonth;
        return this;
    }

    public LimitedCreditCard expYear(Integer expYear) {
        this.expYear = expYear;
        return this;
    }

    public LimitedCreditCard build() {
        return this;
    }
}

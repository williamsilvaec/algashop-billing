package com.williamsilva.algashop.billing.infrastructure.creditcard.fastpay;

public class FastpayCreditCardInput {

    private String tokenizedCard;
    private String customerCode;

    public String getTokenizedCard() {
        return tokenizedCard;
    }

    public void setTokenizedCard(String tokenizedCard) {
        this.tokenizedCard = tokenizedCard;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public static FastpayCreditCardInput builder() {
        return new FastpayCreditCardInput();
    }

    public FastpayCreditCardInput tokenizedCard(String tokenizedCard) {
        this.tokenizedCard = tokenizedCard;
        return this;
    }

    public FastpayCreditCardInput customerCode(String customerCode) {
        this.customerCode = customerCode;
        return this;
    }

    public FastpayCreditCardInput build() {
        return this;
    }
}

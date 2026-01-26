package com.williamsilva.algashop.billing.application.creditcard.management;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class TokenizedCreditCardInput {

    private UUID customerId;

    @NotBlank
    private String tokenizedCard;

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getTokenizedCard() {
        return tokenizedCard;
    }

    public void setTokenizedCard(String tokenizedCard) {
        this.tokenizedCard = tokenizedCard;
    }
}

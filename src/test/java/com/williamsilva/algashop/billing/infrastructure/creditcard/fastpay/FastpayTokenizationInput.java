package com.williamsilva.algashop.billing.infrastructure.creditcard.fastpay;

public record FastpayTokenizationInput(
        String number,
        String cvv,
        String holderName,
        String holderDocument,
        Integer expMonth,
        Integer expYear
) { }

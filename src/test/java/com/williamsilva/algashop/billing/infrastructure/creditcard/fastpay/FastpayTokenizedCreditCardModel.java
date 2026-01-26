package com.williamsilva.algashop.billing.infrastructure.creditcard.fastpay;

import java.time.OffsetDateTime;

public record FastpayTokenizedCreditCardModel(String tokenizedCard, OffsetDateTime expiresAt) {
}

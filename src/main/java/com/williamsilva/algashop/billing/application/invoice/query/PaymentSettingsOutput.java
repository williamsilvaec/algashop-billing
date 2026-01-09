package com.williamsilva.algashop.billing.application.invoice.query;

import com.williamsilva.algashop.billing.domain.model.invoice.PaymentMethod;

import java.util.UUID;

public class PaymentSettingsOutput {

    private UUID id;
    private UUID creditCardId;
    private PaymentMethod method;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(UUID creditCardId) {
        this.creditCardId = creditCardId;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }
}

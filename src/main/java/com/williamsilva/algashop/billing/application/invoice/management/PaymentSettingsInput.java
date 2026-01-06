package com.williamsilva.algashop.billing.application.invoice.management;

import com.williamsilva.algashop.billing.domain.model.invoice.PaymentMethod;

import java.util.UUID;

public record PaymentSettingsInput(
        PaymentMethod method,
        UUID creditCardId
) {

    public static PaymentSettingsInputBuilder builder() {
        return new PaymentSettingsInputBuilder();
    }

    public static class PaymentSettingsInputBuilder {
        private PaymentMethod method;
        private UUID creditCardId;

        public PaymentSettingsInputBuilder method(PaymentMethod method) {
            this.method = method;
            return this;
        }

        public PaymentSettingsInputBuilder creditCardId(UUID creditCardId) {
            this.creditCardId = creditCardId;
            return this;
        }

        public PaymentSettingsInput build() {
            return new PaymentSettingsInput(method, creditCardId);
        }
    }
}

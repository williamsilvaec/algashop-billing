package com.williamsilva.algashop.billing.domain.model.invoice.payment;

import com.williamsilva.algashop.billing.domain.model.FieldValidations;
import com.williamsilva.algashop.billing.domain.model.invoice.PaymentMethod;

import java.util.Objects;
import java.util.UUID;

public class Payment {

    private String gatewayCode;
    private UUID invoiceId;
    private PaymentMethod method;
    private PaymentStatus status;

    public Payment(String gatewayCode, UUID invoiceId, PaymentMethod method, PaymentStatus status) {
        FieldValidations.requiresNonBlank(gatewayCode);
        Objects.requireNonNull(invoiceId);
        Objects.requireNonNull(method);
        Objects.requireNonNull(status);
        this.gatewayCode = gatewayCode;
        this.invoiceId = invoiceId;
        this.method = method;
        this.status = status;
    }

    public String getGatewayCode() {
        return gatewayCode;
    }

    public UUID getInvoiceId() {
        return invoiceId;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Payment payment)) {
            return false;
        }

        return Objects.equals(gatewayCode, payment.gatewayCode)
                && Objects.equals(invoiceId, payment.invoiceId)
                && method == payment.method
                && status == payment.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gatewayCode, invoiceId, method, status);
    }

    public static PaymentBuilder builder() {
        return new PaymentBuilder();
    }

    public static class PaymentBuilder {
        private String gatewayCode;
        private UUID invoiceId;
        private PaymentMethod method;
        private PaymentStatus status;

        PaymentBuilder() {}

        public PaymentBuilder gatewayCode(String gatewayCode) {
            this.gatewayCode = gatewayCode;
            return this;
        }

        public PaymentBuilder invoiceId(UUID invoiceId) {
            this.invoiceId = invoiceId;
            return this;
        }

        public PaymentBuilder method(PaymentMethod method) {
            this.method = method;
            return this;
        }

        public PaymentBuilder status(PaymentStatus status) {
            this.status = status;
            return this;
        }

        public Payment build() {
            return new Payment(gatewayCode, invoiceId, method, status);
        }
    }
}

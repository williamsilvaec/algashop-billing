package com.williamsilva.algashop.billing.domain.model.invoice.payment;

import com.williamsilva.algashop.billing.domain.model.invoice.Payer;
import com.williamsilva.algashop.billing.domain.model.invoice.PaymentMethod;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class PaymentRequest {

    private PaymentMethod method;
    private BigDecimal amount;
    private UUID invoiceId;
    private UUID creditCardId;
    private Payer payer;

    public PaymentRequest(PaymentMethod method,
                          BigDecimal amount, UUID invoiceId,
                          UUID creditCardId, Payer payer) {
        Objects.requireNonNull(method);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(invoiceId);
        Objects.requireNonNull(payer);

        if (method.equals(PaymentMethod.CREDIT_CARD)) {
            Objects.requireNonNull(creditCardId);
        }

        this.method = method;
        this.amount = amount;
        this.invoiceId = invoiceId;
        this.creditCardId = creditCardId;
        this.payer = payer;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public UUID getInvoiceId() {
        return invoiceId;
    }

    public UUID getCreditCardId() {
        return creditCardId;
    }

    public Payer getPayer() {
        return payer;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaymentRequest that = (PaymentRequest) o;
        return getMethod() == that.getMethod()
                && Objects.equals(getAmount(), that.getAmount())
                && Objects.equals(getInvoiceId(), that.getInvoiceId())
                && Objects.equals(getCreditCardId(), that.getCreditCardId())
                && Objects.equals(getPayer(), that.getPayer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMethod(), getAmount(), getInvoiceId(), getCreditCardId(), getPayer());
    }

    public static PaymentRequestBuilder builder() {
        return new PaymentRequestBuilder();
    }

    public static class PaymentRequestBuilder {
        private PaymentMethod method;
        private BigDecimal amount;
        private UUID invoiceId;
        private UUID creditCardId;
        private Payer payer;

        PaymentRequestBuilder() {}

        public PaymentRequestBuilder method(PaymentMethod method) {
            this.method = method;
            return this;
        }

        public PaymentRequestBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public PaymentRequestBuilder invoiceId(UUID invoiceId) {
            this.invoiceId = invoiceId;
            return this;
        }

        public PaymentRequestBuilder creditCardId(UUID creditCardId) {
            this.creditCardId = creditCardId;
            return this;
        }

        public PaymentRequestBuilder payer(Payer payer) {
            this.payer = payer;
            return this;
        }

        public PaymentRequest build() {
            return new PaymentRequest(method, amount, invoiceId, creditCardId, payer);
        }
    }
}

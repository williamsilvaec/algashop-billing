package com.williamsilva.algashop.billing.application.invoice.query;

import com.williamsilva.algashop.billing.application.invoice.management.PayerData;
import com.williamsilva.algashop.billing.domain.model.invoice.InvoiceStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class InvoiceOutput {

    private UUID id;
    private String orderId;
    private UUID customerId;
    private OffsetDateTime issuedAt;
    private OffsetDateTime paidAt;
    private OffsetDateTime canceledAt;
    private OffsetDateTime expiresAt;
    private BigDecimal totalAmount;
    private InvoiceStatus status;
    private PayerData payer;
    private PaymentSettingsOutput paymentSettings;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public OffsetDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(OffsetDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public OffsetDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(OffsetDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public OffsetDateTime getCanceledAt() {
        return canceledAt;
    }

    public void setCanceledAt(OffsetDateTime canceledAt) {
        this.canceledAt = canceledAt;
    }

    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public PayerData getPayer() {
        return payer;
    }

    public void setPayer(PayerData payer) {
        this.payer = payer;
    }

    public PaymentSettingsOutput getPaymentSettings() {
        return paymentSettings;
    }

    public void setPaymentSettings(PaymentSettingsOutput paymentSettings) {
        this.paymentSettings = paymentSettings;
    }
}

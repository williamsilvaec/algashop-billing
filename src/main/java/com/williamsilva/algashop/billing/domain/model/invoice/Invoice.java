package com.williamsilva.algashop.billing.domain.model.invoice;

import com.williamsilva.algashop.billing.domain.model.AbstractAuditableAggregateRoot;
import com.williamsilva.algashop.billing.domain.model.DomainException;
import com.williamsilva.algashop.billing.domain.model.IdGenerator;
import com.williamsilva.algashop.billing.domain.model.invoice.payment.PaymentStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class Invoice extends AbstractAuditableAggregateRoot<Invoice> {

    @Id
    private UUID id;
    private String orderId;
    private UUID customerId;
    private OffsetDateTime issuedAt;
    private OffsetDateTime paidAt;
    private OffsetDateTime canceledAt;
    private OffsetDateTime expiresAt;
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private PaymentSettings paymentSettings;

    @Embedded
    private Payer payer;

    private String cancelReason;

    @ElementCollection
    @CollectionTable(name = "invoice_line_item", joinColumns = @JoinColumn(name = "invoice_id"))
    private Set<LineItem> items = new HashSet<>();

    protected Invoice() {
    }

    protected Invoice(UUID id, String orderId, UUID customerId, OffsetDateTime issuedAt,
                      OffsetDateTime paidAt, OffsetDateTime canceledAt, OffsetDateTime expiresAt,
                      BigDecimal totalAmount, InvoiceStatus status, PaymentSettings paymentSettings,
                      Set<LineItem> items, Payer payer, String cancelReason) {
        this.id = id;
        this.orderId = orderId;
        this.customerId = customerId;
        this.issuedAt = issuedAt;
        this.paidAt = paidAt;
        this.canceledAt = canceledAt;
        this.expiresAt = expiresAt;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentSettings = paymentSettings;
        this.items = items != null ? items : new HashSet<>();
        this.payer = payer;
        this.cancelReason = cancelReason;
    }

    public static Invoice issue(String orderId,
                                UUID customerId,
                                Payer payer,
                                Set<LineItem> items) {
        Objects.requireNonNull(customerId);
        Objects.requireNonNull(payer);
        Objects.requireNonNull(items);

        if (StringUtils.isBlank(orderId)) {
            throw new IllegalArgumentException();
        }

        if (items.isEmpty()) {
            throw new IllegalArgumentException();
        }

        BigDecimal totalAmount = items.stream().map(LineItem::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Invoice invoice = new Invoice(
                IdGenerator.generateTimeBasedUUID(),
                orderId,
                customerId,
                OffsetDateTime.now(),
                null,
                null,
                OffsetDateTime.now().plusDays(3),
                totalAmount,
                InvoiceStatus.UNPAID,
                null,
                items,
                payer,
                null
        );

        invoice.registerEvent(new InvoiceIssuedEvent(invoice.getId(),
                invoice.getCustomerId(), invoice.getOrderId(), invoice.getIssuedAt()));

        return invoice;
    }

    public boolean isCanceled() {
        return InvoiceStatus.CANCELED.equals(this.getStatus());
    }

    public boolean isUnpaid() {
        return InvoiceStatus.UNPAID.equals(this.getStatus());
    }

    public boolean isPaid() {
        return InvoiceStatus.PAID.equals(this.getStatus());
    }

    public void markAsPaid() {
        if (!isUnpaid()) {
            throw new DomainException(String.format("Invoice %s with status %s cannot be marked as paid",
                    this.getId(), this.getStatus().toString().toLowerCase()));
        }
        setPaidAt(OffsetDateTime.now());
        setStatus(InvoiceStatus.PAID);
        setExpiresAt(null);
        registerEvent(new InvoicePaidEvent(this.getId(), this.getCustomerId(), this.getOrderId(), this.getPaidAt()));
    }

    public void cancel(String cancelReason) {
        if (isCanceled()) {
            throw new DomainException(String.format("Invoice %s is already canceled", this.getId()));
        }
        setCancelReason(cancelReason);
        setCanceledAt(OffsetDateTime.now());
        setStatus(InvoiceStatus.CANCELED);
        setExpiresAt(null);
        registerEvent(new InvoiceCanceledEvent(this.getId(), this.getCustomerId(), this.getOrderId(), this.getCanceledAt()));
    }

    public void assignPaymentGatewayCode(String code) {
        if (!isUnpaid()) {
            throw new DomainException(String.format("Invoice %s with status %s cannot be edited",
                    this.getId(), this.getStatus().toString().toLowerCase()));
        }
        this.getPaymentSettings().assignGatewayCode(code);
    }

    public void changePaymentSettings(PaymentMethod method, UUID creditCardId) {
        if (!isUnpaid()) {
            throw new DomainException(String.format("Invoice %s with status %s cannot be edited",
                    this.getId(), this.getStatus().toString().toLowerCase()));
        }
        PaymentSettings paymentSettings = PaymentSettings.brandNew(method, creditCardId);
        paymentSettings.setInvoice(this);
        this.setPaymentSettings(paymentSettings);
    }

    public void updatePaymentStatus(PaymentStatus status) {
        switch (status) {
            case FAILED -> cancel("Payment failed");
            case REFUNDED -> cancel("Payment refunded");
            case PAID -> markAsPaid();
        }
    }


    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    private void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    private void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public OffsetDateTime getIssuedAt() {
        return issuedAt;
    }

    private void setIssuedAt(OffsetDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public OffsetDateTime getPaidAt() {
        return paidAt;
    }

    private void setPaidAt(OffsetDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public OffsetDateTime getCanceledAt() {
        return canceledAt;
    }

    private void setCanceledAt(OffsetDateTime canceledAt) {
        this.canceledAt = canceledAt;
    }

    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }

    private void setExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    private void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    private void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public PaymentSettings getPaymentSettings() {
        return paymentSettings;
    }

    private void setPaymentSettings(PaymentSettings paymentSettings) {
        this.paymentSettings = paymentSettings;
    }

    public Set<LineItem> getItems() {
        return Collections.unmodifiableSet(this.items);
    }

    private void setItems(Set<LineItem> items) {
        this.items = items;
    }

    public Payer getPayer() {
        return payer;
    }

    private void setPayer(Payer payer) {
        this.payer = payer;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    private void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
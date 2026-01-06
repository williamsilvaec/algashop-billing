package com.williamsilva.algashop.billing.application.invoice.management;

import java.util.Set;
import java.util.UUID;

public class GenerateInvoiceInput {

    private String orderId;
    private UUID customerId;
    private PaymentSettingsInput paymentSettings;
    private PayerData payer;
    private Set<LineItemInput> items;

    public GenerateInvoiceInput() {
    }

    private GenerateInvoiceInput(
            String orderId,
            UUID customerId,
            PaymentSettingsInput paymentSettings,
            PayerData payer,
            Set<LineItemInput> items
    ) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.paymentSettings = paymentSettings;
        this.payer = payer;
        this.items = items;
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

    public PaymentSettingsInput getPaymentSettings() {
        return paymentSettings;
    }

    public void setPaymentSettings(PaymentSettingsInput paymentSettings) {
        this.paymentSettings = paymentSettings;
    }

    public PayerData getPayer() {
        return payer;
    }

    public void setPayer(PayerData payer) {
        this.payer = payer;
    }

    public Set<LineItemInput> getItems() {
        return items;
    }

    public void setItems(Set<LineItemInput> items) {
        this.items = items;
    }

    public static GenerateInvoiceInputBuilder builder() {
        return new GenerateInvoiceInputBuilder();
    }

    public static class GenerateInvoiceInputBuilder {
        private String orderId;
        private UUID customerId;
        private PaymentSettingsInput paymentSettings;
        private PayerData payer;
        private Set<LineItemInput> items;

        public GenerateInvoiceInputBuilder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public GenerateInvoiceInputBuilder customerId(UUID customerId) {
            this.customerId = customerId;
            return this;
        }

        public GenerateInvoiceInputBuilder paymentSettings(PaymentSettingsInput paymentSettings) {
            this.paymentSettings = paymentSettings;
            return this;
        }

        public GenerateInvoiceInputBuilder payer(PayerData payer) {
            this.payer = payer;
            return this;
        }

        public GenerateInvoiceInputBuilder items(Set<LineItemInput> items) {
            this.items = items;
            return this;
        }

        public GenerateInvoiceInput build() {
            return new GenerateInvoiceInput(orderId, customerId, paymentSettings, payer, items);
        }
    }
}

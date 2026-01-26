package com.williamsilva.algashop.billing.infrastructure.payment.fastpay;

import java.math.BigDecimal;

public class FastpayPaymentInput {

    private final String referenceCode;
    private final BigDecimal totalAmount;
    private final String method;
    private final String creditCardId;
    private final String fullName;
    private final String document;
    private final String phone;
    private final String addressLine1;
    private final String addressLine2;
    private final String zipCode;
    private final String replyToUrl;

    private FastpayPaymentInput(Builder builder) {
        this.referenceCode = builder.referenceCode;
        this.totalAmount = builder.totalAmount;
        this.method = builder.method;
        this.creditCardId = builder.creditCardId;
        this.fullName = builder.fullName;
        this.document = builder.document;
        this.phone = builder.phone;
        this.addressLine1 = builder.addressLine1;
        this.addressLine2 = builder.addressLine2;
        this.zipCode = builder.zipCode;
        this.replyToUrl = builder.replyToUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getMethod() {
        return method;
    }

    public String getCreditCardId() {
        return creditCardId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDocument() {
        return document;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getReplyToUrl() {
        return replyToUrl;
    }

    public static final class Builder {
        private String referenceCode;
        private BigDecimal totalAmount;
        private String method;
        private String creditCardId;
        private String fullName;
        private String document;
        private String phone;
        private String addressLine1;
        private String addressLine2;
        private String zipCode;
        private String replyToUrl;

        private Builder() {
        }

        public Builder referenceCode(String referenceCode) {
            this.referenceCode = referenceCode;
            return this;
        }

        public Builder totalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder creditCardId(String creditCardId) {
            this.creditCardId = creditCardId;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder document(String document) {
            this.document = document;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder addressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public Builder addressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public Builder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder replyToUrl(String replyToUrl) {
            this.replyToUrl = replyToUrl;
            return this;
        }

        public FastpayPaymentInput build() {
            return new FastpayPaymentInput(this);
        }
    }
}
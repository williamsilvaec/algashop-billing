package com.williamsilva.algashop.billing.domain.model.invoice;

import com.williamsilva.algashop.billing.domain.model.FieldValidations;

import java.util.Objects;

public class Payer {

    private String fullName;
    private String document;
    private String phone;
    private String email;
    private Address address;

    protected Payer() {
    }

    public Payer(String fullName, String document, String phone, String email, Address address) {
        FieldValidations.requiresNonBlank(fullName);
        FieldValidations.requiresNonBlank(document);
        FieldValidations.requiresNonBlank(phone);
        FieldValidations.requiresValidEmail(email);
        Objects.requireNonNull(address);
        this.fullName = fullName;
        this.document = document;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    private void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDocument() {
        return document;
    }

    private void setDocument(String document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    private void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payer payer = (Payer) o;
        return Objects.equals(fullName, payer.fullName) &&
                Objects.equals(document, payer.document) &&
                Objects.equals(phone, payer.phone) &&
                Objects.equals(email, payer.email) &&
                Objects.equals(address, payer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, document, phone, email, address);
    }

    public static PayerBuilder builder() {
        return new PayerBuilder();
    }

    public static class PayerBuilder {
        private String fullName;
        private String document;
        private String phone;
        private String email;
        private Address address;

        PayerBuilder() {
        }

        public PayerBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public PayerBuilder document(String document) {
            this.document = document;
            return this;
        }

        public PayerBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public PayerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public PayerBuilder address(Address address) {
            this.address = address;
            return this;
        }

        public Payer build() {
            return new Payer(fullName, document, phone, email, address);
        }
    }
}
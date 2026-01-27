package com.williamsilva.algashop.billing.application.invoice.management;

public class PayerData {

    private String fullName;
    private String document;
    private String email;
    private String phone;
    private AddressData address;

    public PayerData() {
    }

    private PayerData(PayerDataBuilder builder) {
        this.fullName = builder.fullName;
        this.document = builder.document;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressData getAddress() {
        return address;
    }

    public void setAddress(AddressData address) {
        this.address = address;
    }

    public static PayerDataBuilder builder() {
        return new PayerDataBuilder();
    }

    public static class PayerDataBuilder {
        private String fullName;
        private String document;
        private String email;
        private String phone;
        private AddressData address;

        public PayerDataBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public PayerDataBuilder document(String document) {
            this.document = document;
            return this;
        }

        public PayerDataBuilder email(String email) {
            this.email = email;
            return this;
        }

        public PayerDataBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public PayerDataBuilder address(AddressData address) {
            this.address = address;
            return this;
        }

        public PayerData build() {
            return new PayerData(this);
        }
    }
}

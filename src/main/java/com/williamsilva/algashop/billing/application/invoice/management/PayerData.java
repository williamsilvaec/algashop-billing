package com.williamsilva.algashop.billing.application.invoice.management;

public record PayerData(
        String fullName,
        String document,
        String email,
        String phone,
        AddressData address
) {

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
            return new PayerData(fullName, document, email, phone, address);
        }
    }
}

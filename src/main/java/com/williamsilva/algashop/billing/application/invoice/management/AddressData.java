package com.williamsilva.algashop.billing.application.invoice.management;

public record AddressData(
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String zipCode
) {

    public static AddressDataBuilder builder() {
        return new AddressDataBuilder();
    }

    public static class AddressDataBuilder {
        private String street;
        private String number;
        private String complement;
        private String neighborhood;
        private String city;
        private String state;
        private String zipCode;

        public AddressDataBuilder street(String street) {
            this.street = street;
            return this;
        }

        public AddressDataBuilder number(String number) {
            this.number = number;
            return this;
        }

        public AddressDataBuilder complement(String complement) {
            this.complement = complement;
            return this;
        }

        public AddressDataBuilder neighborhood(String neighborhood) {
            this.neighborhood = neighborhood;
            return this;
        }

        public AddressDataBuilder city(String city) {
            this.city = city;
            return this;
        }

        public AddressDataBuilder state(String state) {
            this.state = state;
            return this;
        }

        public AddressDataBuilder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public AddressData build() {
            return new AddressData(street, number, complement, neighborhood, city, state, zipCode);
        }
    }

}

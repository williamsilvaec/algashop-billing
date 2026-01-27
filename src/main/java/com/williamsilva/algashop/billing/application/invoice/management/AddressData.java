package com.williamsilva.algashop.billing.application.invoice.management;

public class AddressData {

    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;

    public AddressData() {
    }

    private AddressData (AddressDataBuilder builder) {
        this.street = builder.street;
        this.number = builder.number;
        this.complement = builder.complement;
        this.neighborhood = builder.neighborhood;
        this.city = builder.city;
        this.state = builder.state;
        this.zipCode = builder.zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

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
            return new AddressData(this);
        }
    }
}

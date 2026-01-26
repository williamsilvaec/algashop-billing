package com.williamsilva.algashop.billing.infrastructure.creditcard.fastpay;

public class FastpayCreditCardResponse {

    private String id;
    private String lastNumbers;
    private Integer expMonth;
    private Integer expYear;
    private String brand;

    public FastpayCreditCardResponse() {}

    public FastpayCreditCardResponse(String id, String lastNumbers, Integer expMonth, Integer expYear, String brand) {
        this.id = id;
        this.lastNumbers = lastNumbers;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastNumbers() {
        return lastNumbers;
    }

    public void setLastNumbers(String lastNumbers) {
        this.lastNumbers = lastNumbers;
    }

    public Integer getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    public Integer getExpYear() {
        return expYear;
    }

    public void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public static FastpayCreditCardResponseBuilder builder() {
        return new FastpayCreditCardResponseBuilder();
    }

    public static class FastpayCreditCardResponseBuilder {
        private String id;
        private String lastNumbers;
        private Integer expMonth;
        private Integer expYear;
        private String brand;

        private FastpayCreditCardResponseBuilder() {
        }

        public FastpayCreditCardResponseBuilder id(String id) {
            this.id = id;
            return this;
        }

        public FastpayCreditCardResponseBuilder lastNumbers(String lastNumbers) {
            this.lastNumbers = lastNumbers;
            return this;
        }

        public FastpayCreditCardResponseBuilder expMonth(Integer expMonth) {
            this.expMonth = expMonth;
            return this;
        }

        public FastpayCreditCardResponseBuilder expYear(Integer expYear) {
            this.expYear = expYear;
            return this;
        }

        public FastpayCreditCardResponseBuilder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public FastpayCreditCardResponse build() {
            return new FastpayCreditCardResponse(id, lastNumbers, expMonth, expYear, brand);
        }
    }
}

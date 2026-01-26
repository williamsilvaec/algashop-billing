package com.williamsilva.algashop.billing.infrastructure.creditcard.fastpay;

public record FastpayTokenizationInput(
        String number,
        String cvv,
        String holderName,
        String holderDocument,
        Integer expMonth,
        Integer expYear
) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String number;
        private String cvv;
        private String holderName;
        private String holderDocument;
        private Integer expMonth;
        private Integer expYear;

        private Builder() {
        }

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        public Builder cvv(String cvv) {
            this.cvv = cvv;
            return this;
        }

        public Builder holderName(String holderName) {
            this.holderName = holderName;
            return this;
        }

        public Builder holderDocument(String holderDocument) {
            this.holderDocument = holderDocument;
            return this;
        }

        public Builder expMonth(Integer expMonth) {
            this.expMonth = expMonth;
            return this;
        }

        public Builder expYear(Integer expYear) {
            this.expYear = expYear;
            return this;
        }

        public FastpayTokenizationInput build() {
            return new FastpayTokenizationInput(
                    number,
                    cvv,
                    holderName,
                    holderDocument,
                    expMonth,
                    expYear
            );
        }
    }
}

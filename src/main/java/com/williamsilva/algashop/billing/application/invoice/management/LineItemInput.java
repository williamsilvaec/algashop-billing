package com.williamsilva.algashop.billing.application.invoice.management;

import java.math.BigDecimal;

public record LineItemInput(
        String name,
        BigDecimal amount,
        Integer quantity
) {

    public static LineItemInputBuilder builder() {
        return new LineItemInputBuilder();
    }

    public static class LineItemInputBuilder {
        private String name;
        private BigDecimal amount;
        private Integer quantity;

        public LineItemInputBuilder name(String name) {
            this.name = name;
            return this;
        }

        public LineItemInputBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public LineItemInputBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public LineItemInput build() {
            return new LineItemInput(name, amount, quantity);
        }
    }
}

package com.williamsilva.algashop.billing.domain.model.invoice;

import com.williamsilva.algashop.billing.domain.model.FieldValidations;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class LineItem {

    private Integer number;
    private String name;
    private BigDecimal amount;

    protected LineItem() {
    }

    public LineItem(Integer number, String name, BigDecimal amount) {
        FieldValidations.requiresNonBlank(name);
        Objects.requireNonNull(number);
        Objects.requireNonNull(amount);

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException();
        }

        if (number <= 0) {
            throw new IllegalArgumentException();
        }

        this.number = number;
        this.name = name;
        this.amount = amount;
    }

    public Integer getNumber() {
        return number;
    }

    private void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return Objects.equals(number, lineItem.number) &&
                Objects.equals(name, lineItem.name) &&
                Objects.equals(amount, lineItem.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, amount);
    }

    public static LineItemBuilder builder() {
        return new LineItemBuilder();
    }

    public static class LineItemBuilder {
        private Integer number;
        private String name;
        private BigDecimal amount;

        LineItemBuilder() {
        }

        public LineItemBuilder number(Integer number) {
            this.number = number;
            return this;
        }

        public LineItemBuilder name(String name) {
            this.name = name;
            return this;
        }

        public LineItemBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public LineItem build() {
            return new LineItem(number, name, amount);
        }
    }
}
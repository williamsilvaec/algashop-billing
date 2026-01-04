package com.williamsilva.algashop.billing.domain.model.creditcard;

import com.williamsilva.algashop.billing.domain.model.IdGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.apache.commons.lang3.StringUtils;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class CreditCard {

    @Id
    private UUID id;
    private OffsetDateTime createdAt;
    private UUID customerId;
    private String lastNumbers;
    private String brand;
    private Integer expMonth;
    private Integer expYear;
    private String gatewayCode;

    protected CreditCard() {
    }

    protected CreditCard(UUID id, OffsetDateTime createdAt, UUID customerId, String lastNumbers,
                         String brand, Integer expMonth, Integer expYear, String gatewayCode) {
        this.id = id;
        this.createdAt = createdAt;
        this.customerId = customerId;
        this.lastNumbers = lastNumbers;
        this.brand = brand;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.gatewayCode = gatewayCode;
    }

    public static CreditCard brandNew(UUID customerId,
                                      String lastNumbers,
                                      String brand,
                                      Integer expMonth,
                                      Integer expYear,
                                      String gatewayCreditCardCode) {
        Objects.requireNonNull(customerId);
        Objects.requireNonNull(expMonth);
        Objects.requireNonNull(expYear);

        if (StringUtils.isAnyBlank(lastNumbers, brand, gatewayCreditCardCode)) {
            throw new IllegalArgumentException();
        }

        return new CreditCard(
                IdGenerator.generateTimeBasedUUID(),
                OffsetDateTime.now(),
                customerId,
                lastNumbers,
                brand,
                expMonth,
                expYear,
                gatewayCreditCardCode
        );
    }

    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    private void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    private void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getLastNumbers() {
        return lastNumbers;
    }

    private void setLastNumbers(String lastNumbers) {
        this.lastNumbers = lastNumbers;
    }

    public String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getExpMonth() {
        return expMonth;
    }

    private void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    public Integer getExpYear() {
        return expYear;
    }

    private void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }

    public String getGatewayCode() {
        return gatewayCode;
    }

    public void setGatewayCode(String gatewayCode) {
        if (StringUtils.isBlank(gatewayCode)) {
            throw new IllegalArgumentException();
        }
        this.gatewayCode = gatewayCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
package com.williamsilva.algashop.billing.domain.model.invoice;

import com.williamsilva.algashop.billing.domain.model.DomainException;
import com.williamsilva.algashop.billing.domain.model.IdGenerator;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.UUID;

public class PaymentSettings {

    private UUID id;
    private UUID creditCardId;
    private String gatewayCode;
    private PaymentMethod method;

    protected PaymentSettings() {
    }

    protected PaymentSettings(UUID id, UUID creditCardId, String gatewayCode, PaymentMethod method) {
        this.id = id;
        this.creditCardId = creditCardId;
        this.gatewayCode = gatewayCode;
        this.method = method;
    }

    static PaymentSettings brandNew(PaymentMethod method, UUID creditCardId) {
        Objects.requireNonNull(method);
        if (method.equals(PaymentMethod.CREDIT_CARD)) {
            Objects.requireNonNull(creditCardId);
        }
        return new PaymentSettings(
                IdGenerator.generateTimeBasedUUID(),
                creditCardId,
                null,
                method
        );
    }

    void assignGatewayCode(String gatewayCode) {
        if (StringUtils.isBlank(gatewayCode)) {
            throw new IllegalArgumentException();
        }
        if (this.getGatewayCode() != null) {
            throw new DomainException("Gateway code already assigned");
        }
        setGatewayCode(gatewayCode);
    }



    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    public UUID getCreditCardId() {
        return creditCardId;
    }

    private void setCreditCardId(UUID creditCardId) {
        this.creditCardId = creditCardId;
    }

    public String getGatewayCode() {
        return gatewayCode;
    }

    private void setGatewayCode(String gatewayCode) {
        this.gatewayCode = gatewayCode;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    private void setMethod(PaymentMethod method) {
        this.method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentSettings that = (PaymentSettings) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
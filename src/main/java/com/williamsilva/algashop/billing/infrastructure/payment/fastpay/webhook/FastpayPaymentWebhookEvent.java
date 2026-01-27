package com.williamsilva.algashop.billing.infrastructure.payment.fastpay.webhook;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public class FastpayPaymentWebhookEvent {

    @NotBlank
    private String paymentId;

    @NotBlank
    private String referenceCode;

    @NotBlank
    private String status;

    @NotBlank
    private String method;

    @NotNull
    private OffsetDateTime notifiedAt;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public OffsetDateTime getNotifiedAt() {
        return notifiedAt;
    }

    public void setNotifiedAt(OffsetDateTime notifiedAt) {
        this.notifiedAt = notifiedAt;
    }

    @Override
    public String toString() {
        return "FastpayPaymentWebhookEvent{" +
                "paymentId='" + paymentId + '\'' +
                ", referenceCode='" + referenceCode + '\'' +
                ", status='" + status + '\'' +
                ", method='" + method + '\'' +
                ", notifiedAt=" + notifiedAt +
                '}';
    }
}

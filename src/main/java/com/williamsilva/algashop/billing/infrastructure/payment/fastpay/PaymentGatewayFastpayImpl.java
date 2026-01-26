package com.williamsilva.algashop.billing.infrastructure.payment.fastpay;

import com.williamsilva.algashop.billing.domain.model.invoice.payment.Payment;
import com.williamsilva.algashop.billing.domain.model.invoice.payment.PaymentGatewayService;
import com.williamsilva.algashop.billing.domain.model.invoice.payment.PaymentRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "algashop.integrations.payment.provider", havingValue = "FASTPAY")
public class PaymentGatewayFastpayImpl implements PaymentGatewayService {

    @Override
    public Payment capture(PaymentRequest request) {
        return null;
    }

    @Override
    public Payment findByCode(String gatewayCode) {
        return null;
    }
}

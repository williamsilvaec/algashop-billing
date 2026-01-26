package com.williamsilva.algashop.billing.infrastructure.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "algashop.integrations.payment")
@Validated
public class AlgaShopPaymentPropreties {

    @NotNull
    private AlgaShopPaymentProvider provider;

    @NotNull
    private FastpayProperties fastpay;

    public AlgaShopPaymentProvider getProvider() {
        return provider;
    }

    public void setProvider(AlgaShopPaymentProvider provider) {
        this.provider = provider;
    }

    public FastpayProperties getFastpay() {
        return fastpay;
    }

    public void setFastpay(FastpayProperties fastpay) {
        this.fastpay = fastpay;
    }


    public enum AlgaShopPaymentProvider {
        FASTPAY, FAKE
    }

    @Validated
    public static class FastpayProperties {

        @NotBlank
        private String hostname;

        @NotBlank
        private String privateToken;

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        public String getPrivateToken() {
            return privateToken;
        }

        public void setPrivateToken(String privateToken) {
            this.privateToken = privateToken;
        }
    }
}

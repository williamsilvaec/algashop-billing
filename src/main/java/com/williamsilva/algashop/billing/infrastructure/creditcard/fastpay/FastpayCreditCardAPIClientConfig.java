package com.williamsilva.algashop.billing.infrastructure.creditcard.fastpay;

import com.williamsilva.algashop.billing.infrastructure.payment.AlgaShopPaymentPropreties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class FastpayCreditCardAPIClientConfig {

    @Bean
    public FastpayCreditCardAPIClient fastpayCreditCardAPIClient(
            RestClient.Builder builder,
            AlgaShopPaymentPropreties propreties
    ) {

        AlgaShopPaymentPropreties.FastpayProperties fastpayProperties = propreties.getFastpay();

        RestClient restClient = builder.baseUrl(fastpayProperties.getHostname())
                .requestInterceptor(((request, body, execution) -> {
                    request.getHeaders().add("Token", fastpayProperties.getPrivateToken());
                    return  execution.execute(request, body);
                })).build();

        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return proxyFactory.createClient(FastpayCreditCardAPIClient.class);
    }
}

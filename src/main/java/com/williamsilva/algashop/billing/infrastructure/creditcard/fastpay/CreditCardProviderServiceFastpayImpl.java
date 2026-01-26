package com.williamsilva.algashop.billing.infrastructure.creditcard.fastpay;

import com.williamsilva.algashop.billing.domain.model.creditcard.CreditCardProviderService;
import com.williamsilva.algashop.billing.domain.model.creditcard.LimitedCreditCard;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;
import java.util.UUID;

@Service
@ConditionalOnProperty(name = "algashop.integrations.payment.provider", havingValue = "FASTPAY")
public class CreditCardProviderServiceFastpayImpl implements CreditCardProviderService {

    private final FastpayCreditCardAPIClient fastpayCreditCardAPIClient;

    public CreditCardProviderServiceFastpayImpl(FastpayCreditCardAPIClient fastpayCreditCardAPIClient) {
        this.fastpayCreditCardAPIClient = fastpayCreditCardAPIClient;
    }

    @Override
    public LimitedCreditCard register(UUID customerId, String tokenizedCard) {
        FastpayCreditCardInput input = FastpayCreditCardInput.builder()
                .customerCode(customerId.toString())
                .tokenizedCard(tokenizedCard)
                .build();

        FastpayCreditCardResponse response = fastpayCreditCardAPIClient.create(input);

        return toLimitedCreditCard(response);
    }

    @Override
    public Optional<LimitedCreditCard> findById(String gatewayCode) {
        FastpayCreditCardResponse response;

        try {
            response = fastpayCreditCardAPIClient.findById(gatewayCode);
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }

        return Optional.of(toLimitedCreditCard(response));
    }

    @Override
    public void delete(String gatewayCode) {
        fastpayCreditCardAPIClient.delete(gatewayCode);
    }

    private LimitedCreditCard toLimitedCreditCard(FastpayCreditCardResponse response) {
        return LimitedCreditCard.builder()
                .gatewayCode(response.getId())
                .lastNumbers(response.getLastNumbers())
                .brand(response.getBrand())
                .expMonth(response.getExpMonth())
                .expYear(response.getExpYear())
                .build();
    }
}

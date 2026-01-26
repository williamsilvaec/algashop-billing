package com.williamsilva.algashop.billing.infrastructure.creditcard.fastpay;

import com.williamsilva.algashop.billing.domain.model.creditcard.LimitedCreditCard;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.Year;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Import(FastpayCreditCardTokenizationAPIClientConfig.class)
class CreditCardProviderServiceFastpayImplIT {

    @Autowired
    private CreditCardProviderServiceFastpayImpl creditCardProvider;

    @Autowired
    private FastpayCreditCardTokenizationAPIClient tokenizationAPIClient;

    private static final UUID validCustomerId = UUID.randomUUID();
    private static final String alwaysPaidCardNumber = "4622943127011022";

    @Test
    void shouldRegisterCreditCard() {
        LimitedCreditCard limitedCreditCard = registerCreditCard();
        Assertions.assertThat(limitedCreditCard.getGatewayCode()).isNotBlank();
    }

    @Test
    void shouldFindRegisteredCreditCard() {
        LimitedCreditCard limitedCreditCard = registerCreditCard();

        LimitedCreditCard limitedCreditCardFound = creditCardProvider.findById(limitedCreditCard.getGatewayCode())
                .orElseThrow();

        Assertions.assertThat(limitedCreditCardFound.getGatewayCode()).isEqualTo(limitedCreditCard.getGatewayCode());
    }

    @Test
    void shouldDeleteRegisteredCreditCard() {
        LimitedCreditCard limitedCreditCard = registerCreditCard();

        creditCardProvider.delete(limitedCreditCard.getGatewayCode());

        Optional<LimitedCreditCard> possibleCreditCard = creditCardProvider.findById(limitedCreditCard.getGatewayCode());

        Assertions.assertThat(possibleCreditCard).isEmpty();
    }

    private LimitedCreditCard registerCreditCard() {
        FastpayTokenizationInput input = new FastpayTokenizationInput(
                alwaysPaidCardNumber,
                "222",
                "John Doe",
                "12345",
                1,
                Year.now().getValue() + 5
        );

        FastpayTokenizedCreditCardModel response = tokenizationAPIClient.tokenize(input);
        return creditCardProvider.register(validCustomerId, response.tokenizedCard());
    }
}
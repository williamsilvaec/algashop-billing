package com.williamsilva.algashop.billing.application.invoice.management;

import com.williamsilva.algashop.billing.domain.model.creditcard.CreditCardNotFoundException;
import com.williamsilva.algashop.billing.domain.model.creditcard.CreditCardRepository;
import com.williamsilva.algashop.billing.domain.model.invoice.Address;
import com.williamsilva.algashop.billing.domain.model.invoice.Invoice;
import com.williamsilva.algashop.billing.domain.model.invoice.InvoiceRepository;
import com.williamsilva.algashop.billing.domain.model.invoice.InvoicingService;
import com.williamsilva.algashop.billing.domain.model.invoice.LineItem;
import com.williamsilva.algashop.billing.domain.model.invoice.Payer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class InvoiceManagementApplicationService {

    private final InvoicingService invoicingService;
    private final InvoiceRepository invoiceRepository;
    private final CreditCardRepository creditCardRepository;

    public InvoiceManagementApplicationService(InvoicingService invoicingService,
                                               InvoiceRepository invoiceRepository,
                                               CreditCardRepository creditCardRepository) {
        this.invoicingService = invoicingService;
        this.invoiceRepository = invoiceRepository;
        this.creditCardRepository = creditCardRepository;
    }

    @Transactional
    public UUID generate(GenerateInvoiceInput input) {
        PaymentSettingsInput paymentSettings = input.getPaymentSettings();
        verifyCreditCardId(paymentSettings.creditCardId());

        Payer payer = convertToPayer(input.getPayer());
        Set<LineItem> items = convertToLineItems(input.getItems());

        Invoice invoice = invoicingService.issue(input.getOrderId(), input.getCustomerId(), payer, items);
        invoice.changePaymentSettings(paymentSettings.method(), paymentSettings.creditCardId());

        invoiceRepository.saveAndFlush(invoice);

        return invoice.getId();
    }

    private Set<LineItem> convertToLineItems(Set<LineItemInput> itemsInput) {
        Set<LineItem> lineItems = new LinkedHashSet<>();
        int itemNumber = 1;
        for (LineItemInput itemInput : itemsInput) {
            lineItems.add(LineItem.builder()
                    .number(itemNumber)
                    .name(itemInput.name())
                    .amount(itemInput.amount())
                    .build());
            itemNumber++;
        }
        return lineItems;
    }

    private Payer convertToPayer(PayerData payerData) {
        AddressData addressData = payerData.address();

        return Payer.builder()
                .fullName(payerData.fullName())
                .email(payerData.email())
                .document(payerData.document())
                .phone(payerData.phone())
                .address(Address.builder()
                        .city(addressData.city())
                        .state(addressData.state())
                        .neighborhood(addressData.neighborhood())
                        .complement(addressData.complement())
                        .zipCode(addressData.zipCode())
                        .street(addressData.street())
                        .number(addressData.number())
                        .build())
                .build();
    }

    private void verifyCreditCardId(UUID creditCardId) {
        if (creditCardId != null && !creditCardRepository.existsById(creditCardId)) {
            throw new CreditCardNotFoundException();
        }
    }
}

package com.williamsilva.algashop.billing.application.invoice.management;

import com.williamsilva.algashop.billing.domain.model.creditcard.CreditCardNotFoundException;
import com.williamsilva.algashop.billing.domain.model.creditcard.CreditCardRepository;
import com.williamsilva.algashop.billing.domain.model.invoice.Address;
import com.williamsilva.algashop.billing.domain.model.invoice.Invoice;
import com.williamsilva.algashop.billing.domain.model.invoice.InvoiceNotFoundException;
import com.williamsilva.algashop.billing.domain.model.invoice.InvoiceRepository;
import com.williamsilva.algashop.billing.domain.model.invoice.InvoicingService;
import com.williamsilva.algashop.billing.domain.model.invoice.LineItem;
import com.williamsilva.algashop.billing.domain.model.invoice.Payer;
import com.williamsilva.algashop.billing.domain.model.invoice.payment.Payment;
import com.williamsilva.algashop.billing.domain.model.invoice.payment.PaymentGatewayService;
import com.williamsilva.algashop.billing.domain.model.invoice.payment.PaymentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class InvoiceManagementApplicationService {

    private static final Logger log = LoggerFactory.getLogger(InvoiceManagementApplicationService.class);

    private final PaymentGatewayService paymentGatewayService;
    private final InvoicingService invoicingService;
    private final InvoiceRepository invoiceRepository;
    private final CreditCardRepository creditCardRepository;

    public InvoiceManagementApplicationService(PaymentGatewayService paymentGatewayService,
                                               InvoicingService invoicingService,
                                               InvoiceRepository invoiceRepository,
                                               CreditCardRepository creditCardRepository) {
        this.paymentGatewayService = paymentGatewayService;
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

    @Transactional
    public void processPayment(UUID invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new InvoiceNotFoundException());
        PaymentRequest paymentRequest = toPaymentRequest(invoice);

        Payment payment;
        try {
            payment = paymentGatewayService.capture(paymentRequest);
        } catch (Exception e) {
            String errorMessage = "Payment capture failed";
            log.error(errorMessage, e);
            invoice.cancel(errorMessage);
            invoiceRepository.saveAndFlush(invoice);
            return;
        }

        invoicingService.assignPayment(invoice, payment);
        invoiceRepository.saveAndFlush(invoice);
    }

    private PaymentRequest toPaymentRequest(Invoice invoice) {
        return PaymentRequest.builder()
                .amount(invoice.getTotalAmount())
                .method(invoice.getPaymentSettings().getMethod())
                .creditCardId(invoice.getPaymentSettings().getCreditCardId())
                .payer(invoice.getPayer())
                .invoiceId(invoice.getId())
                .build();
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

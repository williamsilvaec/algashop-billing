package com.williamsilva.algashop.billing.domain.model.invoice;

import java.time.OffsetDateTime;
import java.util.UUID;

public record InvoicePaidEvent(
        UUID invoiceId,
        UUID customerId,
        String orderId,
        OffsetDateTime paidAt
) { }

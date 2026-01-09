package com.williamsilva.algashop.billing.application.invoice.query;

public interface InvoiceQueryService {

    InvoiceOutput findByOrderId(String orderId);
}

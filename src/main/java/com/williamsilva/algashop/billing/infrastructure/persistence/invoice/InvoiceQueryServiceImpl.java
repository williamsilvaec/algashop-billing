package com.williamsilva.algashop.billing.infrastructure.persistence.invoice;

import com.williamsilva.algashop.billing.application.invoice.query.InvoiceOutput;
import com.williamsilva.algashop.billing.application.invoice.query.InvoiceQueryService;
import com.williamsilva.algashop.billing.application.utility.Mapper;
import com.williamsilva.algashop.billing.domain.model.invoice.Invoice;
import com.williamsilva.algashop.billing.domain.model.invoice.InvoiceNotFoundException;
import com.williamsilva.algashop.billing.domain.model.invoice.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class InvoiceQueryServiceImpl implements InvoiceQueryService {

    private final InvoiceRepository repository;
    private final Mapper mapper;

    public InvoiceQueryServiceImpl(InvoiceRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public InvoiceOutput findByOrderId(String orderId) {
        Invoice invoice = repository.findByOrderId(orderId).orElseThrow(InvoiceNotFoundException::new);
        return mapper.convert(invoice, InvoiceOutput.class);
    }
}

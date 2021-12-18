package bs.com.invoice.registration.service;

import bs.com.invoice.registration.exception.InvoiceAlreadyPresentException;
import bs.com.invoice.registration.exception.NotFoundException;
import bs.com.invoice.registration.model.InvoiceClient;
import bs.com.invoice.registration.repository.InvoiceClientRepository;
import bs.com.invoice.registration.resorce.request.InvoiceClientDto;
import bs.com.invoice.registration.restmock.RestTemplateMock;
import org.springframework.stereotype.Service;

@Service
public class ClientInvoiceService {

    private final InvoiceClientRepository invoiceClientRepository;
    private final RestTemplateMock restTemplateMock;

    public ClientInvoiceService(final InvoiceClientRepository invoiceClientRepository, final RestTemplateMock restTemplateMock) {
        this.invoiceClientRepository = invoiceClientRepository;
        this.restTemplateMock = restTemplateMock;
    }


    public InvoiceClient createInvoiceClient(final InvoiceClientDto invoiceClientRequestDto) {
        if(invoiceClientRepository.findById(invoiceClientRequestDto.getInvoiceId()).isPresent()) {
            throw new InvoiceAlreadyPresentException();
        }
        callBahamaService(invoiceClientRequestDto);
        return save(invoiceClientRequestDto);

    }

    public InvoiceClient getInvoiceClient(final Long invoiceId) {
        return invoiceClientRepository.findById(invoiceId).orElseThrow(NotFoundException::new);
    }

    private void callBahamaService(final InvoiceClientDto invoiceClientRequestDto) {
        var url = "https://bahamas.gov/register?invoice=%s&fiscal_id=%s&name=%s&email=%s";
        var response = restTemplateMock.makeCall(String.format(url, invoiceClientRequestDto.getInvoiceId(),
                invoiceClientRequestDto.getFiscalId(),
                invoiceClientRequestDto.getName(),
                invoiceClientRequestDto.getEmail()));

        if(response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            throw new RuntimeException();
        }
    }

    private InvoiceClient save(final InvoiceClientDto invoiceClientRequestDto) {
        return invoiceClientRepository.save(InvoiceClient
                .builder()
                .invoiceId(invoiceClientRequestDto.getInvoiceId())
                .email(invoiceClientRequestDto.getEmail())
                .fiscalId(invoiceClientRequestDto.getFiscalId())
                .name(invoiceClientRequestDto.getName())
                .build());
    }

}

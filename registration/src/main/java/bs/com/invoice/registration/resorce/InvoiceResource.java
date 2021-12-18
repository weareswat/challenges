package bs.com.invoice.registration.resorce;

import bs.com.invoice.registration.model.InvoiceClient;
import bs.com.invoice.registration.resorce.request.InvoiceClientDto;
import bs.com.invoice.registration.service.ClientInvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/invoice-client")
public class InvoiceResource {

    private final ClientInvoiceService clientInvoiceService;

    public InvoiceResource(final ClientInvoiceService clientInvoiceService) {
        this.clientInvoiceService = clientInvoiceService;
    }

    @PostMapping
    public ResponseEntity<InvoiceClient> storeInvoiceClient(final InvoiceClientDto invoiceRequestDto) {
        return new ResponseEntity<>(clientInvoiceService.createInvoiceClient(invoiceRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("{invoiceId}")
    public InvoiceClient getInvoiceClient(@PathVariable final Long invoiceId) {
        return clientInvoiceService.getInvoiceClient(invoiceId);
    }
}
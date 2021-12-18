package bs.com.invoice.registration.exception;


import bs.com.invoice.registration.model.InvoiceClient;

public class InvoiceAlreadyPresentException extends RuntimeException{
    public final Long invoiceId;
    public InvoiceAlreadyPresentException(InvoiceClient invoiceClient) {
        this.invoiceId = invoiceClient.getInvoiceId();
    }
}

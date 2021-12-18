package bs.com.invoice.registration;

import bs.com.invoice.registration.exception.InvoiceAlreadyPresentException;
import bs.com.invoice.registration.model.InvoiceClient;
import bs.com.invoice.registration.repository.InvoiceClientRepository;
import bs.com.invoice.registration.resorce.request.InvoiceClientDto;
import bs.com.invoice.registration.restmock.RestTemplateMock;
import bs.com.invoice.registration.service.ClientInvoiceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientInvoiceServiceTest {
	@Mock
	private InvoiceClientRepository invoiceClientRepository;
	@Mock
	private RestTemplateMock restTemplateMock;

	private ClientInvoiceService clientInvoiceService;

	@BeforeEach
	public void setUp() {
		clientInvoiceService = new ClientInvoiceService(invoiceClientRepository, restTemplateMock);
	}


	@Test
	void giveInvoiceClientDtoWhenCreateInvoiceClientThenReturnInvoiceClient() {
		//give
		var invoiceClientDto = InvoiceClientDto
				.builder()
				.invoiceId(123L)
				.email("a@a.com")
				.fiscalId(123L)
				.name("Subject #234")
				.build();
		var invoiceClient = InvoiceClient.builder()
				.invoiceId(123L)
				.email("a@a.com")
				.fiscalId(123L)
				.name("Subject #234")
				.build();

		when(invoiceClientRepository.findById(anyLong())).thenReturn(Optional.empty());
		when(invoiceClientRepository.save(any())).thenReturn(invoiceClient);
		when(restTemplateMock.makeCall(anyString())).thenCallRealMethod();
		var result = clientInvoiceService.createInvoiceClient(invoiceClientDto);

		Assertions.assertEquals(result, invoiceClient);
	}

	@Test
	void giveInvoiceClientDtoWhenCreateInvoiceClientThenException() {
		//give
		var invoiceClientDto = InvoiceClientDto
				.builder()
				.invoiceId(123L)
				.email("a@a.com")
				.fiscalId(123L)
				.name("Subject #234")
				.build();
		var invoiceClient = InvoiceClient.builder()
				.invoiceId(123L)
				.email("a@a.com")
				.fiscalId(123L)
				.name("Subject #234")
				.build();

		when(invoiceClientRepository.findById(anyLong())).thenReturn(Optional.of(invoiceClient));
		Assertions.assertThrows(InvoiceAlreadyPresentException.class, () -> {
					clientInvoiceService.createInvoiceClient(invoiceClientDto);
		});
	}
}

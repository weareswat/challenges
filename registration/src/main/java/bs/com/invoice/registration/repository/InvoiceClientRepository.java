package bs.com.invoice.registration.repository;

import bs.com.invoice.registration.model.InvoiceClient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceClientRepository extends MongoRepository<InvoiceClient, Long> {
}

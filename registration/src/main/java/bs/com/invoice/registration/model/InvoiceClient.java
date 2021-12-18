package bs.com.invoice.registration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("InvoiceClient")
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class InvoiceClient {

    @Id
    private Long invoiceId;
    private Long fiscalId;
    private String name;
    private String email;




}

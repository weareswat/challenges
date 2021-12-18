package bs.com.invoice.registration.resorce.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class InvoiceClientDto {
    @NotNull
    @Min(1)
    private Long fiscalId;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotNull
    @Min(1)
    private Long invoiceId;

}

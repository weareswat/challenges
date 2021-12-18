package bs.com.invoice.registration.resorce.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
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

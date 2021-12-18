package bs.com.invoice.registration.exception.handler.details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
@Builder
public class ErrorDetails {

    private Date timestamp;
    private Integer status;
    private String error;
    private String message;

}

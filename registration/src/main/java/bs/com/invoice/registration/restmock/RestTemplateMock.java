package bs.com.invoice.registration.restmock;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RestTemplateMock  {

    public <T> ResponseEntity makeCall(String url) {
        return new ResponseEntity(HttpStatus.CREATED);
    }
}

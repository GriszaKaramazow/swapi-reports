package pl.softwareplant.swapireports.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class ExternalConnectionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExternalConnectionException(String message) {
        super(message);
    }

}
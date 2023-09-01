package pe.intercorp.retail.customers.config.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException{
    private final String code;
    private final String messageCause;
    private final HttpStatus httpStatus;

    public BusinessException(String code, String message, String messageCause, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.messageCause = messageCause;
        this.httpStatus = httpStatus;
    }
}
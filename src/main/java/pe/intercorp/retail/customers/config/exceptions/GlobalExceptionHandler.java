package pe.intercorp.retail.customers.config.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pe.intercorp.retail.customers.config.MessageConfig;
import pe.intercorp.retail.customers.utils.FechaUtil;

import javax.validation.ConstraintDefinitionException;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

@RequiredArgsConstructor
@ControllerAdvice()
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorResponse> handlerBussinessException(BusinessException ex) {
        var error = ErrorResponse.builder()
                .codigoEstado(String.valueOf(ex.getHttpStatus().value()))
                .estado(ex.getMessage())
                .fechaHora(FechaUtil.formatearFecha(LocalDateTime.now(), FechaUtil.FORMATO_FECHA_HORA))
                .errores(new String[] {ex.getMessageCause()})
                .build();
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        var errores = new ArrayList<String>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String message = error.getDefaultMessage();
            errores.add(message);
        });

        var error = ErrorResponse.builder()
                .codigoEstado(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .estado(HttpStatus.BAD_REQUEST.name())
                .fechaHora(FechaUtil.formatearFecha(LocalDateTime.now(), FechaUtil.FORMATO_FECHA_HORA))
                .errores(errores.stream().toArray(String[]::new))
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handlerConstraintViolationException(ConstraintViolationException ex) {
        var errores = new ArrayList<String>();

        ex.getConstraintViolations().forEach(error -> {
            String message = error.getMessage();
            errores.add(message);
        });

        var error = ErrorResponse.builder()
                .codigoEstado(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .estado(HttpStatus.BAD_REQUEST.name())
                .fechaHora(FechaUtil.formatearFecha(LocalDateTime.now(), FechaUtil.FORMATO_FECHA_HORA))
                .errores(errores.stream().toArray(String[]::new))
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
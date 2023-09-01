package pe.intercorp.retail.customers.config.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorResponse {
    private String codigoEstado;
    private String estado;
    private String fechaHora;
    private String[] errores;
}

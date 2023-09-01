package pe.intercorp.retail.customers.controller.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest implements Serializable {


    @NotBlank(message = "{customer_request.nombre.notblank}")
    private String nombres;

    @NotBlank(message = "{customer_request.apellido.notblank}")
    private String apellidos;

    @NotBlank(message = "{customer_request.email.notempty}")
    @Email(message = "{customer_request.email.valid}")
    private String email;

    @NotBlank(message = "{customer_request.dni.notblank}")
    @Size(min = 8, max = 8, message = "{customer_request.dni.size}")
    private String dni;

    @Past(message = "{customer_request.fecha_nacimiento.past}")
    private LocalDate fechaNacimiento;

}

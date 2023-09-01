package pe.intercorp.retail.customers.model.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements Serializable {

    private Long id;
    private String nombres;
    private String apellidos;
    private String email;
    private String dni;
    private LocalDate fechaNacimiento;
}

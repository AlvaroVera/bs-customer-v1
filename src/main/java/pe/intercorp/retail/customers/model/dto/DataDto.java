package pe.intercorp.retail.customers.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataDto implements Serializable {
    private Integer year;
    private String month;
    private Integer born;
}

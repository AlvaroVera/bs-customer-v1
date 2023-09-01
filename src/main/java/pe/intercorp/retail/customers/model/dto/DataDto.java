package pe.intercorp.retail.customers.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataDto implements Serializable {
    private Integer year;
    private String month;
    private Integer born;
    private Double birthRate;
}

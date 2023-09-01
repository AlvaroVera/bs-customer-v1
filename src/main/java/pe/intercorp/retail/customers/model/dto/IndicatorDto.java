package pe.intercorp.retail.customers.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndicatorDto implements Serializable {
    private String indicator;
    private List<DataDto> data;
}

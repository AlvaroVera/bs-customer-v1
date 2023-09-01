package pe.intercorp.retail.customers.model.entity;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndicatorEntity {
    private Integer year;
    private Integer month;
    private Integer born;
    private Double birthRate;
}

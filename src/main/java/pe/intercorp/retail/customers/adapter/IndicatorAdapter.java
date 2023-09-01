package pe.intercorp.retail.customers.adapter;

import pe.intercorp.retail.customers.model.dto.DataDto;
import pe.intercorp.retail.customers.model.entity.IndicatorEntity;

import java.util.Map;

public class IndicatorAdapter {



    public static DataDto toDataDto(IndicatorEntity indicatorEntity, Map<Integer, String> months){
        return DataDto.builder()
                .born(indicatorEntity.getBorn())
                .month(months.get(indicatorEntity.getMonth() - 1))
                .year(indicatorEntity.getYear())
                .build();
    }
}

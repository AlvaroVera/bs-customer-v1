package pe.intercorp.retail.customers.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pe.intercorp.retail.customers.adapter.CustomerAdapter;
import pe.intercorp.retail.customers.adapter.IndicatorAdapter;
import pe.intercorp.retail.customers.config.ApplicationProperties;
import pe.intercorp.retail.customers.config.exceptions.BusinessException;
import pe.intercorp.retail.customers.controller.dto.CustomerRequest;
import pe.intercorp.retail.customers.model.dto.CustomerDto;
import pe.intercorp.retail.customers.model.dto.IndicatorDto;
import pe.intercorp.retail.customers.model.entity.CustomerEntity;
import pe.intercorp.retail.customers.model.entity.IndicatorEntity;
import pe.intercorp.retail.customers.model.specification.CustomerSpecification;
import pe.intercorp.retail.customers.repository.ICustomerRepository;
import pe.intercorp.retail.customers.service.ICustomerService;
import pe.intercorp.retail.customers.utils.Constants;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final ApplicationProperties applicationProperties;
    @Override
    public List<CustomerDto> obtenerClientes(String dni, String email) {

        CustomerSpecification customerSpecification = new CustomerSpecification(email, dni);
        List<CustomerEntity> customerEntities = customerRepository.findAll(customerSpecification);
        if (customerEntities.isEmpty()){
            throw new BusinessException(
                    String.valueOf(HttpStatus.NOT_FOUND.value()),
                    HttpStatus.NOT_FOUND.name(),
                    applicationProperties.getConstants().getNotFoundCustomer(),
                    HttpStatus.NOT_FOUND
            );
        }

        return customerEntities.stream()
                .map(CustomerAdapter::toCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto crearCliente(CustomerRequest customerRequest) {

        try {
            CustomerEntity customerEntity = customerRepository.save(CustomerAdapter.toCustomerEntity(customerRequest));
            return CustomerAdapter.toCustomerDto(customerEntity);
        }catch (Exception ex){
            throw new BusinessException(
                    String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    applicationProperties.getConstants().getInternalServerErrorCustomer(),
                    ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public IndicatorDto obtenerIndicadores(String indicator) {

        String indicatorLocal = applicationProperties.getIndicators().get(indicator);
        IndicatorDto indicatorDto = new IndicatorDto();
        indicatorDto.setIndicator(indicatorLocal);

        if (indicatorLocal == null) {
            throw new BusinessException(
                    String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.BAD_REQUEST.name(),
                    applicationProperties.getConstants().getNotFoundIndicator(),
                    HttpStatus.BAD_REQUEST
            );
        }

        List<IndicatorEntity> indicatorEntity = customerRepository.getIndicators().stream()
                .map(obj -> {
                    Object[] row = (Object[]) obj;
                    return IndicatorEntity.builder()
                            .year(Integer.parseInt(String.valueOf(row[0])))
                            .month(Integer.parseInt(String.valueOf(row[1])))
                            .born(Integer.parseInt(String.valueOf(row[2])))
                            .build();
                }).collect(Collectors.toList());

        if (indicator.equals(Constants.INDICADOR_01)) {

            indicatorDto.setData(indicatorEntity.stream()
                    .map(indicatorEntity1 -> IndicatorAdapter.toDataDto(indicatorEntity1, applicationProperties.getConstants().getMonths()))
                    .collect(Collectors.toList())
                    );
        }

        if (indicator.equals(Constants.INDICADOR_02)){
            Optional<IndicatorEntity> maxBorn = indicatorEntity.stream().max(Comparator.comparing(IndicatorEntity::getBorn));
            indicatorDto.setData(indicatorEntity.stream()
                    .filter(indicatorEntity1 -> Objects.equals(indicatorEntity1.getBorn(), maxBorn.get().getBorn()))
                    .map(indicatorEntity1 -> IndicatorAdapter.toDataDto(indicatorEntity1, applicationProperties.getConstants().getMonths()))
                    .collect(Collectors.toList()));
        }

        if (indicator.equals(Constants.INDICADOR_03)){
            Optional<IndicatorEntity> minBorn = indicatorEntity.stream().min(Comparator.comparing(IndicatorEntity::getBorn));
            indicatorDto.setData(indicatorEntity.stream()
                    .filter(indicatorEntity1 -> Objects.equals(indicatorEntity1.getBorn(), minBorn.get().getBorn()))
                    .map(indicatorEntity1 -> IndicatorAdapter.toDataDto(indicatorEntity1, applicationProperties.getConstants().getMonths()))
                    .collect(Collectors.toList()));
        }

        if (indicator.equals(Constants.INDICADOR_04)) {
            List<IndicatorEntity> indicatorEntities = customerRepository.getTasaNatalidad(applicationProperties.getConstants().getTotalPopulation())
                    .stream()
                    .map(obj -> {
                        Object[] row = (Object[]) obj;
                        return IndicatorEntity.builder()
                                .month(Integer.parseInt(String.valueOf(row[0])))
                                .birthRate(Double.parseDouble(String.valueOf(row[1])))
                                .build();
                    }).collect(Collectors.toList());

            indicatorDto.setData(indicatorEntities
                    .stream()
                    .map(indicatorEntity1 -> IndicatorAdapter.toDataDto(indicatorEntity1, applicationProperties.getConstants().getMonths()))
                    .collect(Collectors.toList()));
        }



        return indicatorDto;
    }
}

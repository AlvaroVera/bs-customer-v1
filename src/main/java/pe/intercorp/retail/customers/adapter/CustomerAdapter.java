package pe.intercorp.retail.customers.adapter;

import pe.intercorp.retail.customers.controller.dto.CustomerRequest;
import pe.intercorp.retail.customers.model.dto.CustomerDto;
import pe.intercorp.retail.customers.model.entity.CustomerEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerAdapter {
    public static List<CustomerDto> toCustomerDtoList(List<CustomerEntity> list){
        return list.stream().map(customerEntity -> CustomerDto.builder()
                .id(customerEntity.getId())
                .dni(customerEntity.getDni())
                .email(customerEntity.getEmail())
                .apellidos(customerEntity.getApellidos())
                .nombres(customerEntity.getNombres())
                .fechaNacimiento(customerEntity.getFechaNacimiento())
                .build()).collect(Collectors.toList());
    }

    public static CustomerEntity toCustomerEntity(CustomerRequest customerRequest){
        return CustomerEntity.builder()
                .apellidos(customerRequest.getApellidos())
                .nombres(customerRequest.getNombres())
                .dni(customerRequest.getDni())
                .email(customerRequest.getEmail())
                .fechaCreacion(LocalDate.now())
                .fechaNacimiento(customerRequest.getFechaNacimiento())
                .build();
    }

    public static CustomerDto toCustomerDto(CustomerEntity customerEntity){
        return CustomerDto.builder()
                .id(customerEntity.getId())
                .apellidos(customerEntity.getApellidos())
                .nombres(customerEntity.getNombres())
                .dni(customerEntity.getDni())
                .email(customerEntity.getEmail())
                .fechaNacimiento(customerEntity.getFechaNacimiento())
                .build();
    }
}

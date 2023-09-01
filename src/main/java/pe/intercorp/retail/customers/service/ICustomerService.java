package pe.intercorp.retail.customers.service;

import pe.intercorp.retail.customers.controller.dto.CustomerRequest;
import pe.intercorp.retail.customers.model.dto.CustomerDto;
import pe.intercorp.retail.customers.model.dto.IndicatorDto;

import java.util.List;
import java.util.Map;

public interface ICustomerService {
    List<CustomerDto> obtenerClientes(String dni, String email);
    CustomerDto crearCliente(CustomerRequest customerRequest);

    IndicatorDto obtenerIndicadores(String indicator);
}

package pe.intercorp.retail.customers.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import pe.intercorp.retail.customers.controller.dto.CustomerRequest;
import pe.intercorp.retail.customers.model.dto.CustomerDto;
import pe.intercorp.retail.customers.model.dto.IndicatorDto;
import pe.intercorp.retail.customers.service.ICustomerService;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
public class CustomerApiController implements ICustomerAPI{

    private final ICustomerService customerService;



    @Override
    public ResponseEntity<List<CustomerDto>> obtenerClientes(String email, String dni) {
        return new ResponseEntity<>(customerService.obtenerClientes(dni, email), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerDto> crearCliente(CustomerRequest customerRequest) {
        return new ResponseEntity<>(customerService.crearCliente(customerRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<IndicatorDto> obtenerIndicador(String code) {
        return new ResponseEntity<>(customerService.obtenerIndicadores(code), HttpStatus.OK);
    }
}

package pe.intercorp.retail.customers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.intercorp.retail.customers.controller.dto.CustomerRequest;
import pe.intercorp.retail.customers.model.dto.CustomerDto;
import pe.intercorp.retail.customers.model.dto.IndicatorDto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

public interface ICustomerAPI {

    @GetMapping(value = "/customer", produces = {
            "application/json"})
    ResponseEntity<List<CustomerDto>> obtenerClientes(
            @RequestParam(name = "email", required = false)
            @Email(message = "{customer_request.email.valid}") String email,
            @RequestParam(name = "dni", required = false)
            @Size(min = 8, max = 8, message = "{customer_request.dni.size}")
            String dni
    );

    @PostMapping(value = "/customer", produces = {
            "application/json"}, consumes = {"application/json"})
    ResponseEntity<CustomerDto> crearCliente(@RequestBody @Valid CustomerRequest customerRequest);

    @GetMapping(value = "indicator/{code}")
    ResponseEntity<IndicatorDto> obtenerIndicador(@PathVariable String code);
}

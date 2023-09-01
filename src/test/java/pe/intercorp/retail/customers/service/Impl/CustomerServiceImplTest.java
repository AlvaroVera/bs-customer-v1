package pe.intercorp.retail.customers.service.Impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.ResourceUtils;
import pe.intercorp.retail.customers.config.ApplicationProperties;
import pe.intercorp.retail.customers.config.exceptions.BusinessException;
import pe.intercorp.retail.customers.model.dto.CustomerDto;
import pe.intercorp.retail.customers.model.entity.CustomerEntity;
import pe.intercorp.retail.customers.model.specification.CustomerSpecification;
import pe.intercorp.retail.customers.repository.ICustomerRepository;
import pe.intercorp.retail.customers.util.ReadJson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@EnableConfigurationProperties(value = ApplicationProperties.class)
@TestPropertySource("classpath:application.yaml")
class CustomerServiceImplTest {

    @Mock
    private ApplicationProperties applicationProperties;

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;


    CustomerEntity customerEntity;
    List<CustomerEntity> customerEntities;

    @BeforeEach
    void setUp() throws IOException {
        customerEntity = ReadJson.convertJsonFileToObject(
                ResourceUtils.getFile("classpath:json/CustomerEntity.json").getPath(),
                CustomerEntity.class);

        customerEntities = Collections.singletonList(customerEntity);
    }

    @DisplayName("Obtener cliente por dni")
    @Test
    void obtenerClientePorDni_ok(){
        String dni = "71268856";
        String email = null;

        when(customerRepository.findAll(any(CustomerSpecification.class))).thenReturn(customerEntities);

        List<CustomerDto> customerDtos = customerService.obtenerClientes(dni, email);

        assertEquals(dni, customerDtos.get(0).getDni());
    }

    @DisplayName("Obtener cliente por email")
    @Test
    void obtenerClientePorEmail_ok(){
        String dni = null;
        String email = "alvaro291095@gmail.com";

        when(customerRepository.findAll(any(CustomerSpecification.class))).thenReturn(customerEntities);

        List<CustomerDto> customerDtos = customerService.obtenerClientes(dni, email);

        assertEquals(email, customerDtos.get(0).getEmail());
    }

    @DisplayName("Obtener cliente por email - not found")
    @Test
    void obtenerClientePorEmail_notFound(){
        String dni = null;
        String email = "alvaro291095@gmail.com";
        ApplicationProperties applicationProperties1 = new ApplicationProperties();
        applicationProperties1.setConstants(new ApplicationProperties.Constants());
        applicationProperties1.getConstants().setNotFoundCustomer("Recursos no encontrados");


        when(customerRepository.findAll(any(CustomerSpecification.class))).thenReturn(Collections.emptyList());
        when(applicationProperties.getConstants()).thenReturn(applicationProperties1.getConstants());

        assertThrows(BusinessException.class, () -> customerService.obtenerClientes(dni, email));
    }


}
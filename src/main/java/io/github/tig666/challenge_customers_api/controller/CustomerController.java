package io.github.tig666.challenge_customers_api.controller;

import io.github.tig666.challenge_customers_api.dto.customer.CustomerDTO;
import io.github.tig666.challenge_customers_api.dto.generic.ResponseGenericDTO;
import io.github.tig666.challenge_customers_api.entity.Customer;
import io.github.tig666.challenge_customers_api.exception.DomainException;
import io.github.tig666.challenge_customers_api.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping()
    public ResponseEntity<ResponseGenericDTO> getAllCustomers() {
        ResponseGenericDTO responseGenericDTO = new ResponseGenericDTO(200, "Lista de clientes recuperada correctamente.");

        responseGenericDTO.setResponseData(this.customerService.getAllCustomers());

        return ResponseEntity.ok(responseGenericDTO);
    }

    @GetMapping("/find")
    public ResponseEntity<ResponseGenericDTO> getCustomer(@RequestParam("documentNumber") Long documentNumber) throws DomainException {
        ResponseGenericDTO responseGenericDTO = new ResponseGenericDTO(200, "Cliente encontrado.");

        responseGenericDTO.setResponseData(this.customerService.getCustomerById(documentNumber));

        return ResponseEntity.ok(responseGenericDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseGenericDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws DomainException {
        ResponseGenericDTO responseGenericDTO = new ResponseGenericDTO(200, "Cliente creado con exito.");

        Customer customer = new Customer();
        customer.setDocumentNumber(customerDTO.getDocumentNumber());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());

        responseGenericDTO.setResponseData(this.customerService.createCustomer(customer));

        return ResponseEntity.ok(responseGenericDTO);
    }

    @PutMapping
    public ResponseEntity<ResponseGenericDTO> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws DomainException {
        ResponseGenericDTO responseGenericDTO = new ResponseGenericDTO(200, "Cliente actualizado con exito.");

        Customer customer = new Customer();
        customer.setDocumentNumber(customerDTO.getDocumentNumber());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());

        responseGenericDTO.setResponseData(this.customerService.updateCustomer(customer));

        return ResponseEntity.ok(responseGenericDTO);
    }

    @PutMapping("/updateStatus/{documentNumber}")
    public ResponseEntity<ResponseGenericDTO> updateCustomerCustomer(@PathVariable Long documentNumber) throws DomainException {
        ResponseGenericDTO responseGenericDTO = new ResponseGenericDTO(200, "Se ha actualizado el esatado del cliente.");

        responseGenericDTO.setResponseData(this.customerService.updateCustomerStatus(documentNumber));

        return ResponseEntity.ok(responseGenericDTO);
    }

}

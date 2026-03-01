package io.github.tig666.challenge_customers_api.service.impl;

import io.github.tig666.challenge_customers_api.entity.Customer;
import io.github.tig666.challenge_customers_api.exception.DomainException;
import io.github.tig666.challenge_customers_api.repository.CustomerRepository;
import io.github.tig666.challenge_customers_api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long documentNumber) {
        Optional<Customer> customer = this.customerRepository.findById(documentNumber);

        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new DomainException(404, "No existe el cliente con número de identificación: " + documentNumber);
        }
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if (this.customerRepository.existsById(customer.getDocumentNumber())) {
            throw new DomainException(409, "Ya existe un cliente con número de identificación: " + customer.getDocumentNumber());
        } else {
            return this.customerRepository.save(customer);
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Optional<Customer> customerOptional = this.customerRepository.findById(customer.getDocumentNumber());
        if (customerOptional.isPresent()) {
            customer.setActive(customerOptional.get().isActive());
            return this.customerRepository.save(customer);
        } else {
            throw new DomainException(404, "No se encontró el cliente que se quiere modificar: " + customer.getDocumentNumber());
        }
    }

    @Override
    public Customer updateCustomerStatus(Long documentNumber) {
        Optional<Customer> customer = this.customerRepository.findById(documentNumber);

        if (customer.isEmpty()) {
            throw new DomainException(404, "No se encontró el cliente que se quiere modificar: " + documentNumber);
        } else {
            Customer customerEntity = customer.get();
            customer.get().setActive(!customerEntity.isActive());

            return this.customerRepository.save(customerEntity);
        }
    }
}

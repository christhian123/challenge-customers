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
    public Customer getCustomerById(Long documentNumber) throws DomainException {
        Optional<Customer> customer = this.customerRepository.findById(documentNumber);

        if (customer.isPresent()) {
            return customer.get();
        }

        throw new DomainException(404, "No existe el cliente con número de identificación: " + documentNumber);
    }

    @Override
    public Customer createCustomer(Customer customer) throws DomainException {
        if (this.customerRepository.existsById(customer.getDocumentNumber())) {
            throw new DomainException(409, "Ya existe un cliente con número de identificación: " + customer.getDocumentNumber());
        }

        return this.customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) throws DomainException {
        if (this.customerRepository.existsById(customer.getDocumentNumber())) {
            return this.customerRepository.save(customer);
        }

        throw new DomainException(404, "No se encontró el cliente que se quiere modificar: " + customer.getDocumentNumber());
    }

    @Override
    public Customer updateCustomerStatus(Long documentNumber) throws DomainException {
        Optional<Customer> customer = this.customerRepository.findById(documentNumber);

        if (customer.isEmpty()) {
            throw new DomainException(404, "No se encontró el cliente que se quiere modificar: " + documentNumber);
        }

        Customer customerEntity = customer.get();
        customer.get().setActive(!customerEntity.isActive());

        return this.customerRepository.save(customerEntity);
    }
}

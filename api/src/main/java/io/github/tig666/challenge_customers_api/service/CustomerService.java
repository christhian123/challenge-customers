package io.github.tig666.challenge_customers_api.service;

import io.github.tig666.challenge_customers_api.entity.Customer;
import io.github.tig666.challenge_customers_api.exception.DomainException;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long documentNumber);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Customer updateCustomerStatus(Long documentNumber);
}

package io.github.tig666.challenge_customers_api.service;

import io.github.tig666.challenge_customers_api.entity.Customer;
import io.github.tig666.challenge_customers_api.exception.DomainException;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long documentNumber) throws DomainException;
    Customer createCustomer(Customer customer) throws DomainException;
    Customer updateCustomer(Customer customer) throws DomainException;
    Customer updateCustomerStatus(Long documentNumber) throws DomainException;
}
